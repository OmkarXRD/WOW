package com.live.worldsocialintegrationapp.Fragments.Profile.RechargePackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.SilverCoinsTabRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GetCoin.GetSilverCoinRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetCoin.PurchaseSilverCoins;
import com.live.worldsocialintegrationapp.ModelClasses.GetTotalSilverCoinsRoot;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentSilverCoinsTabBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;


public class SilverCoinsTabFragment extends Fragment {

    //    FragmentCoinsTabBinding binding;
    FragmentSilverCoinsTabBinding binding;
    private List<GetSilverCoinRoot.Detail> list;
    private String coinsValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSilverCoinsTabBinding.inflate(inflater, container, false);
//        binding=  FragmentCoinsTabBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getSilverCoinsApi();
        getTotalSilverCoinsApi();


    }


    private void getSilverCoinsApi() {

        new Mvvm().getSilverCoins(requireActivity()).observe(requireActivity(), new Observer<GetSilverCoinRoot>() {
            @Override
            public void onChanged(GetSilverCoinRoot getSilverCoinRoot) {
                if (getSilverCoinRoot.getStatus().equalsIgnoreCase("1")) {

                    list = new ArrayList<>();
                    list = getSilverCoinRoot.getDetails();

                    SilverCoinsTabRVAdapter silverCoinsTabRVAdapter = new SilverCoinsTabRVAdapter(list, new SilverCoinsTabRVAdapter.Callback() {
                        @Override
                        public void callback(GetSilverCoinRoot.Detail value) {

                            purchaseSilverCoins(value);
                        }
                    });
                    binding.coinsRV.setAdapter(silverCoinsTabRVAdapter);

                } else {
//                   Toast.makeText(requireContext(), "0 "+getSilverCoinRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void purchaseSilverCoins(GetSilverCoinRoot.Detail value) {

        new Mvvm().purchaseSilverCoins(requireActivity(), AppConstants.USER_ID, value.getId()).observe(requireActivity(), new Observer<PurchaseSilverCoins>() {
            @Override
            public void onChanged(PurchaseSilverCoins purchaseSilverCoins) {

                if(purchaseSilverCoins != null){
                    if (purchaseSilverCoins.getSuccess().equalsIgnoreCase("1")) {

//                        binding.sivlerCoinsTV.setText(purchaseSilverCoins.getDetails().getCoinValue());
                        getTotalSilverCoinsApi();

                        App.getSharedpref().saveString("silverCoins", purchaseSilverCoins.getDetails().getCoinValue());
                    } else {
                        notEnoughCoins();
                    }
                }else{
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void notEnoughCoins() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Not enough coins, want to recharge?");
        builder.setTitle("Tips");
        builder.setCancelable(false);
        builder.setPositiveButton("Recharge", (DialogInterface.OnClickListener) (dialog, which) -> {
//            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.rechargeCointsFragment);

//            getActivity().findViewById(R.id.callAcitivtyMainRL).setVisibility(View.GONE);
//            getActivity().findViewById(R.id.callActivityFrameLayout).setVisibility(View.VISIBLE);
//            RechargeCointsFragment.CallActivityCheck=1;
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout, new RechargeCointsFragment()).addToBackStack(null).commit();
            dialog.dismiss();
        });

        builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getTotalSilverCoinsApi(){

//        new Mvvm().getTotalSilverCoins(requireActivity(),AppConstants.USER_ID).observe(requireActivity(), new Observer<GetTotalSilverCoinsRoot>() {
//            @Override
//            public void onChanged(GetTotalSilverCoinsRoot getTotalSilverCoinsRoot) {
//
//                if(getTotalSilverCoinsRoot !=  null){
//                    if(getTotalSilverCoinsRoot.getSuccess().equalsIgnoreCase("1")){
//                        binding.sivlerCoinsTV.setText(getTotalSilverCoinsRoot.getDetails().getCoinValue());
//                    }else{
//                        if (getContext() != null){
//                            Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }else{
//                    if (getContext() != null){
//                        Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
    }

}
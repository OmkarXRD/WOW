package com.live.worldsocialintegrationapp.Fragments.Profile.RechargePackage;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Activites.PhonePeActivity;
import com.live.worldsocialintegrationapp.Adapters.CoinsTabRVAdapter;
import com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection.PhoneCodeFragment;
import com.live.worldsocialintegrationapp.ModelClasses.GenerateOrderRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetCoin.Detail;
import com.live.worldsocialintegrationapp.ModelClasses.GetCoin.GetCoinRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Wallet.AddWalletMoneyRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Wallet.GetWalletRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.RazorPay.PaymentActivity;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentCoinsTabBinding;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class CoinsTabFragment extends Fragment {
    FragmentCoinsTabBinding binding;
    List<Detail> list;
    String walletCoins,phoneNumber;
    ImageView coinsTabImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCoinsTabBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clicks(view);
        getCoinsApi();
        getWalletApi();

        coinsTabImg= view.findViewById(R.id.coinsTabImg);
        coinsTabImg.setImageResource(R.drawable.coins_img);

        setStatusBarGradiant(requireActivity());

    }

    private void clicks(View view) {

        binding.CoinsTabonCallTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.helpCenterFragment);
            }
        });
    }

    private void getCoinsApi(){

       new Mvvm().getCoins(requireActivity(),AppConstants.USER_ID).observe(requireActivity(), new Observer<GetCoinRoot>() {
           @Override
           public void onChanged(GetCoinRoot getCoinRoot) {
               if(getCoinRoot.getStatus().equalsIgnoreCase("1")){
                   list = new ArrayList<>();
                   list = getCoinRoot.getDetails();
                   phoneNumber = getCoinRoot.getPhone();

                   CoinsTabRVAdapter coinsTabRVAdapter = new CoinsTabRVAdapter(list, requireContext(), new CoinsTabRVAdapter.Callback() {
                       @Override
                       public void callback(Detail value) {
                           openPhonePe(value);
                       }
                   });
                   binding.coinsRV.setAdapter(coinsTabRVAdapter);
               }else{
               }
           }
       });
    }

    private void openPhonePe(Detail value) {

        Intent intent = new Intent(requireContext(),PhonePeActivity.class);
        intent.putExtra("amountId", value.getId());
        intent.putExtra("amount", value.getMoneyValue());
        intent.putExtra("amountCoin", value.getCoinValue());
        intent.putExtra("phoneNumber", phoneNumber);
        startActivity(intent);

    }

    private void getWalletApi() {

        new Mvvm().getWallet(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetWalletRoot>() {
            @Override
            public void onChanged(GetWalletRoot getWalletRoot) {
                if(getWalletRoot.getSuccess().equalsIgnoreCase("1")){
                    binding.goldCoinTV.setText(getWalletRoot.getDetails().get(0).getWallet_amount());
                }else{
                }
            }
        });
    }

    private void genrateOrder(Detail detail){

        new Mvvm().generateOrder(requireActivity(), detail.getMoneyValue()).observe(requireActivity(), new Observer<GenerateOrderRoot>() {
            @Override
            public void onChanged(GenerateOrderRoot generateOrderRoot) {

                if(generateOrderRoot != null){
                    if(generateOrderRoot.getSuccess().equalsIgnoreCase("1")){

                        Intent intent = new Intent(requireActivity(), PaymentActivity.class);

                        intent.putExtra("orderId",generateOrderRoot.getOrderId());
                        intent.putExtra("key",generateOrderRoot.getKey());
                        intent.putExtra("price",generateOrderRoot.getAmount());
                        intent.putExtra("itemId",detail.getId());

                        startActivity(intent);

                    }else{
                        Toast.makeText(requireContext(), "shit men", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(requireContext(), "shit men", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.wallet_background);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.darker_gray));
            window.setBackgroundDrawable(background);
        }
    }

}
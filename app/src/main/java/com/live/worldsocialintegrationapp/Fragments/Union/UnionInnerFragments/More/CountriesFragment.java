package com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.More;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.CoutriesRVAdapter;

import com.live.worldsocialintegrationapp.ModelClasses.LiveUserByCountry.RootLiveUser;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.databinding.CountriesRvDesignBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import in.aabhasjindal.otptextview.OtpTextView;


public class CountriesFragment extends Fragment implements CoutriesRVAdapter.Callback {


    private RecyclerView countriesRV;
    private String countryName;
    private TextView usaName, noUSerFound;
    CoutriesRVAdapter coutriesRVAdapter ;
    List<RootLiveUser.Detail> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        findIds(view);
        clicks(view);

        if(getArguments() != null){
            countryName = getArguments().getString("CountryName");
        }else{
//            Toast.makeText(requireContext(), "Argmts is null", Toast.LENGTH_SHORT).show();
        }
        usaName.setText(countryName);
        apiGetLIveUser(view);
        coutriesRVAdapter = new CoutriesRVAdapter(new ArrayList<>(), requireContext(), CountriesFragment.this) ;

    }

    private void apiGetLIveUser(View view) {
        Toast.makeText(requireContext(), ""+countryName, Toast.LENGTH_SHORT).show();
        new Mvvm().getLiveUserByCountry(requireActivity(),countryName, AppConstants.USER_ID,AppConstants.USER_ID).observe(requireActivity(), new Observer<RootLiveUser>() {
            @Override
            public void onChanged(RootLiveUser rootLiveUser) {
                if (rootLiveUser != null) {

                    if (rootLiveUser.getSuccess().equalsIgnoreCase("1")) {
//                        Toast.makeText(requireActivity(), ""+rootLiveUser.getMessage(), Toast.LENGTH_SHORT).show();
                        list = rootLiveUser.getDetails();
                        noUSerFound.setVisibility(View.GONE);
                        coutriesRVAdapter.loadData(list);
                    }else {
//                        Toast.makeText(requireActivity(), ""+rootLiveUser.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    noUSerFound.setVisibility(View.VISIBLE);
//                    Toast.makeText(requireActivity(), "Root is null", Toast.LENGTH_SHORT).show();
                }
                countriesRV.setAdapter(coutriesRVAdapter);
            }
        });

    }

    private void clicks(View view) {
        view.findViewById(R.id.countriesBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void findIds(View view) {
        countriesRV=view.findViewById(R.id.countriesRV);
        usaName=view.findViewById(R.id.usaName);
        noUSerFound=view.findViewById(R.id.noUserFound);
    }

    private void startLive(RootLiveUser.Detail detail, int pos){

        App.getSingletone().setLiveType("");
        Intent intent = new Intent(requireActivity(), CallActivity.class);
        intent.putExtra("channelName", detail.getChannelName());
        intent.putExtra("userId", detail.getUserId());
        intent.putExtra("liveType", "multiLive");
        intent.putExtra("liveStatus", "host");
        intent.putExtra("token", detail.getToken());
        intent.putExtra("name", detail.getName());
        intent.putExtra("liveHostId", detail.getId());
        intent.putExtra("status", "1");
        intent.putExtra("liveHostIds", detail.getId());


        if(detail.getImageTitle()!= null && !detail.getImageTitle().isEmpty()){
            intent.putExtra("broadTitle",detail.getImageTitle());
        }else{
            intent.putExtra("broadTitle",detail.getName());
        }

        intent.putExtra("liveImage", detail.getLiveimage());
        intent.putExtra("image", detail.getImageDp());

        startActivity(intent);

    }

    @Override
    public void callback(RootLiveUser.Detail detail, int pos) {
        startLive(detail,pos);
    }

    @Override
    public void enterPassword(RootLiveUser.Detail detail, int pos) {
        Dialog dialog_share = new Dialog(requireContext());
        dialog_share.setContentView(R.layout.set_room_password_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);

        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        OtpTextView liveLock = dialog_share.findViewById(R.id.liveLock_view);
        AppCompatButton confirm_pin = dialog_share.findViewById(R.id.confirm_pin);
        AppCompatButton cancel = dialog_share.findViewById(R.id.cancle_pin);
        confirm_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (liveLock.getOTP().equalsIgnoreCase(detail.getPassword())) {
                    callback(detail,pos);
                    dialog_share.dismiss();
                } else {
                    Toast.makeText(requireContext(), "Wrong Pin Entered", Toast.LENGTH_SHORT).show();
                    dialog_share.dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_share.dismiss();
            }
        });
        dialog_share.show();


    }
}
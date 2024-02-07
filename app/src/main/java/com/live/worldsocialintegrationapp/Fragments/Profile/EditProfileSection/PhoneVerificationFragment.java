package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentPhoneVerificationBinding;
import com.live.worldsocialintegrationapp.utils.App;


public class PhoneVerificationFragment extends Fragment {

    FragmentPhoneVerificationBinding binding;
    String phoneNumber,countryCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentPhoneVerificationBinding.inflate( inflater, container, false );

        onClicks();
        onCreate();
       return  binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    private void onCreate(){
        phoneNumber =  App.getSharedpref().getString("phone");
        countryCode =  App.getSharedpref().getString("countryCode");
        binding.changePhoneNumber.setText("+"+phoneNumber);
    }

    private void onClicks() {

        binding.updateNumber.setOnClickListener( view -> {
                Bundle bundle=new Bundle();
                bundle.putString("phone",phoneNumber);
                bundle.putString("countryCode",countryCode);
                bundle.putBoolean("changePhoneNumber",true);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_phoneVerificationFragment_to_phoneCodeFragment,bundle);
        } );

        binding.backEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        binding.changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("phone",phoneNumber);
                bundle.putString("countryCode",countryCode);
                bundle.putBoolean("resetPassword",true);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_phoneVerificationFragment_to_phoneCodeFragment,bundle);
            }
        });
    }
}
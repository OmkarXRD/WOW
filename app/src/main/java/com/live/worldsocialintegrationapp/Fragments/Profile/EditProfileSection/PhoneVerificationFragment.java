package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentPhoneVerificationBinding;
import com.live.worldsocialintegrationapp.utils.App;


public class PhoneVerificationFragment extends Fragment {

    FragmentPhoneVerificationBinding binding;
    String phoneNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentPhoneVerificationBinding.inflate( inflater, container, false );

        onClicks();
        onCreate();
       return  binding.getRoot();
    }

    private void onCreate(){
        phoneNumber =  App.getSharedpref().getString("phone");
        binding.changePhoneNumber.setText(phoneNumber);
    }

    private void onClicks() {

        binding.updateNumber.setOnClickListener( view -> {
            Navigation.findNavController( binding.getRoot() ).navigate( R.id.action_phoneVerificationFragment_to_phoneCodeFragment );
        } );

        binding.backEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }
}
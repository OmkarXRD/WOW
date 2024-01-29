package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentPhoneVerificationBinding;


public class PhoneVerificationFragment extends Fragment {

    FragmentPhoneVerificationBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentPhoneVerificationBinding.inflate( inflater, container, false );

        onClicks();

       return  binding.getRoot();
    }

    private void onClicks() {

        binding.phoneVerification.setOnClickListener( view -> {
            Navigation.findNavController( binding.getRoot() ).navigate( R.id.action_phoneVerificationFragment_to_phoneCodeFragment );
        } );
    }
}
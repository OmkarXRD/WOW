package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentConnectedAccountsBinding;
import com.live.worldsocialintegrationapp.utils.App;


public class ConnectedAccountsFragment extends Fragment {

    FragmentConnectedAccountsBinding binding;
    private String phone, gamil, facebook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConnectedAccountsBinding.inflate(inflater, container, false);

        onClick();
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phone = App.getSharedpref().getString("phone");
        gamil = App.getSharedpref().getString("gamil");
        facebook = App.getSharedpref().getString("facebook");
        if (phone.isEmpty()) {
            binding.connectedPhoneTv.setText("+91 000000000");
        }else{
            binding.connectedPhoneTv.setText(phone);
        }
        if (facebook.isEmpty()) {
            binding.connectFacebookTv.setText(" ");
        }else{
            binding.connectFacebookTv.setText(facebook);
        }
        if (gamil.isEmpty()) {
            binding.connectGoogle.setText(" ");
        }else{
            binding.connectGoogle.setText(gamil);
        }




    }

    private void onClick() {

        binding.connectAccounts.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_connectedAccountsFragment_to_phoneVerificationFragment);
        });

    }
}
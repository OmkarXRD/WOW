package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentConnectedAccountsBinding;
import com.live.worldsocialintegrationapp.utils.App;


public class ConnectedAccountsFragment extends Fragment {

    FragmentConnectedAccountsBinding binding;
    private String phone, email, facebook,name;

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
        email = App.getSharedpref().getString("email");
        name = App.getSharedpref().getString("name");

        facebook = App.getSharedpref().getString("facebook");
        if (phone.isEmpty()) {
            binding.connectedPhoneTv.setText(" ");
            binding.phoneArrow.setVisibility(View.GONE);
        }else{
            binding.connectedPhoneTv.setText(phone);
            binding.phoneArrow.setVisibility(View.VISIBLE);
        }
        if (facebook.isEmpty()) {
            binding.connectFacebookTv.setText(" ");
            binding.facebookArrow.setVisibility(View.GONE);
        }else{
            binding.connectFacebookTv.setText(facebook);
            binding.facebookArrow.setVisibility(View.VISIBLE);
        }
        if (email.isEmpty()) {
            binding.connectGoogle.setText(" ");
            binding.googleArrow.setVisibility(View.GONE);
        }else{
            binding.connectGoogle.setText(email);
            binding.googleArrow.setVisibility(View.VISIBLE);
        }
    }

    private void onClick() {

        binding.phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone.isEmpty()){
                    Toast.makeText(requireContext(), "You don't have any number linked", Toast.LENGTH_SHORT).show();
                }else{
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_connectedAccountsFragment_to_phoneVerificationFragment);
                }
            }
        });

        binding.backEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }
}
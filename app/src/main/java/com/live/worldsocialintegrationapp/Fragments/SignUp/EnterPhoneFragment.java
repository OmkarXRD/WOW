package com.live.worldsocialintegrationapp.Fragments.SignUp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentEnterPhoneBinding;
import com.live.worldsocialintegrationapp.utils.App;


public class EnterPhoneFragment extends Fragment {

    FragmentEnterPhoneBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEnterPhoneBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClicks();

    }


    private void onClicks() {

        binding.enterPhoneEdtx.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    binding.nextBtn.setBackgroundColor(getResources().getColor(R.color.greeni));
                }else{

                }
            }
        });

        binding.nextBtn.setOnClickListener(view -> {

            String countryCode = binding.ccp.getSelectedCountryCode();
            String country = binding.ccp.getSelectedCountryName().toString();
            App.getSharedpref().saveString("countryCode",countryCode);
            //Toast.makeText(requireContext(), ""+country, Toast.LENGTH_SHORT).show();

            if(binding.enterPhoneEdtx.getText().toString().trim().length() == 0){
                Toast.makeText(requireContext(), "Please enter the Phone no. ", Toast.LENGTH_SHORT).show();
            }
            else{
                Bundle bundle=new Bundle();
                bundle.putString("phone",binding.enterPhoneEdtx.getText().toString());
                bundle.putString("countryCode",countryCode);
                bundle.putString("country",country);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_enterPhoneFragment_to_phoneNumberLoginFragment,bundle);
            }
        });

        binding.backToLoginImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_enterPhoneFragment_to_loginFragment);
            }
        });
    }



}
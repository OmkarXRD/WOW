package com.live.worldsocialintegrationapp.Fragments.SignUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.CountrySelectAdapter;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.Root.CountryRoot;
import com.live.worldsocialintegrationapp.databinding.FragmentCountrySelectBinding;


public class CountrySelectFragment extends Fragment {

    FragmentCountrySelectBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCountrySelectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hitApi();
    }

    private void hitApi() {

        new Mvvm().getLiveDataCountryDetails(requireActivity()).observe(requireActivity(), new Observer<CountryRoot>() {
            @Override
            public void onChanged(CountryRoot response) {
                if (response != null) {

                    if (response.getSuccess().equals("1")) {
                        binding.rvCountryList.setAdapter(new CountrySelectAdapter(response.getDetails()));
                    } else {
                    }
                } else {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
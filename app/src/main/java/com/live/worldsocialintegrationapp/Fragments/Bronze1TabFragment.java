package com.live.worldsocialintegrationapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetFamilyDetails;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentBronzeTabBinding;

public class Bronze1TabFragment extends Fragment {

   FragmentBronzeTabBinding binding ;

    public Bronze1TabFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBronzeTabBinding.inflate(inflater, container, false);

       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        apiGetData();
    }

    private void apiGetData() {


        new Mvvm().getFamilyDetailsData(requireActivity(), 2).observe(requireActivity(), new Observer<GetFamilyDetails>() {
            @Override
            public void onChanged(GetFamilyDetails getFamilyDetails) {
                if (getFamilyDetails != null) {
                    if (getFamilyDetails.getStatus() == 1) {


                        Glide.with(binding.pic1.getContext()).load(getFamilyDetails.getDetails().get(0).getMainImage()).error(R.drawable.demo_user_profile_img).into(binding.pic1);

                        binding.namebadge.setText(getFamilyDetails.getDetails().get(0).getLevelName());


                    } else {

                    }
                } else {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }

            }
        });

        new Mvvm().getFamilyDetailsData(requireActivity(), 2).observe(requireActivity(), new Observer<GetFamilyDetails>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(GetFamilyDetails getFamilyDetails) {
                try {
                    if (getFamilyDetails != null) {
                        if (getFamilyDetails.getStatus() == 1) {
                              Glide.with(binding.pic2.getContext()).load(getFamilyDetails.getDetails().get(1).getMainImage()).error(R.drawable.demo_user_profile_img).into(binding.pic2);
                        } else {
                            Toast.makeText(requireActivity(), "" + getFamilyDetails.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                    }
                } catch (Exception e) {

                }

            }
        });
    }
}
package com.live.worldsocialintegrationapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Adapters.BronzeAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GetFamilyDetails;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentBronzeBinding;
import com.live.worldsocialintegrationapp.databinding.FragmentSilverBinding;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;


public class SilverFragment extends Fragment {
   FragmentSilverBinding binding ;
    RecyclerView recyclerView;
    BronzeAdapter bronzeAdapter;
    public java.util.List<GetFamilyDetails.Detail> detailsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSilverBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  recyclerView = view.findViewById(R.id.recyclerView);
        apiHit();

    }

    private void apiHit() {

        new Mvvm().getFamilyDetailsData(requireActivity(),3).observe(requireActivity(), new Observer<GetFamilyDetails>() {
            @Override
            public void onChanged(GetFamilyDetails getFamilyDetails) {
                if (getFamilyDetails != null) {
                    if (getFamilyDetails.getStatus() == 1) {
                        Glide.with(binding.pic.getContext()).load(getFamilyDetails.getDetails().get(0).getMainImage()).error(R.drawable.demo_user_profile_img).into(binding.pic);
                        Glide.with(binding.pic1.getContext()).load(getFamilyDetails.getDetails().get(0).getMainImage()).error(R.drawable.demo_user_profile_img).into(binding.pic1);
                        Glide.with(binding.monsterss1.getContext()).load(getFamilyDetails.getDetails().get(0).getRankMedal()).error(R.drawable.demo_user_profile_img).into(binding.monsterss1);
                        Glide.with(binding.exBackground.getContext()).load(getFamilyDetails.getDetails().get(0).getExclusiveBackground()).error(R.drawable.demo_user_profile_img).into(binding.exBackground);
                        Glide.with(binding.exFrame.getContext()).load(getFamilyDetails.getDetails().get(0).getExclusiveFrames()).error(R.drawable.exclusive).into(binding.exFrame);
                        binding.members.setText(getFamilyDetails.getDetails().get(0).getMembers()+" "+"members");
                        binding.admins.setText(getFamilyDetails.getDetails().get(0).getAdmin()+" "+"Admins");
                        binding.namebadge.setText(getFamilyDetails.getDetails().get(0).getLevelName());



                    } else {
//                        Toast.makeText(requireActivity(), "" + getAppliedFrameRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }

            }
        });
        new Mvvm().getFamilyDetailsData(requireActivity(),4).observe(requireActivity(), new Observer<GetFamilyDetails>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(GetFamilyDetails getFamilyDetails) {
                try {
                    if (getFamilyDetails != null) {
                        if (getFamilyDetails.getStatus() == 1) {
                            Glide.with(binding.pic2.getContext()).load(getFamilyDetails.getDetails().get(0).getMainImage()).error(R.drawable.demo_user_profile_img).into(binding.pic2);
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
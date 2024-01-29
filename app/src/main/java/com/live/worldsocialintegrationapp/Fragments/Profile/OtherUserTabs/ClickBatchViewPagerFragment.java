package com.live.worldsocialintegrationapp.Fragments.Profile.OtherUserTabs;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.caverock.androidsvg.SVGParser;
import com.live.worldsocialintegrationapp.ModelClasses.GetFamilyDetails;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentClickBatchViewPagerBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class ClickBatchViewPagerFragment extends Fragment {
    public List<GetFamilyDetails.Detail> List = new ArrayList<>();

    FragmentClickBatchViewPagerBinding binding;
    int pos;

    public ClickBatchViewPagerFragment(List<GetFamilyDetails.Detail> detailsList, int position) {
        // Toast.makeText(requireActivity(), "ok"+detailsList.get(position).getLevelName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentClickBatchViewPagerBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiHit();


    }

    private void setData(java.util.List<GetFamilyDetails.Detail> list) {

    }

    private void apiHit() {
        Toast.makeText(getContext(), "iron" , Toast.LENGTH_SHORT).show();

        new Mvvm().getFamilyDetailsData(requireActivity(),1).observe(requireActivity(), new Observer<GetFamilyDetails>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(GetFamilyDetails getFamilyDetails) {
                try {
                    if (getFamilyDetails != null) {
                        if (getFamilyDetails.getStatus() == 1) {
                            Glide.with(binding.pic1.getContext()).load(getFamilyDetails.getDetails().get(0).getMainImage()).error(R.drawable.demo_user_profile_img).into(binding.pic1);
                            Glide.with(binding.renkMedal.getContext()).load(getFamilyDetails.getDetails().get(0).getRankMedal()).error(R.drawable.demo_user_profile_img).into(binding.renkMedal);
                            Glide.with(binding.exBackground.getContext()).load(getFamilyDetails.getDetails().get(0).getExclusiveBackground()).error(R.drawable.demo_user_profile_img).into(binding.exBackground);
                            binding.members.setText(getFamilyDetails.getDetails().get(0).getMembers()+" "+"members");
                            binding.admins.setText(getFamilyDetails.getDetails().get(0).getAdmin()+" "+"Admins");
                            binding.namebadge.setText(getFamilyDetails.getDetails().get(0).getLevelName());



                        } else {
                            Toast.makeText(requireActivity(), "" + getFamilyDetails.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                    }
                } catch (Exception e) {

                }

            }
        });

        new Mvvm().getFamilyDetailsData(requireActivity(),2).observe(requireActivity(), new Observer<GetFamilyDetails>() {
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
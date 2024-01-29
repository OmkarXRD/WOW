package com.live.worldsocialintegrationapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.ModelClasses.GetFamilyDetails;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentBronzeBinding;

import java.util.ArrayList;
import java.util.List;

public class BronzeFragment extends Fragment {
    FragmentBronzeBinding binding;
    private TabLayout tablayout_mall;
    private ViewPager viewPager_ml;
    public java.util.List<GetFamilyDetails.Detail> detailsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBronzeBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // recyclerView = view.findViewById(R.id.recyclerView);
        apiHit();
        setTab();

    }

    private void setTab() {
        binding.bronzeTablyout.addTab(      binding.bronzeTablyout.newTab());
        binding.bronzeTablyout.addTab(      binding.bronzeTablyout.newTab());
        binding.bronzeTablyout.addTab(      binding.bronzeTablyout.newTab());
        binding.bronzeTablyout.addTab(      binding.bronzeTablyout.newTab());
        binding.bronzeTablyout.addTab(      binding.bronzeTablyout.newTab());

        final PagerAdapter pagerAdapter = new Adaptornew(getChildFragmentManager(), binding.bronzeTablyout.getTabCount(),new ArrayList<>());
        binding.bronzeViewPager.setAdapter(pagerAdapter);
        binding.bronzeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.bronzeTablyout));
        binding.bronzeTablyout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.bronzeViewPager));


    }

    public static class Adaptornew extends FragmentStatePagerAdapter {
        private final int totalCount;
        public List<GetFamilyDetails.Detail> detailsList = new ArrayList<>();

        public Adaptornew(@NonNull FragmentManager fm, int behavior, List<GetFamilyDetails.Detail> detailsList ) {
            super(fm, behavior);
            this.totalCount = behavior;
            this.detailsList = detailsList;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new Bronze1TabFragment();
                case 1:
                    return new Bronze2TabFragment();
                case 2:
                    return new Bronze3TabFragment();
                case 3:
                    return new Bronze4TabFragment();
                case 4:
                    return new Bronze5TabFragment();
                default:
                    return null;
            }

        }
        @Override
        public int getCount() {
            return totalCount;
        }
    }


    private void apiHit() {

        new Mvvm().getFamilyDetailsData(requireActivity(), 2).observe(requireActivity(), new Observer<GetFamilyDetails>() {
            @Override
            public void onChanged(GetFamilyDetails getFamilyDetails) {
                if (getFamilyDetails != null) {
                    if (getFamilyDetails.getStatus() == 1) {


                        Glide.with(binding.monsterss1.getContext()).load(getFamilyDetails.getDetails().get(0).getRankMedal()).error(R.drawable.demo_user_profile_img).into(binding.monsterss1);
                        Glide.with(binding.exBackground.getContext()).load(getFamilyDetails.getDetails().get(0).getExclusiveBackground()).error(R.drawable.demo_user_profile_img).into(binding.exBackground);
                        Glide.with(binding.exFrame.getContext()).load(getFamilyDetails.getDetails().get(0).getExclusiveFrames()).error(R.drawable.exclusive).into(binding.exFrame);
                        binding.members.setText(getFamilyDetails.getDetails().get(0).getMembers() + " " + "members");
                        binding.admins.setText(getFamilyDetails.getDetails().get(0).getAdmin() + " " + "Admins");

                        detailsList = getFamilyDetails.getDetails();

                    } else {
//                        Toast.makeText(requireActivity(), "" + getAppliedFrameRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
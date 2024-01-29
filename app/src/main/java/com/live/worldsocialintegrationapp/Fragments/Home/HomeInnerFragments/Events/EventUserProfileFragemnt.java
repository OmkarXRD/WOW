package com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.Events;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.R;


public class EventUserProfileFragemnt extends Fragment {


    private TabLayout tablayout;
    private ViewPager viewPager;
    View view ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_user_profile_fragemnt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findids(view);
        setTabPro(view);
        setonClicks(view);
    }

    private void setonClicks(View view) {

        view.findViewById(R.id.backButton_).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requireActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.frame_holder, new Fragment_Details()).addToBackStack(null).commit();
            }
        });
    }

    private void findids(View view) {
        tablayout = view.findViewById(R.id.tab_pro);
        viewPager = view.findViewById(R.id.viewpager_Pro);

    }

    private void setTabPro(View view) {
        tablayout.addTab(tablayout.newTab().setText("Profile"));
        tablayout.addTab(tablayout.newTab().setText("Glory"));

        final PagerAdapter pagerAdapter = new Adapter_Pro(getChildFragmentManager(), tablayout.getTabCount());
        viewPager .setAdapter(pagerAdapter);
        viewPager .addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }

    public static class Adapter_Pro extends FragmentStatePagerAdapter
    {
        private final  int   totalTabs ;
        public Adapter_Pro(@NonNull FragmentManager fm, int behavior)  {
            super(fm);
            totalTabs = behavior;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:

                    return new EventFamilyFragment();
                case 1:

                    return new EventFamilyFragment();

                default:
                    return null ;
            }
        }

        @Override
        public int getCount() {
            return totalTabs;
        }
    }

}
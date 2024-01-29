package com.live.worldsocialintegrationapp.Fragments.Profile.UserLevel;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.ModelClasses.SendingLevel;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


public class UserLevelFragment extends Fragment {

    private TabLayout tablayout_ExploreScreen;
    private ViewPager viewPager_ES;
    private ImageView userLevelBackImg;

    boolean shouldChangeStatusBarTintToDark;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_level, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        findIds(view);
        clickListeners(view);
        tabLayoutMethod();
    }

    private void clickListeners(View view) {

        userLevelBackImg.setOnClickListener(view1 -> {

            getActivity().onBackPressed();

        });
    }

    private void findIds(View view) {

        userLevelBackImg=view.findViewById(R.id.userLevelBackImg);

        tablayout_ExploreScreen = view.findViewById(R.id.userLvlTablayout);
        viewPager_ES = view.findViewById(R.id.userLvlViewPager);


    }

    public void tabLayoutMethod(){

        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Sending"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Receiving"));

        tablayout_ExploreScreen.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter pagerAdapter= new UserLevelFragment.Adaptor(getChildFragmentManager(),tablayout_ExploreScreen.getTabCount());
        viewPager_ES.setAdapter(pagerAdapter);
        viewPager_ES.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_ExploreScreen));
        tablayout_ExploreScreen.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_ES));
    }


    public static class Adaptor extends FragmentStatePagerAdapter
    {

        private final int totalCount;

        public Adaptor(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);

            this.totalCount=behavior;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position)
            {
                case 0:
                    return new WealthFragment();
                case 1:
                    return new CharmingFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return totalCount;
        }
    }

}
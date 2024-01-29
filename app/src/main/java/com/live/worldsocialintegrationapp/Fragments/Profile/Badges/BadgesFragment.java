package com.live.worldsocialintegrationapp.Fragments.Profile.Badges;

import android.os.Build;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


public class BadgesFragment extends Fragment {

    private TabLayout tablayout_ExploreScreen;
    private ViewPager viewPager_ES;
    private ImageView badgesBackImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_badges, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.badges));
        }
        findIds(view);
        clickListeners(view);
        tabLayoutMethod();
    }

    private void clickListeners(View view) {

        badgesBackImg.setOnClickListener(view1 -> {
            getActivity().onBackPressed();

        });
    }

    private void findIds(View view) {

        badgesBackImg=view.findViewById(R.id.badgesBackImg);

        tablayout_ExploreScreen = view.findViewById(R.id.badgesTablayout);
        viewPager_ES = view.findViewById(R.id.badgesViewPager);
    }

    public void tabLayoutMethod(){

        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Achievement"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Activity"));

        tablayout_ExploreScreen.setTabGravity(TabLayout.GRAVITY_FILL);


        final PagerAdapter pagerAdapter= new Adaptor(getChildFragmentManager(),tablayout_ExploreScreen.getTabCount());
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
                    return new AchievementFragment();
                case 1:
                    return new ActivityFragment();
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
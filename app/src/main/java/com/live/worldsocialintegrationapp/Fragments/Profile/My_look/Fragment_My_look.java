package com.live.worldsocialintegrationapp.Fragments.Profile.My_look;

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

public class Fragment_My_look extends Fragment {


    private TabLayout tablayout_My_look;
    private ViewPager viewPager_look;
    private ImageView myLookBackImg;


    public Fragment_My_look() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__my_look, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        CommonUtils.disableBottomNavigation(requireActivity());
        findIds(view);
        clicks(view);
        tabLayoutMethod(view);

//        if (Build.VERSION.SDK_INT >= 21) {
//            Window window = requireActivity().getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.));
//        }

    }

    private void clicks(View view) {

        myLookBackImg.setOnClickListener(view1 -> getActivity().onBackPressed());
    }

    private void findIds(View view) {

        tablayout_My_look = view.findViewById(R.id.lookTablayout);
        viewPager_look = view.findViewById(R.id.lookViewPager);
        myLookBackImg = view.findViewById(R.id.myLookBackImg);
        tablayout_My_look.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    private void tabLayoutMethod(View view) {

        tablayout_My_look.addTab(tablayout_My_look.newTab().setText("My Cars"));
        tablayout_My_look.addTab(tablayout_My_look.newTab().setText("My Frames"));
       // tablayout_My_look.addTab(tablayout_My_look.newTab().setText("Entry effect"));
        tablayout_My_look.addTab(tablayout_My_look.newTab().setText("Bubble"));
        tablayout_My_look.addTab(tablayout_My_look.newTab().setText("Themes"));



        final PagerAdapter pagerAdapter= new Adaptorlook(getChildFragmentManager(),tablayout_My_look.getTabCount());
        viewPager_look.setAdapter(pagerAdapter);
        viewPager_look.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_My_look));
        tablayout_My_look.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_look));
    }

    public static class Adaptorlook extends FragmentStatePagerAdapter {

        private final int totalCount;


        public Adaptorlook(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);

            this.totalCount=behavior;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment_My_Cars();
                case 1:
                    return new Fragment_My_Frames();
                case 2:
                    return new Fragment_Bubble();
                case 3:
                    return new Fragment_Color_ID();
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
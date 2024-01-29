package com.live.worldsocialintegrationapp.Fragments.Profile.Mall;

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

import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

public class Fragment_mall extends Fragment {

    private TabLayout tablayout_mall;
    private ViewPager viewPager_ml;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mall, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        findIds(view);
        clicks(view);
        tabLayoutMethod(view);
//        if (Build.VERSION.SDK_INT >= 21) {
//            Window window = requireActivity().getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.greenn));
//        }
    }

    private void clicks(View view) {

        view.findViewById(R.id.mallBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }


    private void findIds(View view) {

        tablayout_mall = view.findViewById(R.id.mallTablayout);
        viewPager_ml = view.findViewById(R.id.mallViewPager);
    }



    private void tabLayoutMethod(View view) {
        tablayout_mall.addTab(tablayout_mall.newTab().setText("Cars"));
        tablayout_mall.addTab(tablayout_mall.newTab().setText("Frames"));

        tablayout_mall.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter pagerAdapter= new Adaptornew(getChildFragmentManager(),tablayout_mall.getTabCount());
        viewPager_ml.setAdapter(pagerAdapter);
        viewPager_ml.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_mall));
        tablayout_mall.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_ml));
    }




    public static class Adaptornew extends FragmentStatePagerAdapter
    {
        private final int totalCount;

        public Adaptornew(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);

            this.totalCount=behavior;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {


            switch (position)
            {
                case 0:
                    return new Fragment_cars();
                    case 1:
                    return new Fragment_frames();

                    default:
                    return null;
            }


        }

        @Override
        public int getCount() {
            return totalCount;
        }
    }}
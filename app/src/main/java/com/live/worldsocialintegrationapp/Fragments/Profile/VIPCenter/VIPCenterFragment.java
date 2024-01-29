package com.live.worldsocialintegrationapp.Fragments.Profile.VIPCenter;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.ModelClasses.VipRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class VIPCenterFragment extends Fragment {
    private TabLayout tablayout_ExploreScreen;
    private ViewPager viewPager_ES;
    private ImageView vipBackImg;
    public static List<VipRoot.Detail> list = new ArrayList<>();
    public static VipRoot.Detail detail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_v_i_p_center, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CommonUtils.disableBottomNavigation(requireActivity());

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        }

        findIds(view);
        clicks(view);
        getVip();

    }

    private void clicks(View view) {
        vipBackImg.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });

    }

    private void findIds(View view) {

        tablayout_ExploreScreen = view.findViewById(R.id.vipCenterTablayout);
        viewPager_ES = view.findViewById(R.id.vipCenterViewPager);
        vipBackImg = view.findViewById(R.id.vipBackImg);
    }

    public void tabLayoutMethod() {
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("VIP1"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("VIP2"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("VIP3"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("VIP4"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("VIP5"));
//        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("VIP6"));
//        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("VIP7"));


        tablayout_ExploreScreen.setTabGravity(TabLayout.GRAVITY_FILL);


        if (isAdded() && getChildFragmentManager()!=null){
            final PagerAdapter pagerAdapter = new Adaptor(getChildFragmentManager(), tablayout_ExploreScreen.getTabCount());
            viewPager_ES.setAdapter(pagerAdapter);
            viewPager_ES.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_ExploreScreen));
            tablayout_ExploreScreen.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_ES));
        }

    }

    public static class Adaptor extends FragmentStatePagerAdapter {

        private final int totalCount;

        public Adaptor(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);

            this.totalCount = behavior;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Vip1 vip1 = new Vip1();
                    Bundle b1 = new Bundle();
                    b1.putSerializable("vip1", (Serializable) detail);
                    vip1.setArguments(b1);
                    return vip1;
                case 1:
                    return new Vip2();
                case 2:
                    return new Vip3();
                case 3:
                    return new Vip4();
                case 4:
                    return new Vip5();
//                case 5:
//                    return new Vip5();
//                case 6:
//                    return new Vip5();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return totalCount;
        }
    }

    private void getVip() {
        new Mvvm().getVip(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<VipRoot>() {
            @Override
            public void onChanged(VipRoot vipRoot) {

                if (vipRoot.getStatus() == 1) {

                    list = vipRoot.getDetails();
                    Vip1.detail = vipRoot.getDetails().get(1);
                    Vip2.detail = vipRoot.getDetails().get(2);
                    Vip3.detail = vipRoot.getDetails().get(3);
                    Vip4.detail = vipRoot.getDetails().get(4);
                    Vip5.detail = vipRoot.getDetails().get(5);
                    tabLayoutMethod();

                } else {
//                    Toast.makeText(requireContext(), "roots is null", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
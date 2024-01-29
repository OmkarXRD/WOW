package com.live.worldsocialintegrationapp.Fragments;

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
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;

import com.live.worldsocialintegrationapp.R;

public class LiveDiamondDialogFragment extends BottomSheetDialogFragment {

    private TabLayout tablayout_ExploreScreen;
    private ViewPager viewPager_ES;
    public static  String liveId="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_diamond_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        if(getArguments() != null){
            liveId = getArguments().getString("liveId");
        }else{
            Toast.makeText(requireContext(), "getArgment null", Toast.LENGTH_SHORT).show();
        }
        tabLayoutMethod();
    }

    private void findIds(View view) {
        tablayout_ExploreScreen = view.findViewById(R.id.liveDiamondTablayout);
        viewPager_ES = view.findViewById(R.id.liveDaimondViewPager);
    }

    public void tabLayoutMethod(){

        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("24 Hours"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Total"));

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

          Bundle bundle = new Bundle();
          bundle.putString("liveId",liveId);


            switch (position)
            {
                case 0:
                    LiveDiamond24HoursFragment liveDiamond24HoursFragment = new LiveDiamond24HoursFragment();
                    liveDiamond24HoursFragment.setArguments(bundle);
                    return liveDiamond24HoursFragment;
                case 1:
                    LiveDiamondsTotalFragment liveDiamondsTotalFragment=new LiveDiamondsTotalFragment();
                    liveDiamondsTotalFragment.setArguments(bundle);
                    return  liveDiamondsTotalFragment;
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
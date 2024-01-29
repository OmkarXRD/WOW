package com.live.worldsocialintegrationapp.Fragments.Home.Related.ChiragFragment;

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

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.R;


public class ChiragBottom_Sheet_dialog  extends BottomSheetDialogFragment {
    private TabLayout tablayout_insp, tabLayout_Daily;
    private ViewPager viewPager_insp,  viewPager_daily;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chirag_bottom__sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setPeekHeight(900);
        mBehavior.setMaxHeight(900);
        findids(view);
        tablaymatod(view);

    }



    private void findids(View view) {

        tabLayout_Daily = view.findViewById(R.id.tab_Dialog_Task);
        viewPager_daily = view.findViewById(R.id.viewpager_Task);

    }

    private void tablaymatod(View view) {
        tabLayout_Daily.addTab(tabLayout_Daily.newTab().setText("Daily"));
//        tabLayout_Daily.addTab(tabLayout_Daily.newTab().setText("Weekly"));


        final PagerAdapter pagerAdapter = new Adapter_dailyTask(getChildFragmentManager(), tabLayout_Daily.getTabCount());
        viewPager_daily .setAdapter(pagerAdapter);
        viewPager_daily .addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout_Daily));
        tabLayout_Daily.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_daily));
    }

    public static class Adapter_dailyTask extends FragmentStatePagerAdapter {
        private final int totalCount;


        public Adapter_dailyTask(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);

            totalCount = behavior;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new DailyTaskTab();
//                case 1:
//                    return new FamilyTaskTab();

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
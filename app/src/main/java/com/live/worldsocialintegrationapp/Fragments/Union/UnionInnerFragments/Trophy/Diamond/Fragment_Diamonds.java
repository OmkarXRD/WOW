package com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.Trophy.Diamond;

import android.graphics.Color;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.Trophy.YohoBoard;
import com.live.worldsocialintegrationapp.R;


public class Fragment_Diamonds extends Fragment {

    private TabLayout tablayout_insp;
    private ViewPager viewPager_insp;

    RelativeLayout rooms_lay , dimond_lay, coins_lay, intimacy_lay ;
    TextView rooms_txt , diamond_txt , coins_txt , intimacy_txt ;

    public Fragment_Diamonds() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__diamonds, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        tabLayoutMethod(view);
        setTabfragments(view);
        dimond_lay.setBackgroundResource(R.drawable.back_tabs);
        diamond_txt.setTextColor(Color.BLACK);
    }



    private void findIds(View view) {


        tablayout_insp = view.findViewById(R.id.tab_diamondd);
        viewPager_insp = view.findViewById(R.id.viewpager_diamond);

        rooms_lay = view.findViewById(R.id.Room_lay);
        rooms_txt = view.findViewById(R.id.room_txt);

        dimond_lay = view.findViewById(R.id.dimond_lay);
        diamond_txt = view.findViewById(R.id.dimond_txt);

        coins_lay = view.findViewById(R.id.cois_ly);
        coins_txt = view.findViewById(R.id.coin_txt);

        intimacy_lay = view.findViewById(R.id.intimacy_lay);
        intimacy_txt = view.findViewById(R.id.inti_txt);
    }
    private void setTabfragments(View view) {

        rooms_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().
                        replace(R.id.nav_home, new YohoBoard()).addToBackStack(null).commit();
                rooms_lay.setBackgroundResource(R.drawable.back_tabs);
                dimond_lay.setBackgroundResource(R.drawable.empty_back);
                coins_lay.setBackgroundResource(R.drawable.empty_back);
                intimacy_lay.setBackgroundResource(R.drawable.empty_back);
                rooms_txt.setTextColor(Color.BLACK);
                diamond_txt.setTextColor(Color.WHITE);
                coins_txt.setTextColor(Color.WHITE);
                intimacy_txt.setTextColor(Color.WHITE);

            }
        });

        dimond_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dimond_lay.setBackgroundResource(R.drawable.back_tabs);
                rooms_lay.setBackgroundResource(R.drawable.empty_back);
                coins_lay.setBackgroundResource(R.drawable.empty_back);
                intimacy_lay.setBackgroundResource(R.drawable.empty_back);
                diamond_txt.setTextColor(Color.BLACK);
                rooms_txt.setTextColor(Color.WHITE);
                coins_txt.setTextColor(Color.WHITE);
                intimacy_txt.setTextColor(Color.WHITE);

            }
        });

        coins_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dimond_lay.setBackgroundResource(R.drawable.empty_back);
                rooms_lay.setBackgroundResource(R.drawable.empty_back);
                coins_lay.setBackgroundResource(R.drawable.back_tabs);
                intimacy_lay.setBackgroundResource(R.drawable.empty_back);
                diamond_txt.setTextColor(Color.BLACK);
                rooms_txt.setTextColor(Color.WHITE);
                coins_txt.setTextColor(Color.BLACK);
                intimacy_txt.setTextColor(Color.WHITE);

            }
        });

        intimacy_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dimond_lay.setBackgroundResource(R.drawable.empty_back);
                rooms_lay.setBackgroundResource(R.drawable.empty_back);
                coins_lay.setBackgroundResource(R.drawable.empty_back);
                intimacy_lay.setBackgroundResource(R.drawable.back_tabs);
                diamond_txt.setTextColor(Color.BLACK);
                rooms_txt.setTextColor(Color.WHITE);
                coins_txt.setTextColor(Color.WHITE);
                intimacy_txt.setTextColor(Color.BLACK);

            }
        });

    }

    private void tabLayoutMethod(View view) {

        tablayout_insp.addTab(tablayout_insp.newTab().setText("Daily"));
        tablayout_insp.addTab(tablayout_insp.newTab().setText("Weekly"));
        tablayout_insp.addTab(tablayout_insp.newTab().setText("Monthly"));

        final PagerAdapter pagerAdapter = new Adapter_Dimond(getChildFragmentManager(), tablayout_insp.getTabCount());
        viewPager_insp .setAdapter(pagerAdapter);
        viewPager_insp .addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_insp));
        tablayout_insp.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_insp));
    }
    public static class Adapter_Dimond extends FragmentStatePagerAdapter {
        private final int totalCount;


        public Adapter_Dimond(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            totalCount = behavior;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment_Diamod_Daily();
                case 1:
                    return new Fragment_Diamod_Daily();
                case 2:
                    return new Fragment_Diamod_Daily();
                case 3:
                    return new Fragment_Diamod_Daily();
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

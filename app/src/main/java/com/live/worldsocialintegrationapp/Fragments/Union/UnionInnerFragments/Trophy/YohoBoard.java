package com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.Trophy;

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
import com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.Trophy.Diamond.Fragment_Diamonds;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


public class YohoBoard extends Fragment {


    private TabLayout tablayout_insp;
    private ViewPager viewPager_insp;

    RelativeLayout rooms_lay, dimond_lay, coins_lay, intimacy_lay;
    TextView rooms_txt, diamond_txt, coins_txt, intimacy_txt;

    public YohoBoard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yoho_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        findIds(view);
        tabLayoutMethod(view);
        settabfragments(view);

        rooms_lay.setBackgroundResource(R.drawable.back_tabs);
        dimond_lay.setBackgroundResource(R.drawable.empty_back);
        coins_lay.setBackgroundResource(R.drawable.empty_back);
        intimacy_lay.setBackgroundResource(R.drawable.empty_back);
        rooms_txt.setTextColor(Color.BLACK);
        diamond_txt.setTextColor(Color.WHITE);
        coins_txt.setTextColor(Color.WHITE);
        intimacy_txt.setTextColor(Color.WHITE);

        view.findViewById(R.id.back_search_lead).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void settabfragments(View view) {

        rooms_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                getFragmentManager().beginTransaction().
                        replace(R.id.nav_home, new Fragment_Diamonds()).addToBackStack(null).commit();

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

    private void findIds(View view) {

        tablayout_insp = view.findViewById(R.id.top_yohoboard);
        viewPager_insp = view.findViewById(R.id.viewpager_yohoboard);

        rooms_lay = view.findViewById(R.id.Room_lay);
        rooms_txt = view.findViewById(R.id.room_txt);

        dimond_lay = view.findViewById(R.id.dimond_lay);
        diamond_txt = view.findViewById(R.id.dimond_txt);

        coins_lay = view.findViewById(R.id.cois_ly);
        coins_txt = view.findViewById(R.id.coin_txt);

        intimacy_lay = view.findViewById(R.id.intimacy_lay);
        intimacy_txt = view.findViewById(R.id.inti_txt);
    }


    private void tabLayoutMethod(View view) {

        tablayout_insp.addTab(tablayout_insp.newTab().setText("Daily"));
        tablayout_insp.addTab(tablayout_insp.newTab().setText("Weekly"));
        tablayout_insp.addTab(tablayout_insp.newTab().setText("Monthly"));


        final PagerAdapter pagerAdapter = new Adapter_yohoboard(getChildFragmentManager(), tablayout_insp.getTabCount());
        viewPager_insp.setAdapter(pagerAdapter);
        viewPager_insp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_insp));
        tablayout_insp.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_insp));

    }

    public static class Adapter_yohoboard extends FragmentStatePagerAdapter {

        private final int totalCount;


        public Adapter_yohoboard(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            totalCount = behavior;

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new Fragment_Daily();
                case 1:
                    return new BoardWeeklyFragment();
                case 2:
                    return new BoardMonthlyFragment();
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
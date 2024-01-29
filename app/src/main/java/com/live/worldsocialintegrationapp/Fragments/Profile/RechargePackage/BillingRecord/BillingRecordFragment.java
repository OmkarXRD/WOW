package com.live.worldsocialintegrationapp.Fragments.Profile.RechargePackage.BillingRecord;

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
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


public class BillingRecordFragment extends Fragment {

    private TabLayout tablayout_ExploreScreen;
    private ViewPager viewPager_ES;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_billing_record, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CommonUtils.disableBottomNavigation(requireActivity());

        findIds(view);
        tabLayoutMethod();
        ClickListeners(view);

    }

    private void ClickListeners(View view) {

        view.findViewById(R.id.billingRecBackImg).setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });
    }

    private void findIds(View view) {
        tablayout_ExploreScreen = view.findViewById(R.id.billingRecTablayout);
        viewPager_ES = view.findViewById(R.id.billingRecViewPager);
    }

    public void tabLayoutMethod(){
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Coins"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Silver coins"));

        tablayout_ExploreScreen.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter pagerAdapter= new BillingRecordFragment.Adaptor(getChildFragmentManager(),tablayout_ExploreScreen.getTabCount());
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
                    return new BillingCoinsTab();
                case 1:
                    return new BillingSilverCoinsTab();
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

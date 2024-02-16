package com.live.worldsocialintegrationapp.Fragments.Profile.RechargePackage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.Fragments.Profile.Friends.FriendsFragment;
import com.live.worldsocialintegrationapp.Fragments.Profile.RechargePackage.BillingRecord.BillingRecordFragment;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
public class RechargeCointsFragment extends Fragment {
   private TabLayout tablayout_ExploreScreen;
    private ViewPager viewPager_ES;
    public static int CallActivityCheck=0; //0 means normal visit 1 means CallActivity visit

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recharge_coints, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(CallActivityCheck==0){
            CommonUtils.disableBottomNavigation(requireActivity());
        }
        else{

        }
        App.getSharedpref().saveString("userCheck","");

        //this
        App.getSharedpref().saveString("liveThemeRecharge","");
        findIds(view);
        tabLayoutMethod();
        ClickListeners(view);

    }

    private void ClickListeners(View view) {

        view.findViewById(R.id.rechargeBackImag).setOnClickListener(view1 -> {
            getActivity().onBackPressed();
    });

        view.findViewById(R.id.googleWaletBillingImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BillingRecordFragment billingRecordFragment = new BillingRecordFragment();
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate( R.id.billingRecordFragment2);

            }
        });
    }

    private void findIds(View view) {

        tablayout_ExploreScreen = view.findViewById(R.id.rechargeTabLayout);
        viewPager_ES = view.findViewById(R.id.rechargeViewPager);
    }

    public void tabLayoutMethod(){
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Coins"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Silver Coins"));

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
                    return new CoinsTabFragment();
                case 1:
                    return new SilverCoinsTabFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return totalCount;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FriendsFragment.check=1;
        CallActivityCheck=0;
    }
}
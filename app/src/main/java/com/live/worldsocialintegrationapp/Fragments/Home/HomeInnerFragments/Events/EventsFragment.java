package com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.Events;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.Navigation;
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


public class EventsFragment extends Fragment {
    private TabLayout tablayout_ExploreScreen;
    private ViewPager viewPager_ES;
    private ImageView eventsQuestionImg,eventsInvitationImg;
    private AppCompatButton idWannaPartyBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.greenn));
        }

        findIds(view);
        clicks(view);
        tabLayoutMethod();
    }

    private void clicks(View view) {

        eventsQuestionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.eventCreateAnFragemnt);
            }
        });

        view.findViewById(R.id.eventBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        idWannaPartyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.eventCreateAnFragemnt);
            }
        });
        eventsInvitationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.eventsInvitationsFragment);
            }
        });
    }

    private void findIds(View view) {

        tablayout_ExploreScreen = view.findViewById(R.id.eventsTablayout);
        viewPager_ES = view.findViewById(R.id.eventViewPager);
        eventsQuestionImg = view.findViewById(R.id.eventsQuestionImg);
        idWannaPartyBtn = view.findViewById(R.id.idWannaPartyBtn);
        eventsInvitationImg = view.findViewById(R.id.eventsInvitationImg);
    }

    public void tabLayoutMethod(){
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("What's On"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Subscription"));

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
                    return new WhatsOnFragment();
                case 1:
                    return new SubscriptionsFragment();
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
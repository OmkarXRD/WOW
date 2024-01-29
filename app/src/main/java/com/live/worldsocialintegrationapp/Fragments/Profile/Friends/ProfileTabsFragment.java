package com.live.worldsocialintegrationapp.Fragments.Profile.Friends;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.Objects;


public class ProfileTabsFragment extends Fragment {

    //    private TabLayout tablayout_ExploreScreen;
//    private ViewPager viewPager_ES;
    private ImageView profileTabsBackImg;
    private TextView profileTabsTV, profiletabFriendsTV, profiletabFollowingTV, profiletabFansTV;
    private int tabsCheck = 0;
    private View friendsLine, followingLine, fansLine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tabs2, container, false);

        if (getArguments() != null && getArguments().containsKey("tabsCheck")) {
            tabsCheck = getArguments().getInt("tabsCheck");
        }
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        findIds(view);
//        tabLayoutMethod();

        if (tabsCheck == 0) {
            friendsLine.setVisibility(View.VISIBLE);
            followingLine.setVisibility(View.GONE);
            fansLine.setVisibility(View.GONE);
            profileTabsTV.setText("Friends");
           getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.profileTabsFramlayout, new FriendsFragment()).addToBackStack(null).commit();
        } else if (tabsCheck == 1) {
            friendsLine.setVisibility(View.GONE);
            followingLine.setVisibility(View.VISIBLE);
            fansLine.setVisibility(View.GONE);
            profileTabsTV.setText("Following");
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.profileTabsFramlayout, new FollowingFragment()).addToBackStack(null).commit();
        } else if (tabsCheck == 2) {
            friendsLine.setVisibility(View.GONE);
            followingLine.setVisibility(View.GONE);
            fansLine.setVisibility(View.VISIBLE);
            profileTabsTV.setText("Fans");
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.profileTabsFramlayout, new FansFragment()).addToBackStack(null).commit();
        } else {
            //Toast.makeText(requireContext(), "Not any Check", Toast.LENGTH_SHORT).show();
        }

        clicks(view);
    }

    private void clicks(View view) {
        profileTabsBackImg.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });


        profiletabFriendsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabsCheck = 0;
                profileTabsTV.setText("Friends");
                friendsLine.setVisibility(View.VISIBLE);
                followingLine.setVisibility(View.GONE);
                fansLine.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.profileTabsFramlayout, new FriendsFragment()).addToBackStack(null).commit();
            }
        });
        profiletabFollowingTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabsCheck = 1;
                profileTabsTV.setText("Following");
                friendsLine.setVisibility(View.GONE);
                followingLine.setVisibility(View.VISIBLE);
                fansLine.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.profileTabsFramlayout, new FollowingFragment()).addToBackStack(null).commit();
            }
        });

        profiletabFansTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabsCheck = 2;
                profileTabsTV.setText("Fans");
                friendsLine.setVisibility(View.GONE);
                followingLine.setVisibility(View.GONE);
                fansLine.setVisibility(View.VISIBLE);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.profileTabsFramlayout, new FansFragment()).addToBackStack(null).commit();
            }
        });
    }

    private void findIds(View view) {
//        tablayout_ExploreScreen = view.findViewById(R.id.profileTablayout);
//        viewPager_ES = view.findViewById(R.id.profileTabsViewPager);
        profileTabsBackImg = view.findViewById(R.id.profileTabsBackImg);
        profileTabsTV = view.findViewById(R.id.profileTabsTV);
        profiletabFriendsTV = view.findViewById(R.id.profiletabFriendsTV);
        profiletabFollowingTV = view.findViewById(R.id.profiletabFollowingTV);
        profiletabFansTV = view.findViewById(R.id.profiletabFansTV);

        friendsLine = view.findViewById(R.id.friendsLine);
        followingLine = view.findViewById(R.id.followingLine);
        fansLine = view.findViewById(R.id.fansLine);


    }

//    public void tabLayoutMethod(){
//
//        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Friends"));
//        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Following"));
//        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Fans"));
//
//        tablayout_ExploreScreen.setTabGravity(TabLayout.GRAVITY_FILL);
//
//
//        final PagerAdapter pagerAdapter= new Adaptor(getChildFragmentManager(),tablayout_ExploreScreen.getTabCount());
//        viewPager_ES.setAdapter(pagerAdapter);
//        viewPager_ES.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_ExploreScreen));
//        tablayout_ExploreScreen.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_ES));
//    }


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
                    return new FriendsFragment();
                case 1:
                    return new FollowingFragment();
                case 2:
                    return new FansFragment();
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
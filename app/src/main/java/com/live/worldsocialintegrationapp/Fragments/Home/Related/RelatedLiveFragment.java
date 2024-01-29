package com.live.worldsocialintegrationapp.Fragments.Home.Related;

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
import com.live.worldsocialintegrationapp.Fragments.Home.Related.RelatedLive.FollowingLiveUsersFragment;
import com.live.worldsocialintegrationapp.Fragments.Home.Related.RelatedLive.FriendLiveUsersFragment;
import com.live.worldsocialintegrationapp.R;


public class RelatedLiveFragment extends Fragment {


    private TabLayout tablayout_ExploreScreen;
    private ViewPager viewPager_ES;
    private ImageView profileTabsBackImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_related_live, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        click(view);
        tabLayoutMethod();
    }

    private void click(View view) {
        view.findViewById(R.id.liveBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void findIds(View view) {
        tablayout_ExploreScreen = view.findViewById(R.id.relatedLiveTablayout);
        viewPager_ES = view.findViewById(R.id.relatedLiveViewPager);
//        profileTabsBackImg=view.findViewById(R.id.profileTabsBackImg);
    }

    public void tabLayoutMethod() {

        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Friends"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Following"));


        tablayout_ExploreScreen.setTabGravity(TabLayout.GRAVITY_FILL);


        final PagerAdapter pagerAdapter = new Adaptor(getChildFragmentManager(), tablayout_ExploreScreen.getTabCount());
        viewPager_ES.setAdapter(pagerAdapter);
        viewPager_ES.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_ExploreScreen));
        tablayout_ExploreScreen.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_ES));
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
                    return new FriendLiveUsersFragment();
                case 1:
                    return new FollowingLiveUsersFragment();
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
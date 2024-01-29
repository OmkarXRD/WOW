package com.live.worldsocialintegrationapp.Fragments.Profile.Family;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.live.worldsocialintegrationapp.Fragments.Profile.Family.Invitations.FamilyInvitationTab;
import com.live.worldsocialintegrationapp.Fragments.Profile.Family.Invitations.FamilyRequestsFragment;
import com.live.worldsocialintegrationapp.R;


public class FamilyInvitationsFragment extends Fragment  {

    private TabLayout tablayout_ExploreScreen;
    private ViewPager viewPager_ES;
    private ImageView familyInvitationsBackImg;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference ChatRequestCountRef = firebaseDatabase.getReference().child("ChatRequestCount");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_family_invitations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        clicks(view);
        tabLayoutMethod();
    }

    private void clicks(View view) {

        familyInvitationsBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void findIds(View view) {

        tablayout_ExploreScreen = view.findViewById(R.id.familyInvitationTablayout);
        viewPager_ES = view.findViewById(R.id.familyInvitatiomnViewPager);
        familyInvitationsBackImg=view.findViewById(R.id.familyInvitationsBackImg);
    }


    public void tabLayoutMethod() {
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Invitations"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Requests"));


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
                    FamilyInvitationTab familyInvitationTab = new FamilyInvitationTab();
                    Bundle b1 = new Bundle();
//                    b1.putSerializable("vip1", (Serializable) detail);
                    familyInvitationTab.setArguments(b1);
                    return familyInvitationTab;
                case 1:
                    return new FamilyRequestsFragment();
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
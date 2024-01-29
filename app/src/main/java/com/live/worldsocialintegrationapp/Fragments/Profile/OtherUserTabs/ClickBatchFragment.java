package com.live.worldsocialintegrationapp.Fragments.Profile.OtherUserTabs;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.Adapters.PrivillegeFamilyAdapter;
import com.live.worldsocialintegrationapp.Fragments.BronzeFragment;
import com.live.worldsocialintegrationapp.Fragments.DiamondFragment;
import com.live.worldsocialintegrationapp.Fragments.GoldFragment;
import com.live.worldsocialintegrationapp.Fragments.Profile.Mall.Fragment_cars;
import com.live.worldsocialintegrationapp.Fragments.Profile.Mall.Fragment_frames;
import com.live.worldsocialintegrationapp.Fragments.Profile.Mall.Fragment_mall;
import com.live.worldsocialintegrationapp.Fragments.SilverFragment;
import com.live.worldsocialintegrationapp.ModelClasses.GetFamilyDetails;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;

import java.util.ArrayList;
import java.util.List;


public class ClickBatchFragment extends Fragment {

    private TabLayout tablayout_mall;
    private ViewPager viewPager_ml;
    private ImageView clickBtchBackImg;
    private Button howToLevelUpFamily;
    private LinearLayout bottomSheetRL;

    RecyclerView recyclerView;
    PrivillegeFamilyAdapter privillegeFamilyAdapter;

     public List<GetFamilyDetails.Detail> detailsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_click_batch, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        tabLayoutMethod(view);
        setAdapter();

        clickBtchBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        howToLevelUpFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);

                // passing a layout file for our bottom sheet dialog.
                View layout = LayoutInflater.from(requireContext()).inflate(R.layout.upgradequickelybottomsheet, bottomSheetRL);

                // passing our layout file to our bottom sheet dialog.
                bottomSheetTeachersDialog.setContentView(layout);

                // below line is to set our bottom sheet dialog as cancelable.
                bottomSheetTeachersDialog.setCancelable(false);

                // below line is to set our bottom sheet cancelable.
                bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);

                ImageView imageView = bottomSheetTeachersDialog.findViewById(R.id.cross);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetTeachersDialog.dismiss();
                    }
                });

                // below line is to display our bottom sheet dialog.
                bottomSheetTeachersDialog.show();

            }
        });

    }


    private void setAdapter() {

    }


    private void findIds(View view) {
        tablayout_mall = view.findViewById(R.id.clickBadgeTablyout);
        viewPager_ml = view.findViewById(R.id.clickBagViewPager);
        clickBtchBackImg = view.findViewById(R.id.clickBtchBackImg);
        howToLevelUpFamily = view.findViewById(R.id.howToLevelUpFamily);
        bottomSheetRL = view.findViewById(R.id.rlbottomsheet);
    //    recyclerView = view.findViewById(R.id.recyclerViewPriv);
    }

    private void tabLayoutMethod(View view) {
        tablayout_mall.addTab(tablayout_mall.newTab().setText("Iron"));
        tablayout_mall.addTab(tablayout_mall.newTab().setText("Bronze"));
        tablayout_mall.addTab(tablayout_mall.newTab().setText("Silver"));
        tablayout_mall.addTab(tablayout_mall.newTab().setText("Gold"));
        tablayout_mall.addTab(tablayout_mall.newTab().setText("Diamond"));

        final PagerAdapter pagerAdapter = new Adaptornew(getChildFragmentManager(), tablayout_mall.getTabCount(),new ArrayList<>());
        viewPager_ml.setAdapter(pagerAdapter);
        viewPager_ml.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_mall));
        tablayout_mall.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_ml));
    }

    public static class Adaptornew extends FragmentStatePagerAdapter {
        private final int totalCount;
        public List<GetFamilyDetails.Detail> detailsList = new ArrayList<>();

        public Adaptornew(@NonNull FragmentManager fm, int behavior,List<GetFamilyDetails.Detail> detailsList ) {
            super(fm, behavior);
            this.totalCount = behavior;
            this.detailsList = detailsList;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new ClickBatchViewPagerFragment(detailsList, position);
                case 1:
                    return new BronzeFragment();
                case 2:
                    return new SilverFragment();
                case 3:
                    return new GoldFragment();
                case 4:
                    return new DiamondFragment();
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
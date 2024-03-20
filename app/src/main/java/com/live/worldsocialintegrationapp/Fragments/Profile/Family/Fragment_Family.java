package com.live.worldsocialintegrationapp.Fragments.Profile.Family;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.Fragments.Home.HomeFragment;
import com.live.worldsocialintegrationapp.ModelClasses.ShowMyFamilyModelClass;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Family extends Fragment {

    private TabLayout layout_family;
    private ViewPager viewPager_family;
    private CircleImageView familyShapeableImg;
    private TextView familyNameTv,family1DescriptionTv,fireLevel,txtPosition;
    LinearLayout layout,cratfamly2;
    RelativeLayout cratfamily;
    String familyid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__family, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        familyNameTv = view.findViewById(R.id.familyNameTv);
        family1DescriptionTv = view.findViewById(R.id.family1DescriptionTv);
        fireLevel = view.findViewById(R.id.fireLevel);
        familyShapeableImg = view.findViewById(R.id.familyShapeableImg);
        layout= view.findViewById(R.id.cratfamly2);
        txtPosition= view.findViewById(R.id.txtPosition);
        cratfamily = view.findViewById(R.id.crat_famly);
        cratfamly2 = view.findViewById(R.id.cratfamly2);

        new Mvvm().getShowMyFamily(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<ShowMyFamilyModelClass>() {
            @Override
            public void onChanged(ShowMyFamilyModelClass showMyFamilyModelClass) {
                if (showMyFamilyModelClass.getSuccess().equalsIgnoreCase("1")){
                    //Toast.makeText(requireContext(), "ola"+showMyFamilyModelClass.getDetails().getId(), Toast.LENGTH_SHORT).show();
                    familyid=showMyFamilyModelClass.getDetails().getId();
                    Log.d("id","Position of team : "+showMyFamilyModelClass.getDetails().getPosition());
                    familyNameTv.setText(showMyFamilyModelClass.getDetails().getFamilyName());
                    family1DescriptionTv.setText(showMyFamilyModelClass.getDetails().getDescription());
                    txtPosition.setText(String.valueOf(showMyFamilyModelClass.getDetails().getPosition()));
                    fireLevel.setText(showMyFamilyModelClass.getDetails().getTotal());
                    Glide.with(familyShapeableImg.getContext()).load(showMyFamilyModelClass.getDetails().getImage()).error(R.drawable.demo_user_profile_img).into(familyShapeableImg);
                    layout.setVisibility(View.VISIBLE);
                    cratfamily.setVisibility(View.GONE);
                }else {
                    layout.setVisibility(View.GONE);
                    cratfamily.setVisibility(View.VISIBLE);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.golden_color));
        }

        HomeFragment.backCheck = 0;

        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        Toast.makeText(getActivity(), "Back Pressed", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });


        findIds(view);
        tabLayoutMethod(view);

        view.findViewById(R.id.crat_famly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.fragment_Create_Family);
            }
        });

        view.findViewById(R.id.familyInvitationImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.familyInvitationsFragment);
            }
        });

        view.findViewById(R.id.familyBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        view.findViewById(R.id.cratfamly2).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("familyId",familyid);
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.familyBatchFragment, bundle);
        });
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void findIds(View view) {
        layout_family= view.findViewById(R.id.familyTablayout);
        viewPager_family = view.findViewById(R.id.famlyViewPager);
        layout_family.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    private void tabLayoutMethod(View view) {

        layout_family.addTab(layout_family.newTab().setText("New"));
        layout_family.addTab(layout_family.newTab().setText("Weekly"));
        layout_family.addTab(layout_family.newTab().setText("Monthly"));
//      layout_family.addTab(layout_family.newTab().setText("Competition"));

        final PagerAdapter pagerAdapter= new Adapterfamily(getChildFragmentManager(),layout_family.getTabCount());
        viewPager_family.setAdapter(pagerAdapter);
        viewPager_family.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout_family));
        layout_family.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_family));

    }


    public static class Adapterfamily extends FragmentStatePagerAdapter {

        private  final  int totalcount ;


        public Adapterfamily(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);

            totalcount= behavior ;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new Fragment_family_new();
                case 1:
                    return new Fragment_Weekly();
                case 2:
                    return new Fragment_Monthly();
//                case 3:
//                    return new Fragment_Competition();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return totalcount;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        View view1 = requireActivity().findViewById(R.id.bottom_lay);
        view1.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HomeFragment.backCheck =1;
    }
}
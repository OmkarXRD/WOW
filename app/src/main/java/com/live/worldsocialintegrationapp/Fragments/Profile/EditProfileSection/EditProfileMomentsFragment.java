package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.Adapters.PostsAdapter;
import com.live.worldsocialintegrationapp.Fragment_Detailed_profile;
import com.live.worldsocialintegrationapp.Fragments.Profile.Fragment_Moments;
import com.live.worldsocialintegrationapp.Fragments.Profile.Friends.FansFragment;
import com.live.worldsocialintegrationapp.Fragments.Profile.Friends.FollowingFragment;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.Details;
import com.live.worldsocialintegrationapp.R;

import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentEditProfileMomentsBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


public class EditProfileMomentsFragment extends Fragment {

    FragmentEditProfileMomentsBinding binding;
    private int check = 1;
    private TabLayout tablayout_momment;
    private ViewPager viewPager_momment;
    private static int value_check = 0;
    private boolean vipStatus = false;

    private ImageView coverImage;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileMomentsBinding.inflate(inflater, container, false);



        if (getArguments() != null && getArguments().containsKey("value_check")){
            value_check= getArguments().getInt("value_check");
        }
        return binding.getRoot();
    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(App.getSharedpref().getString("receiveLevel").isEmpty()){
//            binding.editProfileMyLvlTv.setText("0");
            binding.level.setVisibility(View.GONE);
        }else{
            //updated to hide layout when receivelevel is 0
            if (Integer.parseInt(App.getSharedpref().getString("receiveLevel"))==0){
                binding.level.setVisibility(View.GONE);
            }
            else {
                binding.level.setVisibility(View.VISIBLE);
                binding.editProfileMyLvlTv.setText(App.getSharedpref().getString("receiveLevel"));
            }

        }
//         binding.receivingLayout.setBackgroundColor(Color.parseColor(App.getSharedpref().getString("receivingColor")));
//         binding.receivingLayout.getBackground().setColorFilter(Color.parseColor(App.getSharedpref().getString("receivingColor")), PorterDuff.Mode.SRC_ATOP);

        //App.getSharedpref().saveString("ajencyCoin"
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(requireActivity(), WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(requireActivity(), WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //binding.sendingLayout.setBackgroundColor(Color.parseColor(App.getSharedpref().getString("sendingColor")));
        // binding.sendingLayout.getBackground().setColorFilter(Color.parseColor(App.getSharedpref().getString("sendingColor")), PorterDuff.Mode.SRC_ATOP);

        new Mvvm().getSendingLevelData(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), response ->{
            Glide.with(requireContext()).load(response.getDetails().getReciveColor()).into(binding.receivingLayout);
        });
        CommonUtils.disableBottomNavigation(requireActivity());

        setAdapter();
        clickListeners(view);
        hitApi();

        findIds(view);
        tabLayoutMethod(view);
        backPressed(view);

        if (App.getSharedpref().getString("hostStatus").equalsIgnoreCase("2")){
            binding.anchorlayout.setVisibility(View.VISIBLE);
        }else {
            binding.anchorlayout.setVisibility(View.GONE);
        }

        if (App.getSharedpref().getString("ajencyCoin").equalsIgnoreCase("true")){
            binding.coinAjency.setVisibility(View.VISIBLE);
        }else {
            binding.coinAjency.setVisibility(View.GONE);
        }


        binding.editMomentProfileFollowingTV.setText(App.getSharedpref().getString("followingCount"));
        binding.editMomentProfileFansTV.setText(App.getSharedpref().getString("FansCount"));
        binding.editProfileMomentUsernameTv.setText(App.getSharedpref().getString("username"));



        if(vipStatus == true){
//            binding.editProfileVipLvlTv.setText("Viplvl.0");
            binding.vipBTN.setVisibility(View.VISIBLE);
//            binding.vipImage.setVisibility(View.INVISIBLE);
        }else{
            binding.vipImage.setVisibility(View.VISIBLE);

            if(App.getSharedpref().getString("vipLevel").equalsIgnoreCase("1")){
                binding.vipImage.setImageResource(R.drawable.vip1img);

            }else if(App.getSharedpref().getString("vipLevel").equalsIgnoreCase("2")){
                binding.vipImage.setImageResource(R.drawable.vip2img);

            }else if(App.getSharedpref().getString("vipLevel").equalsIgnoreCase("3")){
                binding.vipImage.setImageResource(R.drawable.vip3img);

            }else if(App.getSharedpref().getString("vipLevel").equalsIgnoreCase("4")){
                binding.vipImage.setImageResource(R.drawable.vip4img);
            }else if(App.getSharedpref().getString("vipLevel").equalsIgnoreCase("5")){
                binding.vipImage.setImageResource(R.drawable.vip5img);
            }else{
                binding.editProfileVipLvlTv.setText("Viplvl."+App.getSharedpref().getString("vipLevel"));
                binding.vipImage.setVisibility(View.INVISIBLE);
            }
            binding.editProfileVipLvlTv.setText("Viplvl."+App.getSharedpref().getString("vipLevel"));
        }


        if(App.getSharedpref().getString("mylevel").isEmpty()){
            //binding.WLevelTv.setText("0");
            Log.i("levellll",App.getSharedpref().getString("mylevel"));
        }
        else{
            binding.WLevelTv.setText(App.getSharedpref().getString("mylevel"));
            if (Integer.parseInt(App.getSharedpref().getString("mylevel"))==0){
                binding.sendingLayout.setVisibility(View.GONE);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=1 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=10) {

                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_1);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=11 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=20) {
                binding.lvlimg.setImageResource(R.drawable.badge1);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_2);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=21 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=30) {
                binding.lvlimg.setImageResource(R.drawable.badge1);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_3);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=31 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=40) {
                binding.lvlimg.setImageResource(R.drawable.badge2);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_4);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=41 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=50) {
                binding.lvlimg.setImageResource(R.drawable.badge2);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_5);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=51 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=60) {
                binding.lvlimg.setImageResource(R.drawable.badge3);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_6);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=61 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=70) {
                binding.lvlimg.setImageResource(R.drawable.badge3);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_7);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=71 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=80) {
                binding.lvlimg.setImageResource(R.drawable.badge3);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_8);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=81 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=90) {
                binding.lvlimg.setImageResource(R.drawable.badge3);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_9);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=91 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=100) {
                binding.lvlimg.setImageResource(R.drawable.badge3);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_10);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=101 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=110) {
                binding.lvlimg.setImageResource(R.drawable.badge4);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_11);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=111 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=120) {
                binding.lvlimg.setImageResource(R.drawable.badge4);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_12);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=121 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=130) {
                binding.lvlimg.setImageResource(R.drawable.badge4);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_13);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=131 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=140) {
                binding.lvlimg.setImageResource(R.drawable.badge4);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_14);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>=141 && Integer.parseInt(App.getSharedpref().getString("mylevel"))<=150) {
                binding.lvlimg.setImageResource(R.drawable.badge4);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_15);
            } else if (Integer.parseInt(App.getSharedpref().getString("mylevel"))>150) {
                binding.lvlimg.setImageResource(R.drawable.badge4);
                binding.sendingLayout.setVisibility(View.VISIBLE);
                binding.sendingLayout.setBackgroundResource(R.drawable.level_16);
            }
        }

        if(App.getSharedpref().getString("gender").equalsIgnoreCase("male")||App.getSharedpref().getString("gender").equalsIgnoreCase("Male")){
            binding.myGenderImg.setImageResource(R.drawable.ic_male_gender__2_);
            binding.layout.getBackground().setColorFilter(Color.parseColor("#0ed8a3"), PorterDuff.Mode.SRC_ATOP);
        }else{
            binding.myGenderImg.setImageResource(R.drawable.femenine);
            binding.layout.getBackground().setColorFilter(Color.parseColor("#fd5293"), PorterDuff.Mode.SRC_ATOP);
        }


        binding.myCountryTv.setText(App.getSharedpref().getString("country"));
        binding.myAgeTv.setText(CommonUtils.ageCalcualte(App.getSharedpref().getString("dob")));
        binding.tvBio2.setText(App.getSharedpref().getString("bio"));

    }

    private void hitApi() {

        new Mvvm().getUserDetail(requireActivity(), AppConstants.USER_ID, AppConstants.USER_ID, AppConstants.USER_ID).observe(requireActivity(), response -> {
            if (response !=null){
                if (response.getStatus().equalsIgnoreCase("1")){
                    vipStatus = response.getDetails().vipStatus;
                    setData(response.getDetails());
                }
            }
        });
    }

    private void setData(Details details) {
        binding.tUserName.setText(details.getName());
        if (value_check == 1) {
            PostsAdapter.postAdapterUserImg = App.getSharedpref().getString("otherUserImg");
            Log.i("Profileee","zzzzzzzzz1");
            Glide.with(getContext()).load(App.getSharedpref().getString("otherUserImg")).error(R.drawable.demo_user_profile_img).into(binding.imgCirclePhoto);
            Glide.with(getContext()).load(App.getSharedpref().getString("otherUserImg")).error(R.drawable.demo_user_profile_img).into(binding.editProfileBgImage);
        } else if (value_check == 2) {
            Log.i("Profileee","zzzzzzzzz2");
            Glide.with(getContext()).load(details.getImage()).error(R.drawable.demo_user_profile_img).into(binding.imgCirclePhoto);
            Glide.with(getContext())
                    .load(details.getImage())
                            .error(R.drawable.demo_user_profile_img)
                            .into(binding.editProfileBgImage);

            PostsAdapter.postAdapterUserImg = details.getImage();


        }

        if (details !=null){
            if (details.familyJoinStatus){
                binding.familyRl.setVisibility(View.VISIBLE);
                binding.tvFamily.setText(details.getFamilyJoinName());
            }else {
                binding.familyRl.setVisibility(View.GONE);
            }
        }else {

        }

        if (details.agency_status){
            binding.ajencyIconLayout.setVisibility(View.VISIBLE);
        }else {
            binding.ajencyIconLayout.setVisibility(View.GONE);
        }
    }

    private void findIds(View view) {
        tablayout_momment = view.findViewById(R.id.momentstablay);
        viewPager_momment = view.findViewById(R.id.mommentViewPager);
        coverImage = view.findViewById(R.id.editProfileBgImage);
    }

    private void tabLayoutMethod(View view) {
        tablayout_momment.addTab(tablayout_momment.newTab().setText("Moments"));
        tablayout_momment.addTab(tablayout_momment.newTab().setText("Detailed Profile"));
        tablayout_momment.setTabGravity(TabLayout.GRAVITY_FILL);
        final PagerAdapter pagerAdapter = new AdaptorMomment(getChildFragmentManager(), tablayout_momment.getTabCount());
        viewPager_momment.setAdapter(pagerAdapter);
        viewPager_momment.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_momment));
        tablayout_momment.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_momment));
    }

    public static class AdaptorMomment extends FragmentStatePagerAdapter {

        private final int totalCount;

        public AdaptorMomment(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);

            this.totalCount = behavior;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Fragment_Moments fragment_moments = new Fragment_Moments();
                    fragment_moments.getArguments();
                    return fragment_moments;
                case 1:
                    return  Fragment_Detailed_profile.newInstance(AppConstants.USER_ID);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return totalCount;
        }
    }

    private void clickListeners(View view) {

        binding.editProfileBgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ImageSlideFragment.getImageUserId = AppConstants.USER_ID;
                Bundle bundle = new Bundle();
                bundle.putString("getImageUserId",AppConstants.USER_ID);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.imageSlideFragment,bundle);
            }
        });

        binding.editProfileBackImg.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });

        binding.imgCirclePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.imageSlideFragment);
//                check = 2;
//                showDialog();
            }
        });

        binding.editFollowingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FollowingFragment.check = 1;
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.followingFragment2);
            }
        });
        binding.editFansLinarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FansFragment.check = 1;
                FansFragment fansFragment = new FansFragment();
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.fansFragment);
            }
        });

        binding.tvBio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                check = 3;
//                showDialog();
            }
        });

        binding.familyRl.setOnClickListener(view12 -> {
//                check = 4;
//                showDialog();
            Bundle bundle = new Bundle();
            bundle.putString("familyId", App.getSharedpref().getString("familyId"));
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.familyBatchFragment, bundle);
        });
        binding.heading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Navigation.findNavController(binding.getRoot()).navigate( R.id.action_editProfileMomentsFragment_to_settingsFragment );
            }
        });
        binding.editpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigation.findNavController(binding.getRoot()).navigate( R.id.action_editProfileMomentsFragment_to_fragment_Edit_profile);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.editProfileScreen);
            }
        });

        binding.floatingAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.uploadMomentFragment);
            }
        });
//        view.findViewById(R.id.floatingAddBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate( R.id.uploadMomentFragment);
//            }
//        });
    }

    private void setAdapter() {
        // binding.rvPosts.setAdapter(new PostsAdapter(EditProfileMomentsFragment.this));
    }


//    @Override
//    public void onClick(int position) {
//
//        check = 1;
//        showDialog();
//
//    }

    private void showDialog() {

        if (check == 1) {
            Dialog dialogChooseAlbum = new Dialog(requireContext());

            dialogChooseAlbum.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialogChooseAlbum.setContentView(R.layout.share_post_bottom_sheet);

            dialogChooseAlbum.setCanceledOnTouchOutside(false);

            dialogChooseAlbum.setCancelable(true);

            dialogChooseAlbum.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            dialogChooseAlbum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        dialogChooseAlbum.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

            dialogChooseAlbum.getWindow().setGravity(Gravity.BOTTOM);

            dialogChooseAlbum.show();
        }
        if (check == 2) {
            Dialog dialogChooseAlbum = new Dialog(requireContext());

            dialogChooseAlbum.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialogChooseAlbum.setContentView(R.layout.edit_photo_bottom_sheet);

            dialogChooseAlbum.setCanceledOnTouchOutside(false);

            dialogChooseAlbum.setCancelable(true);

            dialogChooseAlbum.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            dialogChooseAlbum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        dialogChooseAlbum.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

            dialogChooseAlbum.getWindow().setGravity(Gravity.BOTTOM);

            dialogChooseAlbum.show();
        }

        if (check == 3) {
            Dialog dialogChooseAlbum = new Dialog(requireContext());

            dialogChooseAlbum.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialogChooseAlbum.setContentView(R.layout.edit_bio_bottom_sheet);

            dialogChooseAlbum.setCanceledOnTouchOutside(false);

            dialogChooseAlbum.setCancelable(true);

            dialogChooseAlbum.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            dialogChooseAlbum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        dialogChooseAlbum.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

            dialogChooseAlbum.getWindow().setGravity(Gravity.BOTTOM);

            dialogChooseAlbum.show();
        }
        if (check == 4) {
            Dialog dialogChooseAlbum = new Dialog(requireContext());

            dialogChooseAlbum.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialogChooseAlbum.setContentView(R.layout.edit_family_bottom_sheet);

            dialogChooseAlbum.setCanceledOnTouchOutside(false);

            dialogChooseAlbum.setCancelable(true);

            dialogChooseAlbum.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            dialogChooseAlbum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        dialogChooseAlbum.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

            dialogChooseAlbum.getWindow().setGravity(Gravity.BOTTOM);

            dialogChooseAlbum.show();
        }

    }
    private void backPressed(View sView) {

        sView.requestFocus();

        sView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (i == KeyEvent.KEYCODE_BACK) {
                        Navigation.findNavController(requireActivity(), R.id.nav_home).navigate(R.id.profileMainFragment);
                        return true;
                    }
                }
                return true;
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
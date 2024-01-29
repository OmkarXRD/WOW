package com.live.worldsocialintegrationapp.Fragments.Profile;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.Details;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;

import com.live.worldsocialintegrationapp.databinding.FragmentProfileMainBinding;
import com.live.worldsocialintegrationapp.room.AppDatabase;
import com.live.worldsocialintegrationapp.room.CustomerChatTable;
import com.live.worldsocialintegrationapp.room.DiffaultQuestions;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.net.MalformedURLException;
import java.net.URL;

public class ProfileMainFragment extends Fragment {

    private FragmentProfileMainBinding binding;
    private SVGAImageView DpFrame;
    private String frame;
    private SVGAParser parser;
    private String familyId;
    private boolean vipStatus = false;
    private RelativeLayout dailyRewardRL;
    private AppDatabase appDatabase;
    String sendingExpCurrent,sendingEndExp,sendingStart,sendingLvl,sendingColor;
    private Mvvm viewModel;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(Mvvm.class);
        binding = FragmentProfileMainBinding.inflate(inflater, container, false);
        binding.profileName.setText(App.getSharedpref().getString("name"));
        binding.profileid.setText("ID: "+App.getSharedpref().getString("username"));

//        binding.currentLevelLowerBoundTv.setText("LV." + App.getSharedpref().getString("mylevel"));
//        String currentLevelText = App.getSharedpref().getString("sendingLvl");
//        Integer currentLevelInteger = Integer.parseInt(currentLevelText);
//        binding.currentLevelUpperBoundTv.setText("LV." + (currentLevelInteger + 1));
//        binding.WealthprogressBar.setMax(100);
//
//        sendingExpCurrent= App.getSharedpref().getString("sendingExpCurrent");
//        sendingEndExp = App.getSharedpref().getString("sendingEndExp");
//        sendingLvl = App.getSharedpref().getString("sendingLvl");
//        sendingColor = App.getSharedpref().getString("sendingColor");
//
//        sendingStart= App.getSharedpref().getString("SendStart");
//        String currentScore = sendingExpCurrent;
//
//        Integer currentScoreInt = Integer.parseInt(currentScore);
//
//        String currentLevel = sendingLvl;
//        Integer currentLevelLowerBound = Integer.parseInt(sendingStart);
//        Integer currentLevelUpperBound = Integer.parseInt(sendingEndExp);
//        try {
//            Integer   currentLevelProgress = (currentScoreInt - currentLevelLowerBound) * 100 / (currentLevelUpperBound - currentLevelLowerBound);
//            Log.d("keyyy", "currentlevel: " + currentLevel);
//            Log.d("keyyy","level statings :- "+sendingStart);
//            Log.d("keyyy","level ending :- "+sendingEndExp);
//            Log.d("keyyy","keyy :- "+sendingExpCurrent);
//
//            Integer upgradeLevelExp = currentLevelUpperBound - currentScoreInt;
//
//
//            binding.WealthprogressBar.setProgress(currentLevelProgress);
//            //binding.expUpgradeTv.setText("Need " + upgradeLevelExp + " Exp to upgrade");
//            binding.expUpgradeTv.setText(currentScoreInt+"/"+currentLevelUpperBound);
//        }catch (Exception e){
//
//        }


//      EditProfileMomentsFragment.value_check = 2;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appDatabase = AppDatabase.getInstance(requireContext());
        findIDs(view);

        viewModel.getSendingLevelData(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), sendingLevel -> {
            if (sendingLevel!=null){
                if (sendingLevel.getSuccess().equalsIgnoreCase("1")){
                    if (sendingLevel.getDetails().getReciveLevel().isEmpty()) {
                        binding.receivingLayout.setVisibility(View.GONE);
                    }else {
                        binding.editProfileMyLvlTv.setText(sendingLevel.getDetails().getReciveLevel());
                        if (Integer.parseInt(sendingLevel.getDetails().getReciveLevel())==0){
                            binding.receivingLayout.setVisibility(View.GONE);
                            binding.receiveRl.setVisibility(View.GONE);
                        }else {
                            binding.receivingLayout.setVisibility(View.VISIBLE);
//                            binding.receivingLayout.getBackground().setColorFilter(Color.parseColor(sendingLevel.getDetails().getReciveColor()), PorterDuff.Mode.SRC_ATOP);
                            Glide.with(requireContext()).load(sendingLevel.getDetails().getReciveColor()).into(binding.receivingLayout);
                        }
                    }

                    if(sendingLevel.getDetails().getSendLevel().isEmpty()){
                        binding.sendingLayout.setVisibility(View.GONE);
                        binding.WLevelTv.setText("0");
                    }else{
                        binding.sendingLayout.setVisibility(View.VISIBLE);
                        binding.WLevelTv.setText(App.getSharedpref().getString("mylevel"));
                        if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())==0){
                            binding.sendingLayout.setVisibility(View.GONE);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=1 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=10) {
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_1);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=11 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=20) {
                            binding.lvlimg.setImageResource(R.drawable.badge1);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_2);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=21 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=30) {
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_3);
                            binding.lvlimg.setImageResource(R.drawable.badge1);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=31 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=40) {
                            binding.lvlimg.setImageResource(R.drawable.badge2);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_4);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=41 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=50) {
                            binding.lvlimg.setImageResource(R.drawable.badge2);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_5);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=51 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=60) {
                            binding.lvlimg.setImageResource(R.drawable.badge3);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_6);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=61 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=70) {
                            binding.lvlimg.setImageResource(R.drawable.badge3);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_7);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=71 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=80) {
                            binding.lvlimg.setImageResource(R.drawable.badge3);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_8);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=81 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=90) {
                            binding.lvlimg.setImageResource(R.drawable.badge3);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_9);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=91 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=100) {
                            binding.lvlimg.setImageResource(R.drawable.badge3);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_10);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=101 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=110) {
                            binding.lvlimg.setImageResource(R.drawable.badge4);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_11);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=111 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=120) {
                            binding.lvlimg.setImageResource(R.drawable.badge4);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_12);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=121 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=130) {
                            binding.lvlimg.setImageResource(R.drawable.badge4);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_13);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=131 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=140) {
                            binding.lvlimg.setImageResource(R.drawable.badge4);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_14);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>=141 && Integer.parseInt(sendingLevel.getDetails().getSendLevel())<=150) {
                            binding.lvlimg.setImageResource(R.drawable.badge4);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_15);
                        } else if (Integer.parseInt(sendingLevel.getDetails().getSendLevel())>150) {
                            binding.lvlimg.setImageResource(R.drawable.badge4);
                            binding.sendingLayout.setVisibility(View.VISIBLE);
                            binding.sendingLayout.setBackgroundResource(R.drawable.level_16);
                        }
                    }
                  //  binding.sendingLayout.getBackground().setColorFilter(Color.parseColor(sendingLevel.getDetails().getSandColor()), PorterDuff.Mode.SRC_ATOP);

                    App.getSharedpref().saveString("sendingExpCurrent",sendingLevel.getDetails().getSendExp());
                    App.getSharedpref().saveString("sendingEndExp",String.valueOf(sendingLevel.getDetails().getSendEnd()));
                    App.getSharedpref().saveString("requiredExperience",String.valueOf(sendingLevel.getDetails().getRequiredExperience()));
                    App.getSharedpref().saveString("receiveRequiredExperience",String.valueOf(sendingLevel.getDetails().getReceiveRequiredExperience()));
                    App.getSharedpref().saveString("sendingLvl",sendingLevel.getDetails().getSendLevel());
                    App.getSharedpref().saveString("SendStart",String.valueOf(sendingLevel.getDetails().getSendStart()));
                    App.getSharedpref().saveString("sendingColor",sendingLevel.getDetails().getSandColor());

                    App.getSharedpref().saveString("receivingColor",sendingLevel.getDetails().getReciveColor());
                    App.getSharedpref().saveString("receiveStart",String.valueOf(sendingLevel.getDetails().getReciveStart()));
                    App.getSharedpref().saveString("receiveEnd",String.valueOf(sendingLevel.getDetails().getReciveEnd()));
                    App.getSharedpref().saveString("receiveExpCurrent",sendingLevel.getDetails().getReciveExp());
                    App.getSharedpref().saveString("receiveLevel",sendingLevel.getDetails().getReciveLevel());



                }else {
                    //Toast.makeText(requireContext(), "success 0", Toast.LENGTH_SHORT).show();
                }
            }else {
                //Toast.makeText(requireContext(), "null", Toast.LENGTH_SHORT).show();
            }

        });
        backPressed(view);
//        setData();
        getUserDetailApi();
        onClick();
        getCoinsApi();
        getDiamondApi();
        getAppliedFrameApi();

        String receiveLevel =App.getSharedpref().getString("receiveLevel");
        String mylevel =App.getSharedpref().getString("mylevel");

        //check for level and Experience
       /* if(mylevel.equals("0") && receiveLevel.equals("0")){
            binding.userLevelRL.setVisibility(View.GONE);
        }*/



//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = requireActivity().getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }
        setStatusBarGradiant(requireActivity());
//      appDatabase.userDao().deleteQuestionTable();
        if (appDatabase.userDao().questionTableExits(1)){
        }else{
            if (appDatabase.userDao().question_idExits("1")){
            }else{
                getQuestionApi();
            }
        }

        if (vipStatus) {
            Log.i("vipStatus", String.valueOf(vipStatus));
            dailyRewardRL.setVisibility(View.VISIBLE);
        } else {
            Log.i("vipStatus", String.valueOf(vipStatus));
            dailyRewardRL.setVisibility(View.GONE);
        }
    }

    private void getCoinsApi() {
        viewModel.getUserCoinModelLiveData(requireActivity(),AppConstants.USER_ID).observe(requireActivity(), getUserCoinModel -> {
          if (getUserCoinModel !=null){
              if (getUserCoinModel.getSuccess().equalsIgnoreCase("1")){
                  //  String formattedValue = formatValue(Integer.parseInt(getUserCoinModel.getDetails().getMyCoin()));
                  binding.coinsTextView.setText(getUserCoinModel.getDetails().getMyCoin());
              }
          }
        });
    }

    @SuppressLint("DefaultLocale")
    private String formatValue(int value) {
        if (value < 1000) {
            return String.valueOf(value);
        } else if (value < 1000000) {
            return String.format("%.1fk", value / 1000.0);
        } else if (value < 1000000000) {
            return String.format("%.1fm", value / 1000000.0);
        } else {
            return String.format("%.1ft", value / 1000000000.0);
        }
    }

    private void findIDs(View view) {
        DpFrame = view.findViewById(R.id.profieFrame);
        dailyRewardRL = view.findViewById(R.id.dailyRewardRL);
    }

    private void getAppliedFrameApi() {
        viewModel.getAppliedFrame(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), getAppliedFrameRoot -> {
            if (getAppliedFrameRoot != null) {
                if (getAppliedFrameRoot.getStatus() == 1) {
                    frame = getAppliedFrameRoot.getDetails().getFrame_img();
                    App.getSharedpref().saveString("appliedFrame",frame);
                    setFrameONDp(frame);
                } else {
//                        Toast.makeText(requireActivity(), "" + getAppliedFrameRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFrameONDp(String frame) {

        if (getActivity() != null && getActivity().getApplicationContext() != null) {

            parser = new SVGAParser(getActivity().getApplicationContext());
            try {
                parser.decodeFromURL(new URL(frame), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@com.google.firebase.database.annotations.NotNull SVGAVideoEntity videoItem) {
                        SVGADrawable drawable = new SVGADrawable(videoItem);
                        DpFrame.setImageDrawable(drawable);
                        DpFrame.startAnimation();
                    }
                    @Override
                    public void onError() {
                    }
                }, list -> {
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        disableBottomNavigation();
    }

    private void disableBottomNavigation() {

        View view1 = requireActivity().findViewById(R.id.bottom_lay);
        view1.setVisibility(View.VISIBLE);
    }


    private void onClick() {

        binding.profilelayout.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("value_check",2);
//                Fragment_Moments.check_value = 2;
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_profileMainFragment_to_editProfileMomentsFragment,bundle);
        });

        binding.settingsRL.setOnClickListener(view -> Navigation.findNavController(binding.getRoot()).navigate(R.id.settingsFragment));

        binding.rlRechargeCoins.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.rechargeCointsFragment);
//          startActivity(new Intent(requireActivity(), RechargeActivity.class));
        });

        binding.incomeRL.setOnClickListener(view -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_profileMainFragment_to_incomeFragment));
        binding.visitorslayout.setOnClickListener(view -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_profileMainFragment_to_visitorsFragment));

        binding.friendslayout.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("tabsCheck",0);
//            ProfileTabsFragment.tabsCheck = 0;
            Navigation.findNavController(binding.getRoot()).navigate(R.id.profileTabsFragment,bundle);
        });

        binding.fanslayout.setOnClickListener(view -> {
//            ProfileTabsFragment.tabsCheck = 2;
            Bundle bundle = new Bundle();
            bundle.putInt("tabsCheck",2);
            Navigation.findNavController(binding.getRoot()).navigate(R.id.profileTabsFragment,bundle);
        });

        binding.followinglayout.setOnClickListener(view -> {
//            ProfileTabsFragment.tabsCheck = 1;
            Bundle bundle = new Bundle();
            bundle.putInt("tabsCheck",1);
            Navigation.findNavController(binding.getRoot()).navigate(R.id.profileTabsFragment,bundle);
        });

        binding.userLevelRL.setOnClickListener(view -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_profileMainFragment_to_userLevelFragment2));

        binding.vipRL.setOnClickListener(view -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_profileMainFragment_to_VIPCenterFragment));
        binding.mallRL.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_profileMainFragment_to_fragment_mall));
        binding.myLookRL.setOnClickListener(v -> {
            try {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_profileMainFragment_to_fragment_My_look);
            }catch (Exception e){

            }
        });

        binding.familyRL.setOnClickListener(v -> {
//            Navigation.findNavController(binding.getRoot()).navigate(R.id.fragment_Family);
//            if(familyId.equalsIgnoreCase("0")){
//                Navigation.findNavController(binding.getRoot()).navigate(R.id.fragment_Family);
//            }else{  4$
//            Bundle bundle = new Bundle();
//            bundle.putString("familyId", App.getSharedpref().getString("familyId"));
//            Toast.makeText(requireContext(), "tytuty"+App.getSharedpref().getString("statusFamilyjoin"), Toast.LENGTH_SHORT).show();
//            if (App.getSharedpref().getString("familycreate")!=null){
//                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.familyBatchFragment, bundle);
//            }else {
//                Navigation.findNavController(binding.getRoot()).navigate(R.id.fragment_Family);
//            }
//            }

            viewModel.getShowMyFamily(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), showMyFamilyModelClass -> {
                if (showMyFamilyModelClass.getSuccess().equalsIgnoreCase("1")){
                    Bundle bundle = new Bundle();
                    bundle.putString("familyId",showMyFamilyModelClass.getDetails().getId());
                    bundle.putString("leaderId",showMyFamilyModelClass.getDetails().getLeaderId());
                    Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.familyBatchFragment, bundle);
                }else {
                    //Navigation.findNavController(binding.getRoot()).navigate(R.id.fragment_Family);
                    Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.fragment_Family);
                }

            });
        });

        binding.onlineRL.setOnClickListener(v -> {
            DialogBox();
        });

        binding.badgesRL.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.badgesFragment);
        });

    }
    private void DialogBox() {

        Dialog dialog_share = new Dialog(getContext());
        dialog_share.setContentView(R.layout._item_online_services);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();

        AppCompatButton noBtn = dialog_share.findViewById(R.id.no);
        AppCompatButton sendBtn = dialog_share.findViewById(R.id.sendBtn);
        sendBtn.setText("Yes");
        noBtn.setOnClickListener(view -> dialog_share.dismiss());
        sendBtn.setOnClickListener(view -> {
            dialog_share.dismiss();
//                Toast.makeText(requireContext(), "Working on it", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.chatServiceFragment);
        });
    }

    private void getUserDetailApi() {
        //Toast.makeText(requireContext(), "userId "+AppConstants.USER_ID, Toast.LENGTH_SHORT).show();
        viewModel.getUserDetail(requireActivity(), AppConstants.USER_ID, AppConstants.USER_ID, AppConstants.USER_ID).observe(requireActivity(), getUserDetailRoot -> {
            if (getUserDetailRoot != null) {
                if (getUserDetailRoot.getStatus().equalsIgnoreCase("1")) {
                    App.getSharedpref().saveString("image", getUserDetailRoot.getDetails().getProfileImage());
                    App.getSharedpref().saveString("name", getUserDetailRoot.getDetails().getName());
                    App.getSharedpref().saveString("bio", getUserDetailRoot.getDetails().getBio());
                    App.getSharedpref().saveString("gender", getUserDetailRoot.getDetails().getGender());
                    App.getSharedpref().saveString("country", getUserDetailRoot.getDetails().getCountry());
                    App.getSharedpref().saveString("dob", getUserDetailRoot.getDetails().getDob());
                    App.getSharedpref().saveString("followingCount", getUserDetailRoot.getDetails().getFollowingCount());
                    App.getSharedpref().saveString("FansCount", getUserDetailRoot.getDetails().getFollowersCount());
                    App.getSharedpref().saveString("visitorsCount", getUserDetailRoot.getDetails().getVisitorsCount());
                    App.getSharedpref().saveString("friendsCount", getUserDetailRoot.getDetails().getFriendsCount());
                    App.getSharedpref().saveString("myExp", getUserDetailRoot.getDetails().getMyExp());
                    App.getSharedpref().saveString("recieveExp", getUserDetailRoot.getDetails().getMyRecieveExperience());
                    App.getSharedpref().saveString("mylevel", getUserDetailRoot.getDetails().getMyLevel());
                    App.getSharedpref().saveString("receivinglevel", getUserDetailRoot.getDetails().getReceivingLevel());
                    App.getSharedpref().saveString("familyId", getUserDetailRoot.getDetails().getFamilyId());
                    App.getSharedpref().saveString("vipLevel", getUserDetailRoot.getDetails().getVipLevel());
                    App.getSharedpref().saveString("WealthLevel", getUserDetailRoot.getDetails().getMyLevel());
                    App.getSharedpref().saveString("familyName", getUserDetailRoot.getDetails().getFamilyName());
                    App.getSharedpref().saveString("familyImage", getUserDetailRoot.getDetails().getFamilyImage());
                    App.getSharedpref().saveString("hostStatus", getUserDetailRoot.getDetails().getHost_status());
                    App.getSharedpref().saveString("ajencyCoin", String.valueOf(getUserDetailRoot.getDetails().isCoinAgencyCreater()));
                    App.getSharedpref().saveString("agencyCreater", String.valueOf(getUserDetailRoot.getDetails().isAgencyCreater()));
                    vipStatus = getUserDetailRoot.getDetails().isVip_status();
                    setData(getUserDetailRoot.getDetails());
                } else {
                }
            } else {
                Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setData(Details details) {

        if (App.getSharedpref().getString("name").length() != 0) {
            binding.profileName.setText(App.getSharedpref().getString("name"));
        } else {
            binding.profileName.setText("Username");
        }
        if (App.getSharedpref().getString("followingCount").length() == 0) {
            binding.mainProfileFollowingTV.setText("0");
        } else {
            String formattedValue = formatValue(Integer.parseInt(App.getSharedpref().getString("followingCount")));
            binding.mainProfileFollowingTV.setText(formattedValue);
        }
        if (App.getSharedpref().getString("visitorsCount").length() == 0) {
            binding.vistorsCountTV.setText("0");
        } else {
            String formattedValue = formatValue(Integer.parseInt(App.getSharedpref().getString("visitorsCount")));
            binding.vistorsCountTV.setText(formattedValue);
        }
        if (App.getSharedpref().getString("friendsCount").length() == 0) {
            binding.friendsCountTV.setText("0");
        } else {
            String formattedValue = formatValue(Integer.parseInt(App.getSharedpref().getString("friendsCount")));
            binding.friendsCountTV.setText(formattedValue);
        }
        if (App.getSharedpref().getString("FansCount").length() == 0) {
            binding.fanCountTV.setText("0");
        } else {

            String formattedValue = formatValue(Integer.parseInt(App.getSharedpref().getString("FansCount")));
            binding.fanCountTV.setText(formattedValue);
        }

        String username = App.getSharedpref().getString("username");
//      binding.profileid.setText("ID : "+AppConstants.USER_ID);
        familyId = App.getSharedpref().getString("familyId");

        if (username.isEmpty()) {
            binding.profileid.setText("ID : " + App.getSharedpref().getString("uniqueId"));
        } else {
            binding.profileid.setText("ID : " + username);
        }
        if (details !=null){
            Glide.with(binding.profilePhoto.getContext()).load(details.getImage()).error(R.drawable.demo_user_profile_img).into(binding.profilePhoto);
        }else {
            Glide.with(binding.profilePhoto.getContext()).load(App.getSharedpref().getString("image")).error(R.drawable.demo_user_profile_img).into(binding.profilePhoto);
        }
    }

    private void getDiamondApi() {
        viewModel.getDaimonds(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), getDiamondRoot -> {
            if (getDiamondRoot != null) {
                if (getDiamondRoot.getStatus().equalsIgnoreCase("1")) {
                    if (getContext() != null) {
//                            Toast.makeText(requireContext(), "1 " + getDiamondRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {

                    }
                    if (getDiamondRoot.getDetails().getMyDiamond().isEmpty()) {
                        binding.incomeMoneyTV.setText("0");
                    } else {
                      //  String formattedValue = formatValue(Integer.parseInt(getDiamondRoot.getDetails().getMyDiamond()));
                        binding.incomeMoneyTV.setText(getDiamondRoot.getDetails().getMyDiamond());
                    }
                } else {
//                        Toast.makeText(requireContext(), "0 " + getDiamondRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void backPressed(View sView) {
        sView.requestFocus();
        sView.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
            }
            return false;
        });
    }
    private void getQuestionApi() {
        viewModel.getQuestions(requireActivity()).observe(requireActivity(), getQuestionRoot -> {
            if (getQuestionRoot != null) {
                if (getQuestionRoot.getSuccess().equalsIgnoreCase("1")) {
                    for (int i = 0; i < getQuestionRoot.getDetails().size(); i++) {
                        DiffaultQuestions diffaultQuestions = new DiffaultQuestions(getQuestionRoot.getDetails().get(i).getQuestion(), "0",
                                getQuestionRoot.getDetails().get(i).getId());
                        appDatabase.userDao().insert(diffaultQuestions);
                    }
                    CustomerChatTable customerChatTable = new CustomerChatTable("test", "0",
                            "0");
                    appDatabase.userDao().insert(customerChatTable);
                } else {
                }
            } else {

            }
        });
    }
    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            @SuppressLint("UseCompatLoadingForDrawables") Drawable background = activity.getResources().getDrawable(R.drawable.wallet_background);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.darker_gray));
            window.setBackgroundDrawable(background);
        }
    }

}
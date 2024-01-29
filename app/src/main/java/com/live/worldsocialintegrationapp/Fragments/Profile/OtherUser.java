package com.live.worldsocialintegrationapp.Fragments.Profile;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.worldsocialintegrationapp.Adapters.PostsAdapter;
import com.live.worldsocialintegrationapp.Adapters.SliderAdapterExample;
import com.live.worldsocialintegrationapp.Fragment_Detailed_profile;
import com.live.worldsocialintegrationapp.Fragments.Profile.OtherUserTabs.FamilyBatchFragment;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetInvitationsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.FirebaseSendReqRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.Details;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserImagesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.RequstChat;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.ModelClasses.UsersSearch.Detail;
import com.live.worldsocialintegrationapp.ModelClasses.Visitors.GetVisitorsRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import in.aabhasjindal.otptextview.OtpTextView;

public class OtherUser extends Fragment implements SliderAdapterExample.Callback {

    private SVGAImageView profieFrame;
    private TextView otherUserIdTV, othrUserName, othrUsrFollowingTV, countryTv, othrUsrFansTV, othrUsrTopNameTV, otherUserRequestTV,
            otherAgeTv, bioTv,offlineStatusTV,otherUserFriendTv,otherUserAddToBlokedTv,vipText,WLevelTv,editProfileMyLvlTv,familyNameTv;
    private LinearLayout followUserLlayout, userProfileMsglLyout, bottomBtnsLyout,linearLayout;
    private ImageView followingImg, othrUserBgImg, otherUserIdbackImg,lvlimg,vipLayout,receivingLayout,userProfileBackImg, othrUserCircleImg,
            menu, genderImg, ageWithGenderImg,coinAjency;
    private List<Detail> list = new ArrayList<>();
    private int listPoisition;
    private Details details;
    private Detail userSearchDetails;
    private int j = 2;
    private TabLayout tablayout_ExploreScreen;
    private ViewPager viewPager_ES;
    private RelativeLayout othrUsrChatRL, othrUsrFollowingRL,liveFamilyLlayout, sendFriendRequestRL,anchorStats,ajencyStatus;
    private String visitorsUserId, otherUserId,requestCount = "0",hostStatus,ajency,otherUserr;
    private FirebaseDatabase database;
    private DatabaseReference myRef, friendReqRef;
    private LottieAnimationView progressImg;
    boolean isFriendOrNOt = false,blockStatus=false;
    private List<GetUserImagesRoot.Detail> bgSliderImgesList = new ArrayList<>();
    private SliderAdapterExample adapter;
    private SliderView sliderView;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference ChatRequestCountRef = firebaseDatabase.getReference().child("ChatRequestCount");
    private Boolean ajecy = true;
    private RelativeLayout sendingLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ChatRequest");

        Bundle bundle = getArguments();
        otherUserr =bundle.getString("otherUser");

        App.getSharedpref().saveString("userCheck", "");
        findIds(view);
        tabLayoutMethod();
        getData();
        clicks(view);
        getUserDetailApi(view);
        setVisitorApi();
        sendChatRequest();
//        getImagesApi();
        backPressed(view);
        isFriendOrNot();
        otherAgeTv.setText(App.getSharedpref().getString("age"));

        if (otherUserId.equalsIgnoreCase(AppConstants.USER_ID)){
            menu.setVisibility(View.GONE);
        }
    }

    private void setVisitorApi() {

        new Mvvm().setVisitors(requireActivity(), AppConstants.USER_ID, otherUserId).observe(requireActivity(), getVisitorsRoot -> {
            if (getVisitorsRoot.getStatus().equalsIgnoreCase("1")) {
            } else {}
        });
    }

    private void getData() {
        if (getArguments() != null) {
            Bundle bundle = new Bundle();
            bundle = getArguments();
            visitorsUserId = bundle.getString("otherUserId");
            if ((Detail) bundle.getSerializable("list")!=null){
                userSearchDetails = (Detail) bundle.getSerializable("list");
            }else {

            }
            if (bundle.getString("status")!=null){
                if (bundle.getString("status").equalsIgnoreCase("1")){
//                    hostStatus = userSearchDetails.getHostStatus();
//                    String coinAjencyCreator = String.valueOf(userSearchDetails.getCoinaAgencyCreator());
//                    if (coinAjencyCreator.equalsIgnoreCase("true")){
//                        coinAjency.setVisibility(View.VISIBLE);
//                    }else {
//                        coinAjency.setVisibility(View.GONE);
//                    }
                    //String coinAjency = userSearchDetails.
//                    if (hostStatus.equalsIgnoreCase("2")){
//                        anchorStats.setVisibility(View.VISIBLE);
//                    }else {
//                        anchorStats.setVisibility(View.GONE);
//                    }
//                    if (String.valueOf(userSearchDetails.getAgencyCreator()).equalsIgnoreCase("true")){
//                        ajencyStatus.setVisibility(View.VISIBLE);
//                    }else {
//                        ajencyStatus.setVisibility(View.GONE);
//                    }
//                    if(userSearchDetails.getVipLevel().isEmpty()){
//                        vipLayout.setVisibility(View.INVISIBLE);
//                    }else{
//                        vipLayout.setVisibility(View.VISIBLE);
//                        if(userSearchDetails.getVipLevel().equalsIgnoreCase("1")){
//                            vipLayout.setImageResource(R.drawable.vip1img);
//
//                        }else if(userSearchDetails.getVipLevel().equalsIgnoreCase("2")){
//                            vipLayout.setImageResource(R.drawable.vip2img);
//                        }else if(userSearchDetails.getVipLevel().equalsIgnoreCase("3")){
//                            vipLayout.setImageResource(R.drawable.vip3img);
//                        }else if(userSearchDetails.getVipLevel().equalsIgnoreCase("4")){
//                            vipLayout.setImageResource(R.drawable.vip4img);
//                        }else if(userSearchDetails.getVipLevel().equalsIgnoreCase("5")){
//                            vipLayout.setImageResource(R.drawable.vip5img);
//                        }else{
//                            vipLayout.setVisibility(View.INVISIBLE);
//                        }
//                    }
                }
            }else {
            }


            Fragment_Moments.check_value = 1;
            if (userSearchDetails != null) {
                Fragment_Moments.otherId = userSearchDetails.getId();
                otherUserId = userSearchDetails.getId();
                matchOurUserId(otherUserId);
            } else {
                Fragment_Moments.otherId = visitorsUserId;
                otherUserId = visitorsUserId;
                matchOurUserId(otherUserId);
            }
        } else {
            //Toast.makeText(requireContext(), "Argments is null", Toast.LENGTH_SHORT).show();
        }
    }
    private void findIds(View view) {
        linearLayout = view.findViewById(R.id.linearLayout);
        coinAjency = view.findViewById(R.id.coinAjency);
        ajencyStatus = view.findViewById(R.id.ajencystatus);
        tablayout_ExploreScreen = view.findViewById(R.id.otherUserTablayout);
        viewPager_ES = view.findViewById(R.id.otherUserViewPager);
        anchorStats = view.findViewById(R.id.anchorStatus);
        vipLayout = view.findViewById(R.id.vipLayout);
        othrUserBgImg = view.findViewById(R.id.othrUserBgImg);
        othrUserCircleImg = view.findViewById(R.id.othrUserCircleImg);
        profieFrame = view.findViewById(R.id.profieFrame);
        othrUserName = view.findViewById(R.id.othrUserName);
        otherUserIdTV = view.findViewById(R.id.otherUserIdTV);
        countryTv = view.findViewById(R.id.countryTv);
        othrUsrChatRL = view.findViewById(R.id.othrUsrChatRL);
        othrUsrFollowingRL = view.findViewById(R.id.othrUsrFollowingRL);
        otherUserIdbackImg = view.findViewById(R.id.otherUserIdbackImg);
        othrUsrFansTV = view.findViewById(R.id.othrUsrFansTV);
        othrUsrFollowingTV = view.findViewById(R.id.othrUsrFollowingTV);
        followingImg = view.findViewById(R.id.followingImg);
        othrUsrTopNameTV = view.findViewById(R.id.othrUsrTopNameTV);
        sendFriendRequestRL = view.findViewById(R.id.sendFriendRequestRL);
        otherUserRequestTV = view.findViewById(R.id.otherUserRequestTV);
        sliderView = view.findViewById(R.id.imageSlider);
        menu = view.findViewById(R.id.othrUserThreeDotsImg);
        bottomBtnsLyout = view.findViewById(R.id.bottomBtnsLyout);
        editProfileMyLvlTv = view.findViewById(R.id.editProfileMyLvlTv);
        progressImg = view.findViewById(R.id.progressImg);
        genderImg = view.findViewById(R.id.genderImg);
        ageWithGenderImg = view.findViewById(R.id.ageWithGenderImg);
        otherAgeTv = view.findViewById(R.id.otherAgeTv);
        bioTv = view.findViewById(R.id.bioTv);
        WLevelTv = view.findViewById(R.id.WLevelTv);
        lvlimg = view.findViewById(R.id.lvlimg);
        sendingLayout = view.findViewById(R.id.sendingLayout);
        receivingLayout = view.findViewById(R.id.receivingLayout);
        offlineStatusTV = view.findViewById(R.id.offlineStatusTV);
        liveFamilyLlayout = view.findViewById(R.id.liveFamilyLlayout);
        familyNameTv = view.findViewById(R.id.familyNameTv);
    }

    private void matchOurUserId(String userId) {

        if (userId.equalsIgnoreCase(AppConstants.USER_ID)) {
            sendFriendRequestRL.setVisibility(View.GONE);
            othrUsrFollowingRL.setVisibility(View.GONE);
            bottomBtnsLyout.setVisibility(View.GONE);

        } else {
            sendFriendRequestRL.setVisibility(View.VISIBLE);
            othrUsrFollowingRL.setVisibility(View.VISIBLE);
            bottomBtnsLyout.setVisibility(View.VISIBLE);
        }
    }

    private void clicks(View view) {

        othrUserBgImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ImageSlideFragment.getImageUserId = otherUserId;
                Bundle bundle = new Bundle();
                bundle.putString("getImageUserId",otherUserId);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.imageSlideFragment,bundle);
            }
        });
        othrUserCircleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                ImageSlideFragment.getImageUserId = otherUserId;
                Bundle bundle = new Bundle();
                bundle.putString("getImageUserId",otherUserId);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.imageSlideFragment,bundle);
            }
        });
        otherUserIdbackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        othrUsrChatRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(), R.id.nav_home).navigate(R.id.messagesFragment);
            }
        });

        othrUsrFollowingRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followUserApi();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setMenu();
                sideDialogBox();
            }
        });
    }

    private void setMenu() {

        PopupMenu popupMenu = new PopupMenu(requireActivity(), menu);
        popupMenu.getMenuInflater().inflate(R.menu.items, popupMenu.getMenu());


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == 0) {
                    item.setTitle("Blocked");
                }

                //Toast.makeText(requireActivity(),"You Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
                switch (item.getItemId()) {
                    case R.id.item1:
                        Bundle bundle = new Bundle();
                        bundle.putString("otherUserId", details.getId());
                        Navigation.findNavController(requireActivity(), R.id.nav_home).navigate(R.id.usersReportFragment, bundle);
                        return true;
                    case R.id.item2:

//                        dialogBlock(item);

                        return true;

                    default:
                }
                return true;
            }
        });
        popupMenu.show();
    }


    private void sideDialogBox() {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.other_user_side_menu);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.TOP);
        dialog.show();

        TextView otherUserReportTv = dialog.findViewById(R.id.otherUserReportTv);
         otherUserAddToBlokedTv = dialog.findViewById(R.id.otherUserAddToBlokedTv);

        otherUserFriendTv = dialog.findViewById(R.id.otherUserFriendTv);

        if (isFriendOrNOt) {
            otherUserFriendTv.setText("Unfriend");
        } else {
            otherUserFriendTv.setVisibility(View.GONE);
            //otherUserFriendTv.setText("Friend");
        }

        if(otherUserId.equalsIgnoreCase(AppConstants.USER_ID)){
            otherUserAddToBlokedTv.setVisibility(View.GONE);
        }

      if (blockStatus){
          Toast.makeText(requireContext(), "true", Toast.LENGTH_SHORT).show();
          otherUserAddToBlokedTv.setText("Blocked");
      }else{
          Toast.makeText(requireContext(), "false", Toast.LENGTH_SHORT).show();
          otherUserAddToBlokedTv.setText("Add to blocklist");
      }

        otherUserReportTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("otherUserId", details.getId());
                Navigation.findNavController(requireActivity(), R.id.nav_home).navigate(R.id.usersReportFragment, bundle);
                dialog.dismiss();
            }
        });

        otherUserAddToBlokedTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBlock();
                dialog.dismiss();
            }
        });

        otherUserFriendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFriendOrNOt) {

                    unfriend();

                } else {

                    myRef.child(otherUserId).child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                if(snapshot.hasChild("byUnfriend")){

                                    String byUnfriendId=snapshot.child("byUnfriend").getValue().toString();

                                    if(byUnfriendId.equalsIgnoreCase(AppConstants.USER_ID)){

                                        myRef.child(otherUserId).child(AppConstants.USER_ID).child("type").setValue("1");
                                        myRef.child(AppConstants.USER_ID).child(otherUserId).child("type").setValue("1");

                                        myRef.child(AppConstants.USER_ID).child(otherUserId).child("toFriendStatus").setValue("1");
                                        myRef.child(otherUserId).child(AppConstants.USER_ID).child("toFriendStatus").setValue("1");
                                    }else{
                                        Toast.makeText(requireContext(), "You are unfriend", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    myRef.child(otherUserId).child(AppConstants.USER_ID).child("toFriendStatus").setValue("0");
                                    myRef.child(otherUserId).child(AppConstants.USER_ID).child("byUnfriend").setValue("0");


                                    myRef.child(AppConstants.USER_ID).child(otherUserId).child("toFriendStatus").setValue("0");
                                    myRef.child(AppConstants.USER_ID).child(otherUserId).child("byUnfriend").setValue("0");
                                }

                            }else{

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



//                    RequstChat sendChatRequst = new RequstChat(AppConstants.USER_ID, AppConstants.USER_ID, "0",
//                            otherUserId, details.getProfileImage(), details.getName(), myImg, myName, gender + " wants to be your friend", dob, gender, "1", "0");

//                    myRef.child(otherUserId).child(AppConstants.USER_ID).setValue(sendChatRequst).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//
//                                otherUserRequestTV.setText("Sent");
//                                sendChatReqApi(AppConstants.USER_ID, otherUserId);
//
//                                ChatRequestCountRef.child(otherUserId).addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        if (snapshot.exists()) {
//                                            requestCount = snapshot.child("count").getValue().toString();
//                                            String checkRequest = snapshot.child("checkRequest").getValue().toString();
//                                        } else {
//
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });
//
//                                int count = Integer.parseInt(requestCount);
//                                count++;
//
//                                requestCount = String.valueOf(count);
//                                HashMap<String, String> requestCountHp = new HashMap<>();
//                                requestCountHp.put("count", requestCount); //count requests
//                                requestCountHp.put("checkRequest", "1"); //0 means request all checked 1 means not checked
//
//                                ChatRequestCountRef.child(otherUserId).setValue(requestCountHp);
//                            } else {
//                                Log.d("task", task.getException().toString());
//                            }
//                        }
//                    });
                }
                dialog.dismiss();
            }
        });
    }

    private void unfriend() {
        removeFriend(otherUserId);
        myRef.child(AppConstants.USER_ID).child(otherUserId).child("byUnfriend").setValue(AppConstants.USER_ID);
        myRef.child(otherUserId).child(AppConstants.USER_ID).child("byUnfriend").setValue(AppConstants.USER_ID);

        myRef.child(AppConstants.USER_ID).child(otherUserId).child("toFriendStatus").setValue("0");
        myRef.child(otherUserId).child(AppConstants.USER_ID).child("toFriendStatus").setValue("0").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    otherUserFriendTv.setText("Friend");
                    Log.i("UNFriend","in IF");
                }else{
                    Log.i("UNFriend","in ELSE");
                }
            }
        });
    }

    private void removeFriend(String otherUserId) {

        new Mvvm().followUsers(requireActivity(),AppConstants.USER_ID,otherUserId,"removeFriend").observe(requireActivity(), new Observer<SendOtpRoot>() {
            @Override
            public void onChanged(SendOtpRoot sendOtpRoot) {

                Log.i("FriendReq11111","other user id "+otherUserId);
                Log.i("FriendReq11111","my user id "+AppConstants.USER_ID);
            }


        });


    }

    private void dialogBlock() {

        Dialog viewDetails_box = new Dialog(requireContext());
        viewDetails_box.setContentView(R.layout.dialog_bolock_user);
        viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable());
        Window window = viewDetails_box.getWindow();
        viewDetails_box.setCanceledOnTouchOutside(true);
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        // viewDetails_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewDetails_box.show();
        viewDetails_box.setCanceledOnTouchOutside(true);

        TextView confirm = viewDetails_box.findViewById(R.id.confirm_);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiUserBlock(viewDetails_box);
            }
        });

        TextView cancel = viewDetails_box.findViewById(R.id.cancel_);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewDetails_box.dismiss();
            }
        });
    }

    private void apiUserBlock(Dialog viewDetails_box) {

        new Mvvm().blockUser(requireActivity(), AppConstants.USER_ID, otherUserId).observe(requireActivity(), new Observer<GetInvitationsRoot>() {
            @Override
            public void onChanged(GetInvitationsRoot getInvitationsRoot) {
                if (getInvitationsRoot != null) {

                    // 1-> blocked   2->unblocked
                    if (getInvitationsRoot.getStatus()==1){
                        Toast.makeText(requireActivity(), "1 " + getInvitationsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        otherUserAddToBlokedTv.setText("Blocked");
                    }else if(getInvitationsRoot.getStatus()==2){
                        otherUserAddToBlokedTv.setText("Add to blocklist");
                        Toast.makeText(requireActivity(), "0 " + getInvitationsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(requireActivity(), "Techncal issue", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewDetails_box.dismiss();
    }

    private void sendChatRequest() {
//007
        myRef.child(otherUserId).child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()&& !snapshot.child("byUnfriend").exists()) {
                    if (snapshot.child("type").exists()) {
                        String type = Objects.requireNonNull(snapshot.child("type").getValue()).toString();
                        Log.d("OTHERUSER", "type1 : " + type);

                        //type 1
                        if (type.equalsIgnoreCase("1") ) {
                            otherUserRequestTV.setText("Chat");
                            Log.i("isFriend","in if" + !snapshot.child("byUnfriend").exists() +snapshot.child("byUnfriend") + " "+ type);
                            sendFriendRequestRL.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
    //                                Toast.makeText(requireContext(), "click " + otherUserId, Toast.LENGTH_SHORT).show();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("otherUserId", details.getId());
                                    bundle.putString("otherUserImg", details.getProfileImage());
                                    bundle.putString("otherUserName", details.getName());
                                    Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.messagesFragment, bundle);
                                }
                            });

                        }
                        else if (type.equalsIgnoreCase("0")) {
                            otherUserRequestTV.setText("Request Sent");
                                    sendFriendRequestRL.setClickable(false);
                            Log.i("isFriend","in elseif");
                            ChatRequestCountRef.child(otherUserId).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        requestCount = snapshot.child("count").getValue().toString();
                                        String checkRequest = snapshot.child("checkRequest").getValue().toString();

                                        if (Integer.parseInt(requestCount) <= 0) {
                                            HashMap<String, String> requestCountHp = new HashMap<>();
                                            requestCountHp.put("count", "0"); //count requests
                                            requestCountHp.put("checkRequest", "1"); //0 means request all checked 1 means not checked
                                            ChatRequestCountRef.child(otherUserId).setValue(requestCountHp);
                                        } else {

                                        }
                                    } else {
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


//                            sendFriendRequestRL.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    myRef.child(otherUserId).child(AppConstants.USER_ID).removeValue();
//                                    otherUserRequestTV.setText("Add");
//
//                                    int count = Integer.parseInt(requestCount);
//                                    count--;
//
//                                    requestCount = String.valueOf(count);
//                                    HashMap<String, String> requestCountHp = new HashMap<>();
//                                    requestCountHp.put("count", requestCount); //count requests
//                                    requestCountHp.put("checkRequest", "1"); //0 means request all checked 1 means not checked
//
//                                    ChatRequestCountRef.child(otherUserId).setValue(requestCountHp);
//                                }
//                            });
                        }
                        else {
                            Log.i("isFriend","in else");
                            otherUserRequestTV.setText("Add");
                        }
                    }
                }
                else {
                    Log.i("isFriend","in 1 else");
                    sendFriendRequestRL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String myImg = App.getSharedpref().getString("image");
                            String myName = App.getSharedpref().getString("name");
                            String dob = CommonUtils.ageCalcualte(App.getSharedpref().getString("dob"));
                            String gender = App.getSharedpref().getString("gender");


                            RequstChat sendChatRequst = new RequstChat(AppConstants.USER_ID, AppConstants.USER_ID, "0",
                                    otherUserId, details.getProfileImage(), details.getName(), myImg, myName, gender + " wants to be your friend", dob, gender, "1", "0");

                            myRef.child(otherUserId).child(AppConstants.USER_ID).setValue(sendChatRequst).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        otherUserRequestTV.setText("Request Sent");
                                        sendChatReqApi(AppConstants.USER_ID, otherUserId);

                                        ChatRequestCountRef.child(otherUserId).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()) {
                                                    requestCount = snapshot.child("count").getValue().toString();
                                                    String checkRequest = snapshot.child("checkRequest").getValue().toString();
                                                } else {

                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                        int count = Integer.parseInt(requestCount);
                                        count++;

                                        requestCount = String.valueOf(count);
                                        HashMap<String, String> requestCountHp = new HashMap<>();
                                        requestCountHp.put("count", requestCount); //count requests
                                        requestCountHp.put("checkRequest", "1"); //0 means request all checked 1 means not checked

                                        ChatRequestCountRef.child(otherUserId).setValue(requestCountHp);
                                    } else {
                                        Log.d("task", task.getException().toString());
                                    }
                                }
                            });

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    @SuppressLint("SetTextI18n")
    private void getUserDetailApi(View view) {

        App.getSharedpref().saveString("SearhOtheruserId",otherUserId);
        new Mvvm().getUserDetail(requireActivity(), AppConstants.USER_ID, otherUserId, AppConstants.USER_ID).observe(requireActivity(), getUserDetailRoot -> {
                    if (getUserDetailRoot != null) {
                        if (getUserDetailRoot.getStatus().equalsIgnoreCase("1")) {

                            String idBanned = String.valueOf(getUserDetailRoot.getDetails().isIdBannedStatus());
                            if (idBanned.equalsIgnoreCase("false")){
                                if (isAdded() && getContext()!=null){
                                    Glide.with(getContext()).load(getUserDetailRoot.getDetails().getProfileImage()).error(R.drawable.demo_user_profile_img).into(othrUserCircleImg);
                                    Glide.with(getContext()).load(getUserDetailRoot.getDetails().getProfileImage()).error(R.drawable.demo_user_profile_img).into(othrUserBgImg);
                                }
                                otherUserIdTV.setText("ID : " + getUserDetailRoot.getDetails().getUsername());
                                if (getUserDetailRoot.getDetails().familyJoinStatus){
                                    liveFamilyLlayout.setVisibility(View.VISIBLE);
                                    familyNameTv.setText(getUserDetailRoot.getDetails().getFamilyJoinName());
                                    openFamily(getUserDetailRoot.getDetails().getFamilyJoinId(),view);
                                }
                                if (getUserDetailRoot.getDetails().getArchivedTime() !=null){
                                    offlineStatusTV.setText(getUserDetailRoot.getDetails().getArchivedTime() + "ago");
                                }else {
                                    offlineStatusTV.setVisibility(View.GONE);
                                }
                            }else {
                                if (isAdded() && getContext()!=null){
                                    Glide.with(getContext()).load(R.drawable.banusericon).error(R.drawable.demo_user_profile_img).into(othrUserCircleImg);
                                    Glide.with(getContext()).load(R.drawable.banusericon).error(R.drawable.demo_user_profile_img).into(othrUserBgImg);
                                }

                                otherUserIdTV.setText("this id is banned by Admin");

                            }
                            details = new Details();
                            details = getUserDetailRoot.getDetails();
                             String frame = getUserDetailRoot.getDetails().getMyFrame();
                            setFrameONDp(frame);
                            blockStatus=getUserDetailRoot.getDetails().isBlockStatus();

                            othrUserName.setText(getUserDetailRoot.getDetails().getName());
                            othrUsrTopNameTV.setText(getUserDetailRoot.getDetails().getName());
                            countryTv.setText(getUserDetailRoot.getDetails().getCountry());
                       //     WLevelTv.setText(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel());
                            editProfileMyLvlTv.setText(getUserDetailRoot.getDetails().getLavelInfomation().getReciveLevel());
                            if (getUserDetailRoot.getDetails().getLavelInfomation().getReciveLevel().equals("0")){
                                sendingLayout.setVisibility(View.GONE);
                            }
                            //sendingLayout.setBackgroundColor(Color.parseColor(getUserDetailRoot.getDetails().getLavelInfomation().getSandColor()));
                        //    sendingLayout.getBackground().setColorFilter(Color.parseColor(getUserDetailRoot.getDetails().getLavelInfomation().getSandColor()), PorterDuff.Mode.SRC_ATOP);

                            //receivingLayout.setBackgroundColor(Color.parseColor(getUserDetailRoot.getDetails().getLavelInfomation().getReciveColor()));
//                                receivingLayout.getBackground().setColorFilter(Color.parseColor(getUserDetailRoot.getDetails().getLavelInfomation().getReciveColor()), PorterDuff.Mode.SRC_ATOP);
                            Glide.with(requireContext()).load(getUserDetailRoot.getDetails().getLavelInfomation().getReciveColor()).into(receivingLayout);

                            if(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel().isEmpty()){
                                sendingLayout.setVisibility(View.GONE);
                                WLevelTv.setText("0");
                            }else{
                                sendingLayout.setVisibility(View.VISIBLE);
                                WLevelTv.setText(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel());
                                if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())==0){
                                    sendingLayout.setVisibility(View.GONE);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=1 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=10) {
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_1);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=11 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=20) {
                                    lvlimg.setImageResource(R.drawable.badge1);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_2);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=21 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=30) {
                                    lvlimg.setImageResource(R.drawable.badge1);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_3);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=31 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=40) {
                                    lvlimg.setImageResource(R.drawable.badge2);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_4);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=41 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=50) {
                                    lvlimg.setImageResource(R.drawable.badge2);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_5);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=51 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=60) {
                                    lvlimg.setImageResource(R.drawable.badge3);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_6);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=61 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=70) {
                                    lvlimg.setImageResource(R.drawable.badge3);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_7);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=71 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=80) {
                                    lvlimg.setImageResource(R.drawable.badge3);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_8);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=81 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=90) {
                                    lvlimg.setImageResource(R.drawable.badge3);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_9);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=91 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=100) {
                                    lvlimg.setImageResource(R.drawable.badge3);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_10);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=101 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=110) {
                                    lvlimg.setImageResource(R.drawable.badge4);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_11);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=111 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=120) {
                                    lvlimg.setImageResource(R.drawable.badge4);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_12);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=121 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=130) {
                                    lvlimg.setImageResource(R.drawable.badge4);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_13);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=131 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=140) {
                                    lvlimg.setImageResource(R.drawable.badge4);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_14);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>=141 && Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())<=150) {
                                    lvlimg.setImageResource(R.drawable.badge4);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_15);
                                } else if (Integer.parseInt(getUserDetailRoot.getDetails().getLavelInfomation().getSendLevel())>150) {
                                    lvlimg.setImageResource(R.drawable.badge4);
                                    sendingLayout.setVisibility(View.VISIBLE);
                                    sendingLayout.setBackgroundResource(R.drawable.level_16);
                                }
                            }

                            otherAgeTv.setText(CommonUtils.ageCalcualte(getUserDetailRoot.getDetails().getDob()));
                            if (getUserDetailRoot.getDetails().getGender().equalsIgnoreCase("Male")||getUserDetailRoot.getDetails().getGender().equalsIgnoreCase("male")){
                                linearLayout.getBackground().setColorFilter(Color.parseColor("#0ed8a3"), PorterDuff.Mode.SRC_ATOP);
                            }else {
                                linearLayout.getBackground().setColorFilter(Color.parseColor("#fd5293"), PorterDuff.Mode.SRC_ATOP);
                            }


                            othrUsrFansTV.setText("Fans : " + getUserDetailRoot.getDetails().getFollowersCount());
                            App.getSharedpref().saveString("otherUserImg", getUserDetailRoot.getDetails().getProfileImage());


                            if (getUserDetailRoot.getDetails().getGender().equalsIgnoreCase("male")) {
                                genderImg.setImageResource(R.drawable.ic_male_gender__2_);
                                ageWithGenderImg.setImageResource(R.drawable.ic_male_gender__2_);
                            } else {
                                genderImg.setImageResource(R.drawable.femenine);
                                ageWithGenderImg.setImageResource(R.drawable.femenine);
                            }

                            bioTv.setText(getUserDetailRoot.getDetails().getBio());
                            PostsAdapter.postAdapterUserImg = getUserDetailRoot.getDetails().getProfileImage();

                            if (getUserDetailRoot.getDetails().isFollowStatus()) {
                                j = 1;
                                followingImg.setImageResource(R.drawable.ic_minus__2_);
                                followingImg.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                                othrUsrFollowingTV.setText("Following");
                            } else {
                                j = 2;
                                followingImg.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                                followingImg.setImageResource(R.drawable.ic_plus__6_);
                                othrUsrFollowingTV.setText("Follow");
                            }

                            if (getUserDetailRoot.getDetails().isLiveStatus()) {
                                if (getUserDetailRoot.getDetails().isHideStatus()) {
                                    progressImg.setVisibility(View.GONE);
                                    progressImg.cancelAnimation();
                                } else {

                                    progressImg.playAnimation();
                                    progressImg.setVisibility(View.VISIBLE);

                                    if (getUserDetailRoot.getDetails().isKickOutStatus()) {
                                        Toast.makeText(requireContext(), "Your Banned for 24 hours", Toast.LENGTH_SHORT).show();
                                    } else {

                                        progressImg.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                if (!getUserDetailRoot.getDetails().getUserLive().getPassword().equalsIgnoreCase("")) {
                                                    enterPassword(getUserDetailRoot.getDetails().getUserLive());
                                                } else {
                                                    goLive(getUserDetailRoot.getDetails().getUserLive());
                                                }
                                            }
                                        });

                                    }
                                }
                            } else {
                                progressImg.setVisibility(View.GONE);
                                progressImg.cancelAnimation();
                            }

                        } else {
                            Toast.makeText(requireContext(), "0 " + getUserDetailRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void openFamily(String familyId, View view) {

        Toast.makeText(requireContext(), "familyId :- "+familyId , Toast.LENGTH_SHORT).show();

        liveFamilyLlayout.setOnClickListener(view1 -> {
            FamilyBatchFragment.FamilyID = familyId;
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_home,new FamilyBatchFragment()).addToBackStack(null).commit();
        });


    }

    private void setFrameONDp(String frame) {
        if (getActivity() != null && getActivity().getApplicationContext() != null) {
            CommonUtils.setAnimation(requireContext(),frame,profieFrame);
        }
    }

    private void followUserApi() {

        if (details.getId() !=null && !details.getId().equalsIgnoreCase(AppConstants.USER_ID)) {

            if (details.getId() != null) {
                new Mvvm().followUsers(requireActivity(), AppConstants.USER_ID, details.getId(),"follow").observe(requireActivity(), new Observer<SendOtpRoot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onChanged(SendOtpRoot sendOtpRoot) {

                        if (sendOtpRoot.getStatus().equalsIgnoreCase("1")) {
                            Toast.makeText(requireContext(), "1 " + sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();

                            if (j == 2) {
                                followingImg.setImageResource(R.drawable.ic_minus__2_);
                                followingImg.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                                othrUsrFollowingTV.setText("Following");
                                j = 1;

                            } else {
                                j = 2;
                                followingImg.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                                followingImg.setImageResource(R.drawable.ic_plus__6_);
                                othrUsrFollowingTV.setText("Follow");
                            }
                        } else {
                        }
                    }
                });
            } else {
            }
        }
    }

    public void tabLayoutMethod() {

        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Moments"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Detailed Profile"));
        tablayout_ExploreScreen.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter pagerAdapter = new OtherUser.Adaptor(getChildFragmentManager(), tablayout_ExploreScreen.getTabCount());
        viewPager_ES.setAdapter(pagerAdapter);
        viewPager_ES.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout_ExploreScreen));
        tablayout_ExploreScreen.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_ES));
    }

    @Override
    public void bgCallback(String image) {
        if (image.equalsIgnoreCase("1")) {
            Bundle bundle = new Bundle();
            bundle.putString("getImageUserId",otherUserId);
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.imageSlideFragment,bundle);
        }
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
//                    return new OtherUserProfileTab();
                    return new Fragment_Moments();
                case 1:
//                    return new OtherUserGloryTab();
                    return  Fragment_Detailed_profile.newInstance( App.getSharedpref().getString("SearhOtheruserId"));

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return totalCount;
        }

    }

    private void getImagesApi() {
        new Mvvm().getUserImages(requireActivity(), otherUserId).observe(requireActivity(), new Observer<GetUserImagesRoot>() {
            @Override
            public void onChanged(GetUserImagesRoot getUserImagesRoot) {
                if (getUserImagesRoot != null) {
                    if (getUserImagesRoot.getStatus() == 1) {
                        bgSliderImgesList = getUserImagesRoot.getDetails();

                        if (bgSliderImgesList.isEmpty()) {
                            othrUserBgImg.setVisibility(View.VISIBLE);
                            sliderView.setVisibility(View.GONE);
                        } else {
                            othrUserBgImg.setVisibility(View.GONE);
                            sliderView.setVisibility(View.VISIBLE);
                            if (getActivity() != null){
                                adapter = new SliderAdapterExample(getActivity().getApplicationContext(), bgSliderImgesList, OtherUser.this);

                            }
                            sliderView.setSliderAdapter(adapter);
                            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                            sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
                            sliderView.setIndicatorMargin(65);
                        }
                    }
                }

            }
        });
    }


    private void backPressed(View view) {

        view.requestFocus();

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (i == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void goLive(Details.UserLive userLive) {

        App.getSingletone().setLiveType("");
        Intent intent = new Intent(requireActivity(), CallActivity.class);
        intent.putExtra("channelName", userLive.getChannelName());
        intent.putExtra("userId", userLive.getId());

        intent.putExtra("liveHostIds", userLive.getId());
        intent.putExtra("liveType", "multiLive");
        intent.putExtra("liveStatus", "host");
        intent.putExtra("token", userLive.getToken());
        intent.putExtra("name", details.getName());
        intent.putExtra("liveHostId", userLive.getId());

        if (userLive.getLiveimage().isEmpty()) {
            intent.putExtra("image", details.getProfileImage());
        } else {
            intent.putExtra("image", userLive.getLiveimage());
        }
        intent.putExtra("status", "1");
        startActivity(intent);
    }

    private void enterPassword(Details.UserLive userLive) {

        Dialog dialog_share = new Dialog(requireContext());
        dialog_share.setContentView(R.layout.set_room_password_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);

        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        OtpTextView liveLock = dialog_share.findViewById(R.id.liveLock_view);
        AppCompatButton confirm_pin = dialog_share.findViewById(R.id.confirm_pin);
        AppCompatButton cancel = dialog_share.findViewById(R.id.cancle_pin);
        confirm_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (liveLock.getOTP().equalsIgnoreCase(userLive.getPassword())) {
                    goLive(userLive);
                    dialog_share.dismiss();
                } else {
                    Toast.makeText(requireContext(), "Wrong Pin Entered", Toast.LENGTH_SHORT).show();
                    dialog_share.dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_share.dismiss();
            }
        });
        dialog_share.show();

    }

    private void sendChatReqApi(String userId, String otherUserId) {
        new Mvvm().sendChatRequest(requireActivity(), userId, otherUserId).observe(requireActivity(), new Observer<FirebaseSendReqRoot>() {
            @Override
            public void onChanged(FirebaseSendReqRoot firebaseSendReqRoot) {

                if (firebaseSendReqRoot != null) {
                    if (firebaseSendReqRoot.getSuccess() == 1) {
                        if (getContext() != null) {
                            Toast.makeText(requireContext(), "Request Sent", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                    }

                } else {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void isFriendOrNot() {

        friendReqRef = database.getReference("ChatRequest");

        if (!otherUserId.equalsIgnoreCase(AppConstants.USER_ID)) {
            friendReqRef.child(otherUserId).child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        if (snapshot.hasChild("toFriendStatus")) {
                            String isFriend = snapshot.child("toFriendStatus").getValue().toString(); // 0 means no friend // 1 means friend hai
                            isFriendOrNOt = isFriend.equalsIgnoreCase("1");
                        }
                    } else {

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}
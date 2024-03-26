package com.live.worldsocialintegrationapp.Fragments.Profile.OtherUserTabs;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Debug;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.live.worldsocialintegrationapp.Adapters.FamilyLiveRoomRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.familyMembersRVAdapter;
import com.live.worldsocialintegrationapp.BottomFragment;
import com.live.worldsocialintegrationapp.Fragments.Home.Related.SearchUsers;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetFamilyDetailsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetInvitationsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetLiveFamilyJoinersRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.Joiner;
import com.live.worldsocialintegrationapp.ModelClasses.GetFamilyDetails;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import in.aabhasjindal.otptextview.OtpTextView;

public class FamilyBatchFragment extends Fragment implements familyMembersRVAdapter.Callback, FamilyLiveRoomRVAdapter.Callback, BottomFragment.OnBottomSheetDismissListener {
    private RecyclerView familyMemberRv, familyLiveRoomRV;
    private AppCompatButton joinFamilyBtn;
    private familyMembersRVAdapter familyMembersRVAdapter;
    private ImageView familyBatchBackImg, familyBatchImg, leaveFamilyImg, familyInvitationImg,batchClick,backgroundImage;
    private String familyId;
    public static String familyID;
    public static String FamilyID;
    public static String FamilyJoinedID;
    private TextView familyBatchFamilyName,currentLevelLowerBound,currentLevelUpperBound;
    private TextView FamilyDescriptionTv;
    private TextView familyMembersCountTv;
    private TextView familyid,reqestCountTv,totalreciveCoin;
    private List<Joiner> list;
    private List<Joiner> allMembers;
    private List<GetLiveFamilyJoinersRoot> listt;
    private RelativeLayout familyMemberRL;
    public static int mainProfileClick = 0;
    private List<GetLiveFamilyJoinersRoot.Detail> liveJoinersList;
    private CircleImageView familyMemberCirImg;
    private String leaderId,isAdmin,reqestCount,totalrecive,currentFamilyLevel,totalExp;

    private int familyLevel;
    private ImageView editFamily;
    int status;
    private String editleaderId;

    private ProgressBar bar;

    private Mvvm mvvm;
    private boolean familyJoinStatuss,leader = false;

    private String  isFamilyMember,isFamilyLeader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mvvm = new ViewModelProvider(this).get(Mvvm.class);
        return inflater.inflate(R.layout.fragment_family_batch, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        App.getSharedpref().saveString("liveFamily", "");
        findIds(view);
        hitApiGetUserDetails();
        clicks(view);
        view.findViewById(R.id.familyInvitationImg).setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.familyInvitationsFragment));
        getDataFromBundle(view);
        getFamilyDetails();
        getFamilyLiveJoinersApi();
        setStatusBarGradiant(requireActivity());

        if (getArguments() != null) {
            Bundle bundle = getArguments();
        }
//        if (getArguments() != null) {
//            Bundle bundle = getArguments();
//            String leaderID = bundle.getString("leaderId");
//            if (AppConstants.USER_ID.equalsIgnoreCase(leaderID)) {
//                Log.d("LeaderStatus","in if statement"+AppConstants.USER_ID);
//                leaveFamilyImg.setVisibility(View.GONE);
//                editFamily.setVisibility(View.VISIBLE);
//                editFamily.setOnClickListener(v -> {
//                    status = 1;
//                    Bundle bundle12 = new Bundle();
//                    bundle12.putString("status", "1");
//                    Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.createFamilyFragment, bundle12);
//                });
//            }
//            else {
//                Log.d("LeaderStatus","in else statement"+AppConstants.USER_ID);
//                Log.d("LeaderStatus","in else statement"+leaderID);
//                editFamily.setVisibility(View.GONE);
//            }
//        }

        familyMemberCirImg.setOnClickListener(v -> {
            if (leaderId != null) {
                if(Objects.equals(familyId, FamilyJoinedID)){
                    Log.i("Familyyyy","in if iffff");
                    Bundle bundle = new Bundle();
                    bundle.putInt("value_check",2);
//                Fragment_Moments.check_value = 2;
                    Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.editProfileMomentsFragment, bundle);
                }
                else {
                    Log.i("Familyyyy","in if else");
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("otherUserId", leaderId);
                    Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle1);
                }

            } else {
                if (getContext() != null) {
                    Log.i("Familyyyy","else iffff");
                    //Toast.makeText(requireContext(), "userId not found", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("Familyyyy","else elseeee");
                }
            }
        });
        Log.d("CHECKERROR", "1");
    }

    private void hitApiGetUserDetails() {
        mvvm.getUserDetail(requireActivity(), AppConstants.USER_ID, AppConstants.USER_ID, AppConstants.USER_ID).observe(requireActivity(), response -> {
            if (response != null) {
                if (response.getStatus().equalsIgnoreCase("1")) {
                    familyJoinStatuss = response.getDetails().familyJoinStatus;
                    FamilyJoinedID = response.getDetails().familyJoinId;
                    isFamilyMember = response.getDetails().isFamilyMember;
                    isFamilyLeader = response.getDetails().isFamilyLeader;

                }
            }
        });
    }

    private void getFamilyLiveJoinersApi() {

        Log.d("getFamilyLiveJoinersApi", "getFamilyLiveJoinersApi: " + familyId);
        Log.d("getFamilyLiveJoinersApi", "getFamilyLiveJoinersApi: " + AppConstants.USER_ID);
        mvvm.getFamilyLiveJoiners(requireActivity(), familyId, AppConstants.USER_ID, AppConstants.USER_ID).observe(requireActivity(), getLiveFamilyJoinersRoot -> {
            if (getLiveFamilyJoinersRoot != null) {
                if (getLiveFamilyJoinersRoot.getSuccess().equalsIgnoreCase("1")) {
                    liveJoinersList = getLiveFamilyJoinersRoot.getDetails();
                    if (liveJoinersList.isEmpty()) {
                        Log.i("FamilyDetails","Joiner list is empty");
                    } else {
                        FamilyLiveRoomRVAdapter familyLiveRoomRVAdapter = new FamilyLiveRoomRVAdapter(liveJoinersList, requireContext(), FamilyBatchFragment.this);
                        familyLiveRoomRV.setAdapter(familyLiveRoomRVAdapter);
                    }
                } else {
                    Log.i("FamilyDetails","response in 0");
                }
            } else {
                Log.i("FamilyDetails","response in null");
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void joinFamilyApi() {
        joinFamilyBtn.setOnClickListener(view -> {
            mvvm.sendJoinRequest(requireActivity(), AppConstants.USER_ID, familyId).observe(requireActivity(), getInvitationsRoot -> {
                if (getInvitationsRoot.getStatus() == 1) {
                    if(Objects.equals(isFamilyLeader, "1")){
                        //joinFamilyBtn.setText("Invited");
                    }
                    else{
                        joinFamilyBtn.setText("Requested");
                        Toast.makeText(requireContext(), getInvitationsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(requireContext(), getInvitationsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void sendInvitationApi() {
        joinFamilyBtn.setOnClickListener(view -> {
            //family owner send join to user
            SearchUsers.familyJoinCheck = 1;
            SearchUsers.familyId = familyId;
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.searchUsers);
        });
    }

    private void getDataFromBundle(View view) {
        if (getArguments() != null) {
            familyId = getArguments().getString("familyId");
        }
    }

    private void findIds(View view) {
        familyMemberRv = view.findViewById(R.id.familyMemberRv);
        joinFamilyBtn = view.findViewById(R.id.joinFamilyBtn);
        familyBatchBackImg = view.findViewById(R.id.familyBatchBackImg);
        familyBatchFamilyName = view.findViewById(R.id.familyBatchFamilyName);
        FamilyDescriptionTv = view.findViewById(R.id.FamilyDescriptionTv);
        familyMembersCountTv = view.findViewById(R.id.familyMembersCountTv);
        familyBatchImg = view.findViewById(R.id.familyBatchImg);
        backgroundImage = view.findViewById(R.id.backgroundImage);
        leaveFamilyImg = view.findViewById(R.id.leaveFamilyImg);
        familyMemberRL = view.findViewById(R.id.familyMemberRL);
        familyLiveRoomRV = view.findViewById(R.id.familyLiveRoomRV);
        familyid = view.findViewById(R.id.t1);
        reqestCountTv = view.findViewById(R.id.reqestCountTv);
        familyMemberCirImg = view.findViewById(R.id.familyMemberCirImg);
        editFamily = view.findViewById(R.id.editFamily);
        familyInvitationImg = view.findViewById(R.id.familyInvitationImg);
        batchClick = view.findViewById(R.id.batchClick);
        totalreciveCoin = view.findViewById(R.id.totalreciveCoin);
        bar = view.findViewById(R.id.WealthprogressBar);
        currentLevelLowerBound = view.findViewById(R.id.currentLevelLowerBoundTv);
        currentLevelUpperBound = view.findViewById(R.id.currentLevelUpperBoundTv);
    }


    private void clicks(View view) {
        view.findViewById(R.id.batchClick).setOnClickListener(view12 -> Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.clickBatchFragment));
        view.findViewById(R.id.familyBatchQuestion).setOnClickListener(view13 -> Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.familyRulesFragment));
        familyBatchBackImg.setOnClickListener(view1 -> requireActivity().onBackPressed());
        familyMemberRL.setOnClickListener(view14 -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("joinerList", (Serializable) allMembers);
            bundle.putBoolean("admin",leader);
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.familyMembersFragment, bundle);
        });
    }

    @Override
    public void callback(Joiner joiner, int pos) {
        BottomFragment bottomFragment = new BottomFragment(joiner.getUserId(), joiner.getStatus(),joiner.getFamilyId(),leader,joiner.getIs_admin());
        bottomFragment.setOnBottomSheetDismissListener(this);
        bottomFragment.show(requireActivity().getSupportFragmentManager(), bottomFragment.getTag());
    }


    @SuppressLint("SetTextI18n")
    private void getFamilyDetails() {
        String fid;
        if(familyId != null){
             fid = familyId;
        }
        else{
            fid =FamilyID;
        }
        //Toast.makeText(requireContext(), "fid "+fid, Toast.LENGTH_SHORT).show();
        mvvm.getFamilyDetails(requireActivity(), fid, AppConstants.USER_ID).observe(requireActivity(), getFamilyDetailsRoot -> {
            if (getFamilyDetailsRoot != null) {
                if (getFamilyDetailsRoot.getSuccess().equalsIgnoreCase("1")) {

                    familyid.setText("ID:" + getFamilyDetailsRoot.getDetails().getUniqueId());
                    App.getSharedpref().saveString("leaderId", getFamilyDetailsRoot.getDetails().getLeaderId());
                    App.getSharedpref().saveString("id", getFamilyDetailsRoot.getDetails().getId());
                    Glide.with(familyMemberCirImg.getContext()).load(getFamilyDetailsRoot.getDetails().getLeaderImage()).error(R.drawable.demo_user_profile_img).into(familyMemberCirImg);

                    leaderId = getFamilyDetailsRoot.getDetails().getLeaderId();
                    list = new ArrayList<>();
                    allMembers = new ArrayList<>();
                    reqestCount= String.valueOf(getFamilyDetailsRoot.getDetails().getRequest_count());

                    totalrecive = String.valueOf(getFamilyDetailsRoot.getDetails().getTotalRecieving());
                    totalExp = String.valueOf(getFamilyDetailsRoot.getDetails().getTotalExp());
                    bar.setMax(100);
                    currentFamilyLevel = String.valueOf(getFamilyDetailsRoot.getDetails().getFamilyLevel());
                    familyLevel = getFamilyDetailsRoot.getDetails().getFamilyLevel();

                    if(familyLevel >0){
                        apiHit();
                    }
                    else {
                        familyLevel = 1;
                        apiHit();
                    }

                    currentLevelLowerBound.setText(currentFamilyLevel);
                    int upperLevelBoundString =getFamilyDetailsRoot.getDetails().getFamilyLevel() +1;
                    currentLevelUpperBound.setText(String.valueOf(upperLevelBoundString));

                    Integer totalRequiredExperienceInt = Integer.parseInt(totalExp);
                    int currentExpInt = Integer.parseInt(totalrecive);
                    //int currentExp = currentExpInt - totalRequiredExperienceInt;
                    Log.d("checkExp", String.valueOf(totalRequiredExperienceInt));
                    Log.d("checkExp", leaderId);
                    Log.d("checkExp", String.valueOf(totalrecive));
                    Log.d("checkExp", String.valueOf(currentFamilyLevel));
                    int currentLevelProgress = (currentExpInt * 100) / totalRequiredExperienceInt;
                    Log.d("checkExp", String.valueOf(currentLevelProgress));

                    bar.setProgress(currentLevelProgress);

                    totalreciveCoin.setText(totalrecive +"/"+totalRequiredExperienceInt);

                    if(currentExpInt == totalRequiredExperienceInt){
                        bar.setProgress(100);
                    }

                    list = getFamilyDetailsRoot.getDetails().getJoiner();
                    allMembers = getFamilyDetailsRoot.getDetails().getAllMembers();
                    leader = getFamilyDetailsRoot.getDetails().admin;
                    if (getFamilyDetailsRoot.getDetails().admin){
                        familyInvitationImg.setVisibility(View.VISIBLE);
                        if(!Objects.equals(reqestCount, "0")){
                            reqestCountTv.setVisibility(View.VISIBLE);
                            reqestCountTv.setText(reqestCount);
                        }

                    }
                    if (list.isEmpty()) {
                            Log.i("List","List is empty");
                    } else {
                        requireActivity();
                        requireContext();
                        try {
                            setAdapter(list);
                        } catch (Exception ignored) {

                        }
                    }

                    Glide.with(familyBatchImg.getContext()).load(getFamilyDetailsRoot.getDetails().getImage()).error(R.drawable.demo_user_profile_img).into(familyBatchImg);
                    familyBatchFamilyName.setText(getFamilyDetailsRoot.getDetails().getFamilyName());
                    FamilyDescriptionTv.setText(getFamilyDetailsRoot.getDetails().getDescription());

                    /// Note: Updated members to int and getMembers() return to int
                    int currentMembers = getFamilyDetailsRoot.getDetails().getMembers();


                    int newMembers = currentMembers;

                    Log.i("FamilyStatus z", String.valueOf(familyJoinStatuss));
                    Log.i("FamilyStatus z", String.valueOf(getFamilyDetailsRoot.getDetails().isFamily_create_status()));

                    Log.i("FamilyStatus z", familyId + "familyId");
                    Log.i("FamilyStatus z", FamilyJoinedID + "FamilyIDdddd");
                    Log.i("FamilyStatus z", isFamilyMember + "Member?");
                    Log.i("FamilyStatus z", isFamilyLeader + "Leader?");



                    familyMembersCountTv.setText("Family Members " + newMembers  + "/500");

//                    if(Objects.equals(familyId, FamilyJoinedID)){
//                        Log.i("FamilyStatus","First if");
//                        leaveFamilyImg.setVisibility(View.VISIBLE);
//                        joinFamilyBtn.setVisibility(View.VISIBLE);
//                        joinFamilyBtn.setText("Invite");
//                    }
//                    else if(!Objects.equals(isFamilyMember, "1") && !Objects.equals(isFamilyLeader, "1")){
//                        Log.i("FamilyStatus","First Else if");
//                        joinFamilyBtn.setVisibility(View.VISIBLE);
//                        leaveFamilyImg.setVisibility(View.GONE);
//                        joinFamilyBtn.setText("Join");
//                    }
//                    else {
//                        Log.i("FamilyStatus","First else");
//
//                        if (!familyJoinStatuss) {
//                            leaveFamilyImg.setVisibility(View.GONE);
//                            joinFamilyBtn.setVisibility(View.VISIBLE);
//                            Log.i("FamilyStatus","In First else 1");
//                        }
//                        else{
//                            joinFamilyBtn.setVisibility(View.GONE);
//                            leaveFamilyImg.setVisibility(View.GONE);
//                            //joinFamilyBtn.setText("Join");
//                            Log.i("FamilyStatus","In First else 2");
//                        }
//                    }
//                    //condition to check if user is family leader to show edit/leave button
//                    if (Objects.equals(isFamilyLeader, "1") &&  Objects.equals(familyId, FamilyJoinedID)) {
//                        Log.i("FamilyStatus","In Second IF");
//                        leaveFamilyImg.setVisibility(View.GONE);
//                        editFamily.setVisibility(View.VISIBLE);
//                        editFamily.setOnClickListener(v -> {
//                            status = 1;
//                            Bundle bundle12 = new Bundle();
//                            bundle12.putString("status", "1");
//                            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.createFamilyFragment, bundle12);
//                        });
//                    }
//                    else {
//                        Log.i("FamilyStatus","In Second Else");
//                        editFamily.setVisibility(View.GONE);
//                        joinFamilyBtn.setVisibility(View.GONE);
//                    }

                    if(familyJoinStatuss){
                        Log.i("FamilyStatus","In First IF");
                        if(Objects.equals(isFamilyLeader, "1")){
                            Log.i("FamilyStatus","In First IF IF");
                            if(Objects.equals(familyId, FamilyJoinedID)){
                                Log.i("FamilyStatus","In First IF IF IF");
                                joinFamilyBtn.setVisibility(View.VISIBLE);
                                editFamily.setVisibility(View.VISIBLE);
                                editFamily.setOnClickListener(v -> {
                                    status = 1;
                                    Bundle bundle12 = new Bundle();
                                    bundle12.putString("status", "1");
                                    Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.createFamilyFragment, bundle12);
                        });
                                leaveFamilyImg.setVisibility(View.GONE);
                            }
                            else{
                                Log.i("FamilyStatus","In First IF IF Else");
                                joinFamilyBtn.setVisibility(View.GONE);
                                editFamily.setVisibility(View.GONE);
                                leaveFamilyImg.setVisibility(View.GONE);
                            }
                        }
                        else{
                            Log.i("FamilyStatus","In First IF Else");
                            if(Objects.equals(familyId, FamilyJoinedID)){
                                Log.i("FamilyStatus","In First IF Else IF");
                                joinFamilyBtn.setVisibility(View.GONE);
                                editFamily.setVisibility(View.GONE);
                                leaveFamilyImg.setVisibility(View.VISIBLE);
                            }
                           else{
                                Log.i("FamilyStatus","In First IF Else Else");
                                joinFamilyBtn.setVisibility(View.GONE);
                                editFamily.setVisibility(View.GONE);
                                leaveFamilyImg.setVisibility(View.GONE);
                            }
                        }

                    }
                    else{
                        Log.i("FamilyStatus","In First ELSE");

                        if(Objects.equals(familyId, FamilyJoinedID)){
                            Log.i("FamilyStatus","In First Else IF");
                            joinFamilyBtn.setVisibility(View.GONE);
                            editFamily.setVisibility(View.GONE);
                            leaveFamilyImg.setVisibility(View.GONE);
                        }
                        else{
                            Log.i("FamilyStatus","In First Else ELSE");
                            joinFamilyBtn.setVisibility(View.VISIBLE);
                            editFamily.setVisibility(View.GONE);
                            leaveFamilyImg.setVisibility(View.GONE);
                            joinFamilyBtn.setText("Join");
                        }

                    }


                    if (getFamilyDetailsRoot.getDetails().isFamily_create_status()) {
                        Log.i("FamilyStatus","In Third IF");
                        joinFamilyBtn.setText("Invite");
                        //leaveFamilyImg.setVisibility(View.VISIBLE);
                        sendInvitationApi();
                    }
                    else {
                        Log.i("FamilyStatus","In Third else");
                        joinFamilyApi();
                        leaveFamilyDialogBox();
                    }
                }
                }
            else {
                //Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
         });
    }

    private void setAdapter(List<Joiner> list) {
        familyMembersRVAdapter = new familyMembersRVAdapter(list, requireContext(), FamilyBatchFragment.this);
        familyMemberRv.setAdapter(familyMembersRVAdapter);

    }

    private void leaveFamilyDialogBox() {
        leaveFamilyImg.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setMessage("Are you sure you want to leave this family?");
            builder.setTitle("Alert!");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (dialog, which) -> {
                leaveFamilyApi();
                dialog.dismiss();
            });
            builder.setNegativeButton("No", (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

    }

    private void leaveFamilyApi() {
        Log.d("leaveFamilyApi", "USER_ID: "+AppConstants.USER_ID);
        Log.d("leaveFamilyApi", "familyId: "+familyId);
        mvvm.leaveFamily(requireActivity(), AppConstants.USER_ID,familyId).observe(requireActivity(), getFamilyDetailsRoot -> {
            if (getFamilyDetailsRoot != null) {
                if (getFamilyDetailsRoot.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(requireContext(), "1 :-" + getFamilyDetailsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    requireActivity().onBackPressed();
                } else {
                    Toast.makeText(requireContext(), "0 :-"+getFamilyDetailsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                if (getContext() != null) {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void liveJoinerClick(GetLiveFamilyJoinersRoot.Detail detail, int pos) {
        startLive(detail, pos);
    }

    @Override
    public void enterPassword(GetLiveFamilyJoinersRoot.Detail detail, int pos) {
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
        confirm_pin.setOnClickListener(view -> {
            if (liveLock.getOTP().equalsIgnoreCase(detail.getPassword())) {
                startLive(detail, pos);
                dialog_share.dismiss();
            } else {
                Toast.makeText(requireContext(), "Wrong Pin Entered", Toast.LENGTH_SHORT).show();
                dialog_share.dismiss();
            }
        });

        cancel.setOnClickListener(view -> dialog_share.dismiss());
        dialog_share.show();
    }

    private void startLive(GetLiveFamilyJoinersRoot.Detail detail, int pos) {
        if (detail != null) {
            App.getSingletone().setLiveType("");
            Intent intent = new Intent(requireActivity(), CallActivity.class);
            intent.putExtra("channelName", detail.getChannelName());
            intent.putExtra("userId", detail.getUserId());
            intent.putExtra("liveHostIds", detail.getUserId());
            intent.putExtra("liveType", "multiLive");
            intent.putExtra("liveStatus", "host");
            intent.putExtra("token", detail.getToken());
            intent.putExtra("name", detail.getName());
            intent.putExtra("status", "1");
            intent.putExtra("count", detail.getLiveCount());
            if (detail.getImageTitle() != null && !detail.getImageTitle().isEmpty()) {
                intent.putExtra("broadTitle", detail.getImageTitle());
            } else {
                intent.putExtra("broadTitle", detail.getName());
            }
            intent.putExtra("liveImage", detail.getLiveimage());
            intent.putExtra("image", detail.getImageDp());
            startActivity(intent);
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            @SuppressLint("UseCompatLoadingForDrawables")
            Drawable background = activity.getResources().getDrawable(R.drawable.themee);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.darker_gray));
            window.setBackgroundDrawable(background);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("ONResumee","in on resume");
        setStatusBarGradiant(requireActivity());
    }

    @Override
    public void onBottomSheetDismissed() {
        getFamilyDetails();
        setAdapter(list);
    }

    private void apiHit() {
        new Mvvm().getFamilyDetailsData(requireActivity(), familyLevel).observe(requireActivity(), new Observer<GetFamilyDetails>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(GetFamilyDetails getFamilyDetails) {
                try {
                    if (getFamilyDetails != null) {
                        if (getFamilyDetails.getStatus() == 1) {
                            batchClick.setVisibility(View.VISIBLE);
                            Glide.with(batchClick.getContext()).load(getFamilyDetails.getDetails().get(0).getMainImage()).error(R.drawable.demo_user_profile_img).into(batchClick);
                            RequestOptions options = new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true);
                            Glide.with(batchClick.getContext())
                                    .load(getFamilyDetails.getDetails().get(0).getExclusiveBackground())
                                    .apply(options)
                                    .error(R.drawable.demo_user_profile_img) // Placeholder image in case of error
                                    .into(backgroundImage);

//                            Glide.with(getContext())
//                                    .load(getFamilyDetails.getDetails().get(0).getExclusiveBackground())
//                                    .error(R.drawable.demo_user_profile_img) // Optional error image
//                                    .listener(new RequestListener<Drawable>() {
//                                        @Override
//                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
//                                            backgroundImage.setBackground(resource);
//                                            return false;
//                                        }
//
//                                        @Override
//                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                            // Handle loading failure (optional)
//                                            return false;
//                                        }
//                                    })
//                                    .into(backgroundImage);
//                            Glide.with(getContext())
//                                    .load(getFamilyDetails.getDetails().get(0).getExclusiveBackground())
//                                    .listener(new RequestListener<Drawable>() {
//                                        @Override
//                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
//                                            int imageWidth = resource.getIntrinsicWidth();
//                                            int imageHeight = resource.getIntrinsicHeight();
//                                            Log.d("Image Size", "Width: " + imageWidth + ", Height: " + imageHeight);
//
//                                            backgroundImage.setBackground(resource);
//                                            return false;
//                                        }
//                                        @Override
//                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                            // Handle loading failure (optional)
//                                            return false;
//                                        }
//                                    })
//                                    .into(backgroundImage);

                        } else {
                            Toast.makeText(requireActivity(), "" + getFamilyDetails.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                    }
                } catch (Exception e) {

                }

            }
        });
    }
}
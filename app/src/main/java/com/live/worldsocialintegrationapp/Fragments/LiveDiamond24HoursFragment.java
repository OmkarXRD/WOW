package com.live.worldsocialintegrationapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.live.worldsocialintegrationapp.Adapters.LiveDiamondsRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GetLiveDiamondRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.Details;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.ProfileBottomSheetBinding;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.List;


public class LiveDiamond24HoursFragment extends Fragment implements LiveDiamondsRVAdapter.Callback{

    private RecyclerView liveDiamond24HorsHistoryRv;
    private TextView live24hoursDaimondsTv,liveDiamondsNoFoundTV;
    private  String liveId;
    private List<GetLiveDiamondRoot.Detail> list;
    private LiveDiamondsRVAdapter liveDiamondsRVAdapter;
    private Mvvm mvvm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mvvm = new ViewModelProvider(this).get(Mvvm.class);
        return inflater.inflate(R.layout.fragment_live_diamond24_hours, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);

        if(getArguments() !=  null){
            liveId = getArguments().getString("liveId");
//            Toast.makeText(requireContext(), "24hosrs live "+liveId, Toast.LENGTH_SHORT).show();
        }else{
//            Toast.makeText(requireContext(), "getArgment null", Toast.LENGTH_SHORT).show();
        }
        getLiveDiamondsApi();

    }

    private void getLiveDiamondsApi() {

        mvvm.getLiveDiamonds(requireActivity(),liveId,"1").observe(requireActivity(), new Observer<GetLiveDiamondRoot>() {
            @Override
            public void onChanged(GetLiveDiamondRoot getLiveDiamondRoot) {
                if(getLiveDiamondRoot != null){
                    if(getLiveDiamondRoot.getSuccess().equalsIgnoreCase("1")){
                        list = getLiveDiamondRoot.getDetails();
                        live24hoursDaimondsTv.setText(getLiveDiamondRoot.getTotal());
                        if(list.isEmpty()){
                            liveDiamondsNoFoundTV.setVisibility(View.VISIBLE);
                        }else{
                            liveDiamondsNoFoundTV.setVisibility(View.GONE);
                            liveDiamondsRVAdapter=new LiveDiamondsRVAdapter(requireContext(),list,LiveDiamond24HoursFragment.this);
                            liveDiamond24HorsHistoryRv.setAdapter(liveDiamondsRVAdapter);
                        }
                    }else{
                        liveDiamondsNoFoundTV.setVisibility(View.VISIBLE);
                    }
                }else{
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findIds(View view) {
        liveDiamond24HorsHistoryRv=view.findViewById(R.id.liveDiamond24HorsHistoryRv);
        live24hoursDaimondsTv=view.findViewById(R.id.live24hoursDaimondsTv);
        liveDiamondsNoFoundTV=view.findViewById(R.id.liveDiamondsNoFoundTV);

    }

    @Override
    public void userInfo(GetLiveDiamondRoot.Detail detail) {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        ProfileBottomSheetBinding profileBottomSheetBinding = ProfileBottomSheetBinding.inflate(LayoutInflater.from(requireContext()));
        bottomSheetDialog.setContentView(profileBottomSheetBinding.getRoot());
        bottomSheetDialog.show();
        setData(detail.getSenderId(),profileBottomSheetBinding);
        profileBottomSheetBinding.sendGiftsBottomSheetLl.setOnClickListener(view -> openGiftDialog());
//        profileBottomSheetBinding.inviteUserLineayout.setOnClickListener(view -> inviteAudienceRef.child(detail.getReceiverId()).setValue("0"));
        profileBottomSheetBinding.otherUserDialogOpenProfileRL.setOnClickListener(view -> {});
        profileBottomSheetBinding.llProfile.setOnClickListener(view1 -> Toast.makeText(requireContext(), "coming soon", Toast.LENGTH_SHORT).show());
        profileBottomSheetBinding.llBlock.setOnClickListener(view1 -> Toast.makeText(requireContext(), "coming soon", Toast.LENGTH_SHORT).show());

    }

    private void openGiftDialog() {}

    private void setData(String receiverId, ProfileBottomSheetBinding profileBottomSheetBinding) {

        mvvm.getUserDetail(requireActivity(), AppConstants.USER_ID,receiverId,receiverId).observe(requireActivity(), response ->{
            if (response !=null){
                if (response.getStatus().equalsIgnoreCase("1")){
                    getData(response.getDetails(),profileBottomSheetBinding);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void getData(Details details, ProfileBottomSheetBinding profileBottomSheetBinding) {

        if (details !=null  &&  profileBottomSheetBinding !=null){
            profileBottomSheetBinding.progress.setVisibility(View.GONE);
            profileBottomSheetBinding.txtName.setText(details.getName());
            Glide.with(requireContext()).load(details.getImage()).error(R.drawable.wowicon).into(profileBottomSheetBinding.imgProfile);
            profileBottomSheetBinding.userIdAndCountry.setText("ID : "+details.getUsername()+" | "+details.getCountry());
            profileBottomSheetBinding.bottomProfileAgeTv.setText(details.getAge());
            Glide.with(requireContext()).load(details.getLavelInfomation().getReciveColor()).error(R.drawable.badge3).into(profileBottomSheetBinding.receivingLayout);
            profileBottomSheetBinding.receivingLvl.setText(details.getLavelInfomation().getReciveLevel());
            if(details.getLavelInfomation().getSendLevel().isEmpty()){
                profileBottomSheetBinding.sendingLayout.setVisibility(View.GONE);
                profileBottomSheetBinding.sendingLvl.setText("0");
            }else{
                profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                profileBottomSheetBinding.sendingLvl.setText(details.getLavelInfomation().getSendLevel());
                if (Integer.parseInt(details.getLavelInfomation().getSendLevel())==0){
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.GONE);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=1 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=10) {
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_1);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=11 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=20) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge1);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_2);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=21 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=30) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge1);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_3);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=31 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=40) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge2);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_4);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=41 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=50) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge2);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_5);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=51 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=60) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge3);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_6);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=61 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=70) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge3);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_7);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=71 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=80) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge3);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_8);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=81 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=90) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge3);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_9);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=91 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=100) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge3);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_10);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=101 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=110) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge4);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_11);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=111 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=120) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge4);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_12);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=121 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=130) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge4);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_13);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=131 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=140) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge4);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_14);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>=141 && Integer.parseInt(details.getLavelInfomation().getSendLevel())<=150) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge4);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_15);
                } else if (Integer.parseInt(details.getLavelInfomation().getSendLevel())>150) {
                    profileBottomSheetBinding.lvlimg.setImageResource(R.drawable.badge4);
                    profileBottomSheetBinding.sendingLayout.setVisibility(View.VISIBLE);
                    profileBottomSheetBinding.sendingLayout.setBackgroundResource(R.drawable.level_16);
                }
            }
        }else {
            profileBottomSheetBinding.progress.setVisibility(View.VISIBLE);
        }

    }

}
package com.live.worldsocialintegrationapp.Fragments.Home;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection.EditProfileMomentsFragment;
import com.live.worldsocialintegrationapp.Fragments.Profile.Fragment_Moments;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.Details;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.GetUserDetailRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.ModelClasses.UsersSearch.Detail;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;


public class UserProfile extends Fragment {

    private ImageView userProfileBackImg,userImg;
    private TextView otherUserFansTV,otherUserFollowingTV,otherUserDiamondsTV,otherUserSendTV,otherUserIdTV,otherUserNameTV,userFollowTV,profileMomentTV;
    private LinearLayout followUserLlayout,userProfileMsglLyout;
    private ImageView followPlusImg;
    private List<Detail> list = new ArrayList<>();
    private int listPoisition;
    Details details;
    int j=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        getData();
        clicks(view);
        getUserDetailApi();

    }

    private void getUserDetailApi() {

        new Mvvm().getUserDetail(requireActivity(), AppConstants.USER_ID,list.get(listPoisition).getId(),AppConstants.USER_ID).observe(requireActivity(), new Observer<GetUserDetailRoot>() {
            @Override
            public void onChanged(GetUserDetailRoot getUserDetailRoot) {

                if(getUserDetailRoot.getStatus().equalsIgnoreCase("1")){
                    details = new Details();
                    details = getUserDetailRoot.getDetails();
                    Toast.makeText(requireContext(), "1 "+getUserDetailRoot.getMessage(), Toast.LENGTH_SHORT).show();
                     otherUserNameTV.setText(getUserDetailRoot.getDetails().getName());
                     otherUserIdTV.setText(getUserDetailRoot.getDetails().getId());
                     otherUserFollowingTV.setText(getUserDetailRoot.getDetails().getFollowingCount());
                     otherUserDiamondsTV.setText(getUserDetailRoot.getDetails().getMyDiamond());
                     otherUserFansTV.setText(getUserDetailRoot.getDetails().getFollowersCount());
                     App.getSharedpref().saveString("otherUserImg",getUserDetailRoot.getDetails().getProfileImage());

                    if(getUserDetailRoot.getDetails().isFollowStatus()){
                        j=1;
                        followPlusImg.setImageResource(R.drawable.ic_minus__2_);
                        followPlusImg.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                        userFollowTV.setText("Unfollow");
                    } else{
                        j=2;
                        followPlusImg.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                        followPlusImg.setImageResource(R.drawable.ic_plus__6_);
                        userFollowTV.setText("Follow");
                    }
                     Glide.with(requireContext()).load(getUserDetailRoot.getDetails().getProfileImage()).error(R.drawable.demo_user_profile_img).into(userImg);
                }else{
                    Toast.makeText(requireContext(), "0 "+getUserDetailRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clicks(View view) {

        userProfileBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        userProfileMsglLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(),R.id.nav_home).navigate(R.id.messagesFragment);
            }
        });

        profileMomentTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment_Moments.check_value=1;
                Fragment_Moments.otherId = list.get(listPoisition).getId();


//                EditProfileMomentsFragment.value_check=1;
                Bundle bundle = new Bundle();
                bundle.putInt("value_check",1);

                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.editProfileMomentsFragment,bundle);
            }
        });

        followUserLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followUserApi();
            }
        });
    }

    private void setDataOnViews() {

//        otherUserNameTV.setText(list.get(listPoisition).getName());
//        otherUserIdTV.setText(list.get(listPoisition).getId());
//        Glide.with(requireContext()).load(list.get(listPoisition).getImage()).error(R.drawable.demo_user_profile_img).into(userImg);
    }

    private void getData() {

        if(getArguments() != null){
            Bundle bundle = new Bundle();

            bundle = getArguments();
            list =(List<Detail> ) bundle.getSerializable("list");
            listPoisition = bundle.getInt("position");
            setDataOnViews();
        }
        else{
//            Toast.makeText(requireContext(), "Argments is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void findIds(View view) {

        userProfileBackImg=view.findViewById(R.id.userProfileBackImg);
        otherUserFansTV=view.findViewById(R.id.otherUserFansTV);
        otherUserFollowingTV=view.findViewById(R.id.otherUserFollowingTV);
        otherUserDiamondsTV=view.findViewById(R.id.otherUserDiamondsTV);
        otherUserSendTV=view.findViewById(R.id.otherUserSendTV);
        otherUserIdTV=view.findViewById(R.id.otherUserIdTV);
        otherUserNameTV=view.findViewById(R.id.otherUserNameTV);
        userImg=view.findViewById(R.id.userImg);
        followUserLlayout=view.findViewById(R.id.followUserLlayout);
        followPlusImg=view.findViewById(R.id.followPlusImg);
        userFollowTV=view.findViewById(R.id.userFollowTV);
        userProfileMsglLyout=view.findViewById(R.id.userProfileMsglLyout);
        profileMomentTV=view.findViewById(R.id.profileMomentTV);
    }

    private void followUserApi() {

        new Mvvm().followUsers(requireActivity(),AppConstants.USER_ID,list.get(listPoisition).getId(),"follow").observe(requireActivity(), new Observer<SendOtpRoot>() {
            @Override
            public void onChanged(SendOtpRoot sendOtpRoot) {

                if(sendOtpRoot.getStatus().equalsIgnoreCase("1")){
                    Toast.makeText(requireContext(), "1 "+sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();

                    if(j==2){
                        followPlusImg.setImageResource(R.drawable.ic_minus__2_);
                        followPlusImg.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                        //Toast.makeText(requireContext(), ""+details.isFollowStatus(), Toast.LENGTH_SHORT).show();
                        userFollowTV.setText("Unfollow");
                        j=1;

                    } else{
                        j=2;
                        //Toast.makeText(requireContext(), ""+details.isFollowStatus(), Toast.LENGTH_SHORT).show();
                        followPlusImg.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                        followPlusImg.setImageResource(R.drawable.ic_plus__6_);
                        userFollowTV.setText("Follow");
                    }
                }else{
//                    Toast.makeText(requireContext(), "0 "+sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
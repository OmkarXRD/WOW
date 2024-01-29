package com.live.worldsocialintegrationapp.Fragments.Home.Related.RelatedMoment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.FollowingAndFriendsRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.FollowingFriend.FollowingFriendLiveRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.utils.App;

import java.util.ArrayList;
import java.util.List;

import in.aabhasjindal.otptextview.OtpTextView;


public class MomentFollowingFriendsLiveFragment extends Fragment implements FollowingAndFriendsRVAdapter.Callback  {

    private RecyclerView followingFriendsRV;
    FollowingAndFriendsRVAdapter followingLiveUsersRVAapter;
    List<FollowingFriendLiveRoot.Detail> list;
    TextView noLive;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moment_following_friends_live, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);

        getFollowingLiveUsers();

    }

    private void findIds(View view) {

        followingFriendsRV=view.findViewById(R.id.followingFriendsRV);
        noLive=view.findViewById(R.id.noLiveFollowingSqure);
    }


    private void getFollowingLiveUsers() {

        String id = App.getSharedpref().getString("userId");

        if(!id.isEmpty()){
            new Mvvm().getFriendFollowingLiveUsers(requireActivity(),id).observe(requireActivity(), new Observer<FollowingFriendLiveRoot>() {
                @Override
                public void onChanged(FollowingFriendLiveRoot getFollowingLiveRoot) {

                    if(getFollowingLiveRoot.getStatus()==1){

                        list = new ArrayList<>();
                        noLive.setVisibility(View.GONE);
                        list = getFollowingLiveRoot.getDetails();
                        followingLiveUsersRVAapter= new FollowingAndFriendsRVAdapter(list,requireContext(), MomentFollowingFriendsLiveFragment.this);
                        followingFriendsRV.setAdapter(followingLiveUsersRVAapter);

                    }else{
                        noLive.setVisibility(View.VISIBLE);
                    }
                }
            });
        }else{
//            Toast.makeText(requireContext(), "id is null", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void callback(FollowingFriendLiveRoot.Detail detail, int pos) {

        startLive(detail,pos);

    }

    @Override
    public void enterPassword(FollowingFriendLiveRoot.Detail detail, int pos) {
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
                if (liveLock.getOTP().equalsIgnoreCase(detail.getPassword())) {
                    startLive(detail, pos);
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

    private void startLive(FollowingFriendLiveRoot.Detail detail, int pos){
        App.getSingletone().setLiveType("");
        Intent intent = new Intent(requireActivity(), CallActivity.class);
        intent.putExtra("channelName", detail.getChannelName());
        intent.putExtra("userId", detail.getUserId());
        intent.putExtra("liveType", "multiLive");
        intent.putExtra("liveStatus", "host");
        intent.putExtra("liveHostIds", detail.getId());
        intent.putExtra("token", detail.getToken());
        intent.putExtra("name", detail.getName());
        intent.putExtra("liveHostId", detail.getId());

        intent.putExtra("status", "1");

        if(detail.getImageTitle()!= null && !detail.getImageTitle().isEmpty()){
            intent.putExtra("broadTitle",detail.getImageTitle());
        }else{
            intent.putExtra("broadTitle",detail.getName());
        }

        intent.putExtra("liveImage", detail.getLiveimage());
        intent.putExtra("image", detail.getImageDp());


        startActivity(intent);
    }
}
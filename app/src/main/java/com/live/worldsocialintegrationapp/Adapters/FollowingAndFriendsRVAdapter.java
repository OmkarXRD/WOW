package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.FollowingFriend.FollowingFriendLiveRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetFollowingLiveRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class FollowingAndFriendsRVAdapter extends RecyclerView.Adapter<FollowingAndFriendsRVAdapter.ViewHolder> {
    List<FollowingFriendLiveRoot.Detail> list;
    Context context;
    Callback callback;


    public FollowingAndFriendsRVAdapter(List<FollowingFriendLiveRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FollowingAndFriendsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.following_and_friends_rv_design,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull FollowingAndFriendsRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(list.get(position).getLiveHideUnhideStatus().equalsIgnoreCase("0")){
            holder.followingFriendCardViewId.setVisibility(View.VISIBLE);
            holder.friendsFollowinglvName.setVisibility(View.VISIBLE);
        }else{
            holder.followingFriendCardViewId.setVisibility(View.GONE);
            holder.friendsFollowinglvName.setVisibility(View.GONE);

        }

        if (list.get(position).getLiveimage().isEmpty()) {
            Glide.with(holder.followingAndFriendImg.getContext()).load(list.get(position).getImageDp()).error(R.drawable.demo_user_profile_img).into(holder.followingAndFriendImg);
        } else {
            Glide.with(holder.followingAndFriendImg.getContext()).load(list.get(position).getLiveimage()).error(R.drawable.demo_user_profile_img).into(holder.followingAndFriendImg);
        }

        if(!list.get(position).getImageTitle().isEmpty() && list.get(position).getImageTitle() != null){
            holder.friendsFollowinglvName.setText(list.get(position).getImageTitle());
        }else{
            if (list.get(position).getName() != null){
                holder.friendsFollowinglvName.setText(list.get(position).getName());
            }else{
                holder.friendsFollowinglvName.setText("Welcome to Wows lives");
            }
        }

        if(!list.get(position).getImageText().isEmpty() && list.get(position).getImageText() != null){
            holder.friendLiveFollwingImgText.setText(list.get(position).getImageText());
        }else{
            holder.friendLiveFollwingImgText.setText("Any");
        }
        holder.followingFreindslvUserId.setText(list.get(position).getUserId());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(list.get(position).getKickOutStatus()){
                    Toast.makeText(context, "Your Banned for 24 hours", Toast.LENGTH_SHORT).show();
                }else {

                    if (list.get(position).getPassword().equalsIgnoreCase("")) {
                        callback.callback(list.get(position), position);
                    } else {
                        Toast.makeText(context, "Live is private", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        if (list.get(position).getPassword() != null && !list.get(position).getPassword().equalsIgnoreCase("")) {
            holder.lockLiveImageFollowingAndFriends.setVisibility(View.VISIBLE);
        } else {
            holder.lockLiveImageFollowingAndFriends.setVisibility(View.GONE);
        }

        holder.lockLiveImageFollowingAndFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(list.get(position).getKickOutStatus()){
                    Toast.makeText(context, "Your Banned for 24 hours", Toast.LENGTH_SHORT).show();
                }else {
                    callback.enterPassword(list.get(position), position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView followingAndFriendImg,lockLiveImageFollowingAndFriends;
        TextView friendsFollowinglvName,followingFreindslvUserId,friendLiveFollwingImgText;
        CardView followingFriendCardViewId;
        LottieAnimationView followingFriendsLottieImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            followingAndFriendImg=itemView.findViewById(R.id.followingAndFriendImg);
            friendsFollowinglvName=itemView.findViewById(R.id.friendsFollowinglvName);
            followingFreindslvUserId=itemView.findViewById(R.id.followingFreindslvUserId);
            lockLiveImageFollowingAndFriends=itemView.findViewById(R.id.lockLiveImageFollowingAndFriends);
            followingFriendCardViewId=itemView.findViewById(R.id.followingFriendCardViewId);
            followingFriendsLottieImg=itemView.findViewById(R.id.followingFriendsLottieImg);
            friendLiveFollwingImgText=itemView.findViewById(R.id.friendLiveFollwingImgText);
        }
    }

    public interface Callback{

        void callback(FollowingFriendLiveRoot.Detail detail,int pos);
        void enterPassword(FollowingFriendLiveRoot.Detail detail, int pos);

    }
}

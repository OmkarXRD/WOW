package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Activites.MainActivity;
import com.live.worldsocialintegrationapp.MainActivity2;
import com.live.worldsocialintegrationapp.ModelClasses.GetFollowingLiveRoot;
import com.live.worldsocialintegrationapp.ModelClasses.RootNewUser;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class FollowingLiveUsersRVAapter extends RecyclerView.Adapter<FollowingLiveUsersRVAapter.ViewHolder> {
    List<GetFollowingLiveRoot.Detail> list;
    Context context;
    Callback callback;

    public FollowingLiveUsersRVAapter(List<GetFollowingLiveRoot.Detail> list, Context context,Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FollowingLiveUsersRVAapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.following_live_users_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingLiveUsersRVAapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(list.get(position).getLiveHideUnhideStatus().equalsIgnoreCase("0")){
            holder.followingLiveCardViewId.setVisibility(View.VISIBLE);
        }else{
            holder.followingLiveCardViewId.setVisibility(View.GONE);
        }


        if(!list.get(position).getImageTitle().isEmpty() && list.get(position).getImageTitle() != null){
            holder.followingliveUsertitle.setText(list.get(position).getImageTitle());
        }else{
            if (list.get(position).getName() != null){
                holder.followingliveUsertitle.setText(list.get(position).getName());
            }else{
                holder.followingliveUsertitle.setText("Welcome to Wows lives");
            }
        }

        if(!list.get(position).getImageText().isEmpty() && list.get(position).getImageText() != null){
            holder.followingImgText.setText(list.get(position).getImageText());
        }else{
            holder.followingImgText.setText("Any");
        }

        if (list.get(position).getLiveimage().isEmpty()) {
            Glide.with(holder.followingLiveImg.getContext()).load(list.get(position).getImageDp()).error(R.drawable.photo2).into(holder.followingLiveImg);
        } else {
            Glide.with(holder.followingLiveImg.getContext()).load(list.get(position).getLiveimage()).error(R.drawable.photo2).into(holder.followingLiveImg);
        }

        if(list.get(position).getMyCoin().isEmpty()){
            holder.followingCoinsTV.setText("0");
        }else{
            holder.followingCoinsTV.setText(list.get(position).getMyCoin());
        }
        holder.followingUserId.setText(list.get(position).getUserId());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(list.get(position).getKickOutStatus()){
                    Toast.makeText(context, "Your Banned for 24 hours", Toast.LENGTH_SHORT).show();
                }else{
                    if (list.get(position).getLivePassword().equalsIgnoreCase("")) {
                        callback.callback(list.get(position),position);
                    } else {
                        if(context != null){
                            Toast.makeText(context, "Live is private", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }
        });

        if (!list.get(position).getLivePassword().equalsIgnoreCase("")) {
            holder.lockLiveImageFollowing.setVisibility(View.VISIBLE);
        } else {
            holder.lockLiveImageFollowing.setVisibility(View.GONE);
        }

        holder.lockLiveImageFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(list.get(position).getKickOutStatus()){
                    Toast.makeText(context, "Your Banned for 24 hours", Toast.LENGTH_SHORT).show();
                }else{
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
        ImageView followingLiveImg,lockLiveImageFollowing;
        TextView followingCoinsTV,followingUserId,followingliveUsertitle,followingImgText;
        CardView followingLiveCardViewId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            followingLiveImg=itemView.findViewById(R.id.followingLiveImg);
            followingCoinsTV=itemView.findViewById(R.id.followingCoinsTV);
            followingUserId=itemView.findViewById(R.id.followingUserId);
            followingliveUsertitle=itemView.findViewById(R.id.followingliveUsertitle);
            followingLiveCardViewId=itemView.findViewById(R.id.followingLiveCardViewId);
            lockLiveImageFollowing=itemView.findViewById(R.id.lockLiveImageFollowing);
            followingImgText=itemView.findViewById(R.id.followingImgText);
        }
    }

    public interface Callback{

        void callback(GetFollowingLiveRoot.Detail detail,int pos);
        void enterPassword(GetFollowingLiveRoot.Detail detail, int pos);
    }
}

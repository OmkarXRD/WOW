package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.NearByLiveUsers.NearByLiveUsersRoot;
import com.live.worldsocialintegrationapp.ModelClasses.RootNewUser;
import com.live.worldsocialintegrationapp.R;

import java.util.ArrayList;
import java.util.List;

public class LiveNearbyRVAdapter extends RecyclerView.Adapter<LiveNearbyRVAdapter.ViewHolder> {

    List<NearByLiveUsersRoot.Detail> list = new ArrayList<>();
    Context context;
    Callback callback;
    int lastPosition=-1;

    public LiveNearbyRVAdapter(List<NearByLiveUsersRoot.Detail> list,Context context,Callback callback) {
        this.list = list;
        this.callback= callback;
        this.context=context;
    }

    @NonNull
    @Override
    public LiveNearbyRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.family_tab_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LiveNearbyRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        } else {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }

        if(list.get(position).getId() == null){
            holder.itemView.setVisibility(View.GONE);
        }else {
            holder.itemView.setVisibility(View.VISIBLE);

            if (list.get(position).getLiveHideUnhideStatus().equalsIgnoreCase("0")) {
                holder.nearByLiveCardViewId.setVisibility(View.VISIBLE);
                holder.itemView.setVisibility(View.VISIBLE);
            } else {
                holder.nearByLiveCardViewId.setVisibility(View.GONE);
                holder.itemView.setVisibility(View.GONE);
            }

            if (list.get(position).getLiveimage().isEmpty()) {
                Glide.with(holder.nearbyLiveImg.getContext()).load(list.get(position).getUserProfileImage()).error(R.drawable.demo_user_profile_img).into(holder.nearbyLiveImg);
            } else {
                Glide.with(holder.nearbyLiveImg.getContext()).load(list.get(position).getLiveimage()).error(R.drawable.demo_user_profile_img).into(holder.nearbyLiveImg);
            }

            if (!list.get(position).getUserLivePassword().equalsIgnoreCase("")) {
                holder.NearBylockLiveImage.setVisibility(View.VISIBLE);
            } else {
                holder.NearBylockLiveImage.setVisibility(View.GONE);
            }

            holder.nearByLiveCountTV.setText(list.get(position).getVipLevel());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(list.get(position).getKickOutStatus()){
                        if(context != null) {
                            Toast.makeText(context, "You are Banned for 24 hours", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if (list.get(position).getUserLivePassword().equalsIgnoreCase("")) {
                            callback.callback(list.get(position), position);
                        } else {
                            if (context != null) {
                                Toast.makeText(context, "Live is private", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                }
            });

            if (!list.get(position).getImageTitle().isEmpty() && list.get(position).getImageTitle() != null) {
                holder.nearbyliveTitleTv.setText(list.get(position).getImageTitle());
            } else {
                if (list.get(position).getName() != null) {
                    holder.nearbyliveTitleTv.setText(list.get(position).getName());
                } else {
                    holder.nearbyliveTitleTv.setText("Welcome to Wows lives");
                }
            }

            holder.NearBylockLiveImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(list.get(position).getKickOutStatus()){
                        Toast.makeText(context, "You are Banned for 24 hours", Toast.LENGTH_SHORT).show();
                    }else {
                        callback.enterPassword(list.get(position), position);
                    }
                }
            });


            if (list.get(position).getImageText().isEmpty()) {
                holder.nameUSer.setText("Any");
            } else {
                holder.nameUSer.setText(list.get(position).getImageText());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView nearbyLiveImg,NearBylockLiveImage;
        CardView nearByLiveCardViewId;
        TextView nearByLiveCountTV , nameUSer , liveCount,nearbyliveTitleTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nearbyLiveImg=itemView.findViewById(R.id.nearbyLiveImg);
            nearByLiveCountTV=itemView.findViewById(R.id.nearByLiveCountTV);
            nameUSer  =itemView.findViewById(R.id.nearUser);
            NearBylockLiveImage  =itemView.findViewById(R.id.NearBylockLiveImage);
            nearByLiveCardViewId  =itemView.findViewById(R.id.nearByLiveCardViewId);
            nearbyliveTitleTv  =itemView.findViewById(R.id.nearbyliveTitleTv);

        }
    }

    public interface  Callback{
        void callback(NearByLiveUsersRoot.Detail detail,int pos);
        void enterPassword(NearByLiveUsersRoot.Detail detail, int pos);
    }
}
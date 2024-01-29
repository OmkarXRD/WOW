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

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Fragments.Home.Related.RelatedMoment.MomentSquareFragment;
import com.live.worldsocialintegrationapp.ModelClasses.FollowingFriend.FollowingFriendLiveRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GiftWallLiveUsersRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class MomentSquareRVAdapter extends RecyclerView.Adapter<MomentSquareRVAdapter.ViewHolder> {

    List<GiftWallLiveUsersRoot.Detail> list;
    Context context;
    Callback callback;

    public MomentSquareRVAdapter(List<GiftWallLiveUsersRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MomentSquareRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.moment_square_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MomentSquareRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(list.get(position).getLiveHideUnhideStatus().equalsIgnoreCase("0")){
            holder.squareCardViewId.setVisibility(View.VISIBLE);
            holder.squarelvName.setVisibility(View.VISIBLE);
        }else{
            holder.squareCardViewId.setVisibility(View.GONE);
            holder.squarelvName.setVisibility(View.GONE);

        }

        if (list.get(position).getLiveimage().isEmpty()) {
            Glide.with(holder.squareImg.getContext()).load(list.get(position).getImageDp()).error(R.drawable.demo_user_profile_img).into(holder.squareImg);
        } else {
            Glide.with(holder.squareImg.getContext()).load(list.get(position).getLiveimage()).error(R.drawable.demo_user_profile_img).into(holder.squareImg);
        }

        if(!list.get(position).getImageTitle().isEmpty() && list.get(position).getImageTitle() != null){
            holder.squarelvName.setText(list.get(position).getImageTitle());
        }else{
            if (list.get(position).getName() != null){
                holder.squarelvName.setText(list.get(position).getName());
            }else{
                holder.squarelvName.setText("Welcome to Wows lives");
            }
        }

        if(!list.get(position).getImageText().isEmpty() && list.get(position).getImageText() != null){
            holder.squareImgText.setText(list.get(position).getImageText());
        }else{
            holder.squareImgText.setText("Any");
        }
//        holder.followingFreindslvUserId.setText(list.get(position).getUserId());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (list.get(position).getPassword().equalsIgnoreCase("")) {
                    callback.callback(list.get(position),position);
                } else {
                    Toast.makeText(context, "Live is private", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (list.get(position).getPassword() != null && !list.get(position).getPassword().equalsIgnoreCase("")) {
            holder.lockLiveImageSquare.setVisibility(View.VISIBLE);
        } else {
            holder.lockLiveImageSquare.setVisibility(View.GONE);
        }

        holder.lockLiveImageSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.enterPassword(list.get(position), position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView lockLiveImageSquare,squareImg;
        TextView squareImgText,squarelvName;
        CardView squareCardViewId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            squareCardViewId=itemView.findViewById(R.id.squareCardViewId);
            lockLiveImageSquare=itemView.findViewById(R.id.lockLiveImageSquare);
            squareImg=itemView.findViewById(R.id.squareImg);
            squareImgText=itemView.findViewById(R.id.squareImgText);
            squarelvName=itemView.findViewById(R.id.squarelvName);
        }
    }


    public interface Callback{

        void callback(GiftWallLiveUsersRoot.Detail detail,int pos);
        void enterPassword(GiftWallLiveUsersRoot.Detail detail, int pos);

    }
}

package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetFollowing.Detail;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowingRVAdapter extends RecyclerView.Adapter<FollowingRVAdapter.ViewHolder> {

    Context context;
    List<Detail> list=  new ArrayList<>();
    Callback callback;

    public FollowingRVAdapter(Context context, List<Detail> list, Callback callback){
        this.context = context;
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FollowingRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.following_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.followingUserName.setText(list.get(position).getName());
        Glide.with(holder.followingCirleImg.getContext()).load(list.get(position).getImageDp()).
                error(R.drawable.demo_user_profile_img).into(holder.followingCirleImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.callback(list.get(position).getFollowingUserId(),list.get(position));
            }
        });

      //  holder.sendingLvl.setText(list.get(position).getSendLevel());

      //  holder.sendingLayout.getBackground().setColorFilter(Color.parseColor(list.get(position).getSandColor()), PorterDuff.Mode.SRC_ATOP);


        if (list.get(position).getReceivingLevel().isEmpty()){
            holder.receivingLayout.setVisibility(View.GONE);
        }else {
            holder.receivingLvl.setText(list.get(position).getReceivingLevel());
            if (Integer.parseInt(list.get(position).getReceivingLevel())==0){
                holder.receivingLayout.setVisibility(View.GONE);
                holder.followingReceivingRL.setVisibility(View.GONE);
            }else {
                holder.receivingLayout.setVisibility(View.VISIBLE);
                holder.followingReceivingRL.setVisibility(View.VISIBLE);
                Glide.with(context).load(list.get(position).getReciveColor()).into(holder.receivingLayout);
//                holder.receivingLayout.getBackground().setColorFilter(Color.parseColor(list.get(position).getReciveColor()), PorterDuff.Mode.SRC_ATOP);
            }
        }

        if (list.get(position).getSendLevel().isEmpty()){
            holder.sendingLayout.setVisibility(View.GONE);
        }else {
            holder.sendingLvl.setText(list.get(position).getSendLevel());
            if (Integer.parseInt(list.get(position).getSendLevel())==0){
                holder.sendingLayout.setVisibility(View.GONE);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=1 && Integer.parseInt(list.get(position).getSendLevel())<=10) {
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onetotengradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=11 && Integer.parseInt(list.get(position).getSendLevel())<=20) {
                holder.lvlimg.setImageResource(R.drawable.badge1);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.tentotwentygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=21 && Integer.parseInt(list.get(position).getSendLevel())<=30) {
                holder.lvlimg.setImageResource(R.drawable.badge1);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.tewntytothirtygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=31 && Integer.parseInt(list.get(position).getSendLevel())<=40) {
                holder.lvlimg.setImageResource(R.drawable.badge2);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.thirtytofourtygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=41 && Integer.parseInt(list.get(position).getSendLevel())<=50) {
                holder.lvlimg.setImageResource(R.drawable.badge2);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.fourtytofiftygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=51 && Integer.parseInt(list.get(position).getSendLevel())<=60) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.fiftytosixtygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=61 && Integer.parseInt(list.get(position).getSendLevel())<=70) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.sixtytoseventy);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=71 && Integer.parseInt(list.get(position).getSendLevel())<=80) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.seventytoeighty);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=81 && Integer.parseInt(list.get(position).getSendLevel())<=90) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.eightytonightygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=91 && Integer.parseInt(list.get(position).getSendLevel())<=100) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.nightytohundredgradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=101 && Integer.parseInt(list.get(position).getSendLevel())<=110) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.hundredtoonetoten);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=111 && Integer.parseInt(list.get(position).getSendLevel())<=120) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onetentotwentygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=121 && Integer.parseInt(list.get(position).getSendLevel())<=130) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onetwentytoonethirty);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=131 && Integer.parseInt(list.get(position).getSendLevel())<=140) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onethirtytoonefourty);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=141 && Integer.parseInt(list.get(position).getSendLevel())<=150) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onefourtytoonefifty);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>150) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onefourtytoonefifty);
            }
        }


        if(list.get(position).getGender().equalsIgnoreCase("male")|| list.get(position).getGender().equalsIgnoreCase("Male")){
            holder.followingGenderImg.setImageResource(R.drawable.male_gender__4_);
         //   holder.genderLayout.getBackground().setColorFilter(Color.parseColor("#0ed8a3"), PorterDuff.Mode.SRC_ATOP);
            holder.genLayout.getBackground().setColorFilter(Color.parseColor("#0ed8a3"), PorterDuff.Mode.SRC_ATOP);
        }else{
            holder.followingGenderImg.setImageResource(R.drawable.femenine);
          //  holder.genderLayout.getBackground().setColorFilter(Color.parseColor("#fd5293"), PorterDuff.Mode.SRC_ATOP);
            holder.genLayout.getBackground().setColorFilter(Color.parseColor("#fd5293"), PorterDuff.Mode.SRC_ATOP);
        }
        holder.followingAgeTv.setText(CommonUtils.ageCalcualte(list.get(position).getDob()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView followingUserName,followingAgeTv;
        ImageView followingGenderImg,lvlimg,receivingLayout;
        CircleImageView followingCirleImg;
        LinearLayout  sendingLayout,genLayout;
        TextView sendingLvl, receivingLvl;
        RelativeLayout followingReceivingRL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            followingCirleImg=itemView.findViewById(R.id.followingCirleImg);
            followingUserName=itemView.findViewById(R.id.followingUserName);
            followingAgeTv=itemView.findViewById(R.id.followingAgeTv);
            followingGenderImg=itemView.findViewById(R.id.followingGenderImg);
            genLayout = itemView.findViewById(R.id.genLayout);
            sendingLayout = itemView.findViewById(R.id.sendingLayout);
            receivingLayout = itemView.findViewById(R.id.receivingLayout);
            sendingLvl = itemView.findViewById(R.id.sendingLvl);
            receivingLvl = itemView.findViewById(R.id.receivingLvl);
            lvlimg = itemView.findViewById(R.id.lvlimg);
            followingReceivingRL = itemView.findViewById(R.id.followingReceivingRL);
        }
    }

    public interface Callback{
        void callback(String otherUserId,Detail detail);
    }
}

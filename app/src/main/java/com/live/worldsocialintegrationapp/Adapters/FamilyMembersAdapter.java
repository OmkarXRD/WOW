package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
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
import com.live.worldsocialintegrationapp.ModelClasses.Family.Joiner;
import com.live.worldsocialintegrationapp.ModelClasses.GetFans.Detail;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FamilyMembersAdapter extends RecyclerView.Adapter<FamilyMembersAdapter.ViewHolder> {

    private List<Joiner> list ;
    private List<Detail> userData;
    private Context context;
    private Callback callback;

    public FamilyMembersAdapter(List<Joiner> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FamilyMembersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.family_members_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyMembersAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (list.get(position).show_status){
            holder.top2FamilyFireRL.setVisibility(View.VISIBLE);
        }else {
            holder.top2FamilyFireRL.setVisibility(View.GONE);
        }

        if(list.get(position).getGender().equalsIgnoreCase("male")||list.get(position).getGender().equalsIgnoreCase("Male")){
            holder.genderIconFamMember.setImageResource(R.drawable.male_gender__4_);
            holder.genderLl.getBackground().setColorFilter(Color.parseColor("#0ed8a3"), PorterDuff.Mode.SRC_ATOP);
        }else{
            holder.genderIconFamMember.setImageResource(R.drawable.femenine);
            holder.genderLl.getBackground().setColorFilter(Color.parseColor("#fd5293"), PorterDuff.Mode.SRC_ATOP);
        }
        holder.genderTxt.setText(CommonUtils.ageCalcualte(list.get(position).getDob()));



        if (list.get(position).getReciveLevel().isEmpty()){
            holder.memberReceivingRL.setVisibility(View.GONE);
        }
        else {
            holder.receivingLvl.setText(list.get(position).getReciveLevel());
            if (Integer.parseInt(list.get(position).getReciveLevel())==0){
                holder.memberReceivingRL.setVisibility(View.GONE);

            }else {
                holder.memberReceivingRL.setVisibility(View.VISIBLE);

                Glide.with(context).load(list.get(position).getReciveColor()).into(holder.receivingLayout);
//                holder.receivingLayout.getBackground().setColorFilter(Color.parseColor(list.get(position).getReciveColor()), PorterDuff.Mode.SRC_ATOP);
            }
        }


        if (list.get(position).getSendLevel().isEmpty()){
            holder.sendingLinearLayout.setVisibility(View.GONE);
        }
        else {
            holder.sendingLvl.setText(list.get(position).getSendLevel());
            if (Integer.parseInt(list.get(position).getSendLevel())==0){
                holder.sendingLinearLayout.setVisibility(View.GONE);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=1 && Integer.parseInt(list.get(position).getSendLevel())<=10) {
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.onetotengradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=11 && Integer.parseInt(list.get(position).getSendLevel())<=20) {
                holder.lvlimg.setImageResource(R.drawable.badge1);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.tentotwentygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=21 && Integer.parseInt(list.get(position).getSendLevel())<=30) {
                holder.lvlimg.setImageResource(R.drawable.badge1);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.tewntytothirtygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=31 && Integer.parseInt(list.get(position).getSendLevel())<=40) {
                holder.lvlimg.setImageResource(R.drawable.badge2);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.thirtytofourtygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=41 && Integer.parseInt(list.get(position).getSendLevel())<=50) {
                holder.lvlimg.setImageResource(R.drawable.badge2);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.fourtytofiftygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=51 && Integer.parseInt(list.get(position).getSendLevel())<=60) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.fiftytosixtygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=61 && Integer.parseInt(list.get(position).getSendLevel())<=70) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.sixtytoseventy);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=71 && Integer.parseInt(list.get(position).getSendLevel())<=80) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.seventytoeighty);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=81 && Integer.parseInt(list.get(position).getSendLevel())<=90) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.eightytonightygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=91 && Integer.parseInt(list.get(position).getSendLevel())<=100) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.nightytohundredgradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=101 && Integer.parseInt(list.get(position).getSendLevel())<=110) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.hundredtoonetoten);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=111 && Integer.parseInt(list.get(position).getSendLevel())<=120) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.onetentotwentygradient);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=121 && Integer.parseInt(list.get(position).getSendLevel())<=130) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.onetwentytoonethirty);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=131 && Integer.parseInt(list.get(position).getSendLevel())<=140) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.onethirtytoonefourty);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>=141 && Integer.parseInt(list.get(position).getSendLevel())<=150) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.onefourtytoonefifty);
            } else if (Integer.parseInt(list.get(position).getSendLevel())>150) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLinearLayout.setVisibility(View.VISIBLE);
                holder.sendingLinearLayout.setBackgroundResource(R.drawable.onefourtytoonefifty);
            }
        }





        holder.familyMembersNameTv.setText(list.get(position).getName());
        holder.newFamilyFireTV.setText(list.get(position).getContribution());
        Glide.with(holder.familyMembersCirImg.getContext()).load(list.get(position).getUserProfileImage()).error(R.drawable.demo_user_profile_img).into(holder.familyMembersCirImg);
        holder.itemView.setOnClickListener(view -> callback.callback(list.get(position)));

        if (list.get(position).getIs_admin().equalsIgnoreCase("1")){
            holder.adminTV.setVisibility(View.VISIBLE);
        }else {
            holder.adminTV.setVisibility(View.GONE);

        }
        Log.i("Familyyyy","in if " + list.get(position).getIs_leader());
        if (list.get(position).getIs_leader().equalsIgnoreCase("1")){
            holder.adminIcon.setVisibility(View.VISIBLE);
        }else {
            holder.adminIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView familyMembersCirImg;
        private TextView familyMembersNameTv,newFamilyFireTV,genderTxt,receivingLvl,sendingLvl;
        private ImageView adminTV,adminIcon,genderIconFamMember,receivingLayout,lvlimg;
        private RelativeLayout top2FamilyFireRL,memberReceivingRL;
        private LinearLayout genderLl,sendingLinearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            familyMembersNameTv=itemView.findViewById(R.id.familyMembersNameTv);
            genderTxt=itemView.findViewById(R.id.genderTxt);
            receivingLvl=itemView.findViewById(R.id.receivingLvl);
            sendingLvl=itemView.findViewById(R.id.sendingLvl);
            familyMembersCirImg=itemView.findViewById(R.id.familyMembersCirImg);
            adminTV=itemView.findViewById(R.id.adminTV);
            genderIconFamMember=itemView.findViewById(R.id.genderIconFamMember);
            receivingLayout=itemView.findViewById(R.id.receivingLayout);
            lvlimg=itemView.findViewById(R.id.lvlimg);
            adminIcon=itemView.findViewById(R.id.adminIcon);
            newFamilyFireTV=itemView.findViewById(R.id.newFamilyFireTV);
            top2FamilyFireRL=itemView.findViewById(R.id.top2FamilyFireRL);
            memberReceivingRL=itemView.findViewById(R.id.memberReceivingRL);
            genderLl=itemView.findViewById(R.id.genderLl);
            sendingLinearLayout=itemView.findViewById(R.id.sendingLinearLayout);
        }
    }

    public interface Callback{
        void callback(Joiner detail);
    }
}

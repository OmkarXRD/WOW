package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetFriends.Detail;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

import javax.security.auth.callback.Callback;

import de.hdodenhof.circleimageview.CircleImageView;

public class GiftFirendAdapter extends RecyclerView.Adapter<GiftFirendAdapter.holder> {

    private List<Detail> list ;
    private Context context;
    private Callback callback;

    public GiftFirendAdapter(List<Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }
    public interface Callback{
        void sendGift(String otherUserId,int poistion);
    }

    @NonNull
    @Override
    public GiftFirendAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_rv_design, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GiftFirendAdapter.holder holder, int position) {


        holder.friendUserName.setText(list.get(position).getName());
        Glide.with(holder.friendCicleImg.getContext()).load(list.get(position).getImageDp())
                .error(R.drawable.demo_user_profile_img).into(holder.friendCicleImg);
        holder.friendInviteTv.setText("Send");

        if (list.get(position).getReceivingLevel().isEmpty()){
            holder.receivingLayout.setVisibility(View.GONE);
        }
        else {
            holder.receivingTxt.setText(list.get(position).getReceivingLevel());
            if (Integer.parseInt(list.get(position).getReceivingLevel())==0){
                holder.receivingLayout.setVisibility(View.GONE);
                holder.friendsReceivingRL.setVisibility(View.GONE);
            }else {
                holder.receivingLayout.setVisibility(View.VISIBLE);
                holder.friendsReceivingRL.setVisibility(View.VISIBLE);
                Glide.with(context).load(list.get(position).getReciveColor()).into(holder.receivingLayout);
//                holder.receivingLayout.getBackground().setColorFilter(Color.parseColor(list.get(position).getReciveColor()), PorterDuff.Mode.SRC_ATOP);
            }
        }



        if (list.get(position).getLavelInformation().getSendLevel().isEmpty()){
            holder.sendingLayout.setVisibility(View.GONE);
        }else {
            holder.sendingTxt.setText(list.get(position).getLavelInformation().getSendLevel());
            if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())==0){
                holder.sendingLayout.setVisibility(View.GONE);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=1 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=10) {
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onetotengradient);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=11 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=20) {
                holder.lvlimg.setImageResource(R.drawable.badge1);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.tentotwentygradient);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=21 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=30) {
                holder.lvlimg.setImageResource(R.drawable.badge1);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.tewntytothirtygradient);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=31 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=40) {
                holder.lvlimg.setImageResource(R.drawable.badge2);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.thirtytofourtygradient);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=41 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=50) {
                holder.lvlimg.setImageResource(R.drawable.badge2);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.fourtytofiftygradient);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=51 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=60) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.fiftytosixtygradient);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=61 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=70) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.sixtytoseventy);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=71 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=80) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.seventytoeighty);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=81 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=90) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.eightytonightygradient);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=91 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=100) {
                holder.lvlimg.setImageResource(R.drawable.badge3);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.nightytohundredgradient);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=101 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=110) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.hundredtoonetoten);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=111 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=120) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onetentotwentygradient);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=121 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=130) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onetwentytoonethirty);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=131 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=140) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onethirtytoonefourty);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>=141 && Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())<=150) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onefourtytoonefifty);
            } else if (Integer.parseInt(list.get(position).getLavelInformation().getSendLevel())>150) {
                holder.lvlimg.setImageResource(R.drawable.badge4);
                holder.sendingLayout.setVisibility(View.VISIBLE);
                holder.sendingLayout.setBackgroundResource(R.drawable.onefourtytoonefifty);
            }
        }

        holder.friendInviteTv.setOnClickListener(view -> {
            callback.sendGift(list.get(position).getId(),position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        private ImageView friendGenderImg,lvlimg,receivingLayout;
        private TextView friendUserName,friendInviteTv,friendsDateTimeTv,friendAgeTv;
        private CircleImageView friendCicleImg;
        private LinearLayout genderLayout;
        private LinearLayout sendingLayout;
        private TextView sendingTxt,receivingTxt;
        private CheckBox shareCheckBox;
        private RelativeLayout friendsReceivingRL;

        public holder(@NonNull View itemView) {
            super(itemView);
            friendUserName = itemView.findViewById(R.id.friendUserName);
            friendCicleImg = itemView.findViewById(R.id.friendCicleImg);
            friendInviteTv = itemView.findViewById(R.id.friendInviteTv);
            friendsDateTimeTv = itemView.findViewById(R.id.friendsDateTimeTv);
            friendGenderImg = itemView.findViewById(R.id.friendGenderImg);
            friendAgeTv = itemView.findViewById(R.id.friendAgeTv);
            shareCheckBox = itemView.findViewById(R.id.shareCheckBox);
            genderLayout = itemView.findViewById(R.id.genderLayout);
            sendingLayout = itemView.findViewById(R.id.sendingLayout);
            receivingLayout = itemView.findViewById(R.id.receivingLayout);
            friendsReceivingRL = itemView.findViewById(R.id.friendsReceivingRL);
            sendingTxt = itemView.findViewById(R.id.sendingLvl);
            receivingTxt = itemView.findViewById(R.id.receivingLvl);
            lvlimg = itemView.findViewById(R.id.lvlimg);
        }
    }
}

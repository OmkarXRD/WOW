package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetFriends.Detail;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendRVAdapter extends RecyclerView.Adapter<FriendRVAdapter.ViewHolder> {
    List<Detail> list = new ArrayList<>();
    Context context;
    Callback callback;
    public  static int sendCheck = 0;
    public static int sendEventInvitationCheck=0;


    public FriendRVAdapter(List<Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FriendRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.shareCheckBox.setVisibility(View.GONE);

        //this is for share live link
        if ( App.getSharedpref().getString("liveShareCheckAdpter").equalsIgnoreCase("1")){
            holder.shareCheckBox.setVisibility(View.VISIBLE);
            holder.shareCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.shareCheckBox.isChecked()){
                        callback.checkBoxChecked(list.get(position));
                    }else{
                        callback.checkBoxUnchecked(list.get(position));
                    }
                }
            });
        }

        holder.friendUserName.setText(list.get(position).getName());
        Glide.with(holder.friendCicleImg.getContext()).load(list.get(position).getImageDp())
                .error(R.drawable.demo_user_profile_img).into(holder.friendCicleImg);


        if(list.get(position).getGender().equalsIgnoreCase("male")){
            holder.friendGenderImg.setImageResource(R.drawable.male_gender__4_);
            holder.genderLayout.getBackground().setColorFilter(Color.parseColor("#0ed8a3"), PorterDuff.Mode.SRC_ATOP);
        }else{
            holder.friendGenderImg.setImageResource(R.drawable.femenine);
            holder.genderLayout.getBackground().setColorFilter(Color.parseColor("#fd5293"), PorterDuff.Mode.SRC_ATOP);
        }
        holder.friendAgeTv.setText(CommonUtils.ageCalcualte(list.get(position).getDob()));

//        holder.sendingTxt.setText(list.get(position).getLavelInformation().getSendLevel());


//        holder.sendingLayout.getBackground().setColorFilter(Color.parseColor(list.get(position).getLavelInformation().getSandColor()), PorterDuff.Mode.SRC_ATOP);

        if (list.get(position).getLavelInformation().getReciveLevel().isEmpty()){
            holder.receivingLayout.setVisibility(View.GONE);
        }else {
            holder.receivingTxt.setText(list.get(position).getLavelInformation().getReciveLevel());
            if (Integer.parseInt(list.get(position).getLavelInformation().getReciveLevel())==0){
                holder.receivingLayout.setVisibility(View.GONE);
            }else {
                holder.receivingLayout.setVisibility(View.VISIBLE);
                Glide.with(context).load(list.get(position).getLavelInformation().getReciveColor()).into(holder.receivingLayout);
//                holder.receivingLayout.getBackground().setColorFilter(Color.parseColor(list.get(position).getLavelInformation().getReciveColor()), PorterDuff.Mode.SRC_ATOP);
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

        Log.d("onBindViewHolder", "onBindViewHolder: "+sendEventInvitationCheck);
        Log.d("onBindViewHolder", "onBindViewHolder: "+sendCheck);

        if(sendEventInvitationCheck==1){
            holder.friendInviteTv.setVisibility(View.VISIBLE);
            //Toast.makeText(context, "852845841554154", Toast.LENGTH_SHORT).show();
//            holder.friendsDateTimeTv.setVisibility(View.GONE);

            holder.friendInviteTv.setOnClickListener(view -> callback.sendEventInvitation(list.get(position).getId(),holder.friendInviteTv));
        }else{
            holder.friendInviteTv.setVisibility(View.GONE);
//            holder.friendsDateTimeTv.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(view -> {
                //Toast.makeText(context, "fszdcgbm", Toast.LENGTH_SHORT).show();
                //send check ==0 means profile open hovegi otherWise gift send hoga
                if (sendCheck == 0) {
                    callback.callback(list.get(position).getId(),position);
                } else {
                    callback.callback("001",position);
                }
            });
        }
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
        CircleImageView friendCicleImg;
        ImageView friendGenderImg,lvlimg,receivingLayout;
        TextView friendUserName,friendInviteTv,friendsDateTimeTv,friendAgeTv;
        CheckBox shareCheckBox;
        LinearLayout genderLayout;
        LinearLayout sendingLayout;
        TextView sendingTxt,receivingTxt;

        public ViewHolder(@NonNull View itemView) {
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
            sendingTxt = itemView.findViewById(R.id.sendingLvl);
            receivingTxt = itemView.findViewById(R.id.receivingLvl);
            lvlimg = itemView.findViewById(R.id.lvlimg);


        }
    }

    public interface Callback {
        void callback(String otherUserId,int poistion);
        void sendEventInvitation(String UserId,TextView friendInviteTv);
        void checkBoxChecked(Detail detail);
        void checkBoxUnchecked(Detail detail);
    }
}

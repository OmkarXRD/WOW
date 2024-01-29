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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.Visitors.GetVisitorsRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VisitorsRVAdapter extends RecyclerView.Adapter<VisitorsRVAdapter.ViewHolder> {

    List<GetVisitorsRoot.Deatil> list= new ArrayList<>();
    Context context;
    Callback callback;

    public  VisitorsRVAdapter(List<GetVisitorsRoot.Deatil> list, Context context,Callback callback){
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public VisitorsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.visitors_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorsRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

//        Toast.makeText(context, "otherId "+list.get(position).getId(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(context, "id "+AppConstants.USER_ID, Toast.LENGTH_SHORT).show();


        if(!list.get(position).getId().equalsIgnoreCase(AppConstants.USER_ID)){

            if(!list.isEmpty()) {

                if (list.get(position).getGender().equalsIgnoreCase("Male")||list.get(position).getGender().equalsIgnoreCase("male")){
                    holder.genderTxt.setText(CommonUtils.ageCalcualte(list.get(position).getDob()));
                    holder.genderIcon.setImageResource(R.drawable.ic_male_gender__2_);
                    holder.genderLayout.getBackground().setColorFilter(Color.parseColor("#0ed8a3"), PorterDuff.Mode.SRC_ATOP);
                }else {
                    holder.genderTxt.setText(list.get(position).getAge());
                    holder.genderIcon.setImageResource(R.drawable.femenine);
                    holder.genderLayout.getBackground().setColorFilter(Color.parseColor("#fd5293"), PorterDuff.Mode.SRC_ATOP);
                }


                if (list.get(position).getReceivingLevel().isEmpty()){
                    holder.receivingLayout.setVisibility(View.GONE);
                }else {
                    holder.receivingTxt.setText(list.get(position).getReciveLevel());
                    if (Integer.parseInt(list.get(position).getReceivingLevel())==0){
                        holder.receivingLayout.setVisibility(View.GONE);
                    }else {
                        holder.receivingLayout.setVisibility(View.VISIBLE);
                        Glide.with(context).load(list.get(position).getReciveColor()).into(holder.receivingLayout);
//                        holder.receivingLayout.getBackground().setColorFilter(Color.parseColor(list.get(position).getReciveColor()), PorterDuff.Mode.SRC_ATOP);
                    }
                }

             //   holder.receivingTxt.setText(list.get(position).getReceivingLevel());

             //   holder.sendingLayout.getBackground().setColorFilter(Color.parseColor(list.get(position).getSandColor()), PorterDuff.Mode.SRC_ATOP);

                if (list.get(position).getSendLevel().isEmpty()){
                    holder.sendingLayout.setVisibility(View.GONE);
                }else {
                    holder.sendingTxt.setText(list.get(position).getSendLevel());
                    if (Integer.parseInt(list.get(position).getSendLevel())==0){
                        holder.sendingLayout.setVisibility(View.GONE);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=1 && Integer.parseInt(list.get(position).getSendLevel())<=10) {
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_1);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=11 && Integer.parseInt(list.get(position).getSendLevel())<=20) {
                        holder.lvlimg.setImageResource(R.drawable.badge1);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_2);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=21 && Integer.parseInt(list.get(position).getSendLevel())<=30) {
                        holder.lvlimg.setImageResource(R.drawable.badge1);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_3);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=31 && Integer.parseInt(list.get(position).getSendLevel())<=40) {
                        holder.lvlimg.setImageResource(R.drawable.badge2);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_4);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=41 && Integer.parseInt(list.get(position).getSendLevel())<=50) {
                        holder.lvlimg.setImageResource(R.drawable.badge2);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_5);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=51 && Integer.parseInt(list.get(position).getSendLevel())<=60) {
                        holder.lvlimg.setImageResource(R.drawable.badge3);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_6);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=61 && Integer.parseInt(list.get(position).getSendLevel())<=70) {
                        holder.lvlimg.setImageResource(R.drawable.badge3);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_7);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=71 && Integer.parseInt(list.get(position).getSendLevel())<=80) {
                        holder.lvlimg.setImageResource(R.drawable.badge3);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_8);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=81 && Integer.parseInt(list.get(position).getSendLevel())<=90) {
                        holder.lvlimg.setImageResource(R.drawable.badge3);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_9);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=91 && Integer.parseInt(list.get(position).getSendLevel())<=100) {
                        holder.lvlimg.setImageResource(R.drawable.badge3);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_10);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=101 && Integer.parseInt(list.get(position).getSendLevel())<=110) {
                        holder.lvlimg.setImageResource(R.drawable.badge4);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_11);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=111 && Integer.parseInt(list.get(position).getSendLevel())<=120) {
                        holder.lvlimg.setImageResource(R.drawable.badge4);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_12);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=121 && Integer.parseInt(list.get(position).getSendLevel())<=130) {
                        holder.lvlimg.setImageResource(R.drawable.badge4);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_13);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=131 && Integer.parseInt(list.get(position).getSendLevel())<=140) {
                        holder.lvlimg.setImageResource(R.drawable.badge4);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_14);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>=141 && Integer.parseInt(list.get(position).getSendLevel())<=150) {
                        holder.lvlimg.setImageResource(R.drawable.badge4);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_15);
                    } else if (Integer.parseInt(list.get(position).getSendLevel())>150) {
                        holder.lvlimg.setImageResource(R.drawable.badge4);
                        holder.sendingLayout.setVisibility(View.VISIBLE);
                        holder.sendingLayout.setBackgroundResource(R.drawable.level_16);
                    }
                }



                holder.visitor_rv_linearLayout.setVisibility(View.VISIBLE);
                Glide.with(holder.visitorsCirImg.getContext()).load(list.get(position).getImageDp()).
                        error(R.drawable.demo_user_profile_img).into(holder.visitorsCirImg);

                holder.visitorsName.setText(list.get(position).getName());

                holder.visitor_rv_linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callback.callback(list.get(position).getId());
                    }
                });
            }else{
                holder.visitorsCirImg.setImageResource(R.drawable.demo_user_profile_img);
                holder.visitorsName.setText("Username");
            }

        }else{
            holder.visitor_rv_linearLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView visitorsCirImg;

        TextView topDateTV,visitorsName,genderTxt,sendingTxt,receivingTxt;
        ImageView genderIcon,lvlimg,receivingLayout;
        LinearLayout visitor_rv_linearLayout,genderLayout,sendingLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topDateTV= itemView.findViewById(R.id.topDateTV);
            visitorsCirImg=itemView.findViewById(R.id.visitorsCirImg);
            visitorsName=itemView.findViewById(R.id.visitorsName);
            visitor_rv_linearLayout=itemView.findViewById(R.id.visitor_rv_linearLayout);
            genderIcon = itemView.findViewById(R.id.genderIcon);
            genderTxt = itemView.findViewById(R.id.genderTxt);
            sendingTxt = itemView.findViewById(R.id.sendingLvl);
            receivingTxt = itemView.findViewById(R.id.receivingLvl);
            genderLayout = itemView.findViewById(R.id.genderLayout);
            sendingLayout = itemView.findViewById(R.id.sendingLayout);
            receivingLayout = itemView.findViewById(R.id.receivingLayout);
            lvlimg = itemView.findViewById(R.id.lvlimg);
        }
    }
    public interface Callback{
        void callback(String otherUserId);
    }
}


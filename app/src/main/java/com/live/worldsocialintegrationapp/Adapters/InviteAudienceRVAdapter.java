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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.firebase.GoLiveModelClass;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class InviteAudienceRVAdapter extends RecyclerView.Adapter<InviteAudienceRVAdapter.ViewHolder> {

    List<GoLiveModelClass> list = new ArrayList<>();
    Context context;
    Callback callback;
    public static String directHostId = "";
    public static int memeberPostion;
    private String liveStatus = "";

    public InviteAudienceRVAdapter(List<GoLiveModelClass> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public InviteAudienceRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.invite_audience_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InviteAudienceRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.inviteAudienceNameTv.setTextColor(Color.BLACK);


        if (position == 0) {
            //host
            holder.homeOwnerTv.setText("Homeowner");
            holder.hostIconImg.setVisibility(View.VISIBLE);
            holder.inviteAudienceNameTv.setText(list.get(position).getName());
            holder.bottomProfileAgeTv.setText(list.get(position).getAge());
            if(list.get(position).getGender().equalsIgnoreCase("male")){
                holder.bottomProfileGenderImg.setImageResource(R.drawable.male_gender__4_);
                holder.genderRl.getBackground().setColorFilter(Color.parseColor("#0ed8a3"), PorterDuff.Mode.SRC_ATOP);

            }else{
                holder.bottomProfileGenderImg.setImageResource(R.drawable.femenine);
                holder.genderRl.getBackground().setColorFilter(Color.parseColor("#fd5293"), PorterDuff.Mode.SRC_ATOP);

            }
            Glide.with(holder.inviteAudienceCirImg.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.inviteAudienceCirImg);

        } else if (list.get(position).isAdminStatus()) {
            if (position == 1) {
                holder.homeOwnerTv.setText("Admin");
                holder.homeOwnerTv.setVisibility(View.VISIBLE);
            } else {
                holder.homeOwnerTv.setVisibility(View.GONE);
            }
            holder.hostIconImg.setVisibility(View.VISIBLE);
            holder.hostIconImg.setImageResource(R.drawable.live_admin_icon);
            holder.inviteAudienceNameTv.setText(list.get(position).getName());
            holder.bottomProfileAgeTv.setText(list.get(position).getAge());
            if(list.get(position).getGender().equalsIgnoreCase("male")){
                holder.bottomProfileGenderImg.setImageResource(R.drawable.male_gender__4_);
                holder.genderRl.getBackground().setColorFilter(Color.parseColor("#0ed8a3"), PorterDuff.Mode.SRC_ATOP);
            }else{
                holder.bottomProfileGenderImg.setImageResource(R.drawable.femenine);
                holder.genderRl.getBackground().setColorFilter(Color.parseColor("#fd5293"), PorterDuff.Mode.SRC_ATOP);
            }
            Glide.with(holder.inviteAudienceCirImg.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.inviteAudienceCirImg);
        } else {
            if (list.get(position).isAdminStatus()) {
            } else {
                if (position == memeberPostion) {
                    holder.homeOwnerTv.setText("Member");
                    holder.homeOwnerTv.setVisibility(View.VISIBLE);
                } else {
                    holder.homeOwnerTv.setVisibility(View.GONE);
                }
                holder.hostIconImg.setVisibility(View.GONE);
                holder.inviteAudienceNameTv.setText(list.get(position).getName());
                holder.bottomProfileAgeTv.setText(list.get(position).getAge());
                if(list.get(position).getGender().equalsIgnoreCase("male")){
                    holder.bottomProfileGenderImg.setImageResource(R.drawable.male_gender__4_);
                    holder.genderRl.getBackground().setColorFilter(Color.parseColor("#0ed8a3"), PorterDuff.Mode.SRC_ATOP);
                }else{
                    holder.bottomProfileGenderImg.setImageResource(R.drawable.femenine);
                    holder.genderRl.getBackground().setColorFilter(Color.parseColor("#fd5293"), PorterDuff.Mode.SRC_ATOP);
                }
                Glide.with(holder.inviteAudienceCirImg.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.inviteAudienceCirImg);

            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                callback.callback(list.get(position));
                callback.onBanned(position,!liveStatus.equalsIgnoreCase("host"),list.get(position).getUserID());
            }
        });

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
        CircleImageView inviteAudienceCirImg;
        ImageView hostIconImg,adminIconImg,bottomProfileGenderImg;
        TextView homeOwnerTv, inviteAudienceNameTv,bottomProfileAgeTv;
        LinearLayout genderRl,genderLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            inviteAudienceCirImg = itemView.findViewById(R.id.inviteAudienceCirImg);
            homeOwnerTv = itemView.findViewById(R.id.homeOwnerTv);
            inviteAudienceNameTv = itemView.findViewById(R.id.inviteAudienceNameTv);
            hostIconImg = itemView.findViewById(R.id.hostIconImg);
            bottomProfileAgeTv = itemView.findViewById(R.id.bottomProfileAgeTv);
            bottomProfileGenderImg = itemView.findViewById(R.id.bottomProfileGenderImg);
            genderLayout = itemView.findViewById(R.id.genderLayout);
            genderRl = itemView.findViewById(R.id.genderRl);
        }
    }

    public interface Callback {
        void callback(GoLiveModelClass goLiveModelClass);
        void onBanned(int position,boolean liveHost,String userId);
    }
}

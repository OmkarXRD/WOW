package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.firebase.GoLiveModelClass;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SendGiftUserRVAdapter extends RecyclerView.Adapter<SendGiftUserRVAdapter.ViewHolder> {
    List<GoLiveModelClass> list;
    Context context;
    Callback callback;

    public SendGiftUserRVAdapter(List<GoLiveModelClass> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public SendGiftUserRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reserved_seat_live_users_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SendGiftUserRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

//        if(!list.get(position).getUserID().equalsIgnoreCase(AppConstants.USER_ID)){
            holder.reservedSeatLiveRL.setVisibility(View.VISIBLE);

            Glide.with(holder.reservedSeatUserImg.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.reservedSeatUserImg);
            holder.reservedSeatUserNameImg.setText(list.get(position).getName());
            holder.reservedUserSendGiftTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.callback(list.get(position),holder.reservedUserSendGiftTv);
                }
            });
//        }else{
//            holder.reservedSeatLiveRL.setVisibility(View.GONE);
//        }
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
        TextView reservedUserSendGiftTv,reservedSeatUserNameImg;
        ImageView reservedSeatUserImg;
        RelativeLayout reservedSeatLiveRL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            reservedUserSendGiftTv=itemView.findViewById(R.id.reservedUserSendGiftTv);
            reservedSeatUserImg=itemView.findViewById(R.id.reservedSeatUserImg);
            reservedSeatUserNameImg=itemView.findViewById(R.id.reservedSeatUserNameImg);
            reservedSeatLiveRL=itemView.findViewById(R.id.reservedSeatLiveRL);
        }
    }

    public interface  Callback{
        void callback(GoLiveModelClass goLiveModelClass, TextView textView);
    }
}

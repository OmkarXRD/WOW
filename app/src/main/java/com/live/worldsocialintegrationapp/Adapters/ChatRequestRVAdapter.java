package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.RequstChat;
import com.live.worldsocialintegrationapp.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatRequestRVAdapter extends RecyclerView.Adapter<ChatRequestRVAdapter.ViewHolder> {

    Callback callback;
    Context context;
    List<RequstChat> list = new ArrayList<>();


    public ChatRequestRVAdapter(Context context, List<RequstChat> list, Callback callback) {
        this.callback = callback;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChatRequestRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_requests_rv_design, parent, false));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChatRequestRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.chatRequestUserNameRV.setText(list.get(position).getFromName());
        Glide.with(holder.chatReqCirImg.getContext()).load(list.get(position).getFromImg()).error(R.drawable.demo_user_profile_img)
                .into(holder.chatReqCirImg);
        Log.i("ReqMsg","zzzzzzz "+ list.get(position).getFromName());
        if (list.get(position).getFromGender().equalsIgnoreCase("male")) {
            holder.heSheWantsTV.setText("He wants to become your friend");
        } else {
            holder.heSheWantsTV.setText("She wants to become your friend");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.callback(list.get(position).getFrom(), String.valueOf(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView chatRequestUserNameRV, heSheWantsTV;
        CircleImageView chatReqCirImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chatRequestUserNameRV = itemView.findViewById(R.id.chatRequestUserNameRV);
            chatReqCirImg = itemView.findViewById(R.id.chatReqCirImg);
            heSheWantsTV = itemView.findViewById(R.id.heSheWantsTV);
        }
    }


    public interface Callback {
        void callback(String otherUserId, String poistion);
    }

    public void loadData(List<RequstChat> list) {
        this.list = list;
        notifyDataSetChanged();
    }

}


package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GiftWallReceiverModelClass;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;

import java.util.List;

public class ProfileGiftWallAdapter extends RecyclerView.Adapter<ProfileGiftWallAdapter.holder> {
    Context context;
    List<GiftWallReceiverModelClass.Detail> list;

    public ProfileGiftWallAdapter(Context context, List<GiftWallReceiverModelClass.Detail> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProfileGiftWallAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.giftssenddesign,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileGiftWallAdapter.holder holder, int position) {
        holder.giftCountTextView.setText(list.get(position).getCounts());
        Glide.with(context).load(list.get(position).getImage()).placeholder(R.drawable.themeblack).into(holder.imaeg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        ImageView imaeg;
        TextView giftCountTextView;
        public holder(@NonNull View itemView) {
            super(itemView);
            imaeg = itemView.findViewById(R.id.img);
            giftCountTextView = itemView.findViewById(R.id.giftCountText);
        }
    }
}

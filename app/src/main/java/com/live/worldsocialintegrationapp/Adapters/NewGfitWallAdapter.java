package com.live.worldsocialintegrationapp.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GiftWallReceiverModelClass;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class NewGfitWallAdapter extends RecyclerView.Adapter<NewGfitWallAdapter.holder> {

    private List<GiftWallReceiverModelClass.Detail> details;
    private Context context;

    public NewGfitWallAdapter(List<GiftWallReceiverModelClass.Detail> details, Context context) {
        this.details = details;
        this.context = context;
    }

    @NonNull
    @Override
    public NewGfitWallAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.giftssenddesign,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewGfitWallAdapter.holder holder, int position) {

        Glide.with(context).load(details.get(position).getImage()).error(R.drawable.wow_icon).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        private ImageView img;

        public holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }
    }
}

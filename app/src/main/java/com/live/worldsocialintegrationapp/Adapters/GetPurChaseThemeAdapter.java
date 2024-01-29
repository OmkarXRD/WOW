package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetPurchaseGallery;
import com.live.worldsocialintegrationapp.R;


import java.util.List;

public class GetPurChaseThemeAdapter extends RecyclerView.Adapter<GetPurChaseThemeAdapter.holder> {
    List<GetPurchaseGallery.Detail> list;
    Context context;
    Callback callback;
    public static String themeUrl;

    public GetPurChaseThemeAdapter(List<GetPurchaseGallery.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    public interface Callback {
        void enableFrame(GetPurchaseGallery.Detail detail, TextView view);
        void testCars(GetPurchaseGallery.Detail detail);
    }

    @NonNull
    @Override
    public GetPurChaseThemeAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.themelayoutdesign,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GetPurChaseThemeAdapter.holder holder, @SuppressLint("RecyclerView") int position) {
        if (list.get(position).isApplied) {
            holder.myframesEnableTV.setText("Remove");
        } else {
            holder.myframesEnableTV.setText("Apply");
        }
        if (!list.get(position).getExp_time().isEmpty()){
            holder.textDate.setText(list.get(position).getDateFrom()+" / "+list.get(position).getExp_time());
        }
        holder.mylookEnableRL.setOnClickListener(v -> callback.enableFrame(list.get(position),holder.myframesEnableTV));
        holder.testWear.setOnClickListener(view -> callback.testCars(list.get(position)));

        Log.d("strinng","striing : "+list.get(position).getImage());

        Glide.with(context).load(list.get(position).getImage()).placeholder(R.drawable.user).into(holder.mylookFrameImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        ImageView mylookFrameImg;
        TextView myframesEnableTV,textDate,testWear;
        RelativeLayout mylookEnableRL;
        public holder(@NonNull View itemView) {
            super(itemView);
            myframesEnableTV=itemView.findViewById(R.id.myThemeTextView);
            mylookFrameImg=itemView.findViewById(R.id.mylookFrameImg);
            mylookEnableRL =itemView.findViewById(R.id.mylookEnableRL);
            textDate= itemView.findViewById(R.id.textDate);
            testWear= itemView.findViewById(R.id.testWear);
        }
    }
}

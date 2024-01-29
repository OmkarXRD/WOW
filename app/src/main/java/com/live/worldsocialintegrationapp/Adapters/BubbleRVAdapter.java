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
import com.live.worldsocialintegrationapp.ModelClasses.VipImagesRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class BubbleRVAdapter extends RecyclerView.Adapter<BubbleRVAdapter.ViewHolder> {
    List<VipImagesRoot.Detail> list;
    Context context;
    Callback callback;

    public BubbleRVAdapter(List<VipImagesRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public BubbleRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bubble_rv_design,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BubbleRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (list.get(position).getApplied()){
            holder.myLookCarEnableTv.setText("Remove");
        }else {
            holder.myLookCarEnableTv.setText("Apply");
        }


        Glide.with(holder.mylookBubbleImg.getContext()).load(list.get(position).getVipImage()).into(holder.mylookBubbleImg);

        holder.bublleTestwear.setOnClickListener(view -> callback.callback(list.get(position)));
        holder.myLookBubbleEnableRL.setOnClickListener(view -> callback.enable(list.get(position),holder.myLookCarEnableTv));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mylookBubbleImg;
        TextView bublleTestwear,myLookCarEnableTv;
        RelativeLayout myLookBubbleEnableRL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mylookBubbleImg=itemView.findViewById(R.id.mylookBubbleImg);
            bublleTestwear = itemView.findViewById(R.id.bublleTestwear);
            myLookBubbleEnableRL = itemView.findViewById(R.id.myLookBubbleEnableRL);
            myLookCarEnableTv = itemView.findViewById(R.id.myLookCarEnableTv);
        }
    }

    public interface Callback{
        void callback(VipImagesRoot.Detail detail);
        void enable(VipImagesRoot.Detail detail,TextView textView);
    }
}

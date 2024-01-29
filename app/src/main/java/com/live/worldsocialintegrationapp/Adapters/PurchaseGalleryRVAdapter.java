package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.ModelClasses.LiveGallery.GetLiveGalleryRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseGallery;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class PurchaseGalleryRVAdapter extends RecyclerView.Adapter<PurchaseGalleryRVAdapter.ViewHolder> {

    List<GetLiveGalleryRoot.Detail> list;
    Context context;
    Callback callback;

    public PurchaseGalleryRVAdapter(List<GetLiveGalleryRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public PurchaseGalleryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_gallery_rv_design,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PurchaseGalleryRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.galleryValidTv.setText("Valid "+list.get(position).getValidity()+" days");
        holder.gelleryCoinsTv.setText(list.get(position).getCoins());

//        if(list.get(position).isPurStatus()){
//            holder.purchase_gallery_tv.setText("Bought");
//            holder.galleryValidTv.setText("Valid "+list.get(position).getRemainingDays()+" days");
//            callback.showVisibleCheck(true);
//        }else{
//            holder.purchase_gallery_tv.setText("buy");
//        }

        holder.purchase_gallery_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.buyGallery(list.get(position),holder.purchase_gallery_tv);
            }
        });
        holder.send_gallery_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.sendGallery(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView gelleryCoinsTv,galleryValidTv,purchase_gallery_tv,send_gallery_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gelleryCoinsTv=itemView.findViewById(R.id.gelleryCoinsTv);
            galleryValidTv=itemView.findViewById(R.id.galleryValidTv);
            purchase_gallery_tv=itemView.findViewById(R.id.purchase_gallery_tv);
            send_gallery_tv=itemView.findViewById(R.id.send_gallery_tv);
        }
    }
    public interface Callback{
        void buyGallery(GetLiveGalleryRoot.Detail purchaseGallery,TextView textView);
        void sendGallery(GetLiveGalleryRoot.Detail purchaseGallery);
        void showVisibleCheck(boolean b);
    }
}

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
import com.live.worldsocialintegrationapp.ModelClasses.LiveTheme.GetLiveThemeRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class LivePurchasedThemeRVAdapter extends RecyclerView.Adapter<LivePurchasedThemeRVAdapter.ViewHolder> {

    List<GetLiveThemeRoot.Detail> list;
    Context context;
    Callback callback;
    public static String themeUrl;

    public LivePurchasedThemeRVAdapter(List<GetLiveThemeRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public LivePurchasedThemeRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.live_purchase_theme_rv_design,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LivePurchasedThemeRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(holder.livePurchseThemeImg.getContext()).load(list.get(position).getTheme()).into(holder.livePurchseThemeImg);
        holder.liveThemePriceTv.setText(list.get(position).getPrice()+" coins");
        if(list.get(position).getPurchasedType()){
            if(themeUrl != null && themeUrl.equals(list.get(position).getTheme())){
               holder.liveThemeBuyTv.setText("Enabled");
           }else{
                holder.liveThemeBuyTv.setText("Buy");
            }
            holder.liveValidityTv.setText(list.get(position).getRemainingDays()+" days");
            holder.liveThemeBuyTv.setOnClickListener(view -> callback.enableLiveTheme(list.get(position),holder.liveThemeBuyTv));
        }else{
            holder.liveThemeBuyTv.setText("Buy");
            holder.liveValidityTv.setText(list.get(position).getValditity()+" days");
            holder.liveThemeBuyTv.setOnClickListener(view -> callback.purchaseLiveTheme(list.get(position),holder.liveThemeBuyTv));
            holder.liveSendThemeTv.setOnClickListener(view -> callback.sendLiveTheme(list.get(position),holder.liveSendThemeTv));
        }
        holder.purchaseThemeTestDriveImg.setOnClickListener(view -> callback.callback(list.get(position)));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView purchaseThemeTestDriveImg,livePurchseThemeImg;
        TextView liveValidityTv,liveThemePriceTv,liveThemeBuyTv,liveSendThemeTv;
//        RelativeLayout liveThemeBuyRl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            purchaseThemeTestDriveImg=itemView.findViewById(R.id.purchaseThemeTestDriveImg);
            liveValidityTv=itemView.findViewById(R.id.liveValidityTv);
            liveThemePriceTv=itemView.findViewById(R.id.liveThemePriceTv);
            liveThemeBuyTv=itemView.findViewById(R.id.liveThemeBuyTv);
            livePurchseThemeImg=itemView.findViewById(R.id.livePurchseThemeImg);
            liveSendThemeTv=itemView.findViewById(R.id.liveSendThemeTv);
//            liveThemeBuyRl=itemView.findViewById(R.id.liveThemeBuyRl);
        }
    }

    public  interface  Callback{
        void callback(GetLiveThemeRoot.Detail detail);
        void purchaseLiveTheme(GetLiveThemeRoot.Detail detail,TextView textView);
        void enableLiveTheme(GetLiveThemeRoot.Detail detail,TextView textView);
        void sendLiveTheme(GetLiveThemeRoot.Detail detail,TextView textView);
    }
}

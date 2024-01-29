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

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.ModelClasses.GetFramesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseCarsRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyLookCarsRVAdpter extends RecyclerView.Adapter<MyLookCarsRVAdpter.ViewHolder> {

    List<PurchaseCarsRoot.Detail> list=new ArrayList<>();
    Context context;
    Callback callback;
    int i=0;
    SVGAParser parser;

    public MyLookCarsRVAdpter(List<PurchaseCarsRoot.Detail> list, Context context,Callback callback) {
        this.list = list;
        this.context = context;
        this.callback= callback;
    }

    @NonNull
    @Override
    public MyLookCarsRVAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mylook_cars_rv_design,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyLookCarsRVAdpter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.timeTV.setText(list.get(position).getDateFrom()+" / "+list.get(position).getExp_time());
        if (list.get(position).isApplied()) {
            holder.myLookCarEnableTv.setText("Remove");
        } else {
            holder.myLookCarEnableTv.setText("Apply");
        }
        holder.myLookCarEnableRL.setOnClickListener(view -> {
            if (!list.get(position).isApplied) {
                callback.enableCarFrames(list.get(position),position,holder.myLookCarEnableTv);
            }
        });
        holder.cars_test.setOnClickListener(view -> callback.testCars(list.get(position)));

        CommonUtils.setAnimation(context,list.get(position).getFrameIMage(), holder.muCarsRVImage);

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

        ImageView mylookCarsImg;
        TextView myLookCarEnableTv, cars_test,timeTV;
        RelativeLayout myLookCarEnableRL;
        SVGAImageView muCarsRVImage;
//        LottieAnimationView myCarLottieAniamtion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myLookCarEnableTv=itemView.findViewById(R.id.myLookCarEnableTv);
            myLookCarEnableRL=itemView.findViewById(R.id.myLookCarEnableRL);
            cars_test = itemView.findViewById(R.id.testWear);
            muCarsRVImage = itemView.findViewById(R.id.muCarsRVImage);
            timeTV = itemView.findViewById(R.id.timeTV);
        }
    }

    public interface Callback{
        void  enableCarFrames(PurchaseCarsRoot.Detail detail,int position,TextView textView);
        void testCars(PurchaseCarsRoot.Detail Cars_Detail);
    }
    public void loadData(List<PurchaseCarsRoot.Detail> list){
        this.list = list;
        notifyDataSetChanged();
    }

}

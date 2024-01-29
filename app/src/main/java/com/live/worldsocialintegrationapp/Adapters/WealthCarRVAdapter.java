package com.live.worldsocialintegrationapp.Adapters;

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
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetCarUserLevel;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetFrameByLevel;
import com.live.worldsocialintegrationapp.R;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WealthCarRVAdapter extends RecyclerView.Adapter<WealthCarRVAdapter.ViewHolder> {
    List<GetCarUserLevel.Detail> list;
    Context context;
    Callback callback;
    SVGAParser parser;

    public WealthCarRVAdapter(List<GetCarUserLevel.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public WealthCarRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wealth_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WealthCarRVAdapter.ViewHolder holder, int position) {

        holder.carLevelDayTv.setText(list.get(position).getValidity() + "(s)");
        holder.carLevelTv.setText("Unlock LV " + list.get(position).getLevel());

        if (list.get(position).isAvailable()) {

            holder.wealthCarLockRL.setVisibility(View.GONE);
            holder.wealthCarsRVImage.setVisibility(View.VISIBLE);
            holder.wealth_img.setVisibility(View.GONE);

            parser = new SVGAParser(context.getApplicationContext());

            try {
                parser.decodeFromURL(new URL(list.get(position).getLuckyId().get(0).getLuckyIdImage()), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                        SVGADrawable drawable = new SVGADrawable(videoItem);

                        holder.wealthCarsRVImage.setImageDrawable(drawable);
                        holder.wealthCarsRVImage.startAnimation();
                    }

                    @Override
                    public void onError() {

                    }
                }, new SVGAParser.PlayCallback() {
                    @Override
                    public void onPlay(@NonNull List<? extends File> list) {

                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {

            holder.wealthCarLockRL.setVisibility(View.VISIBLE);
            holder.wealthCarsRVImage.setVisibility(View.GONE);
            holder.wealth_img.setVisibility(View.VISIBLE);
            Glide.with(holder.wealth_img.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.wealth_img);


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView wealth_img;
        TextView carLevelTv, carLevelDayTv;
        RelativeLayout wealthCarLockRL;
        SVGAImageView wealthCarsRVImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wealth_img = itemView.findViewById(R.id.wealth_img);
            carLevelTv = itemView.findViewById(R.id.carLevelTv);
            carLevelDayTv = itemView.findViewById(R.id.carLevelDayTv);
            wealthCarLockRL = itemView.findViewById(R.id.wealthCarLockRL);
            wealthCarsRVImage = itemView.findViewById(R.id.wealthCarsRVImage);
        }
    }

    public interface Callback {
        void carDetail(GetCarUserLevel.Detail detail);

    }
}

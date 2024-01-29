package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetCarUserLevel;
import com.live.worldsocialintegrationapp.R;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WealthInsideCarRVAdapter extends RecyclerView.Adapter<WealthInsideCarRVAdapter.ViewHolder> {
    List<GetCarUserLevel.Detail> list;
    Context context;
    Callback callback;
    SVGAParser parser;

    public WealthInsideCarRVAdapter(List<GetCarUserLevel.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public WealthInsideCarRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wealth_car_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WealthInsideCarRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.carInsideDaysTv.setText(list.get(position).getValidity() + "(s)");
        holder.carInsideLevelTv.setText("Unlock LV " + list.get(position).getLevel());

        holder.wealthCarPreviewTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.previewCallback(list.get(position));
            }
        });

        if (list.get(position).isAvailable()) {

            holder.wealthInsideCarLockImg.setVisibility(View.GONE);
            holder.userLevelCarsRVImage.setVisibility(View.VISIBLE);
            holder.carInsideImg.setVisibility(View.INVISIBLE);

            parser = new SVGAParser(context.getApplicationContext());
            try {
                parser.decodeFromURL(new URL(list.get(position).getLuckyId().get(0).getLuckyIdImage()), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                        SVGADrawable drawable = new SVGADrawable(videoItem);

                        holder.userLevelCarsRVImage.setImageDrawable(drawable);
                        holder.userLevelCarsRVImage.startAnimation();
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

            holder.wealthInsideCarLockImg.setVisibility(View.VISIBLE);

            holder.userLevelCarsRVImage.setVisibility(View.INVISIBLE);
            holder.carInsideImg.setVisibility(View.VISIBLE);
            Glide.with(holder.carInsideImg.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.carInsideImg);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView carInsideImg, wealthInsideCarLockImg;
        TextView carInsideLevelTv, carInsideDaysTv, wealthCarPreviewTv;
        SVGAImageView userLevelCarsRVImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            carInsideImg = itemView.findViewById(R.id.carInsideImg);
            carInsideLevelTv = itemView.findViewById(R.id.carInsideLevelTv);
            carInsideDaysTv = itemView.findViewById(R.id.carInsideDaysTv);
            wealthCarPreviewTv = itemView.findViewById(R.id.wealthCarPreviewTv);
            wealthInsideCarLockImg = itemView.findViewById(R.id.wealthInsideCarLockImg);
            userLevelCarsRVImage = itemView.findViewById(R.id.userLevelCarsRVImage);
        }
    }

    public interface Callback {
        void previewCallback(GetCarUserLevel.Detail detail);
    }
}

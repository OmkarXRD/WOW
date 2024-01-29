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
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.ModelClasses.GetFramesRoot;
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
import java.util.ArrayList;
import java.util.List;

public class WealthInsideFrameRVAdapter extends RecyclerView.Adapter<WealthInsideFrameRVAdapter.ViewHolder> {

    List<GetFrameByLevel.Detail> list=new ArrayList<>();
    Context context ;
    SVGAParser parser;

    public WealthInsideFrameRVAdapter(List<GetFrameByLevel.Detail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public WealthInsideFrameRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wealth_frame_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WealthInsideFrameRVAdapter.ViewHolder holder, int position) {


        holder.frameInsideDayTv.setText(list.get(position).getValidity()+"(s)");
        holder.frameInsideLevelTv.setText("Unlock LV "+list.get(position).getLevel());


        if (list.get(position).isAvailable()) {

            holder.wealthFrameLockImg.setVisibility(View.GONE);

            holder.userLevelFramesRVImageSVGA.setVisibility(View.VISIBLE);
            holder.frameInsideImg.setVisibility(View.INVISIBLE);

            if(list.get(position).getFrames() != null){
                parser = new SVGAParser(context.getApplicationContext());
                try {
                    parser.decodeFromURL(new URL(list.get(position).getFrames().get(0).getFrameImage()), new SVGAParser.ParseCompletion() {
                        @Override
                        public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                            SVGADrawable drawable = new SVGADrawable(videoItem);

                            holder.userLevelFramesRVImageSVGA.setImageDrawable(drawable);
                            holder.userLevelFramesRVImageSVGA.startAnimation();
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
            }

        } else {

            holder.wealthFrameLockImg.setVisibility(View.VISIBLE);

            holder.userLevelFramesRVImageSVGA.setVisibility(View.INVISIBLE);
            holder.frameInsideImg.setVisibility(View.VISIBLE);
            Glide.with(holder.frameInsideImg.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.frameInsideImg);        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView frameInsideImg,wealthFrameLockImg;
        TextView frameInsideLevelTv,frameInsideDayTv;
        SVGAImageView userLevelFramesRVImageSVGA;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            frameInsideImg = itemView.findViewById(R.id.frameInsideImg);
            wealthFrameLockImg = itemView.findViewById(R.id.wealthFrameLockImg);
            frameInsideLevelTv = itemView.findViewById(R.id.frameInsideLevelTv);
            frameInsideDayTv = itemView.findViewById(R.id.frameInsideDayTv);
            userLevelFramesRVImageSVGA = itemView.findViewById(R.id.userLevelFramesRVImageSVGA);


        }
    }
}

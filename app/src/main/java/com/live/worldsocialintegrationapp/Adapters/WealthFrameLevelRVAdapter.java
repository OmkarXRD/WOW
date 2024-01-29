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

public class WealthFrameLevelRVAdapter extends RecyclerView.Adapter<WealthFrameLevelRVAdapter.ViewHolder> {

    List<GetFrameByLevel.Detail> list;
    Context context;
    Callback callback;
    SVGAParser parser;

    public WealthFrameLevelRVAdapter(List<GetFrameByLevel.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public WealthFrameLevelRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wealth_frame_level_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WealthFrameLevelRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.frameLevelDayTv.setText(list.get(position).getValidity()+"(s)");
        holder.frameLevelTv.setText("Unlock LV "+list.get(position).getLevel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.frameDetail(list.get(position));
            }
        });

        if(list.get(position).isAvailable()){
            holder.wealthFrameLockRL.setVisibility(View.GONE);

            holder.frameLevelImg.setVisibility(View.GONE);
            holder.wealthFramesRVImage.setVisibility(View.VISIBLE);


            parser = new SVGAParser(context.getApplicationContext());
            if(list.get(position).getFrames() != null){
                try {
                    parser.decodeFromURL(new URL(list.get(position).getFrames().get(0).getFrameImage()), new SVGAParser.ParseCompletion() {
                        @Override
                        public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                            SVGADrawable drawable = new SVGADrawable(videoItem);

                            holder.wealthFramesRVImage.setImageDrawable(drawable);
                            holder.wealthFramesRVImage.startAnimation();
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

        }else{

            holder.wealthFrameLockRL.setVisibility(View.VISIBLE);
            holder.frameLevelImg.setVisibility(View.VISIBLE);
            holder.wealthFramesRVImage.setVisibility(View.GONE);

            Glide.with(holder.frameLevelImg.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.frameLevelImg);

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView frameLevelImg;
        TextView frameLevelTv,frameLevelDayTv;
        RelativeLayout wealthFrameLockRL;
        SVGAImageView wealthFramesRVImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            frameLevelImg = itemView.findViewById(R.id.frameLevelImg);
            frameLevelTv = itemView.findViewById(R.id.frameLevelTv);
            frameLevelDayTv = itemView.findViewById(R.id.frameLevelDayTv);
            wealthFrameLockRL = itemView.findViewById(R.id.wealthFrameLockRL);
            wealthFramesRVImage=itemView.findViewById(R.id.wealthFramesRVImage);

        }
    }

    public interface Callback {

        void frameDetail(GetFrameByLevel.Detail detail);
    }
}

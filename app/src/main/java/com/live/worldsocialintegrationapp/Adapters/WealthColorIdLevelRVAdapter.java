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
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetColorByLevel;
import com.live.worldsocialintegrationapp.R;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WealthColorIdLevelRVAdapter extends RecyclerView.Adapter<WealthColorIdLevelRVAdapter.ViewHolder> {

    List<GetColorByLevel.Detail> list;
    Context context;
    Callback callback;
    SVGAParser parser;

    public WealthColorIdLevelRVAdapter(List<GetColorByLevel.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public WealthColorIdLevelRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wealth_color_id_level_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WealthColorIdLevelRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.colorIdLevelDayTv.setText(list.get(position).getValidity() + "(s)");
        holder.colorIdLevelTv.setText("Unlock LV " + list.get(position).getLevel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.colorIdDetail(list.get(position));
            }
        });

        if (list.get(position).isAvailable()) {

            holder.colorIdLockRL.setVisibility(View.GONE);
//            holder.wealthColorsRVImage.setVisibility(View.VISIBLE);  //svga
//            holder.ColorIdLevelImg.setVisibility(View.GONE);   //gif

//            parser = new SVGAParser(context.getApplicationContext());
//            try {
//                parser.decodeFromURL(new URL(list.get(position).getThemes().get(0).getTheme()), new SVGAParser.ParseCompletion() {
//                    @Override
//                    public void onComplete(@NotNull SVGAVideoEntity videoItem) {
//                        SVGADrawable drawable = new SVGADrawable(videoItem);
//
//                        holder.wealthColorsRVImage.setImageDrawable(drawable);
//                        holder.wealthColorsRVImage.startAnimation();
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//                }, new SVGAParser.PlayCallback() {
//                    @Override
//                    public void onPlay(@NonNull List<? extends File> list) {
//
//                    }
//                });
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
            holder.ColorIdLevelImg.setVisibility(View.VISIBLE);
            Glide.with(holder.ColorIdLevelImg.getContext()).load(list.get(position).getThemes().get(0).getTheme()).error(R.drawable.demo_user_profile_img).into(holder.ColorIdLevelImg);


        } else {

            holder.colorIdLockRL.setVisibility(View.VISIBLE);
            holder.wealthColorsRVImage.setVisibility(View.GONE);
            holder.ColorIdLevelImg.setVisibility(View.VISIBLE);

            Glide.with(holder.ColorIdLevelImg.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.ColorIdLevelImg);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ColorIdLevelImg;
        TextView colorIdLevelTv, colorIdLevelDayTv;
        RelativeLayout colorIdLockRL;
        SVGAImageView wealthColorsRVImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ColorIdLevelImg = itemView.findViewById(R.id.ColorIdLevelImg);
            colorIdLevelTv = itemView.findViewById(R.id.colorIdLevelTv);
            colorIdLevelDayTv = itemView.findViewById(R.id.colorIdLevelDayTv);
            colorIdLockRL = itemView.findViewById(R.id.colorIdLockRL);
            wealthColorsRVImage = itemView.findViewById(R.id.wealthColorsRVImage);
        }
    }

    public interface Callback {
        void colorIdDetail(GetColorByLevel.Detail detail);
    }
}

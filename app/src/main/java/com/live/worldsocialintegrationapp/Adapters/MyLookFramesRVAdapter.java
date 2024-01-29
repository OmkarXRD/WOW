package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseCarsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseFramesRoot;
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

public class MyLookFramesRVAdapter extends RecyclerView.Adapter<MyLookFramesRVAdapter.ViewHolder> {

    List<PurchaseFramesRoot.Detail> list = new ArrayList<>();
    Context context;
    Callback callback;
    SVGAParser parser;

    public MyLookFramesRVAdapter(List<PurchaseFramesRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyLookFramesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mylook_frames_rv_design, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyLookFramesRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        if (list.get(position).isApplied()) {
            holder.myframesEnableTV.setText("Remove");
        } else {
            holder.myframesEnableTV.setText("Apply");
        }

        holder.timeTV.setText(list.get(position).getDateFrom()+" / "+list.get(position).getExp_time());
        holder.mylookEnableRL.setOnClickListener(view -> callback.enableFrame(list.get(position), holder.myframesEnableTV));

        parser = new SVGAParser(context.getApplicationContext());
        try {
            parser.decodeFromURL(new URL(list.get(position).getFrameIMage()), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    SVGADrawable drawable = new SVGADrawable(videoItem);
                    holder.mylookFrameImg.setImageDrawable(drawable);
                    holder.mylookFrameImg.startAnimation();
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

        holder.cars_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.testCars(list.get(position));
            }
        });
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

        SVGAImageView mylookFrameImg;
        RelativeLayout mylookEnableRL;
        TextView myframesEnableTV, cars_test,timeTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mylookFrameImg = itemView.findViewById(R.id.mylookFrameImg);
            mylookEnableRL = itemView.findViewById(R.id.mylookEnableRL);
            myframesEnableTV = itemView.findViewById(R.id.myframesEnableTV);
            cars_test = itemView.findViewById(R.id.testWear);
            timeTV = itemView.findViewById(R.id.timeTV);
        }
    }

    public interface Callback {
        void enableFrame(PurchaseFramesRoot.Detail detail, TextView view);
        void testCars(PurchaseFramesRoot.Detail frame_Detail);
    }

    public void loadData(List<PurchaseFramesRoot.Detail> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}

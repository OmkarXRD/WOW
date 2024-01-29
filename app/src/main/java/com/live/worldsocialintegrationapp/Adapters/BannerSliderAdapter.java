package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.BannerSliderRoot;
import com.live.worldsocialintegrationapp.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class BannerSliderAdapter extends SliderViewAdapter<BannerSliderAdapter.SliderAdapterViewHolder> {

    private final List<BannerSliderRoot.Detail> mSliderItems;
    Callback callback;

    public BannerSliderAdapter(List<BannerSliderRoot.Detail> mSliderItems, Callback callback) {
        this.mSliderItems = mSliderItems;
        this.callback = callback;
    }

    public interface Callback{
        void onClickCallback(int pos);
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_slider_design, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {

        // Glide is use to load image
        // from url in your imageview.
        Glide.with(viewHolder.itemView)
                .load(mSliderItems.get(position).getImage())
                .fitCenter()
                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickCallback(position);
            }
        });
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    public class SliderAdapterViewHolder extends ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);

            imageViewBackground = itemView.findViewById(R.id.myimage);
            this.itemView = itemView;
        }
    }
}

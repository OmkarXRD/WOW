package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserImagesRoot;
import com.live.worldsocialintegrationapp.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import java.util.List;


public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderViewHolder> {

    Context context;
    List<GetUserImagesRoot.Detail> list;
    Callback callback;

    public SliderAdapterExample(Context context, List<GetUserImagesRoot.Detail> list, Callback callback) {
        this.context = context;
        this.list = list;
        this.callback = callback;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_layout, null);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {

        Glide.with(viewHolder.imageView.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(viewHolder.imageView);
        viewHolder.sliderImageTv.setText(String.valueOf(position+"/"+list.size()));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.bgCallback("1");
            }
        });
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public class SliderViewHolder extends ViewHolder {
        private ImageView imageView;
        private TextView textView,sliderImageTv;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            sliderImageTv = itemView.findViewById(R.id.sliderImageTv);
        }
    }

    public interface Callback {
        void bgCallback(String image);
    }
}

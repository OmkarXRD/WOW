package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserImagesRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class ImageRVAdpater extends PagerAdapter {

    Context context;
    List<GetUserImagesRoot.Detail> list;

    public ImageRVAdpater(Context context, List<GetUserImagesRoot.Detail> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(list.get(position).getImage());
        Glide.with(imageView.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(imageView);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}

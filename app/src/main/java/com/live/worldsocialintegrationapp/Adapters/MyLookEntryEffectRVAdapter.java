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
import com.live.worldsocialintegrationapp.ModelClasses.EntryEffectsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseFramesRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class MyLookEntryEffectRVAdapter extends RecyclerView.Adapter<MyLookEntryEffectRVAdapter.ViewHolder> {

    List<EntryEffectsRoot.Detail> list;
    Context context;
    Callback callback;

    public MyLookEntryEffectRVAdapter(List<EntryEffectsRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }



    @NonNull
    @Override
    public MyLookEntryEffectRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mylook_entry_effects_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyLookEntryEffectRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(holder.mylookEntryEffectsImg.getContext()).load(list.get(position).getGifUrl())
                .error(R.drawable.demo_user_profile_img).into(holder.mylookEntryEffectsImg);


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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mylookEntryEffectsImg;
        TextView  cars_test ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mylookEntryEffectsImg=itemView.findViewById(R.id.mylookEntryEffectsImg);
            cars_test = itemView.findViewById(R.id.testWear);
        }
    }

    public interface Callback {
        void enableFrame(EntryEffectsRoot.Detail detail, TextView view);

        void testCars(EntryEffectsRoot.Detail frame_Detail);
    }
}

package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetLiveBackgroundImagesRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class AdapterLiveThemes extends RecyclerView.Adapter<AdapterLiveThemes.Holder> {
    List<GetLiveBackgroundImagesRoot.Detail> list;
    Callback callback;

    public AdapterLiveThemes(List<GetLiveBackgroundImagesRoot.Detail> list, Callback callback) {
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public AdapterLiveThemes.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_themes, parent, false);
        Holder viewHolder = new Holder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLiveThemes.Holder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(holder.theamImage.getContext()).load(list.get(position).getImages()).error(R.drawable.wowicon).into(holder.theamImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.setBackground(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView theamImage;

        public Holder(@NonNull View itemView) {
            super(itemView);
            theamImage = itemView.findViewById(R.id.theamImage);
        }
    }

    public interface Callback {
        void setBackground(GetLiveBackgroundImagesRoot.Detail list);
    }
}
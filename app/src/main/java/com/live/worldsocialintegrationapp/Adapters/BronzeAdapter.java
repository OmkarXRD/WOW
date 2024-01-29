package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.R;
import com.opensource.svgaplayer.SVGAImageView;

import java.util.Arrays;
import java.util.List;

public class BronzeAdapter extends RecyclerView.Adapter<BronzeAdapter.holder> {

    List<String> list;
    Context context;

    @NonNull
    @Override
    public BronzeAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.svgaimageviewlayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BronzeAdapter.holder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class holder extends RecyclerView.ViewHolder {

        SVGAImageView imageView;
        public holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

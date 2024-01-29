package com.live.worldsocialintegrationapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.R;

public class MomentsNotificationAdapter extends RecyclerView.Adapter<MomentsNotificationAdapter.InnerClassHolder> {
    @NonNull
    @Override
    public InnerClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerClassHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.moments_notification_items , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerClassHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class InnerClassHolder extends RecyclerView.ViewHolder {
        public InnerClassHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

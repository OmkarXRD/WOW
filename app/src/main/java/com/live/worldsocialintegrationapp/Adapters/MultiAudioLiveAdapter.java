package com.live.worldsocialintegrationapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.R;

public class MultiAudioLiveAdapter extends RecyclerView.Adapter<MultiAudioLiveAdapter.InnerClassAdapter> {
    @NonNull
    @Override
    public InnerClassAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerClassAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_live_items, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull InnerClassAdapter holder, int position) {

        if (holder.getAdapterPosition() == 0) {
            holder.countMutliAudio.setText("1");
            holder.starMultiAudio.setVisibility(View.VISIBLE);
        }
        if (holder.getAdapterPosition() == 1) {
            holder.countMutliAudio.setText("2");
            holder.starMultiAudio.setVisibility(View.VISIBLE);
        }
        if (holder.getAdapterPosition() == 2) {
            holder.countMutliAudio.setText("3");
            holder.starMultiAudio.setVisibility(View.GONE);
        }
        if (holder.getAdapterPosition() == 3) {
            holder.countMutliAudio.setText("4");
            holder.starMultiAudio.setVisibility(View.GONE);
        }
        if (holder.getAdapterPosition() == 4) {
            holder.countMutliAudio.setText("5");
            holder.starMultiAudio.setVisibility(View.GONE);
        }
        if (holder.getAdapterPosition() == 5) {
            holder.countMutliAudio.setText("6");
            holder.starMultiAudio.setVisibility(View.GONE);
        }
        if (holder.getAdapterPosition() == 6) {
            holder.countMutliAudio.setText("7");
            holder.starMultiAudio.setVisibility(View.GONE);
        }
        if (holder.getAdapterPosition() == 7) {
            holder.countMutliAudio.setText("8");
            holder.starMultiAudio.setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        return 8;
    }

    public static class InnerClassAdapter extends RecyclerView.ViewHolder {
        private ImageView starMultiAudio;
        private TextView countMutliAudio;
        public InnerClassAdapter(@NonNull View itemView) {
            super(itemView);
            countMutliAudio = itemView.findViewById(R.id.countMutliAudio);
            starMultiAudio = itemView.findViewById(R.id.starMultiAudio);
        }
    }
}

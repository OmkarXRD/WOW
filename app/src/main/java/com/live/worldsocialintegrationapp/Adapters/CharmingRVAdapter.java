package com.live.worldsocialintegrationapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.R;

public class CharmingRVAdapter extends RecyclerView.Adapter<CharmingRVAdapter.ViewHolder> {
    @NonNull
    @Override
    public CharmingRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.charming_rv_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharmingRVAdapter.ViewHolder holder, int position) {

        if(position==1){
            holder.receivedTV.setText("Received Tricks");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView receivedTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receivedTV=itemView.findViewById(R.id.receivedTV);
        }
    }
}

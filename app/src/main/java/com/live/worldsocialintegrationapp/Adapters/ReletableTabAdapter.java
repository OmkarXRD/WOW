package com.live.worldsocialintegrationapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.R;

public class ReletableTabAdapter extends RecyclerView.Adapter<ReletableTabAdapter.InnerClassAdapter> {
    @NonNull
    @Override
    public InnerClassAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerClassAdapter( LayoutInflater.from( parent.getContext() ).inflate( R.layout.family_tab_items, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull InnerClassAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class InnerClassAdapter extends RecyclerView.ViewHolder {
        public InnerClassAdapter(@NonNull View itemView) {
            super( itemView );
        }
    }
}

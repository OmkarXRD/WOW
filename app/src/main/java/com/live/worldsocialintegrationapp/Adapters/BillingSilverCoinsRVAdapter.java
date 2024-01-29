package com.live.worldsocialintegrationapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.R;

public class BillingSilverCoinsRVAdapter extends RecyclerView.Adapter<BillingSilverCoinsRVAdapter.ViewHolder> {
    @NonNull
    @Override
    public BillingSilverCoinsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.billing_silver_coins_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillingSilverCoinsRVAdapter.ViewHolder holder, int position) {

        if(position == 0){
            holder.billingRvMonthName.setVisibility(View.VISIBLE);
        }else{
            holder.billingRvMonthName.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout billingRvMonthName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            billingRvMonthName=itemView.findViewById(R.id.billingRvMonthName);
        }
    }
}

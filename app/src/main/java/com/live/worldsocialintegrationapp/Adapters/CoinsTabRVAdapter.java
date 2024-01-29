package com.live.worldsocialintegrationapp.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.ModelClasses.GenerateOrderRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetCoin.Detail;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.RazorPay.PaymentActivity;

import java.util.List;

public class CoinsTabRVAdapter extends RecyclerView.Adapter<CoinsTabRVAdapter.ViewHolder> {


    List<Detail> list;
    Context context;
    Callback callback ;

    public CoinsTabRVAdapter(List<Detail> list,Context context,Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public CoinsTabRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.coins_tab_rv_design,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CoinsTabRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.coinsTv.setText(list.get(position).getCoinValue());
        holder.coinsMoneyTV.setText("₹"+list.get(position).getMoneyValue());

        holder.itemView.setOnClickListener(view -> callback.callback(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView coinsTv,coinsMoneyTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            coinsTv=itemView.findViewById(R.id.coinsTv);
            coinsMoneyTV=itemView.findViewById(R.id.coinsMoneyTV);
        }
    }

    public interface  Callback{
        void callback(Detail value);
    }
}

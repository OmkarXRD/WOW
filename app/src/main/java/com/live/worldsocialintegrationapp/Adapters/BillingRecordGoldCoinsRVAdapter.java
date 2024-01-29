package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.ModelClasses.PurchaseHistory.PurchaseLuckyIdHistory;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.openvcall.model.BillingRecordModelClass;

import java.util.ArrayList;
import java.util.List;

public class BillingRecordGoldCoinsRVAdapter extends RecyclerView.Adapter<BillingRecordGoldCoinsRVAdapter.ViewHodler> {

    List<BillingRecordModelClass.Detail> list= new ArrayList<>();
    Context context;

    public BillingRecordGoldCoinsRVAdapter(List<BillingRecordModelClass.Detail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public BillingRecordGoldCoinsRVAdapter.ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHodler(LayoutInflater.from(parent.getContext()).inflate(R.layout.billing_record_gold_coins_history,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillingRecordGoldCoinsRVAdapter.ViewHodler holder, int position) {

        holder.billingGoldCoinTV.setText(list.get(position).getCoins());
        holder.silverCoinsTV.setText(list.get(position).getMessage());
        holder.textViewDate.setText(list.get(position).getCreated());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        TextView billingGoldCoinTV,silverCoinsTV,textViewDate;
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            billingGoldCoinTV = itemView.findViewById(R.id.billingGoldCoinTV);
            silverCoinsTV = itemView.findViewById(R.id.silverCoinsTV);
            textViewDate = itemView.findViewById(R.id.textViewDate);
        }
    }
}

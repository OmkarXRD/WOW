package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.ModelClasses.GetCoin.GetSilverCoinRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class SilverCoinsTabRVAdapter extends RecyclerView.Adapter<SilverCoinsTabRVAdapter.ViewHolder> {

    List<GetSilverCoinRoot.Detail>  list;
    Callback callback;

    public SilverCoinsTabRVAdapter(List<GetSilverCoinRoot.Detail> list, Callback callback) {
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public SilverCoinsTabRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.silver_coins_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SilverCoinsTabRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.silverCoinsTV.setText(list.get(position).getCoinValue());
        holder.silverRVMoney.setText(list.get(position).getMoneyValue());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callback.callback(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView silverCoinsTV,silverRVMoney;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            silverCoinsTV=itemView.findViewById(R.id.silverCoinsTV);
            silverRVMoney=itemView.findViewById(R.id.silverRVMoney);
        }
    }

    public interface  Callback{
        void callback(GetSilverCoinRoot.Detail value);
    }
}

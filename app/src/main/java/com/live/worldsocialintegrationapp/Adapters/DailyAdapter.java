package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.ModelClasses.DailyTask.GetDailyTaskRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.MyViewHolder> {

    Callback callback;
    List<GetDailyTaskRoot.Detail> list;
    Context context;

    public DailyAdapter(Callback callback, List<GetDailyTaskRoot.Detail> list, Context context) {
        this.callback = callback;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String status = list.get(position).getDaytype().toString();
        if (status.equalsIgnoreCase("0")) {
            holder.dailyTaskGoRL.setVisibility(View.VISIBLE);
            holder.dailyTaskDoneRL.setVisibility(View.GONE);
        } else if(status.equalsIgnoreCase("1")) {
            holder.dailyTaskGoRL.setVisibility(View.GONE);
            holder.dailyTaskDoneRL.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.callback(list.get(position).getDay());
            }
        });

        if (list.get(position).getReward().equalsIgnoreCase("100")) {
            holder.hour_name.setText("Send any gift for 1 time");
            holder.plusCoinsTV.setText(" +" + list.get(position).getReward());

        } else {
            holder.hour_name.setText("Play 1 round of lucky finishing");
            holder.plusCoinsTV.setText(" +" + list.get(position).getReward());
        }

        if (list.get(position).getRewardType().equalsIgnoreCase("10") && list.get(position).getReward().equalsIgnoreCase("10")) {
            holder.hour_name.setText("Check in");
            holder.plusCoinsTV.setText(" +" + list.get(position).getReward() + "  Test wear");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout dailyTaskGoRL, dailyTaskDoneRL;
        TextView plusCoinsTV, hour_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dailyTaskGoRL = itemView.findViewById(R.id.dailyTaskGoRL);
            dailyTaskDoneRL = itemView.findViewById(R.id.dailyTaskDoneRL);
            plusCoinsTV = itemView.findViewById(R.id.plusCoinsTV);
            hour_name = itemView.findViewById(R.id.hour_name);
        }
    }

    public interface Callback {
        void callback(int value);
    }

}
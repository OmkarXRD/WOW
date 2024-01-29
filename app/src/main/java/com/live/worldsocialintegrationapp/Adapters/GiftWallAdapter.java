package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GiftWall.RootGiftWall;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GiftWallAdapter extends RecyclerView.Adapter<GiftWallAdapter.ViewHolder> {

    private List<RootGiftWall.Detail> list;
    Context context;
    Callback callback;

    public GiftWallAdapter(List<RootGiftWall.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public GiftWallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gift_wall_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GiftWallAdapter.ViewHolder holder, int position) {

        holder.coinGift.setVisibility(View.VISIBLE);

        holder.reciverName.setText(list.get(position).getReceiverName());
        holder.senderName.setText(list.get(position).getSenderName());
        holder.coinGift.setText(list.get(position).getDiamond());

        Glide.with(context).load(list.get(position).getSenderImage()).error(R.drawable.user).into(holder.senderImage);
        Glide.with(context).load(list.get(position).getReceiverImage()).error(R.drawable.user).into(holder.reciverImage);
    }

    public void loadData(List<RootGiftWall.Detail> ListData) {
        list = ListData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView senderName , reciverName, coinGift ;
        CircleImageView senderImage , reciverImage ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            senderName = itemView.findViewById(R.id.senderName);
            reciverName = itemView.findViewById(R.id.reciverName);
            senderImage = itemView.findViewById(R.id.senderImage);
            reciverImage = itemView.findViewById(R.id.reciverImage);
            coinGift = itemView.findViewById(R.id.coinsGiftWall);

        }
    }

    public interface  Callback {
        void callback(RootGiftWall.Detail detail, int pos);
    }
}
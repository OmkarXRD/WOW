package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.wowsBoard.Datum;
import com.live.worldsocialintegrationapp.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRooms extends RecyclerView.Adapter<AdapterRooms.MyViewHolder> {

    List<Datum> list = new ArrayList<>();
    Context context;
    Callback callback;
    int i = 3;

    public AdapterRooms(List<Datum> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        i++;

        holder.one1Tv.setText(String.valueOf(i));

        if (list.get(position).getDiamond().equalsIgnoreCase("0")) {

        } else if (list.get(position).getUserDetails().getProfileImage() == null) {
        } else {
            holder.hour_name.setText(list.get(position).getUserDetails().getName());
            Glide.with(holder.roomsCirImg.getContext()).load(list.get(position).getUserDetails().getProfileImage().getImage())
                    .error(R.drawable.demo_user_profile_img).into(holder.roomsCirImg);
            holder.roomDiamondsTv.setText(list.get(position).getDiamond());

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView hour_name, roomDiamondsTv, one1Tv;
        CircleImageView roomsCirImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            hour_name = itemView.findViewById(R.id.hour_name);
            roomDiamondsTv = itemView.findViewById(R.id.roomDiamondsTv);
            roomsCirImg = itemView.findViewById(R.id.roomsCirImg);
            one1Tv = itemView.findViewById(R.id.one1Tv);
        }
    }

    public interface Callback {
        void callback(Datum datum);
    }
}

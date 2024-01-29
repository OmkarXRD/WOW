package com.live.worldsocialintegrationapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetLiveGiftHistoryRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ScoreBoardHistroyRVAdapter extends RecyclerView.Adapter<ScoreBoardHistroyRVAdapter.ViewHolder> {
    List<GetLiveGiftHistoryRoot.Detail> list;

    public ScoreBoardHistroyRVAdapter(List<GetLiveGiftHistoryRoot.Detail> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ScoreBoardHistroyRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver_gift_histroy_rv_design, parent, false));
    }

        @Override
    public void onBindViewHolder(@NonNull ScoreBoardHistroyRVAdapter.ViewHolder holder, int position) {
            Glide.with(holder.giftersCirImg.getContext()).load(list.get(position).getSenderImage()).error(R.drawable.demo_user_profile_img)
                    .into(holder.giftersCirImg);
            holder.gifterName.setText(list.get(position).getSender_name());
            holder.gifterDiamondTV.setText(list.get(position).getDiamond());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView giftersCirImg;
        TextView gifterName,gifterDiamondTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gifterDiamondTV=itemView.findViewById(R.id.gifterDiamondTV);
            giftersCirImg=itemView.findViewById(R.id.giftersCirImg);
            gifterName=itemView.findViewById(R.id.gifterName);
        }
    }
}

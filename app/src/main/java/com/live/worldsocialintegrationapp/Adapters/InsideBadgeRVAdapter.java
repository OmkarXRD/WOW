package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserTalentLevelRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class InsideBadgeRVAdapter extends RecyclerView.Adapter<InsideBadgeRVAdapter.ViewHolder> {
    List<GetUserTalentLevelRoot.Child> list;
    Context context;

    public InsideBadgeRVAdapter(List<GetUserTalentLevelRoot.Child> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public InsideBadgeRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.inside_badges_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InsideBadgeRVAdapter.ViewHolder holder, int position) {

        Glide.with(holder.bageImg.getContext()).load(list.get(position).getImage()).error(R.drawable.pk_queen).into(holder.bageImg);
        holder.badgesTv.setText("This Achievement Badge is only for Weekly Star Event Reward in exclusive regions.");

        if (position == 0) {
            holder.bgDetailTV.setText("Super Star Level.1");
        } else if (position == 1) {
            holder.bgDetailTV.setText("Super Star Level.2");
        } else {
            holder.bgDetailTV.setText("Super Star Level.3");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView bageImg;
        TextView badgesTv, bgDetailTV;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            bageImg = itemView.findViewById(R.id.bageImg);
            badgesTv = itemView.findViewById(R.id.badgesTv);
            bgDetailTV = itemView.findViewById(R.id.bgDetailTV);
        }
    }
}

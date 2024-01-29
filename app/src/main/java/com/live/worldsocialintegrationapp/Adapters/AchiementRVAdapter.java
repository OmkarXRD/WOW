package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserTalentLevelRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class AchiementRVAdapter extends RecyclerView.Adapter<AchiementRVAdapter.ViewHodlder> {
    Callback callback;
    Context context;
    List<GetUserTalentLevelRoot.Detail> list;

    public AchiementRVAdapter(Context context, List<GetUserTalentLevelRoot.Detail> list,Callback callback) {
        this.callback = callback;
        this.context = context;
        this.list = list ;
    }

    @NonNull
    @Override
    public AchiementRVAdapter.ViewHodlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHodlder(LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AchiementRVAdapter.ViewHodlder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(holder.achievementImg.getContext()).load(list.get(position).getImage())
                .error(R.drawable.demo_user_profile_img).into(holder.achievementImg);

        holder.itemView.setOnClickListener(view -> callback.callback(list.get(position).getChild()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodlder extends RecyclerView.ViewHolder {
        ImageView achievementImg;
        public ViewHodlder(@NonNull View itemView) {
            super(itemView);
            achievementImg= itemView.findViewById(R.id.achievementImg);
        }
    }

    public interface Callback{

        public void callback(List<GetUserTalentLevelRoot.Child> childDetail);
    }
}
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
import com.live.worldsocialintegrationapp.ModelClasses.Family.Joiner;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class familyMembersRVAdapter extends RecyclerView.Adapter<familyMembersRVAdapter.ViewHolder> {
    List<Joiner> list;
    Context context;
    Callback callback;

    public familyMembersRVAdapter(List<Joiner> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public familyMembersRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.family_member_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull familyMembersRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (list.get(position).getIs_admin().equalsIgnoreCase("1")){
            holder.isAdmin.setVisibility(View.VISIBLE);
        }else {
            holder.isAdmin.setVisibility(View.GONE);
        }
        if (position==1){
            Glide.with(holder.familyMemberCirImg.getContext()).load(list.get(position).getUserProfileImage()).error(R.drawable.demo_user_profile_img).into(holder.familyMemberCirImg);
        }else {
            Glide.with(holder.familyMemberCirImg.getContext()).load(list.get(position).getUserProfileImage()).error(R.drawable.demo_user_profile_img).into(holder.familyMemberCirImg);
        }
        holder.itemView.setOnClickListener(view -> callback.callback(list.get(position),position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView familyMemberCirImg;
        private ImageView isAdmin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            familyMemberCirImg = itemView.findViewById(R.id.familyMemberCirImg);
            isAdmin = itemView.findViewById(R.id.isAdmin);
        }
    }


    public interface Callback {
        void callback(Joiner joiner,int position);
    }
}

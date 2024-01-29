package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.AdminFirebaseRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class AdminRVAdapter extends RecyclerView.Adapter<AdminRVAdapter.ViewHolder> {
    List<AdminFirebaseRoot> list;
    Context context;
    Callback callback;

    public AdminRVAdapter(List<AdminFirebaseRoot> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public AdminRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(holder.adminImg.getContext()).load(list.get(position).getAdminImg()).error(R.drawable.demo_user_profile_img).into(holder.adminImg);
        holder.adminNameTv.setText(list.get(position).getAdminName());

        holder.adminDelImg.setOnClickListener(new View.OnClickListener() {
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
        ImageView adminImg,adminDelImg;
        TextView adminNameTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            adminImg=itemView.findViewById(R.id.adminImg);
            adminDelImg=itemView.findViewById(R.id.adminDelImg);
            adminNameTv=itemView.findViewById(R.id.adminNameTv);
        }
    }
    public interface  Callback{
        void callback(AdminFirebaseRoot adminFirebaseRoot);
    }
}

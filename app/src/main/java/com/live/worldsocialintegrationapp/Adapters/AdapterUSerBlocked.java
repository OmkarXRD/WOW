package com.live.worldsocialintegrationapp.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.BlockUser.RootBlocked;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterUSerBlocked extends RecyclerView.Adapter<AdapterUSerBlocked.Holder> {

    List<RootBlocked.Detail> list;
    Context context;
    Callback callback ;

    public AdapterUSerBlocked(List<RootBlocked.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public AdapterUSerBlocked.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_block_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUSerBlocked.Holder holder, int position) {


        holder.userName.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getImageDp()).error(R.drawable.demo_user_profile_img).into(holder.userImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.openUserProfile(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView userName ;
        CircleImageView userImage ;
        public Holder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.blockUSerTxt);
            userImage = itemView.findViewById(R.id.blockUserImag);
        }
    }

    public interface Callback{
        void openUserProfile(RootBlocked.Detail detail);

    }
}
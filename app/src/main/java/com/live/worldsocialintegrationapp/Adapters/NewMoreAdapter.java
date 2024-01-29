package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.RootNewUser;
import com.live.worldsocialintegrationapp.R;

import java.util.ArrayList;
import java.util.List;

public class NewMoreAdapter extends RecyclerView.Adapter<NewMoreAdapter.InnerClassAdapter> {

    private List<RootNewUser.Detail> list = new ArrayList<>();
    Context context;
    Callback callback;

    public NewMoreAdapter(List<RootNewUser.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public InnerClassAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerClassAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerClassAdapter holder, @SuppressLint("RecyclerView") int position) {

        //0 means unhide hai or 1 means hide hai
//        if(list.get(position).getLiveHideUnhideStatus().equalsIgnoreCase("0")) {
//            holder.meetfragCardViewId.setVisibility(View.VISIBLE);
//        }else{
//            holder.meetfragCardViewId.setVisibility(View.GONE);
//        }

            if (list.get(position).getLiveimage().isEmpty()) {
                Glide.with(holder.userImage.getContext()).load(list.get(position).getImageDp()).error(R.drawable.demo_user_profile_img).into(holder.userImage);
            } else {
                Glide.with(holder.userImage.getContext()).load(list.get(position).getLiveimage()).error(R.drawable.demo_user_profile_img).into(holder.userImage);
            }

            if (!list.get(position).getPassword().equalsIgnoreCase("")) {
                holder.lockLiveImage.setVisibility(View.VISIBLE);
            } else {
                holder.lockLiveImage.setVisibility(View.GONE);
            }

            holder.UserName.setText(list.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (list.get(position).getPassword().equalsIgnoreCase("")) {
                        callback.callback(list.get(position), position);
                    } else {
                        Toast.makeText(context, "Live is private", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            holder.lockLiveImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.enterPassword(list.get(position), position);
                }
            });

        if(list.get(position).getImageText()!=null){
//            holder.newMorenameUSer.set("Any");
            holder.newMorenameUSer.setText("Any");
        }else{
            holder.newMorenameUSer.setText(list.get(position).getImageText());
        }


    }

    public void loadData(List<RootNewUser.Detail> ListData) {
        list = ListData;

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class InnerClassAdapter extends RecyclerView.ViewHolder {
        ImageView userImage, lockLiveImage;
        TextView UserName,newMorenameUSer;
        CardView meetfragCardViewId;

        public InnerClassAdapter(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.userImage);
            UserName = itemView.findViewById(R.id.Name_user);
            lockLiveImage = itemView.findViewById(R.id.lockLiveImage);
            newMorenameUSer = itemView.findViewById(R.id.newMorenameUSer);
            meetfragCardViewId = itemView.findViewById(R.id.meetfragCardViewId);

        }
    }

    public interface Callback {

        void callback(RootNewUser.Detail detail, int pos);
        void enterPassword(RootNewUser.Detail detail, int pos);

    }
}
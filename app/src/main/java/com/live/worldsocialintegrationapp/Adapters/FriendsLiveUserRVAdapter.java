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
import com.live.worldsocialintegrationapp.ModelClasses.GetFollowingLiveRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class FriendsLiveUserRVAdapter extends RecyclerView.Adapter<FriendsLiveUserRVAdapter.ViewHolder> {

    List<GetFollowingLiveRoot.Detail> list;
    Context context;
    Callback callback;

    public FriendsLiveUserRVAdapter(List<GetFollowingLiveRoot.Detail> list, Context context,Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FriendsLiveUserRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_live_users_rv_desing,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsLiveUserRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(list.get(position).getLiveHideUnhideStatus().equalsIgnoreCase("0")){
            holder.freindsliveCardViewId.setVisibility(View.VISIBLE);
        }else{
            holder.freindsliveCardViewId.setVisibility(View.GONE);
        }

        if(!list.get(position).getImageTitle().isEmpty() && list.get(position).getImageTitle() != null){
            holder.friendliveTitleTv.setText(list.get(position).getImageTitle());
        }else{
            if (list.get(position).getName() != null){
                holder.friendliveTitleTv.setText(list.get(position).getName());
            }else{
                holder.friendliveTitleTv.setText("Welcome to Wows lives");
            }
        }

        if(!list.get(position).getImageText().isEmpty() && list.get(position).getImageText() != null){
            holder.friendsImgText.setText(list.get(position).getImageText());
        }else{
            holder.friendsImgText.setText("Any");
        }

        if (list.get(position).getLiveimage().isEmpty()) {
            Glide.with(holder.friendsLiveImag.getContext()).load(list.get(position).getImageDp()).error(R.drawable.photo2).into(holder.friendsLiveImag);
        } else {
            Glide.with(holder.friendsLiveImag.getContext()).load(list.get(position).getLiveimage()).error(R.drawable.photo2).into(holder.friendsLiveImag);
        }

        if(list.get(position).getMyCoin() == null && list.get(position).getMyCoin().isEmpty()){
            holder.friendsCoinsTV.setText("0");
        }else{
            holder.friendsCoinsTV.setText(list.get(position).getMyCoin());
        }
        holder.friendUserIdTv.setText(list.get(position).getUserId());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(list.get(position).getKickOutStatus()){
                    Toast.makeText(context, "Your Banned for 24 hours", Toast.LENGTH_SHORT).show();
                }else {

                    if (list.get(position).getPassword().equalsIgnoreCase("")) {
                        callback.callback(list.get(position), position);
                    } else {
                        Toast.makeText(context, "Live is private", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        if (!list.get(position).getPassword().equalsIgnoreCase("")) {
            holder.lockLiveImageFriends.setVisibility(View.VISIBLE);
        } else {
            holder.lockLiveImageFriends.setVisibility(View.GONE);
        }

        holder.lockLiveImageFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(list.get(position).getKickOutStatus()){
                    Toast.makeText(context, "Your Banned for 24 hours", Toast.LENGTH_SHORT).show();
                }else {
                    callback.enterPassword(list.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView friendsLiveImag,lockLiveImageFriends;
        TextView friendsCoinsTV,friendUserIdTv,friendliveTitleTv,friendsImgText;
        CardView freindsliveCardViewId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friendsLiveImag=itemView.findViewById(R.id.friendsLiveImag);
            friendsCoinsTV=itemView.findViewById(R.id.friendsCoinsTV);
            friendUserIdTv=itemView.findViewById(R.id.friendUserIdTv);
            friendliveTitleTv=itemView.findViewById(R.id.friendliveTitleTv);
            lockLiveImageFriends=itemView.findViewById(R.id.lockLiveImageFriends);
            freindsliveCardViewId=itemView.findViewById(R.id.freindsliveCardViewId);
            friendsImgText=itemView.findViewById(R.id.friendsImgText);

        }
    }
    public interface Callback{

        void callback(GetFollowingLiveRoot.Detail detail,int pos);
        void enterPassword(GetFollowingLiveRoot.Detail detail, int pos);
    }
}

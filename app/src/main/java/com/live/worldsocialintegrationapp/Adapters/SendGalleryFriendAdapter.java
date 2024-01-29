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
import com.live.worldsocialintegrationapp.ModelClasses.GetFriends.Detail;
import com.live.worldsocialintegrationapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SendGalleryFriendAdapter extends RecyclerView.Adapter<SendGalleryFriendAdapter.ViewHolder> {
    List<Detail> list = new ArrayList<>();
    Context context;
    Callback callback;

    public SendGalleryFriendAdapter(List<Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public SendGalleryFriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_live_gallery_send_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        if(list.get(position) != null){
            if(list.get(position).getName() != null){
                Glide.with(holder.gallerySendCirImg.getContext()).load(list.get(position).getImageDp()).error(R.drawable.demo_user_profile_img).into(holder.gallerySendCirImg);
                holder.gallerySendNameTv.setText(list.get(position).getName());
                holder.sendGalleryTv.setOnClickListener(view -> callback.sendGallery(list.get(position),holder.sendGalleryTv));
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView gallerySendCirImg;
        TextView gallerySendNameTv,sendGalleryTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gallerySendCirImg=itemView.findViewById(R.id.gallerySendCirImg);
            gallerySendNameTv=itemView.findViewById(R.id.gallerySendNameTv);
            sendGalleryTv=itemView.findViewById(R.id.sendGalleryTv);
        }
    }

    public interface Callback {
        void callback(String otherUserId,int poistion);
       void sendGallery(Detail detail,TextView sendGalleryTv);
    }
}

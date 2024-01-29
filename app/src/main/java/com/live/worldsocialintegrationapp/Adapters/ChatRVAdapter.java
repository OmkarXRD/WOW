package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.worldsocialintegrationapp.ModelClasses.RequstChat;
import com.live.worldsocialintegrationapp.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatRVAdapter extends RecyclerView.Adapter<ChatRVAdapter.ViewHolder> {
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference onlineUsers = firebaseDatabase.getReference().child("onlineUsers");

    Context context;
    List<RequstChat> list = new ArrayList<>();
    Callback callback;

    public ChatRVAdapter(Context context, List<RequstChat> list, Callback callback) {
        this.callback = callback;
        this.context = context;
        this.list =list;
    }

    @NonNull
    @Override
    public ChatRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.chatUserName.setText(list.get(position).getToName());
        Glide.with(holder.chatCirleImg.getContext()).load(list.get(position).getToImg()).error(R.drawable.demo_user_profile_img).into(holder.chatCirleImg);
        //holder.lastMessageTV.setText(list.get(position).getLastMessage());

        onlineUsers.child(list.get(position).getTo()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    holder.userOnlineRL.setVisibility(View.VISIBLE);
                }else{
                    holder.userOnlineRL.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callback(list.get(position).getTo(),list.get(position).getToName(),list.get(position).getToImg(),list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
            return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView chatUserName;
        TextView lastMessageTV;
        CircleImageView chatCirleImg;
        RelativeLayout userOnlineRL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chatUserName=itemView.findViewById(R.id.chatUserName);
            lastMessageTV=itemView.findViewById(R.id.lastMessageTV);
            chatCirleImg=itemView.findViewById(R.id.chatCirleImg);
            userOnlineRL=itemView.findViewById(R.id.userOnlineRL);
        }
    }

    public interface  Callback{
        public void callback(String otherUserid,String toName,String image,RequstChat chatRequests);
    }

    public void loadData(List<RequstChat> list){
        this.list = list;
    }

}

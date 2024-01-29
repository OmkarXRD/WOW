package com.live.worldsocialintegrationapp.agora.firebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.worldsocialintegrationapp.databinding.ListLiveChatBinding;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.security.auth.callback.Callback;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private final Context context;
    private List<ChatMessageModel> chatMessages;
    private Callback callback;
    public static String liveHostBackImg;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference cleanUserCommentsRef = firebaseDatabase.getReference().child("cleanUserCommentsRef");
    int p=0;


    public CommentAdapter(Context context, List<ChatMessageModel> chatMessages,Callback callback) {
        this.context = context;
        this.chatMessages = chatMessages;
        this.callback = callback;
    }

    public interface Callback{
        void openUserProfile(ChatMessageModel chatMessageModel);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListLiveChatBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        if (chatMessages.get(position).getGift().equalsIgnoreCase("")) {
            holder.chatBinding.rlGifts.setVisibility(View.GONE);
            holder.chatBinding.txtUserNameAndMessage.setText(chatMessages.get(position).getName() + ": " + chatMessages.get(position).getMessage());
        } else {
            holder.chatBinding.rlGifts.setVisibility(View.VISIBLE);
            holder.chatBinding.txtUserNameAndMessage.setText(chatMessages.get(position).getName() + ": " + "Send Gifts");
            Glide.with(context).load(chatMessages.get(position).gift).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    holder.chatBinding.progress.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    holder.chatBinding.progress.setVisibility(View.GONE);
                    return false;
                }
            }).into(holder.chatBinding.imgGift);
        }

        Glide.with(context).load(chatMessages.get(position).image).into(holder.chatBinding.imgProfile);
        if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)) {
            cleanUserCommentsRef.child(liveHostBackImg).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                         p=Integer.parseInt(snapshot.getValue().toString());
                        if(position<p){
                            holder.chatBinding.relative.setVisibility(View.GONE);
                        }else{
                            holder.chatBinding.relative.setVisibility(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {

        }

        holder.chatBinding.llImage.setOnClickListener(v -> callback.openUserProfile(chatMessages.get(position)));

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ListLiveChatBinding chatBinding;

        public ViewHolder(@NonNull @NotNull ListLiveChatBinding itemView) {
            super(itemView.getRoot());
            chatBinding = itemView;
        }
    }
}

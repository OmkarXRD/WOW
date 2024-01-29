package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.live.worldsocialintegrationapp.ModelClasses.Meet.RootMeet;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MeetItemsAdapter extends RecyclerView.Adapter<MeetItemsAdapter.InnerClassAdapter> {
    private List<RootMeet.Detail> list;
    Context context;
    Callback callback;


    public MeetItemsAdapter(List<RootMeet.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public InnerClassAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerClassAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.meet_items, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull InnerClassAdapter holder, @SuppressLint("RecyclerView") int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callback.callback(list.get(position),"1",position);
            }
        });
        holder.name.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getImageDp()).placeholder(R.drawable.user).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

//                    holder.forgot_loading.setVisibility(View.GONE);

                return false;

            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

//                    holder.forgot_loading.setVisibility(View.GONE);

                return false;

            }
        }).into(holder.DpImage);
        holder.userId.setText(list.get(position).getId());

    }

    public void loadData(List<RootMeet.Detail> ListData) {
        list = ListData;

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class InnerClassAdapter extends RecyclerView.ViewHolder {
        TextView  name , userId ;
        CircleImageView DpImage ;
        public InnerClassAdapter(@NonNull View itemView) {
            super( itemView );
            name = itemView.findViewById(R.id.topUserName);
            DpImage= itemView.findViewById(R.id.profile_image);
            userId= itemView.findViewById(R.id.topUserId);
        }
    }

    public interface  Callback{
        void callback(RootMeet.Detail detail, String value, int pos);


    }
}

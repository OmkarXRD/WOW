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
import com.live.worldsocialintegrationapp.ModelClasses.Events.EventsDetailsRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EventDetailRVAdapter  extends RecyclerView.Adapter<EventDetailRVAdapter.ViewHolder>{

    List<EventsDetailsRoot.EventSubscriber> list;
    Context context;
    Callback callback;

    public EventDetailRVAdapter(List<EventsDetailsRoot.EventSubscriber> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public EventDetailRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.details_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventDetailRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(holder.eventSuscriberCirImg.getContext()).load(list.get(position).getImageDp()).error(R.drawable.demo_user_profile_img).into(holder.eventSuscriberCirImg);
        holder.eventSuscriberNameTv.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView eventSuscriberCirImg;
        TextView eventSuscriberNameTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventSuscriberNameTv=itemView.findViewById(R.id.eventSuscriberNameTv);
            eventSuscriberCirImg=itemView.findViewById(R.id.eventSuscriberCirImg);

        }
    }

    public interface Callback{
        void callback(EventsDetailsRoot.EventSubscriber details);
    }
}

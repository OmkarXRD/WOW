package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.Events.GetEventsRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WhatsOnRVAdapter extends RecyclerView.Adapter<WhatsOnRVAdapter.ViewHolder> {

    Callback callback;
    List<GetEventsRoot.Detail> list;
    Context context;

    public WhatsOnRVAdapter(Callback callback, List<GetEventsRoot.Detail> list, Context context) {
        this.callback = callback;
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public WhatsOnRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.what_on_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsOnRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        if(list.get(position).getEventSubscriber_counts().isEmpty()){
            holder.eventsMemberCountTv.setText("0");
        }else{
            holder.eventsMemberCountTv.setText(list.get(position).getEventSubscriber_counts());
        }
        Glide.with(holder.eventBackgroundImg.getContext()).load(list.get(position).getEvent_image()).error(R.drawable.birthday_image_11).into(holder.eventBackgroundImg);
        Glide.with(holder.eventUserProfileCirImg.getContext()).load(list.get(position).getImageDp()).error(R.drawable.birthday_image_11).into(holder.eventUserProfileCirImg);
        holder.eventUserName.setText(list.get(position).getName());
        holder.eventsTopicTv.setText(list.get(position).getEvent_topic());
        holder.eventIdTv.setText("Id : 452" + list.get(position).getId());
        holder.eventsStartTimeTv.setText("will start on " + list.get(position).getEvent_startTime());

        if(list.get(position).getEventCreaterId().equalsIgnoreCase(AppConstants.USER_ID)){
            holder.eventShareBtn.setVisibility(View.VISIBLE);
            holder.eventsSuscribeBtn.setVisibility(View.GONE);
        }else{
            holder.eventShareBtn.setVisibility(View.GONE);
            holder.eventsSuscribeBtn.setVisibility(View.VISIBLE);
        }
//        if (list.get(position).isSubscriberStatus()) {
//            holder.eventsSuscribeBtn.setText("Suscribed");
//        } else {
//            holder.eventsSuscribeBtn.setText("Suscribe");
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.callback(list.get(position));
            }
        });
        holder.eventUserProfileCirImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.profileImgClick(list.get(position));
            }
        });

        holder.eventsSuscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.suscribeUnscribe(list.get(position),holder.eventsSuscribeBtn);
//                if (list.get(position).isSubscriberStatus()) {
//                    holder.eventsSuscribeBtn.setText("Suscribed");
//                } else {
//                    holder.eventsSuscribeBtn.setText("Suscribe");
//                }
            }
        });
        holder.eventShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.shareBtn(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView eventUserProfileCirImg;
        private TextView eventsTopicTv, eventsStartTimeTv, eventsMemberCountTv, eventUserName, eventIdTv;
        ImageView eventBackgroundImg;
        AppCompatButton eventsSuscribeBtn,eventShareBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventUserProfileCirImg = itemView.findViewById(R.id.eventUserProfileCirImg);
            eventsTopicTv = itemView.findViewById(R.id.eventsTopicTv);
            eventsStartTimeTv = itemView.findViewById(R.id.eventsStartTimeTv);
            eventsMemberCountTv = itemView.findViewById(R.id.eventsMemberCountTv);
            eventBackgroundImg = itemView.findViewById(R.id.eventBackgroundImg);
            eventsSuscribeBtn = itemView.findViewById(R.id.eventsSuscribeBtn);
            eventUserName = itemView.findViewById(R.id.eventUserName);
            eventIdTv = itemView.findViewById(R.id.eventIdTv);
            eventsStartTimeTv = itemView.findViewById(R.id.eventsStartTimeTv);
            eventShareBtn = itemView.findViewById(R.id.eventShareBtn);
        }
    }

    public interface Callback {
        void callback(GetEventsRoot.Detail detail);

        void suscribeUnscribe(GetEventsRoot.Detail detail, AppCompatButton suscribeBtn);

        void profileImgClick(GetEventsRoot.Detail detail);

        void shareBtn(GetEventsRoot.Detail detail);
    }
}

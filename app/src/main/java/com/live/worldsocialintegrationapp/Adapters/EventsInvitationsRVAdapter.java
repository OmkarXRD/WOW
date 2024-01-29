package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.Events.GetAllEventInvitationsRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EventsInvitationsRVAdapter extends RecyclerView.Adapter<EventsInvitationsRVAdapter.ViewHolder> {

    List<GetAllEventInvitationsRoot.Detail> list;
    Callback callback;
    Context context;

    public EventsInvitationsRVAdapter(List<GetAllEventInvitationsRoot.Detail> list, Callback callback, Context context) {
        this.list = list;
        this.callback = callback;
        this.context = context;
    }

    @NonNull
    @Override
    public EventsInvitationsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.events_invitations_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventsInvitationsRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(!list.get(position).getUserId().equalsIgnoreCase(AppConstants.USER_ID)){

            Glide.with(holder.eventsInvitationsCirImg.getContext()).load(list.get(position).getImageDp()).error(R.drawable.demo_user_profile_img).into(holder.eventsInvitationsCirImg);
            holder.eventsInvitationNameTv.setText(list.get(position).getName());
            holder.eventsInvitationAcceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.acceptRejectInvitation(list.get(position),"2");
                }
            });
            holder.eventsInvitationRejectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.acceptRejectInvitation(list.get(position),"3");
                }
            });
        }else{}


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView eventsInvitationsCirImg;
        TextView eventsInvitationNameTv;
        AppCompatButton eventsInvitationAcceptBtn,eventsInvitationRejectBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventsInvitationsCirImg=itemView.findViewById(R.id.eventsInvitationsCirImg);
            eventsInvitationNameTv=itemView.findViewById(R.id.eventsInvitationNameTv);
            eventsInvitationAcceptBtn=itemView.findViewById(R.id.eventsInvitationAcceptBtn);
            eventsInvitationRejectBtn=itemView.findViewById(R.id.eventsInvitationRejectBtn);
        }
    }
    public interface Callback{
        void acceptRejectInvitation(GetAllEventInvitationsRoot.Detail detail,String status);
    }
}

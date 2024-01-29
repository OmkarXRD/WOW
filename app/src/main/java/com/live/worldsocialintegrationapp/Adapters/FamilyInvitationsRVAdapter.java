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
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetInvitationsRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FamilyInvitationsRVAdapter extends RecyclerView.Adapter<FamilyInvitationsRVAdapter.ViewHolder> {

    List<GetInvitationsRoot.Detail> list;
    Context context;
    Callback callback;

    public FamilyInvitationsRVAdapter(List<GetInvitationsRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FamilyInvitationsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.family_invitations_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyInvitationsRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(holder.invitationsCirImg.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.invitationsCirImg);
        holder.familyInvitationNameTv.setText(list.get(position).getFamilyName());
        holder.familyInvitationDesTv.setText(list.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.callback(list.get(position));
            }
        });
        holder.familyInvitationAcceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.AcceptAndRejcetInvitation(list.get(position),"2",holder.familyInvitationAcceptBtn);
            }
        });

        holder.familyInvitationRejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.AcceptAndRejcetInvitation(list.get(position),"3",holder.familyInvitationAcceptBtn);
            }
        });

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
        AppCompatButton familyInvitationAcceptBtn,familyInvitationRejectBtn;
        CircleImageView invitationsCirImg;
        TextView familyInvitationNameTv,familyInvitationDesTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            familyInvitationAcceptBtn=itemView.findViewById(R.id.familyInvitationAcceptBtn);
            familyInvitationRejectBtn = itemView.findViewById(R.id.familyInvitationRejectBtn);
            invitationsCirImg = itemView.findViewById(R.id.invitationsCirImg);
            familyInvitationNameTv = itemView.findViewById(R.id.familyInvitationNameTv);
            familyInvitationDesTv = itemView.findViewById(R.id.familyInvitationDesTv);
        }
    }

    public interface  Callback{
        void callback(GetInvitationsRoot.Detail detail);
        void AcceptAndRejcetInvitation(GetInvitationsRoot.Detail detail,String status,AppCompatButton acceptOrRejectBtn);
    }
}

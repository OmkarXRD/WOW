package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.Family.Joiner;
import com.live.worldsocialintegrationapp.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FamilyMembersAdapter extends RecyclerView.Adapter<FamilyMembersAdapter.ViewHolder> {

    private List<Joiner> list ;
    private Context context;
    private Callback callback;

    public FamilyMembersAdapter(List<Joiner> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FamilyMembersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.family_members_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyMembersAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (list.get(position).show_status){
            holder.top2FamilyFireRL.setVisibility(View.VISIBLE);
        }else {
            holder.top2FamilyFireRL.setVisibility(View.GONE);
        }

        holder.familyMembersNameTv.setText(list.get(position).getName());
        holder.newFamilyFireTV.setText(list.get(position).getContribution());
        Glide.with(holder.familyMembersCirImg.getContext()).load(list.get(position).getUserProfileImage()).error(R.drawable.demo_user_profile_img).into(holder.familyMembersCirImg);
        holder.itemView.setOnClickListener(view -> callback.callback(list.get(position)));

        if (list.get(position).getIs_admin().equalsIgnoreCase("1")){
            holder.adminTV.setVisibility(View.VISIBLE);
        }else {
            holder.adminTV.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView familyMembersCirImg;
        private TextView familyMembersNameTv,newFamilyFireTV;
        private ImageView adminTV;
        private RelativeLayout top2FamilyFireRL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            familyMembersNameTv=itemView.findViewById(R.id.familyMembersNameTv);
            familyMembersCirImg=itemView.findViewById(R.id.familyMembersCirImg);
            adminTV=itemView.findViewById(R.id.adminTV);
            newFamilyFireTV=itemView.findViewById(R.id.newFamilyFireTV);
            top2FamilyFireRL=itemView.findViewById(R.id.top2FamilyFireRL);
        }
    }

    public interface Callback{
        void callback(Joiner detail);
    }
}

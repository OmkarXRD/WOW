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
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetLiveFamilyJoinersRoot;
import com.live.worldsocialintegrationapp.ModelClasses.RootNewUser;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class FamilyLiveRoomRVAdapter extends RecyclerView.Adapter<FamilyLiveRoomRVAdapter.ViewHolder> {
    List<GetLiveFamilyJoinersRoot.Detail> list;
    Context context;
    Callback  callback;

    public FamilyLiveRoomRVAdapter(List<GetLiveFamilyJoinersRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FamilyLiveRoomRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.family_live_room_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyLiveRoomRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(list.get(position).getLiveHideUnhideStatus().equalsIgnoreCase("0")){
            holder.familyLiveJoinerCardViewId.setVisibility(View.VISIBLE);
        }else{
            holder.familyLiveJoinerCardViewId.setVisibility(View.GONE);
        }

        if (list.get(position).getLiveimage().isEmpty()) {
            Glide.with(holder.liveFamilyImg.getContext()).load(list.get(position).getImageDp()).error(R.drawable.demo_user_profile_img).into(holder.liveFamilyImg);
        } else {
            Glide.with(holder.liveFamilyImg.getContext()).load(list.get(position).getLiveimage()).error(R.drawable.demo_user_profile_img).into(holder.liveFamilyImg);
        }


        if(!list.get(position).getImageTitle().isEmpty() && list.get(position).getImageTitle() != null){
            holder.familyLiveTv.setText(list.get(position).getImageTitle());
        }else{
            if (list.get(position).getName() != null){
                holder.familyLiveTv.setText(list.get(position).getName());
            }else{
                holder.familyLiveTv.setText("Welcome to Wows lives");
            }
        }

        if (!list.get(position).getPassword().equalsIgnoreCase("")) {
            holder.lockLiveImageFamilyJoiners.setVisibility(View.VISIBLE);
        } else {
            holder.lockLiveImageFamilyJoiners.setVisibility(View.GONE);
        }

        if(!list.get(position).getImageText().isEmpty() && list.get(position).getImageText() != null){
            holder.familyLiveChattv.setText(list.get(position).getImageText());
        }else{
            holder.familyLiveChattv.setText("Any");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(list.get(position).getKickOutStatus()){
                    Toast.makeText(context, "Your Banned for 24 hours", Toast.LENGTH_SHORT).show();
                }else {

                    if (list.get(position).getPassword().equalsIgnoreCase("")) {
                        callback.liveJoinerClick(list.get(position), position);
                    } else {
                        Toast.makeText(context, "Live is private", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        holder.lockLiveImageFamilyJoiners.setOnClickListener(new View.OnClickListener() {
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
        ImageView liveFamilyImg,lockLiveImageFamilyJoiners;
        CardView familyLiveJoinerCardViewId;

        TextView familyLiveTv,familyLiveChattv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            liveFamilyImg=itemView.findViewById(R.id.liveFamilyImg);
            familyLiveJoinerCardViewId=itemView.findViewById(R.id.familyLiveJoinerCardViewId);
            lockLiveImageFamilyJoiners=itemView.findViewById(R.id.lockLiveImageFamilyJoiners);
            familyLiveTv=itemView.findViewById(R.id.familyLiveTv);
            familyLiveChattv=itemView.findViewById(R.id.familyLiveChattv);
        }
    }

    public interface Callback{
        void liveJoinerClick(GetLiveFamilyJoinersRoot.Detail detail,int pos);
        void enterPassword(GetLiveFamilyJoinersRoot.Detail detail, int pos);
    }
}

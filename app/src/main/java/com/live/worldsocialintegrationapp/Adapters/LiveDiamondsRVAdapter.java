package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetCoin.Detail;
import com.live.worldsocialintegrationapp.ModelClasses.GetLiveDiamondRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LiveDiamondsRVAdapter extends RecyclerView.Adapter<LiveDiamondsRVAdapter.ViewHolder> {

    Context context;
    List<GetLiveDiamondRoot.Detail> list;
    Callback callback;

    public LiveDiamondsRVAdapter(Context context,List<GetLiveDiamondRoot.Detail> list,Callback callback) {
        this.context = context;
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public LiveDiamondsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.live_diamond_histroy_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LiveDiamondsRVAdapter.ViewHolder holder, int position) {

        Glide.with(holder.liveDaimondCirImg).load(list.get(position).getSenderImg()).error(R.drawable.demo_user_profile_img).into(holder.liveDaimondCirImg);
        holder.liveDiamondsNameTv.setText(list.get(position).getSenderName()+" sent to "+list.get(position).getReceiverName());

        if(!list.get(position).getReceiverDob().isEmpty()){

            holder.liveDiamondAgeTv.setText(CommonUtils.ageCalcualte(list.get(position).getSenderDob()));

            if(list.get(position).getSenderGender().equalsIgnoreCase("male")){
                holder.liveDiamondGenderImg.setImageResource(R.drawable.male_gender__4_);
            }else{
                holder.liveDiamondGenderImg.setImageResource(R.drawable.femenine);
            }
        }else{
            holder.liveDiamondDoblLayout.setVisibility(View.GONE);
        }
        holder.liveDiamondTv.setText(list.get(position).getDiamond());

        holder.itemView.setOnClickListener(view -> callback.userInfo(list.get(position)));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView liveDaimondCirImg;
        TextView liveDiamondsNameTv,liveDiamondAgeTv,liveDiamondTv;
        ImageView liveDiamondGenderImg;
        LinearLayout liveDiamondDoblLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            liveDaimondCirImg=itemView.findViewById(R.id.liveDaimondCirImg);
            liveDiamondsNameTv=itemView.findViewById(R.id.liveDiamondsNameTv);
            liveDiamondGenderImg=itemView.findViewById(R.id.liveDiamondGenderImg);
            liveDiamondAgeTv=itemView.findViewById(R.id.liveDiamondAgeTv);
            liveDiamondTv=itemView.findViewById(R.id.liveDiamondTv);
            liveDiamondDoblLayout=itemView.findViewById(R.id.liveDiamondDoblLayout);
        }
    }
    public interface Callback{
        void userInfo(GetLiveDiamondRoot.Detail detail);
    }
}

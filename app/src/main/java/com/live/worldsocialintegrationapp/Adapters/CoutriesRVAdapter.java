package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.live.worldsocialintegrationapp.ModelClasses.AllPopularUsers.GetAllPopularRoot;
import com.live.worldsocialintegrationapp.ModelClasses.LiveUserByCountry.RootLiveUser;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class CoutriesRVAdapter extends RecyclerView.Adapter<CoutriesRVAdapter.ViewHolder> {

    private List<RootLiveUser.Detail> list;
    Context context;
    Callback callback;

    public CoutriesRVAdapter(List<RootLiveUser.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public CoutriesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.countries_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CoutriesRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        if(list.get(position).getLiveHideUnhideStatus().equalsIgnoreCase("0")){
            holder.contriesCV.setVisibility(View.VISIBLE);
            holder.countryLiveTv.setVisibility(View.VISIBLE);
        }else{
            holder.contriesCV.setVisibility(View.GONE);
            holder.countryLiveTv.setVisibility(View.GONE);

        }
        holder.countryLiveTv.setText(list.get(position).getLiveCount());

        if (list.get(position).getLiveimage().isEmpty()) {
            Glide.with(holder.userLIveImage.getContext()).load(list.get(position).getImageDp()).error(R.drawable.demo_user_profile_img).into(holder.userLIveImage);
        } else {
            Glide.with(holder.userLIveImage.getContext()).load(list.get(position).getLiveimage()).error(R.drawable.demo_user_profile_img).into(holder.userLIveImage);
        }

        if(!list.get(position).getImageTitle().isEmpty() && list.get(position).getImageTitle() != null){
            holder.countryLiveTitleTv.setText(list.get(position).getImageTitle());
        }else{
            if (list.get(position).getName() != null){
                holder.countryLiveTitleTv.setText(list.get(position).getName());
            }else{
                holder.countryLiveTitleTv.setText("Welcome to Wows lives");
            }
        }

//        Glide.with(context).load(list.get(position).getImageDp()).placeholder(R.drawable.user).listener(new RequestListener<Drawable>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//
////                    holder.forgot_loading.setVisibility(View.GONE);
//
//                return false;
//
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//
////                    holder.forgot_loading.setVisibility(View.GONE);
//
//                return false;
//
//            }
//        }).into(holder.userIMAGE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(list.get(position).getKickOutStatus()){
                    Toast.makeText(context, "Your Banned for 24 hours", Toast.LENGTH_SHORT).show();
                }else {
                    if (list.get(position).getPassword().equalsIgnoreCase("")) {
                        callback.callback(list.get(position), position);
                    } else {
                        Toast.makeText(context, "Live is private", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        if (!list.get(position).getPassword().equalsIgnoreCase("")) {
            holder.lockLiveImageCountry.setVisibility(View.VISIBLE);
        } else {
            holder.lockLiveImageCountry.setVisibility(View.GONE);
        }
        holder.lockLiveImageCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position).getKickOutStatus()){
                    Toast.makeText(context, "You are Banned for 24 hours", Toast.LENGTH_SHORT).show();
                }else {
                    callback.enterPassword(list.get(position), position);
                }
            }
        });

        if(list.get(position).getImageText().isEmpty()){
            holder.liveTextTv.setText("Any");
        }else{
            holder.liveTextTv.setText(list.get(position).getImageText());
        }
    }

    public void loadData(List<RootLiveUser.Detail> ListData) {
        list = ListData;

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userIMAGE,userLIveImage,lockLiveImageCountry;
        CardView contriesCV;
        TextView countryLiveTv,countryLiveTitleTv,liveTextTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userIMAGE = itemView.findViewById(R.id.userLIveImage);
            userLIveImage = itemView.findViewById(R.id.userLIveImage);
            lockLiveImageCountry = itemView.findViewById(R.id.lockLiveImageCountry);
            contriesCV = itemView.findViewById(R.id.contriesCV);
            countryLiveTv = itemView.findViewById(R.id.countryLiveTv);
            countryLiveTitleTv = itemView.findViewById(R.id.countryLiveTitleTv);
            liveTextTv = itemView.findViewById(R.id.liveTextTv);

        }
    }

    public interface Callback {
        void callback(RootLiveUser.Detail detail, int pos);
        void enterPassword(RootLiveUser.Detail detail, int pos);


    }
}
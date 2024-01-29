package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.PopularTabs.PopularAllFragment;
import com.live.worldsocialintegrationapp.ModelClasses.AllPopularUsers.Detail;
import com.live.worldsocialintegrationapp.ModelClasses.AllPopularUsers.GetAllPopularRoot;
import com.live.worldsocialintegrationapp.ModelClasses.NearByLiveUsers.NearByLiveUsersRoot;
import com.live.worldsocialintegrationapp.R;
import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.List;

public class PopularAllItemsAdapter extends RecyclerView.Adapter<PopularAllItemsAdapter.InnerClassAdapter> {
    List<GetAllPopularRoot.Detail> list = new ArrayList<>();
    Context context;
    Callback callback;
    int lastPosition=-1;
    public PopularAllItemsAdapter(List<GetAllPopularRoot.Detail> list,Callback callback,Context context) {
        this.list = list;
        this.callback = callback;
        this.context = context ;
    }

    @NonNull
    @Override
    public InnerClassAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerClassAdapter( LayoutInflater.from( parent.getContext() ).inflate( R.layout.popular_all_items , parent, false) );
    }

    @Override
    public void onBindViewHolder(@NonNull InnerClassAdapter holder, @SuppressLint("RecyclerView") int position) {

        if (holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        } else {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }

        if(list.get(position).getLiveimage().isEmpty()){
            Glide.with(holder.popluarAdpterImg.getContext()).load(list.get(position).getImageDp()).error(R.drawable.demo_user_profile_img).into(holder.popluarAdpterImg);
        }else{
            Glide.with(holder.popluarAdpterImg.getContext()).load(list.get(position).getLiveimage()).error(R.drawable.demo_user_profile_img).into(holder.popluarAdpterImg);
        }

        if(list.get(position).getLiveCount().isEmpty()){
            holder.liveCountsTv.setText("0");
        }else{
           holder.liveCountsTv.setText(list.get(position).getLiveCount());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(list.get(position).getKickOutStatus()){
//                    Toast.makeText(context, "Your Banned for 24 hours", Toast.LENGTH_SHORT).show();
                }else {
                    if (list.get(position).getPassword().equalsIgnoreCase("")) {
                        callback.callback(list.get(position), position);
                    } else {
                        if (context != null) {
                            Toast.makeText(context, "Live is private", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        if(!list.get(position).getImageTitle().isEmpty() && list.get(position).getImageTitle() != null){
            holder.liveTitleTv.setText(list.get(position).getImageTitle());
        }else{
            if (list.get(position).getName() != null){
                holder.liveTitleTv.setText(list.get(position).getName());
            }else{
                holder.liveTitleTv.setText("Welcome to Wows lives");
            }
        }
        if(!list.get(position).getImageText().isEmpty() && list.get(position).getImageText() != null){
            holder.allFragLiveTv.setText(list.get(position).getImageText());
        }else{
            holder.allFragLiveTv.setText("Any");
        }
        if (!list.get(position).getPassword().equalsIgnoreCase("")) {
            holder.lockLiveImagePopular.setVisibility(View.VISIBLE);
        } else {
            holder.lockLiveImagePopular.setVisibility(View.GONE);
        }
        holder.lockLiveImagePopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position).getKickOutStatus()){
//                    Toast.makeText(context, "Your Banned for 24 hours", Toast.LENGTH_SHORT).show();
                }else{
                    callback.enterPassword(list.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (list!=null)? list.size() : 0;
    }

    public class InnerClassAdapter extends RecyclerView.ViewHolder {
        ImageView popluarAdpterImg,lockLiveImagePopular;
        TextView liveCountsTv,liveTitleTv,allFragLiveTv;
        public InnerClassAdapter(@NonNull View itemView) {
            super( itemView );
            popluarAdpterImg=itemView.findViewById(R.id.popluarAdpterImg);
            liveCountsTv=itemView.findViewById(R.id.liveCountsTv);
            lockLiveImagePopular=itemView.findViewById(R.id.lockLiveImagePopular);
            liveTitleTv=itemView.findViewById(R.id.liveTitleTv);
            allFragLiveTv=itemView.findViewById(R.id.allFragLiveTv);
        }
    }

    public interface Callback{
        void callback(GetAllPopularRoot.Detail detail , int pos);
        void enterPassword(GetAllPopularRoot.Detail detail, int pos);
    }
}

package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetJoinRequestRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FamilyRequestRVAdapter extends RecyclerView.Adapter<FamilyRequestRVAdapter.ViewHolder> {

    List<GetJoinRequestRoot.Detail> list;
    Callback callback;
    Context context;

    public FamilyRequestRVAdapter(List<GetJoinRequestRoot.Detail> list, Callback callback, Context context) {
        this.list = list;
        this.callback = callback;
        this.context = context;
    }

    @NonNull
    @Override
    public FamilyRequestRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.family_requests_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyRequestRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(holder.requestCirImg.getContext()).load(list.get(position).getImageDp()).error(R.drawable.demo_user_profile_img).into(holder.requestCirImg);
        holder.familyRequestNameTv.setText(list.get(position).getName());

        holder.familyRequestAcceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "AcceptBnt", Toast.LENGTH_SHORT).show();
                callback.acceptOrRejectRequest(list.get(position),"2",holder.familyRequestAcceptBtn);
            }
        });
        holder.familyRequestRejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "rejectBtn", Toast.LENGTH_SHORT).show();
                callback.acceptOrRejectRequest(list.get(position),"3",holder.familyRequestRejectBtn);
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
        CircleImageView requestCirImg;
        TextView familyRequestNameTv;
        AppCompatButton familyRequestRejectBtn,familyRequestAcceptBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            requestCirImg=itemView.findViewById(R.id.requestCirImg);
            familyRequestNameTv=itemView.findViewById(R.id.familyRequestNameTv);
            familyRequestRejectBtn=itemView.findViewById(R.id.familyRequestRejectBtn);
            familyRequestAcceptBtn=itemView.findViewById(R.id.familyRequestAcceptBtn);
        }
    }
    public interface Callback{
        void acceptOrRejectRequest(GetJoinRequestRoot.Detail detail,String status,AppCompatButton textView);
        void callback(GetJoinRequestRoot.Detail detail);
    }
}

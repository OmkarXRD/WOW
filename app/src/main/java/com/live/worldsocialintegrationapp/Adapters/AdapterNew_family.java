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
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.material.imageview.ShapeableImageView;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetFamilyTopGiftersRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.App;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterNew_family extends RecyclerView.Adapter<AdapterNew_family.MyViewHolder> {

    List<GetFamilyTopGiftersRoot.Detail> list;
    Context context;
    Callback callback;
    int i = 3;

    public AdapterNew_family(List<GetFamilyTopGiftersRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public AdapterNew_family.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new, parent, false);
        AdapterNew_family.MyViewHolder myViewHolder = new AdapterNew_family.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNew_family.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.familyNameTv.setText(list.get(position).getFamilyName());
        Glide.with(holder.circularImageView.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.circularImageView);
        holder.family1DescriptionTv.setText(list.get(position).getDescription());
        holder.newFamilyFireTV.setText(String.valueOf(list.get(position).getTotal()));
        holder.item_new_digits.setText(String.valueOf(i+1));
        //holder.item_new_digits.setText(String.valueOf(++i));
        App.getSharedpref().saveString("position",String.valueOf(++i));

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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView familyNameTv,family1DescriptionTv,newFamilyFireTV,item_new_digits;
        CircleImageView circularImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            familyNameTv = itemView.findViewById(R.id.familyNameTv);
            circularImageView = itemView.findViewById(R.id.circularImageView);
            family1DescriptionTv = itemView.findViewById(R.id.family1DescriptionTv);
            newFamilyFireTV = itemView.findViewById(R.id.newFamilyFireTV);
            item_new_digits = itemView.findViewById(R.id.item_new_digits);
        }
    }

    public interface Callback {

        void callback(GetFamilyTopGiftersRoot.Detail detail);
    }
}

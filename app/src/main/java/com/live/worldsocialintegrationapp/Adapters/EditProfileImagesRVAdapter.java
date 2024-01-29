package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserImagesRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;

public class EditProfileImagesRVAdapter extends RecyclerView.Adapter<EditProfileImagesRVAdapter.ViewHolder> {

    List<GetUserImagesRoot.Detail> list;
    Context context;
    Callback callback;

    public EditProfileImagesRVAdapter(List<GetUserImagesRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public EditProfileImagesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_profile_images_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EditProfileImagesRVAdapter.ViewHolder holder, int position) {

        Glide.with(holder.editProfileScreenUserImgAdpter.getContext()).load(list.get(position).getImage()).error(R.drawable.demo_user_profile_img).into(holder.editProfileScreenUserImgAdpter);
        if(position==0){
            holder.itemView.setOnClickListener(view -> callback.callback("1"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView editProfileScreenUserImgAdpter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            editProfileScreenUserImgAdpter=itemView.findViewById(R.id.editProfileScreenUserImgAdpter);
        }
    }

    public void loadData(List<GetUserImagesRoot.Detail> imagesList){
        this.list = list;
    }
    public interface  Callback{
        void callback(String value);
    }
}

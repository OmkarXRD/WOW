package com.live.worldsocialintegrationapp.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.openvcall.model.LiveUserModel;
import com.live.worldsocialintegrationapp.databinding.LayoutPopularLiveDesignBinding;

import java.util.List;

public class PopularLiveAdapter extends RecyclerView.Adapter<PopularLiveAdapter.ViewHolder> {


    private List<LiveUserModel.Detail> list;
    private Click click;

    public PopularLiveAdapter(List<LiveUserModel.Detail> list, Click click) {
        this.list = list;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutPopularLiveDesignBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(holder.binding.getRoot().getContext()).load(list.get(position).getImage()).placeholder(R.drawable.photo).into(holder.binding.imgProfile);

        holder.itemView.setOnClickListener(view -> {
            click.setOnOpenLiveUser(list.get(position));
        });

        holder.binding.txtName.setText(list.get(position).getName());
//        if(list.get(position).isEmpty()){
//            holder.binding.multiCoinsTV.setText("0");
//        }else{
//            holder.binding.multiCoinsTV.setText(list.get(position).getUser().getCoin());
//        }
        holder.binding.multiUserIdTV.setText(list.get(position).getUserId());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LayoutPopularLiveDesignBinding binding;

        public ViewHolder(@NonNull LayoutPopularLiveDesignBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }

    public interface Click {
        void setOnOpenLiveUser(LiveUserModel.Detail detail);

    }
}

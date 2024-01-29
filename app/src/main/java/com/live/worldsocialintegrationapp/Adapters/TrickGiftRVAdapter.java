package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.Privilege;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.Trick;
import com.live.worldsocialintegrationapp.databinding.ListGiftBinding;

import java.util.List;

public class TrickGiftRVAdapter extends RecyclerView.Adapter<TrickGiftRVAdapter.ViewHolder> {

    private Context context;
    private List<Trick> list;
    Select select;

    public interface Select {
        void details(Trick detail);
    }

    public TrickGiftRVAdapter(Context context, List<Trick> list, Select select) {
        this.context = context;
        this.list = list;
        this.select = select;
    }

    @NonNull
    @Override
    public TrickGiftRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListGiftBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrickGiftRVAdapter.ViewHolder holder, int position) {
        holder.binding.imggg.setVisibility(View.GONE);
        Trick model = list.get(position);

        if (model.getPrimeAccount().equalsIgnoreCase("0")){
            holder.binding.txtAmount.setText("Free");
        }
        Glide.with(context).load(model.getImage()).into(holder.binding.imgGifts);

        holder.binding.imgGifts.setOnClickListener(v -> {
            select.details(list.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ListGiftBinding binding;
        public ViewHolder(@NonNull ListGiftBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

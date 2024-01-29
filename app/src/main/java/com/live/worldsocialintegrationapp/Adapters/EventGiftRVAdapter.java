package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.EventGift;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.SoundGift;
import com.live.worldsocialintegrationapp.databinding.ListGiftBinding;

import java.util.List;

public class EventGiftRVAdapter extends RecyclerView.Adapter<EventGiftRVAdapter.ViewHolder> {

    private Context context;
    private List<EventGift> list;
    Select select;

    public interface Select {
        void details(EventGift detail);
    }


    public EventGiftRVAdapter(Context context, List<EventGift> list,Select select) {
        this.context = context;
        this.list = list;
        this.select = select;
    }

    @NonNull
    @Override
    public EventGiftRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListGiftBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventGiftRVAdapter.ViewHolder holder, int position) {
        EventGift model = list.get(position);

        holder.binding.txtAmount.setText(model.getPrimeAccount());
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

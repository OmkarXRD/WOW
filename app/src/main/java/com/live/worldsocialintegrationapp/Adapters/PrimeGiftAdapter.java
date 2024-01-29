package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.Privilege;
import com.live.worldsocialintegrationapp.agora.openvcall.model.PrimeGiftModel;
import com.live.worldsocialintegrationapp.databinding.ListGiftBinding;

import java.util.List;

public class PrimeGiftAdapter extends RecyclerView.Adapter<PrimeGiftAdapter.ViewHolder> {
    private Context context;
    private List<Privilege> list;
    Select select;


    public interface Select {
        void details(Privilege detail);
    }


    public PrimeGiftAdapter(Context context, List<Privilege> list, Select select) {
        this.context = context;
        this.list = list;
        this.select = select;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListGiftBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Privilege model = list.get(position);
        holder.binding.txtAmount.setText(model.getPrimeAccount());
//        if (Integer.parseInt(model.getPrimeAccount()) < 1000) {
//
//        } else if (Integer.parseInt(model.getPrimeAccount()) < 1000000) {
//            holder.binding.txtAmount.setText(String.format("%.1fk", Integer.parseInt(model.getPrimeAccount()) / 1000));
//        } else if (Integer.parseInt(model.getPrimeAccount()) < 1000000000) {
//            holder.binding.txtAmount.setText(String.format("%.1fm", Integer.parseInt(model.getPrimeAccount()) / 1000000.0));
//        } else {
//            holder.binding.txtAmount.setText(String.format("%.1ft", Integer.parseInt(model.getPrimeAccount()) / 1000000000.0));
//        }


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




































package com.live.worldsocialintegrationapp.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.Activites.SpinnerActivity;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.SoundGift;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.Trick;
import com.live.worldsocialintegrationapp.databinding.EventgiftlistBinding;
import com.live.worldsocialintegrationapp.databinding.ListGiftBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SoundGiftRVAdapter extends RecyclerView.Adapter<SoundGiftRVAdapter.ViewHolder> {
    private Context context;
    private List<SoundGift> list;
    Select select;

    public interface Select {
        void details(SoundGift detail);
    }


    public SoundGiftRVAdapter(Context context, List<SoundGift> list, Select select) {
        this.context = context;
        this.list = list;
        this.select = select;
    }

    @NonNull
    @Override
    public SoundGiftRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(EventgiftlistBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SoundGiftRVAdapter.ViewHolder holder, int position) {
        SoundGift model = list.get(position);

        holder.binding.txtAmount.setText(model.getPrimeAccount());
      //  Glide.with(context).load(model.getImage()).into(holder.binding.imgGifts);
        SVGAParser parser;
        parser = new SVGAParser(context);
        try {
            parser.decodeFromURL(new URL(model.getImage()), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    SVGADrawable drawable = new SVGADrawable(videoItem);
                    holder.binding.imgGifts.setImageDrawable(drawable);
                    holder.binding.imgGifts.startAnimation();
                }

                @Override
                public void onError() {

                }
            }, new SVGAParser.PlayCallback() {
                @Override
                public void onPlay(@NonNull List<? extends File> list) {

                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



        holder.binding.imgGifts.setOnClickListener(v -> {
            select.details(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EventgiftlistBinding binding;
        public ViewHolder(@NonNull EventgiftlistBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }
    }
}

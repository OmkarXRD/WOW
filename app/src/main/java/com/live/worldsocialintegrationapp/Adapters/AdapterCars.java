package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.ModelClasses.GetLuckIdRoot;
import com.live.worldsocialintegrationapp.R;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AdapterCars extends RecyclerView.Adapter<AdapterCars.MyviewHolder> {

    List<GetLuckIdRoot.Detail> list = new ArrayList<>();
    Context context;
    Callback callback;
    SVGAParser parser;


    public AdapterCars(List<GetLuckIdRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_cars, parent, false);
        AdapterCars.MyviewHolder viewHolder = new AdapterCars.MyviewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (list.get(position).isMy()) {
            // purchased 1
            holder.carsBuyTV.setText("Bought");
            holder.validityDaysTV.setText(list.get(position).getRemainingDays());
        } else {
            //not purchased 0
            holder.carsBuyTV.setText("Buy");
            buyClickDialogbox(holder, position);
            holder.validityDaysTV.setText(list.get(position).getValidity());

        }

        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.sendCar(list.get(position));
            }
        });

//        Glide.with(holder.imag_cars.getContext()).load(list.get(position).getImage()).error(R.drawable.pirateship).into(holder.imag_cars);
        holder.coin_car.setText(list.get(position).getPrice());


        holder.testWear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.testCars(list.get(position));
            }
        });


        parser = new SVGAParser(context.getApplicationContext());
        try {
            parser.decodeFromURL(new URL(list.get(position).getImage()), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    SVGADrawable drawable = new SVGADrawable(videoItem);

//                    holder.lottieAnimationView.setVisibility(View.GONE);
//                    holder.lottieAnimationView.cancelAnimation();

                    holder.carsRVImage.setImageDrawable(drawable);
                    holder.carsRVImage.startAnimation();
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

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        RelativeLayout send, buy;
        ImageView imag_cars;
        TextView coin_car, validityDaysTV, carsBuyTV , testWear;
//        LottieAnimationView lottieAnimationView;
        SVGAImageView carsRVImage;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            send = itemView.findViewById(R.id.sendRL);
            buy = itemView.findViewById(R.id.buy_b);
            imag_cars = itemView.findViewById(R.id.imag_cars);
            coin_car = itemView.findViewById(R.id.coin_car);
            validityDaysTV = itemView.findViewById(R.id.validityDaysTV);
            carsBuyTV = itemView.findViewById(R.id.carsBuyTV);
            testWear = itemView.findViewById(R.id.testWear);
//            lottieAnimationView=itemView.findViewById(R.id.carLottieAniamtion);
            carsRVImage=itemView.findViewById(R.id.carsRVImage);

        }
    }

    public interface Callback {
        void purchaseCar(GetLuckIdRoot.Detail detail,TextView textView);
        void sendCar(GetLuckIdRoot.Detail detail);
        void testCars(GetLuckIdRoot.Detail detail);
    }


    private void buyClickDialogbox(MyviewHolder holder, int position) {

        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog_share = new Dialog(v.getContext());
                dialog_share.setContentView(R.layout.item_dialog_buy);
                dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog_share.setCanceledOnTouchOutside(true);
                Window window = dialog_share.getWindow();
                window.setGravity(Gravity.CENTER);
                dialog_share.show();

                dialog_share.findViewById(R.id.Cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(v.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                        dialog_share.dismiss();

                    }
                });

                dialog_share.findViewById(R.id.buy_).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        callback.purchaseCar(list.get(position),holder.carsBuyTV);
                        Log.i("Car","purchaseOnclick");
                        dialog_share.dismiss();
                    }
                });


            }
        });
    }
}

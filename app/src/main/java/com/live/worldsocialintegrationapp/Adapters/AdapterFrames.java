package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
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
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.ModelClasses.GetFramesRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AdapterFrames extends RecyclerView.Adapter<AdapterFrames.Myviewholder> {

    List<GetFramesRoot.Detail> list = new ArrayList<>();
    Context context;
    Callback callback;
    SVGAParser parser;

    public AdapterFrames(List<GetFramesRoot.Detail> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frames, parent, false);
        Myviewholder viewHolder = new Myviewholder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, @SuppressLint("RecyclerView") int position) {
        if (list.get(position).isMy()) {
            holder.framesBuyTV.setText("Bought");
            holder.framesValidityTv.setText(list.get(position).getRemainingDays());
        } else {
            holder.framesBuyTV.setText("Buy");
            buyClickDialogbox(holder, position);
            holder.framesValidityTv.setText(list.get(position).getValidity());
        }
        holder.framesSendRL.setOnClickListener(view -> callback.sendFrame(list.get(position)));
        holder.framesRVMoney.setText(list.get(position).getPrice());
        holder.testWear.setOnClickListener(view -> callback.testWare(list.get(position)));
        CommonUtils.setAnimation(context,list.get(position).getFrame_img(),holder.frameImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class Myviewholder extends RecyclerView.ViewHolder {

//        LottieAnimationView loadingBarImg;
        TextView framesRVMoney, framesBuyTV, testWear,framesValidityTv;
        RelativeLayout buyFrameRL, framesSendRL;
        SVGAImageView frameImage;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            frameImage = itemView.findViewById(R.id.framesRVImage);
            framesRVMoney = itemView.findViewById(R.id.framesRVMoney);
            buyFrameRL = itemView.findViewById(R.id.buyFrameRL);
            framesBuyTV = itemView.findViewById(R.id.framesBuyTV);
            framesSendRL = itemView.findViewById(R.id.framesSendRL);
            testWear = itemView.findViewById(R.id.testWear);
            framesValidityTv = itemView.findViewById(R.id.framesValidityTv);
//            loadingBarImg = itemView.findViewById(R.id.loadingBarImg);
        }
    }

    public interface Callback {
        void purchaseFrame(String frameId,TextView textView);

        void sendFrame(GetFramesRoot.Detail detail);

        void testWare(GetFramesRoot.Detail frameDetail);
    }

    private void buyClickDialogbox(Myviewholder holder, int position) {

        holder.buyFrameRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog_share = new Dialog(v.getContext());
                dialog_share.setContentView(R.layout.item_dialog_buy);
                dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog_share.setCanceledOnTouchOutside(true);
//            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//            layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
//            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                //  dialog_share.getWindow().setAttributes(layoutParams);
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

                        callback.purchaseFrame(list.get(position).getId(),holder.framesBuyTV);
                        dialog_share.dismiss();

                    }
                });


            }
        });
    }


}

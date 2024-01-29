package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.ModelClasses.ChatModel;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.opensource.svgaplayer.SVGAImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesRVAdapter extends RecyclerView.Adapter<MessagesRVAdapter.ViewHolder> {

    List<ChatModel> list = new ArrayList<>();
    Context context;
    public static String userId;
    Callback callback;
    MediaPlayer mPlayer;


    public MessagesRVAdapter(List<ChatModel> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MessagesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_rv_design, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MessagesRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.senderLlayout.setVisibility(View.GONE);
        holder.receiverLlayout.setVisibility(View.GONE);

        holder.sender_message_text.setVisibility(View.GONE);
        holder.receiver_message_text.setVisibility(View.GONE);
        holder.message_sender_image_view.setVisibility(View.GONE);
        holder.receiver_message_text.setVisibility(View.GONE);
        holder.receiverPlayVideoImg.setVisibility(View.GONE);
        holder.senderPlayVideoImg.setVisibility(View.GONE);
        holder.senderAudioLlayout.setVisibility(View.GONE);
        holder.receiverAudioLlayout.setVisibility(View.GONE);
        holder.receiverMessageTimeTv.setVisibility(View.GONE);
        holder.senderMessageTimeTv.setVisibility(View.GONE);
        holder.sendImgTimeTv.setVisibility(View.GONE);
        holder.receiveImgTimeTv.setVisibility(View.GONE);
        holder.senderCardView.setVisibility(View.GONE);
        holder.receiverCardView.setVisibility(View.GONE);

        if (list.get(position).isClaim()){
            holder.clainTV.setText("Claimed");
        }else {
            holder.clainTV.setText("Claim");
        }

        if (list.get(position).getFrom().equalsIgnoreCase(userId)) {

            if (list.get(position).getMsgType().equalsIgnoreCase("6")){

                if (list.get(position).getCarId() !=null && !list.get(position).getCarId().isEmpty()){
                    holder.senderCardView.setVisibility(View.VISIBLE);
                    holder.sendFrame.setVisibility(View.VISIBLE);
                    CommonUtils.setAnimation(context,list.get(position).getCar(),holder.sendFramesImg);
                }else if (list.get(position).getFrameId() !=null && !list.get(position).getFrameId().isEmpty()){
                    holder.senderCardView.setVisibility(View.VISIBLE);
                    holder.sendFrame.setVisibility(View.VISIBLE);
                    CommonUtils.setAnimation(context,list.get(position).getFrame(),holder.sendFramesImg);
                }else if (list.get(position).getGalleryId() !=null && !list.get(position).getGalleryId().isEmpty()){
                    holder.senderCardView.setVisibility(View.VISIBLE);
                    holder.sendFrame.setVisibility(View.VISIBLE);
                    holder.sendFramesImg.setVisibility(View.GONE);
                    holder.themeImage.setVisibility(View.VISIBLE);
                    Glide.with(context).load(list.get(position).getGalleryTheme()).error(R.drawable.wowicon).into(holder.themeImage);
                }else if (list.get(position).getThemeId() !=null && !list.get(position).getThemeId().isEmpty()){
                    holder.senderCardView.setVisibility(View.VISIBLE);
                    holder.sendFrame.setVisibility(View.VISIBLE);
                    holder.sendFramesImg.setVisibility(View.GONE);
                    holder.themeImage.setVisibility(View.VISIBLE);
                    Glide.with(context).load(list.get(position).getThemeImage()).error(R.drawable.wowicon).into(holder.themeImage);
                }
            }else if (list.get(position).getMsgType().equalsIgnoreCase("5")) {
                //type 3 means live link
                if (list.get(position).getLiveExtraMsg() != null && !list.get(position).getLiveExtraMsg().isEmpty()) {
                    holder.sender_message_text.setText(list.get(position).getMessageUrl() + " " + list.get(position).getLiveExtraMsg());
                } else {
                    holder.sender_message_text.setText(list.get(position).getMessageUrl());
                }
                if (list.get(position).getTime() != null && !list.get(position).getTime().isEmpty()) {
                    holder.senderMessageTimeTv.setVisibility(View.VISIBLE);
                    holder.senderMessageTimeTv.setText(list.get(position).getTime());
                }
                holder.sender_message_text.setLinksClickable(false);
                holder.sender_message_text.setVisibility(View.VISIBLE);
                holder.senderLlayout.setVisibility(View.VISIBLE);

                holder.sender_message_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callback.openLink(list.get(position).getMessage(), list.get(position).getLiveKey());

                    }
                });


            } else if (list.get(position).getMsgType().equalsIgnoreCase("4")) {
                //this is for audio
                holder.senderAudioLlayout.setVisibility(View.VISIBLE);
                if (list.get(position).getTime() != null && !list.get(position).getTime().isEmpty()){
                    holder.senderAudioTimeTv.setText(list.get(position).getTime());
                }
                Glide.with(holder.senderCirAudioImg.getContext()).load(App.getSharedpref().getString("image")).error(R.drawable.demo_user_profile_img).into(holder.senderCirAudioImg);

                senderAudioPlayer(holder, position, holder.sendAudioPlayImg, holder.senderSeekbar);

            } else if (list.get(position).getMsgType().equalsIgnoreCase("3")) {
                //type 3 means msg
                if (list.get(position).getTime() != null && !list.get(position).getTime().isEmpty()) {
                    holder.senderMessageTimeTv.setVisibility(View.VISIBLE);
                    holder.senderMessageTimeTv.setText(list.get(position).getTime());
                }
//                if (checkStringLetter(list.get(position).getMessage())){
//                    RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) holder.sender_message_text.getLayoutParams();
//                    relativeParams.width=100;  // left, top, right, bottom
//                    holder.sender_message_text.setLayoutParams(relativeParams);
//                }
                holder.sender_message_text.setText(list.get(position).getMessage());
                holder.sender_message_text.setVisibility(View.VISIBLE);
                holder.senderLlayout.setVisibility(View.VISIBLE);
            } else if (list.get(position).getMsgType().equalsIgnoreCase("2")) {
                //type 2 means video
                holder.message_sender_image_view.setVisibility(View.VISIBLE);
                holder.senderPlayVideoImg.setVisibility(View.VISIBLE);
                try {
                    Glide.with(holder.message_sender_image_view.getContext()).load(retriveVideoFrameFromVideo(list.get(position).getMessage())).error(R.drawable.play_video_icon).into(holder.message_sender_image_view);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                holder.message_sender_image_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callback.openImage(list.get(position).getMessage(), "2");
                    }
                });


            } else if (list.get(position).getMsgType().equalsIgnoreCase("1")) {

                //type 1 means image

                if (list.get(position).getTime() != null && !list.get(position).getTime().isEmpty()){
                    holder.sendImgTimeTv.setVisibility(View.VISIBLE);
                    holder.sendImgTimeTv.setText(list.get(position).getTime());
                }

                holder.senderCardView.setVisibility(View.VISIBLE);

                holder.message_sender_image_view.setVisibility(View.VISIBLE);
                Glide.with(holder.message_sender_image_view.getContext()).load(list.get(position).getMessage()).into(holder.message_sender_image_view);
//                Glide.with(holder.message_sender_image_view.getContext()).load(list.get(position).getMessageUrl()).into(holder.message_sender_image_view);
                holder.message_sender_image_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callback.openImage(list.get(position).getMessage(), "1");
                    }
                });

            }

        } else {

            if (list.get(position).getMsgType().equalsIgnoreCase("6")){
                if (list.get(position).getCarId() !=null && !list.get(position).getCarId().isEmpty()){
                    holder.receiverCardView.setVisibility(View.VISIBLE);
                    holder.receiverFrame.setVisibility(View.VISIBLE);
                    CommonUtils.setAnimation(context,list.get(position).getCar(),holder.framesImg);
                    holder.claimBTN.setOnClickListener(view -> callback.claimFrame(list.get(position),holder.clainTV));
                }else if (list.get(position).getFrameId() !=null && !list.get(position).getFrameId().isEmpty()){
                    holder.receiverCardView.setVisibility(View.VISIBLE);
                    holder.receiverFrame.setVisibility(View.VISIBLE);
                    CommonUtils.setAnimation(context,list.get(position).getFrame(),holder.framesImg);
                    holder.claimBTN.setOnClickListener(view -> callback.claimFrame(list.get(position),holder.clainTV));
                }else if (list.get(position).getGalleryId() !=null && !list.get(position).getGalleryId().isEmpty()){
                    holder.receiverCardView.setVisibility(View.VISIBLE);
                    holder.receiverFrame.setVisibility(View.VISIBLE);
                    holder.framesImg.setVisibility(View.GONE);
                    holder.receiveThemeImage.setVisibility(View.VISIBLE);
                    Glide.with(context).load(list.get(position).getGalleryTheme()).error(R.drawable.wowicon).into(holder.receiveThemeImage);
                    holder.claimBTN.setOnClickListener(view -> callback.claimFrame(list.get(position),holder.clainTV));
                }else if (list.get(position).getThemeId() !=null && !list.get(position).getThemeId().isEmpty()){
                    holder.receiverCardView.setVisibility(View.VISIBLE);
                    holder.receiverFrame.setVisibility(View.VISIBLE);
                    holder.framesImg.setVisibility(View.GONE);
                    holder.receiveThemeImage.setVisibility(View.VISIBLE);
                    Glide.with(context).load(list.get(position).getThemeImage()).error(R.drawable.wowicon).into(holder.receiveThemeImage);
                    holder.claimBTN.setOnClickListener(view -> callback.claimFrame(list.get(position),holder.clainTV));
                }

            }else if (list.get(position).getMsgType().equalsIgnoreCase("5")) {

                //type 5 means link
                if (list.get(position).getTime() != null && !list.get(position).getTime().isEmpty()) {
                    holder.receiverMessageTimeTv.setVisibility(View.VISIBLE);
                    holder.receiverMessageTimeTv.setText(list.get(position).getTime());
                }


                if (list.get(position).getLiveExtraMsg() != null && !list.get(position).getLiveExtraMsg().isEmpty()) {
                    holder.receiver_message_text.setText(list.get(position).getMessageUrl() + " " + list.get(position).getLiveExtraMsg());
                } else {
                    holder.receiver_message_text.setText(list.get(position).getMessageUrl());
                }
                holder.receiverLlayout.setVisibility(View.VISIBLE);
                holder.receiver_message_text.setLinksClickable(false);
                holder.receiver_message_text.setVisibility(View.VISIBLE);

                holder.receiver_message_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callback.openLink(list.get(position).getMessage(), list.get(position).getLiveKey());
                    }
                });

            } else if (list.get(position).getMsgType().equalsIgnoreCase("4")) {
                //this is for audio

                if (list.get(position).getTime() != null && !list.get(position).getTime().isEmpty()){
                    holder.receiveAduioTimetv.setText(list.get(position).getTime());
                }

                holder.receiverAudioLlayout.setVisibility(View.VISIBLE);
                Glide.with(holder.receiverCirAudioImg.getContext()).load(App.getSharedpref().getString("otherUserImg")).error(R.drawable.demo_user_profile_img).into(holder.receiverCirAudioImg);
                senderAudioPlayer(holder, position, holder.receiverAudioPlayImg, holder.receiverSeekbar);

            } else if (list.get(position).getMsgType().equalsIgnoreCase("3")) {
                //type 3 means msg

                if (list.get(position).getTime() != null && !list.get(position).getTime().isEmpty()) {
                    holder.receiverMessageTimeTv.setVisibility(View.VISIBLE);
                    holder.receiverMessageTimeTv.setText(list.get(position).getTime());
                }
//                if (checkStringLetter(list.get(position).getMessage())){
//                    RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) holder.receiver_message_text.getLayoutParams();
//                    relativeParams.width=100;  // left, top, right, bottom
//                    holder.receiver_message_text.setLayoutParams(relativeParams);
//                }
                holder.receiver_message_text.setText(list.get(position).getMessage());
                holder.receiver_message_text.setVisibility(View.VISIBLE);
                holder.receiverLlayout.setVisibility(View.VISIBLE);

            } else if (list.get(position).getMsgType().equalsIgnoreCase("2")) {
                //type 2 means video
                holder.message_receiver_image_view.setVisibility(View.VISIBLE);
                holder.receiverPlayVideoImg.setVisibility(View.VISIBLE);
                try {
                    Glide.with(holder.message_receiver_image_view.getContext()).load(retriveVideoFrameFromVideo(list.get(position).getMessage())).error(R.drawable.play_video_icon).into(holder.message_receiver_image_view);
                } catch (Throwable e) {
                    e.printStackTrace();
                }

                holder.message_receiver_image_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callback.openImage(list.get(position).getMessage(), "2");
                    }
                });
            } else if (list.get(position).getMsgType().equalsIgnoreCase("1")) {
                //type 1 means image

                if (list.get(position).getTime() != null && !list.get(position).getTime().isEmpty()){
                    holder.receiveImgTimeTv.setVisibility(View.VISIBLE);
                    holder.receiveImgTimeTv.setText(list.get(position).getTime());
                }
                holder.receiverCardView.setVisibility(View.VISIBLE);

                holder.message_receiver_image_view.setVisibility(View.VISIBLE);

                Glide.with(holder.message_receiver_image_view.getContext()).load(list.get(position).getMessage()).into(holder.message_receiver_image_view);

                holder.message_receiver_image_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callback.openImage(list.get(position).getMessage(), "1");
                    }
                });
            }
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                callback.deleteMessage(list.get(position).getMsgId());
                return true;
            }
        });


    }

    private void senderAudioPlayer(ViewHolder holder, int position, ImageView imageView, SeekBar seekBar) {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mPlayer != null) {
                    if (mPlayer.isPlaying()) {
                        pauseMediaPlayer(imageView);
                    } else {
                        medioPlayer(list.get(position).getMessage(), imageView, seekBar);
                    }
                } else {
                    medioPlayer(list.get(position).getMessage(), imageView, seekBar);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout receiverLinearLayout, senderLinearLayout ,senderLlayout,receiverLlayout,receiverFrame;
        private TextView receiverTV, senderTV, receiver_message_text, sender_message_text, senderMessageTimeTv,
                receiverMessageTimeTv,receiveImgTimeTv,sendImgTimeTv,senderAudioTimeTv,receiveAduioTimetv,clainTV;
        private ImageView message_sender_image_view, message_receiver_image_view, receiverPlayVideoImg,
                senderPlayVideoImg, receiverAudioPlayImg, sendAudioPlayImg,themeImage,receiveThemeImage;
        private CircleImageView senderCirAudioImg, receiverCirAudioImg;
        private SeekBar senderSeekbar, receiverSeekbar;
        private RelativeLayout receiverTimeRl,receiverAudioLlayout,senderAudioLlayout,claimBTN,sendFrame;
        private CardView senderCardView,receiverCardView;
        private SVGAImageView sendFramesImg,framesImg;

        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            receiverLinearLayout= itemView.findViewById(R.id.receiverLinearLayout);
//            senderLinearLayout=itemView.findViewById(R.id.senderLinearLayout);
            senderTV = itemView.findViewById(R.id.senderTV);
            receiverTV = itemView.findViewById(R.id.senderTV);
            senderLlayout = itemView.findViewById(R.id.senderLlayout);
            receiverLlayout = itemView.findViewById(R.id.receiverLlayout);
            receiver_message_text = itemView.findViewById(R.id.receiver_message_text);
            sender_message_text = itemView.findViewById(R.id.sender_message_text);
            message_sender_image_view = itemView.findViewById(R.id.message_sender_image_view);
            message_receiver_image_view = itemView.findViewById(R.id.message_receiver_image_view);
            receiverPlayVideoImg = itemView.findViewById(R.id.receiverPlayVideoImg);
            senderPlayVideoImg = itemView.findViewById(R.id.senderPlayVideoImg);
            receiverAudioLlayout = itemView.findViewById(R.id.receiverAudioLlayout);
            senderAudioLlayout = itemView.findViewById(R.id.senderAudioLlayout);
            receiverAudioPlayImg = itemView.findViewById(R.id.receiverAudioPlayImg);
            sendAudioPlayImg = itemView.findViewById(R.id.sendAudioPlayImg);
            senderCirAudioImg = itemView.findViewById(R.id.senderCirAudioImg);
            receiverCirAudioImg = itemView.findViewById(R.id.receiverCirAudioImg);
            senderSeekbar = itemView.findViewById(R.id.senderSeekbar);
            receiverSeekbar = itemView.findViewById(R.id.receiverSeekbar);
            senderMessageTimeTv = itemView.findViewById(R.id.senderMessageTimeTv);
            receiverMessageTimeTv = itemView.findViewById(R.id.receiverMessageTimeTv);
            receiveImgTimeTv = itemView.findViewById(R.id.receiveImgTimeTv);
            sendImgTimeTv = itemView.findViewById(R.id.sendImgTimeTv);
            senderCardView = itemView.findViewById(R.id.senderCardView);
            receiverCardView = itemView.findViewById(R.id.receiverCardView);
            senderAudioTimeTv = itemView.findViewById(R.id.senderAudioTimeTv);
            receiveAduioTimetv = itemView.findViewById(R.id.receiveAduioTimetv);
            sendFrame = itemView.findViewById(R.id.sendFrame);
            sendFramesImg = itemView.findViewById(R.id.sendFramesImg);
            receiverFrame = itemView.findViewById(R.id.receiverFrame);
            framesImg = itemView.findViewById(R.id.framesImg);
            claimBTN = itemView.findViewById(R.id.claimBTN);
            clainTV = itemView.findViewById(R.id.claimTV);
            themeImage = itemView.findViewById(R.id.themeImage);
            receiveThemeImage = itemView.findViewById(R.id.receiveThemeImage);
        }
    }

    public interface Callback {
        void openImage(String url, String statusVideoOrImage);

        void deleteMessage(String msgKey);

        void openLink(String userId, String liveKey);
        void claimFrame(ChatModel model,TextView textView);
    }


    private boolean checkStringLetter(String text){
        boolean b=false;
        if (text != null){
            int length= text.length();
            if (length<4){
                b=true;
            }
        }

        return b;
    }
    public void medioPlayer(String path, ImageView imageView, SeekBar seekBar) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(path);
            mPlayer.prepare();
            mPlayer.start();
            seekBar.setProgress(0);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int currentPosition = mPlayer.getCurrentPosition();
                    int total = mPlayer.getDuration();

                    while (mPlayer != null && mPlayer.isPlaying() && currentPosition < total) {
                        try {
                            Thread.sleep(1000);
                            currentPosition = mPlayer.getCurrentPosition();

                        } catch (InterruptedException e) {
                            return;
                        } catch (Exception e) {
                            return;
                        }
                        seekBar.setProgress(currentPosition);
                    }
                }
            }).start();
            seekBar.setMax(mPlayer.getDuration());

            imageView.setImageResource(R.drawable.ic_baseline_pause_circle_24);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        mPlayer.seekTo(seekBar.getProgress());
                    }
                    if (seekBar.getProgress() == mPlayer.getCurrentPosition()) {
                        imageView.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        } catch (IOException e) {
            Log.e("TAG", "prepare() failed");
        }
    }

    public void pauseMediaPlayer(ImageView imageView) {
        imageView.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
    }

    //this this for Url video thrmbnail
    public Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(videoPath);
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());
        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }


}

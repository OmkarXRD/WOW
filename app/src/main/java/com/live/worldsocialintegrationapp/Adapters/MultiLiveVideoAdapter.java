package com.live.worldsocialintegrationapp.Adapters;

import static com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity.LAYOUT_TYPE_DEFAULT;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.firebase.GoLiveModelClass;
import com.live.worldsocialintegrationapp.agora.openvcall.model.AGEventHandler;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.layout.VideoViewAdapterUtil;
import com.live.worldsocialintegrationapp.databinding.LayoutMultiUserLiveListBinding;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.agora.rtc.IAudioEffectManager;
import io.agora.rtc.IAudioFrameObserver;
import io.agora.rtc.ILogWriter;
import io.agora.rtc.IMetadataObserver;
import io.agora.rtc.IRtcChannelEventHandler;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.IVideoEncodedFrameObserver;
import io.agora.rtc.IVideoFrameObserver;
import io.agora.rtc.RtcChannel;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.audio.AudioRecordingConfiguration;
import io.agora.rtc.internal.EncryptionConfig;
import io.agora.rtc.internal.LastmileProbeConfig;
import io.agora.rtc.internal.RtcEngineImpl;
import io.agora.rtc.live.LiveInjectStreamConfig;
import io.agora.rtc.live.LiveTranscoding;
import io.agora.rtc.mediaio.IVideoSink;
import io.agora.rtc.mediaio.IVideoSource;
import io.agora.rtc.models.ChannelMediaOptions;
import io.agora.rtc.models.ClientRoleOptions;
import io.agora.rtc.models.DataStreamConfig;
import io.agora.rtc.models.UserInfo;
import io.agora.rtc.video.AgoraImage;
import io.agora.rtc.video.AgoraVideoFrame;
import io.agora.rtc.video.BeautyOptions;
import io.agora.rtc.video.CameraCapturerConfiguration;
import io.agora.rtc.video.ChannelMediaRelayConfiguration;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;
import io.agora.rtc.video.VirtualBackgroundSource;
import io.agora.rtc.video.WatermarkOptions;

public class MultiLiveVideoAdapter extends RecyclerView.Adapter<MultiLiveVideoAdapter.ViewHolder> {

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference emojiRef = firebaseDatabase.getReference().child("emojiRef");
    private final DatabaseReference lockSeat = firebaseDatabase.getReference().child("lockSeat");
    private final DatabaseReference adminLiveRef = firebaseDatabase.getReference().child("adminLiveRef");
    private final DatabaseReference ref = firebaseDatabase.getReference().child("userInfo");

    private Context context;
    private List<GoLiveModelClass> goLiveModelClasses;
    private Click click;
    public static String directHostId;
    private TextView sitOnSeatTv, openSeatTv, micTV, kickOutTv, ProfileTv, inviteAudienceTv;
    public static String adminId = "0", liveType, peerUid ="", volume = "",profileAdminCheck="0";

    private final int minVolume = 10;
    private final int maxVolume = 140;
    public int mLayoutType = LAYOUT_TYPE_DEFAULT;

    //this is for emoji
    public static String userId, emoji, hostId, status;

    public MultiLiveVideoAdapter(Context context, List<GoLiveModelClass> goLiveModelClasses, Click click) {
        this.context = context;
        this.goLiveModelClasses = goLiveModelClasses;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutMultiUserLiveListBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        long voicedectect =  goLiveModelClasses.get(position).getPeerId();

        if (goLiveModelClasses.get(position).getSvga() != null) {
          //  Toast.makeText(context, "Adapter :-"+ goLiveModelClasses.get(position).getSvga(), Toast.LENGTH_SHORT).show();
            CommonUtils.setAnimation(holder.itemView.getContext(),goLiveModelClasses.get(position).getSvga(), holder.binding.profieFrame);
        }else {
            Toast.makeText(context, "Technical issue...", Toast.LENGTH_SHORT).show();
        }


        if (!goLiveModelClasses.get(position).getUserID().equalsIgnoreCase("")){
            if (goLiveModelClasses.get(position).getMute().equalsIgnoreCase("1")){
                holder.binding.voiceIndicationLottie.setVisibility(View.VISIBLE);
                holder.binding.voiceIndicationLottie.playAnimation();
            }else {
                holder.binding.voiceIndicationLottie.setVisibility(View.GONE);
                holder.binding.voiceIndicationLottie.cancelAnimation();
            }

            seatLock(holder, position);
            //this for emoji if user joined live
            if (goLiveModelClasses.get(position).getUserID().equalsIgnoreCase(userId) && userId != null) {

//                Toast.makeText(context, "user not joined 1", Toast.LENGTH_SHORT).show();
                holder.binding.emojiImg.setVisibility(View.VISIBLE);
                Glide.with(context).load(emoji).into(holder.binding.emojiImg);

                Log.d("ADAPTER", "emoji: " + emoji);
                new Handler().postDelayed(() -> holder.binding.emojiImg.setVisibility(View.GONE), 5000);
                emoji = null;
                emojiRef.child(hostId).removeValue();

            } else if (hostId != null) {

                //if user not join live but send emoji
//                Toast.makeText(context, "user not joined", Toast.LENGTH_SHORT).show();
//                click.showEmojiBackToActivity(emoji,userId,hostId);
                emojiRef.child(hostId).child("status").setValue("1");
            }

            Glide.with(context).load(goLiveModelClasses.get(position).getImage()).into(holder.binding.imgUserProfile);
            holder.binding.txtUserName.setText((!goLiveModelClasses.get(position).getName().equalsIgnoreCase("")) ? goLiveModelClasses.get(position).getName() : "User not set name");
            holder.binding.rlMic.setVisibility(View.VISIBLE);

        } else {
            holder.binding.rlMic.setVisibility(View.GONE);
//          holder.binding.txtUserName.setText("Seat No:- " + (position + 1));
            holder.binding.txtUserName.setText(" " + (position + 1));
            seatLock(holder, position);
        }

        holder.binding.rlMic.setOnClickListener(view -> {
            if (goLiveModelClasses.get(position).getMute().equalsIgnoreCase("1")) {
                click.muteMic(goLiveModelClasses.get(position), holder.binding.imgMic, "0");
            } else {
                click.muteMic(goLiveModelClasses.get(position), holder.binding.imgMic, "1");
            }
        });

        if (goLiveModelClasses.get(position).getMute().equalsIgnoreCase("1")) {
            holder.binding.imgMic.setImageResource(R.drawable.ic_baseline_mic_24);
            holder.binding.imgMic.setVisibility(View.GONE);
            if (goLiveModelClasses.get(position).getPeerId()==1){
                holder.binding.voiceIndicationLottie.setVisibility(View.VISIBLE);
                holder.binding.voiceIndicationLottie.playAnimation();
            }else {
                holder.binding.voiceIndicationLottie.setVisibility(View.GONE);
                holder.binding.voiceIndicationLottie.cancelAnimation();
            }

        } else {
            holder.binding.imgMic.setVisibility(View.VISIBLE);
            holder.binding.imgMic.setImageResource(R.drawable.ic_baseline_mic_off_24);
            holder.binding.voiceIndicationLottie.setVisibility(View.GONE);
            holder.binding.voiceIndicationLottie.cancelAnimation();
        }
    }

    @Override
    public int getItemCount() {
        return goLiveModelClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LayoutMultiUserLiveListBinding binding;
        public ViewHolder(@NonNull LayoutMultiUserLiveListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface Click {
        void setOnUserKickListener(GoLiveModelClass goLiveModelClass, String admin);

        void setOnShowUserProfile(GoLiveModelClass goLiveModelClass, int adminStatus, String adminIdThroughCallback);

        void muteMic(GoLiveModelClass goLiveModelClass, AppCompatImageView muteImg, String muteStatus);

        void showEmojiBackToActivity(String emoji, String userId, String hostId);

        void selectSeat(GoLiveModelClass goLiveModelClass, String positon);

        void lockSeat(GoLiveModelClass goLiveModelClass, String positon);

        void inviteForSeat(String position);

    }


    private void seatLock(ViewHolder holder, int position) {
        checkAdmin();
        lockSeat.child(directHostId).child("s" + position).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    if (snapshot.getValue().toString().equalsIgnoreCase("1")) {
                        holder.binding.imgChair.setImageResource(R.drawable.padlock);
                    } else if (snapshot.getValue().toString().equalsIgnoreCase("0")) {
                        holder.binding.imgChair.setImageResource(R.drawable.ic_baseline_add);
                        lockSeat.child(directHostId).child("s" + String.valueOf(position)).removeValue();
                    } else {
                        holder.binding.imgChair.setImageResource(R.drawable.ic_baseline_add);
                    }
                    holder.binding.imgUserProfile.setOnClickListener(view -> {
                        if (directHostId.equalsIgnoreCase(AppConstants.USER_ID) || adminId.equalsIgnoreCase(AppConstants.USER_ID)) {
                            //1 means seat locked
                            if (snapshot.getValue().toString().equalsIgnoreCase("1")) {
                                dialogbox(holder, position, "1");
                            } else {
                                dialogbox(holder, position, "0");
                            }
//                                if(goLiveModelClasses.get(position).getUserID().equalsIgnoreCase(adminId) && adminId.equalsIgnoreCase(AppConstants.USER_ID)){
//                                    click.selectSeat(goLiveModelClasses.get(position), String.valueOf(position));
//                                    Toast.makeText(context,"both are admin exits", Toast.LENGTH_SHORT).show();
//                                }
                        } else {
                            if (snapshot.getValue().toString().equalsIgnoreCase("1")) {
                            } else {
                                if (!goLiveModelClasses.get(position).getUserID().equalsIgnoreCase("")) {
                                } else {
                                    click.selectSeat(goLiveModelClasses.get(position), String.valueOf(position));
                                }
                            }
                        }
                    });

                } else {
                    holder.binding.imgUserProfile.setOnClickListener(view -> {
                        if (directHostId.equalsIgnoreCase(AppConstants.USER_ID) || adminId.equalsIgnoreCase(AppConstants.USER_ID)) {
                            if (!goLiveModelClasses.get(position).getUserID().equalsIgnoreCase("")) {
                                //this for when double admin you and other admin profile
                                adminLiveRef.child(directHostId).child(goLiveModelClasses.get(position).getUserID()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                        if (snapshot1.exists()) {
                                            profileAdminCheck = snapshot1.child("adminId").getValue().toString();
                                            Toast.makeText(context, "otherUserAdmin "+profileAdminCheck, Toast.LENGTH_SHORT).show();
                                            if (adminId.equalsIgnoreCase(AppConstants.USER_ID)){
                                                click.setOnShowUserProfile(goLiveModelClasses.get(position), 1, profileAdminCheck);
                                            }else if (directHostId.equalsIgnoreCase(AppConstants.USER_ID)){
                                                dialogbox(holder, position, "0");
                                            }
                                        }else{
                                            profileAdminCheck="";
                                            dialogbox(holder, position, "0");
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }else{
                                dialogbox(holder, position, "0");
                            }

//                                if(goLiveModelClasses.get(position).getUserID().equalsIgnoreCase(adminId) && adminId.equalsIgnoreCase(AppConstants.USER_ID)){
//                                    click.selectSeat(goLiveModelClasses.get(position), String.valueOf(position));
//                                    Toast.makeText(context,"both are admin not exits", Toast.LENGTH_SHORT).show();
//                                }

                        } else {
                            if (goLiveModelClasses.get(position).getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {
                                dialogbox(holder, position, "2"); //2 means want to stand from the seat that user who dont have a admin and host
                            } else {
                                if (!goLiveModelClasses.get(position).getUserID().equalsIgnoreCase("")) {
                                    int adminInt = 0;
                                    if (goLiveModelClasses.get(position).getUserID().equalsIgnoreCase(adminId)) {
                                        adminInt = 1;
                                        Toast.makeText(context, "admin11", Toast.LENGTH_SHORT).show();
                                    } else {
                                        adminInt = 0;
                                        Toast.makeText(context, "not admin22", Toast.LENGTH_SHORT).show();
                                    }
                                    click.setOnShowUserProfile(goLiveModelClasses.get(position), adminInt, adminId);
                                } else {
                                    click.selectSeat(goLiveModelClasses.get(position), String.valueOf(position));
                                }

//                                    if(goLiveModelClasses.get(position).getUserID().equalsIgnoreCase(adminId) && adminId.equalsIgnoreCase(AppConstants.USER_ID)){
//                                        click.selectSeat(goLiveModelClasses.get(position), String.valueOf(position));
//                                        Toast.makeText(context,"both are admin not exits", Toast.LENGTH_SHORT).show();
//                                    }

                            }

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void checkAdmin() {

        adminLiveRef.child(directHostId).child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminId = snapshot.child("adminId").getValue().toString();
                }else{
                    adminId="";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void dialogbox(ViewHolder holder, int position, String s) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.click_on_live_users_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.show();

        openSeatTv = dialog.findViewById(R.id.openSeatTv);
        sitOnSeatTv = dialog.findViewById(R.id.sitOnSeatTv);
        micTV = dialog.findViewById(R.id.micTV);
        kickOutTv = dialog.findViewById(R.id.kickOutTv);
        ProfileTv = dialog.findViewById(R.id.ProfileTv);
        inviteAudienceTv = dialog.findViewById(R.id.inviteAudienceTv);

        if (s.equalsIgnoreCase("2")) {
            openSeatTv.setVisibility(View.GONE);
            inviteAudienceTv.setVisibility(View.GONE);
            sitOnSeatTv.setVisibility(View.VISIBLE);
            ProfileTv.setVisibility(View.VISIBLE);

        } else {
            openSeatTv.setVisibility(View.VISIBLE);
            inviteAudienceTv.setVisibility(View.VISIBLE);
            sitOnSeatTv.setVisibility(View.VISIBLE);
        }

        if (!goLiveModelClasses.get(position).getUserID().equalsIgnoreCase("")) {

            if (adminId.equalsIgnoreCase(AppConstants.USER_ID) || directHostId.equalsIgnoreCase(AppConstants.USER_ID)) {

                openSeatTv.setVisibility(View.GONE);
                inviteAudienceTv.setVisibility(View.GONE);
                ProfileTv.setVisibility(View.VISIBLE);
                kickOutTv.setVisibility(View.VISIBLE);
                micTV.setVisibility(View.VISIBLE);
                sitOnSeatTv.setVisibility(View.VISIBLE);


                sitOnSeatTv.setText("Stand");

                //mute unmute mic
                if (goLiveModelClasses.get(position).getMute().equalsIgnoreCase("1")) {
                    holder.binding.imgMic.setImageResource(R.drawable.ic_baseline_mic_24);
                    micTV.setText("Mute mic");

                } else {
                    holder.binding.imgMic.setImageResource(R.drawable.ic_baseline_mic_off_24);
                    micTV.setText("Unmute mic");
                }

                ProfileTv.setOnClickListener(view -> {
                    if (adminId.equalsIgnoreCase(AppConstants.USER_ID)) {
                        Toast.makeText(context, "admin", Toast.LENGTH_SHORT).show();
                        click.setOnShowUserProfile(goLiveModelClasses.get(position), 1, adminId);
                    } else if (directHostId.equalsIgnoreCase(AppConstants.USER_ID)) {
                        Toast.makeText(context, "host admin", Toast.LENGTH_SHORT).show();
                        click.setOnShowUserProfile(goLiveModelClasses.get(position), 0, AppConstants.USER_ID);
                    } else {
                        Toast.makeText(context, "user admi", Toast.LENGTH_SHORT).show();
                        click.setOnShowUserProfile(goLiveModelClasses.get(position), 0, adminId);
                    }
                });

            } else {

                openSeatTv.setVisibility(View.GONE);
                inviteAudienceTv.setVisibility(View.GONE);


                if (goLiveModelClasses.get(position).getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {
                    sitOnSeatTv.setVisibility(View.VISIBLE);
                    sitOnSeatTv.setText("Stand");
                } else {

                    int adminInt = 0;
                    if (goLiveModelClasses.get(position).getUserID().equalsIgnoreCase(adminId)) {
                        adminInt = 1;
                        Toast.makeText(context, "admin", Toast.LENGTH_SHORT).show();
                    } else {
                        adminInt = 0;
                        Toast.makeText(context, "not admin 12", Toast.LENGTH_SHORT).show();
                    }
                    click.setOnShowUserProfile(goLiveModelClasses.get(position), adminInt, adminId);
                }
            }
            micTV.setOnClickListener(view -> {
                if (adminId.equalsIgnoreCase(AppConstants.USER_ID) || directHostId.equalsIgnoreCase(AppConstants.USER_ID)) {
                    if (goLiveModelClasses.get(position).getMute().equalsIgnoreCase("1")) {
                        click.muteMic(goLiveModelClasses.get(position), holder.binding.imgMic, "0");
                        dialog.dismiss();
                    } else {
                        click.muteMic(goLiveModelClasses.get(position), holder.binding.imgMic, "1");
                        dialog.dismiss();
                    }
                }
            });
            kickOutTv.setOnClickListener(view -> {
                if (adminId.equalsIgnoreCase(AppConstants.USER_ID) || directHostId.equalsIgnoreCase(AppConstants.USER_ID)) {
                    click.setOnUserKickListener(goLiveModelClasses.get(position), adminId);
                    dialog.dismiss();
                }
            });
        } else {
            if (adminId.equalsIgnoreCase(AppConstants.USER_ID) || directHostId.equalsIgnoreCase(AppConstants.USER_ID)) {

                if (adminId.equalsIgnoreCase(AppConstants.USER_ID)) {
                    sitOnSeatTv.setText("Sit");
                } else {
                    if (sitOnSeatTv.getText().toString().equalsIgnoreCase("Sit")) {
                        sitOnSeatTv.setVisibility(View.GONE);
                    } else if (directHostId.equalsIgnoreCase(AppConstants.USER_ID)) {
                        sitOnSeatTv.setVisibility(View.VISIBLE);
                    }
                }
                if (s.equalsIgnoreCase("1")) {
                    openSeatTv.setText("Open");
                } else {
                    openSeatTv.setText("Close");
                }
            } else {
                sitOnSeatTv.setText("Sit");
            }
            //lock open seat
            openSeatTv.setOnClickListener(view -> {
                if (s.equalsIgnoreCase("1")) {
                    lockSeat.child(directHostId).child("s" + position).setValue("0");
                    dialog.dismiss();
                } else {
                    lockSeat.child(directHostId).child("s" + position).setValue("1"); //1 for true
                    dialog.dismiss();
                }
            });

            inviteAudienceTv.setOnClickListener(view -> click.inviteForSeat(String.valueOf(position)));
        }

        sitOnSeatTv.setOnClickListener(view -> {
            if (sitOnSeatTv.getText().toString().equalsIgnoreCase("Sit")) {
                if (!directHostId.equalsIgnoreCase(AppConstants.USER_ID)) {
                    click.selectSeat(goLiveModelClasses.get(position), String.valueOf(position));
                    dialog.dismiss();
                } else {
                }
            } else {
                if (adminId.equalsIgnoreCase(AppConstants.USER_ID) || directHostId.equalsIgnoreCase(AppConstants.USER_ID)
                        || goLiveModelClasses.get(position).getUserID().equalsIgnoreCase(AppConstants.USER_ID)) {

                    ref.child(directHostId).child(liveType).child(directHostId).child("multiLiveRequest").child(goLiveModelClasses.get(position).getUserID())
                            .child("requestStatus").setValue("2");
                    dialog.dismiss();
                }
            }
        });
        ProfileTv.setOnClickListener(view -> {
            if (adminId.equalsIgnoreCase(AppConstants.USER_ID)) {
                Toast.makeText(context, "admin", Toast.LENGTH_SHORT).show();
                click.setOnShowUserProfile(goLiveModelClasses.get(position), 1, adminId);
            } else if (directHostId.equalsIgnoreCase(AppConstants.USER_ID)) {
                Toast.makeText(context, "host admin", Toast.LENGTH_SHORT).show();
                click.setOnShowUserProfile(goLiveModelClasses.get(position), 0, AppConstants.USER_ID);
            } else {
                Toast.makeText(context, "user admi", Toast.LENGTH_SHORT).show();
                click.setOnShowUserProfile(goLiveModelClasses.get(position), 0, adminId);
            }
        });
    }

}

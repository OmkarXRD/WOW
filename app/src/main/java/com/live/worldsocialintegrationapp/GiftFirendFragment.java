package com.live.worldsocialintegrationapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.live.worldsocialintegrationapp.Adapters.GiftFirendAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.ChatModel;
import com.live.worldsocialintegrationapp.ModelClasses.ChatNotificationRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetFramesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetFriends.Detail;
import com.live.worldsocialintegrationapp.ModelClasses.GetLuckIdRoot;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiftFirendFragment extends Fragment {

   private RecyclerView friendsRV ;
   private String carId,image,frameId,frameImage ;
   int poistion = 0;
   private List<Detail> list ;
   private DatabaseReference chatRef ;
   public static int check = 0 , type = 0;
   private Mvvm mvvm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        chatRef = FirebaseDatabase.getInstance().getReference("ChatMessages");
        mvvm =  new ViewModelProvider(this).get(Mvvm.class);
        return inflater.inflate(R.layout.fragment_gift_firend, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        finds(view);
        hitApiGetFriend();
        click(view);
    }

    private void click(View view) {

        view.findViewById(R.id.editProfileFriendBakImg).setOnClickListener(view1 -> requireActivity().onBackPressed());
    }

    private void hitApiGetFriend() {
        mvvm.getFriends(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), getFriendRoot -> {

            if (getFriendRoot != null) {
                if (getFriendRoot.getStatus().equalsIgnoreCase("1")) {
                    list = getFriendRoot.getDetails();
                    GiftFirendAdapter adapter = new GiftFirendAdapter(list, requireContext(), (otherUserId, poistion) -> hitApiSendGift(otherUserId));
                    friendsRV.setAdapter(adapter);
                } else {
                }
            } else {
            }
        });
    }

    private void hitApiSendGift(String otherUserId) {

        Dialog dialog_share = new Dialog(requireContext());
        dialog_share.setContentView(R.layout.send_frame_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();

        //initlize views
        TextView sendFrameTv1 = dialog_share.findViewById(R.id.sendFrameTv1);
        TextView sendFrameCoinsTV = dialog_share.findViewById(R.id.sendFrameCoinsTV);
        TextView sendFrameDialogValidityTv = dialog_share.findViewById(R.id.sendFrameDialogValidityTv);
        RelativeLayout sendFrameRL = dialog_share.findViewById(R.id.sendFrameRL);
        RelativeLayout cancelFrameRL = dialog_share.findViewById(R.id.cancelFrameRL);
        ImageView dialogFrameImage = dialog_share.findViewById(R.id.dialogFrameImage);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            String validity = bundle.getString("validity");
            String img = bundle.getString("img");
            String price = bundle.getString("price");
            String giftType = bundle.getString("giftType");
            String carId = bundle.getString("getLuckId");

            sendFrameTv1.setText("Are you sure you want to send this " + giftType + " to your friend" + " " + list.get(0).getName() + "?");
            sendFrameCoinsTV.setText(price);
            sendFrameDialogValidityTv.setText(validity);
            Glide.with(dialogFrameImage.getContext()).load(img).error(R.drawable.demo_user_profile_img).into(dialogFrameImage);
        } else {
        }

        cancelFrameRL.setOnClickListener(v -> dialog_share.dismiss());

        sendFrameRL.setOnClickListener(v -> {
            if (check == 1){
                carId = getArguments().getString("getLuckId");
                image = getArguments().getString("img");
                type = 1 ;
                sendCar(carId,image,type,otherUserId);
            }else if(check == 2){
                frameId = getArguments().getString("getFrameId");
                frameImage = getArguments().getString("image");
                type = 2;
                sendFrame(frameId,frameImage,type,otherUserId);
            }


            dialog_share.dismiss();
        });
    }

    private void sendFrame(String frameId, String frameImage, int type, String otherUserId) {



        mvvm.sendFrame(requireActivity(), AppConstants.USER_ID, otherUserId, frameId).observe(requireActivity(), new Observer<GetFramesRoot>() {
            @Override
            public void onChanged(GetFramesRoot getFramesRoot) {
                if (getFramesRoot != null) {
                    if (getFramesRoot.getStatus() == 1) {
                        notEnoughCoins(frameId,frameImage,type);
                        //Toast.makeText(requireContext(), "1"+getFramesRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(requireContext(), "0"+getFramesRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

            private void notEnoughCoins(String frameId, String frameImage, int type) {


                Log.d("sendFrame", "frameImage: "+frameImage);

                String msgSendRef = "Messages/" + AppConstants.USER_ID + "/" + otherUserId;
                String msgRecRef = "Messages/" + otherUserId + "/" + AppConstants.USER_ID;
                DatabaseReference userMsgKeyRef = chatRef.child("Messages").child(AppConstants.USER_ID).child(otherUserId).push();
                String msgPushId = userMsgKeyRef.getKey();
                ChatModel messages = new ChatModel();
                messages.setFrom(AppConstants.USER_ID);
                messages.setMsgType("6");
                messages.setTo(otherUserId);
                messages.setMsgId(msgPushId);
                messages.setFrame(frameImage);
                messages.setFrameId(frameId);
                messages.setType2(type);
                Map messageBodyDetails = new HashMap();
                messageBodyDetails.put(msgSendRef + "/" + msgPushId, messages);

                messageBodyDetails.put(msgRecRef + "/" + msgPushId, messages);

                chatRef.updateChildren(messageBodyDetails).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if (task.isSuccessful()) {

                                sendMessageNotificationApi();

//                            lastMessage(chatMsgEdtx.getText().toString());
                        } else {
                        }
                    }

                    private void sendMessageNotificationApi() {

                        mvvm.sendChatNotification(requireActivity(), AppConstants.USER_ID, otherUserId).observe(requireActivity(), new Observer<ChatNotificationRoot>() {
                            @Override
                            public void onChanged(ChatNotificationRoot chatNotificationRoot) {
                                if (chatNotificationRoot != null) {
                                    if (chatNotificationRoot.getSuccess().equalsIgnoreCase("1")) {
                                        if (getContext() != null) {
                                            //Toast.makeText(requireContext(), "" + chatNotificationRoot.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        });
                    }
                });

            }
        });

    }

    private void sendCar(String carId, String image, int type, String otherUserId) {

        mvvm.sendLuckId(requireActivity(), AppConstants.USER_ID, otherUserId, carId).observe(requireActivity(), new Observer<GetLuckIdRoot>() {
            @Override
            public void onChanged(GetLuckIdRoot getLuckIdRoot) {
                if (getLuckIdRoot.getStatus() == 1) {
                    sendusermessage();
                    //Toast.makeText(requireContext(), "1 " + getLuckIdRoot.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(requireContext(), "0 " + getLuckIdRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            private void sendusermessage() {

                String msgSendRef = "Messages/" + AppConstants.USER_ID + "/" + otherUserId;
                String msgRecRef = "Messages/" + otherUserId + "/" + AppConstants.USER_ID;
                DatabaseReference userMsgKeyRef = chatRef.child("Messages").child(AppConstants.USER_ID).child(otherUserId).push();
                String msgPushId = userMsgKeyRef.getKey();
                ChatModel messages = new ChatModel();
                messages.setFrom(AppConstants.USER_ID);
                messages.setMsgType("6");
                messages.setTo(otherUserId);
                messages.setMsgId(msgPushId);
                messages.setCar(image);
                messages.setCarId(carId);
                messages.setType2(type);
                Map messageBodyDetails = new HashMap();
                messageBodyDetails.put(msgSendRef + "/" + msgPushId, messages);

                messageBodyDetails.put(msgRecRef + "/" + msgPushId, messages);

                chatRef.updateChildren(messageBodyDetails).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if (task.isSuccessful()) {
                            sendMSGAPI();
//                            lastMessage(chatMsgEdtx.getText().toString());
                        } else {
                        }
                    }

                    private void sendMSGAPI() {

                        mvvm.sendChatNotification(requireActivity(), AppConstants.USER_ID, otherUserId).observe(requireActivity(), new Observer<ChatNotificationRoot>() {
                            @Override
                            public void onChanged(ChatNotificationRoot chatNotificationRoot) {
                                if (chatNotificationRoot != null) {
                                    if (chatNotificationRoot.getSuccess().equalsIgnoreCase("1")) {
                                        if (getContext() != null) {
                                            Toast.makeText(requireContext(), "" + chatNotificationRoot.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });
    }


    private void finds(View view) {
        friendsRV = view.findViewById(R.id.friendsRV);
    }
}

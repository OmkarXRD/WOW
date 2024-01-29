package com.live.worldsocialintegrationapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.live.worldsocialintegrationapp.Adapters.SendGalleryFriendAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.ChatModel;
import com.live.worldsocialintegrationapp.ModelClasses.ChatNotificationRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetFriends.Detail;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SendGalleryDialogFragment extends BottomSheetDialogFragment implements SendGalleryFriendAdapter.Callback {


    private RecyclerView friendsRV;
    private List<Detail> friendList;
    private ImageView sendGalleryBackImg;
    public static int sendCheck = 0;
    private String galleryId,liveThemeId,themeImage = "",galleryTheme = "";
    private int type = 0;
    private DatabaseReference chatRef ;
    private Mvvm mvvm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        chatRef = FirebaseDatabase.getInstance().getReference("ChatMessages");
        mvvm = new ViewModelProvider(this).get(Mvvm.class);
        return inflater.inflate(R.layout.fragment_send_gallery_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        friendsRV = view.findViewById(R.id.friendsGalleryRV);
        sendGalleryBackImg = view.findViewById(R.id.sendGalleryBackImg);

        clicks(view);
        if(getArguments() != null){
            if(sendCheck==0){
                galleryId = getArguments().getString("galleryId");
                themeImage = getArguments().getString("theme");
            }else{
                liveThemeId =  getArguments().getString("liveThemeId");
                galleryTheme = getArguments().getString("purchaseTheme");
            }

        }else{
            Toast.makeText(requireContext(), "Technical issue...", Toast.LENGTH_SHORT).show();
        }
        getFriendListApi();
    }

    private void clicks(View view) {
        sendGalleryBackImg.setOnClickListener(view1 -> requireActivity().onBackPressed());
    }

    private void getFriendListApi() {

        mvvm.getFriends(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), getFriendRoot -> {
            if (getFriendRoot != null) {
                if (getFriendRoot.getStatus().equalsIgnoreCase("1")) {
                    Toast.makeText(requireContext(), "1 " + getFriendRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    friendList = new ArrayList<>();
                    friendList = getFriendRoot.getDetails();
                    SendGalleryFriendAdapter friendRVAdapter = new SendGalleryFriendAdapter(friendList, requireContext(), SendGalleryDialogFragment.this);
                    friendsRV.setAdapter(friendRVAdapter);
                } else {
                    Toast.makeText(requireContext(), "0 " + getFriendRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void callback(String otherUserId, int poistion) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void sendGallery(Detail detail, TextView textView) {
        if (sendCheck == 1) {
            mvvm.sendLiveTheme(requireActivity(),AppConstants.USER_ID,detail.getId(),liveThemeId).observe(requireActivity(), sendLiveTheme -> {
                if(sendLiveTheme != null){
                    if(sendLiveTheme.getStatus()==1){
                        Toast.makeText(requireContext(), ""+sendLiveTheme.getMessage(), Toast.LENGTH_SHORT).show();
                        textView.setText("Sent");
                        type  = 3;
                        sendTheme(detail,type);
                    }else{
                        Toast.makeText(requireContext(), ""+sendLiveTheme.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            mvvm.sendGallery(requireActivity(),
                    CommonUtils.getRequestBodyText(AppConstants.USER_ID),
                    CommonUtils.getRequestBodyText(detail.getId()),
                    CommonUtils.getRequestBodyText(galleryId),CommonUtils.getFileData(themeImage,"image")).observe(requireActivity(), sendGalleryRoot -> {
                if (sendGalleryRoot != null) {
                    if (sendGalleryRoot.getStatus() == 1) {
                        textView.setText("Sent");
                        type = 4;
                        sendusermessage(detail,type,sendGalleryRoot.getUploaded());
                        Toast.makeText(requireContext(), ""+sendGalleryRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), ""+sendGalleryRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void sendTheme(Detail detail, int type) {

        String msgSendRef = "Messages/" + AppConstants.USER_ID + "/" + detail.getId();
        String msgRecRef = "Messages/" + detail.getId() + "/" + AppConstants.USER_ID;
        DatabaseReference userMsgKeyRef = chatRef.child("Messages").child(AppConstants.USER_ID).child(detail.getId()).push();
        String msgPushId = userMsgKeyRef.getKey();
        ChatModel messages = new ChatModel();
        messages.setFrom(AppConstants.USER_ID);
        messages.setMsgType("6");
        messages.setTo(detail.getId());
        messages.setMsgId(msgPushId);
        messages.setThemeImage(galleryTheme);
        messages.setThemeId(liveThemeId);
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
                mvvm.sendChatNotification(requireActivity(), AppConstants.USER_ID, detail.getId()).observe(requireActivity(), new Observer<ChatNotificationRoot>() {
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

    private void sendusermessage(Detail detail, int type, String image) {

        String msgSendRef = "Messages/" + AppConstants.USER_ID + "/" + detail.getId();
        String msgRecRef = "Messages/" + detail.getId() + "/" + AppConstants.USER_ID;
        DatabaseReference userMsgKeyRef = chatRef.child("Messages").child(AppConstants.USER_ID).child(detail.getId()).push();
        String msgPushId = userMsgKeyRef.getKey();
        ChatModel messages = new ChatModel();
        messages.setFrom(AppConstants.USER_ID);
        messages.setMsgType("6");
        messages.setTo(detail.getId());
        messages.setMsgId(msgPushId);
        messages.setGalleryTheme(image);
        messages.setGalleryId(galleryId);
        messages.setType2(type);
        Map messageBodyDetails = new HashMap();
        messageBodyDetails.put(msgSendRef + "/" + msgPushId, messages);
        messageBodyDetails.put(msgRecRef + "/" + msgPushId, messages);

        chatRef.updateChildren(messageBodyDetails).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()) {

                    sendAPi();
//                            lastMessage(chatMsgEdtx.getText().toString());
                } else {
                }
            }

            private void sendAPi() {
                mvvm.sendChatNotification(requireActivity(), AppConstants.USER_ID, detail.getId()).observe(requireActivity(), new Observer<ChatNotificationRoot>() {
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
}
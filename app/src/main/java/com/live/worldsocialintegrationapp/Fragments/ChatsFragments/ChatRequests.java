package com.live.worldsocialintegrationapp.Fragments.ChatsFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.worldsocialintegrationapp.Adapters.ChatRequestRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.RequstChat;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ChatRequests extends Fragment implements ChatRequestRVAdapter.Callback{

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<RequstChat> chatRequestList;
    private AlertDialog.Builder builder;
    ChatRequestRVAdapter chatRequestsAdapter;
    RecyclerView chatRequestsRV;
    private TextView noChatRequestTv;
    private ImageView chatRequestBackImg;
    public static int backPressed=0,checkForFragment=0;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference ChatRequestCountRef = firebaseDatabase.getReference().child("ChatRequestCount");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_requests, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //this is for notification
        if(getArguments() !=null && getArguments().containsKey("data_key")){
            String data=getArguments().getString("data_key");

        }else if(getArguments() !=null && getArguments().containsKey("backPressed")){
            backPressed=getArguments().getInt("backPressed");
        }

        findIds(view);
        onBackPressed(view);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ChatRequest");
        chatRequestList = new ArrayList<>();


        chatRequestsAdapter = new ChatRequestRVAdapter(requireContext(),new ArrayList<>(),ChatRequests.this);
        chatRequestsRV.setAdapter(chatRequestsAdapter);

        ReceiveRequest();
        clicks(view);
        chatRequestCount();

    }

    private void chatRequestCount() {

        HashMap<String,String> requestCountHp = new HashMap<>();
        requestCountHp.put("count","0"); //count requests
        requestCountHp.put("checkRequest","0"); //0 means request all checked 1 means not checked

        ChatRequestCountRef.child(AppConstants.USER_ID).setValue(requestCountHp);
    }

    private void clicks(View view) {

        chatRequestBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(backPressed==0){
                    getActivity().onBackPressed();
                }else{
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout,new ChatFragment()).addToBackStack(null).commit();
                }

            }
        });
    }

    private void findIds(View view) {

        chatRequestsRV=view.findViewById(R.id.chatRequestsRV);
        noChatRequestTv=view.findViewById(R.id.noChatRequestTv);
        chatRequestBackImg=view.findViewById(R.id.chatRequestBackImg);
    }

    private void ReceiveRequest(){

        myRef.child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    chatRequestList.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren())
                    {
                        RequstChat requstChat = snapshot1.getValue(RequstChat.class);
                        String type = snapshot1.child("type").getValue().toString();
                        String otherUserId = snapshot1.child("from").getValue().toString();
                        noChatRequestTv.setVisibility(View.GONE);

                        if(!otherUserId.equalsIgnoreCase(AppConstants.USER_ID) && type.equalsIgnoreCase("0")){
                            chatRequestList.add(requstChat);
                            noChatRequestTv.setVisibility(View.GONE);
                            Log.i("ReqMsg",type + "_______" + otherUserId);
                        }else{
                                chatRequestList.remove(requstChat);

                            if (chatRequestList.isEmpty()){
                                noChatRequestTv.setVisibility(View.VISIBLE);
                            }

                        }
                    }

                    if(!chatRequestList.isEmpty()){
                        chatRequestsAdapter.loadData(chatRequestList);
                        chatRequestsAdapter.notifyDataSetChanged();
                        noChatRequestTv.setVisibility(View.GONE);
                    }else{
                        noChatRequestTv.setVisibility(View.VISIBLE);
                    }

                }else{
                    noChatRequestTv.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ChatRequestDialogBox(String poistion){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setMessage("Do you want to accept chat request ?");
        builder.setTitle("Chat Request");
        builder.setCancelable(false);

        builder.setPositiveButton("Accept", (DialogInterface.OnClickListener) (dialog, which) -> {
            updateChatRequest(poistion);
            chatRequestList.remove(poistion);
            chatRequestsAdapter.notifyDataSetChanged();
            dialog.dismiss();
            friendRequestAccept(chatRequestList.get(Integer.parseInt(poistion)).getUserId());

        });

        builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.green));

    }

    private void updateChatRequest(String poistion){

        int p=Integer.parseInt(poistion);
        RequstChat requstChat = new RequstChat(chatRequestList.get(p).getFrom(),chatRequestList.get(p).getUserId(),"1",chatRequestList.get(p).getTo()
        ,chatRequestList.get(p).getToImg(),chatRequestList.get(p).getToName(),chatRequestList.get(p).getFromImg(),chatRequestList.get(p).getFromName(),chatRequestList.get(p).getLastMessage(),chatRequestList.get(p).getFromDob(),chatRequestList.get(p).getFromGender(),chatRequestList.get(p).getFromFriendStatus(),"1");

        RequstChat requstChatSender = new RequstChat(chatRequestList.get(p).getTo(),AppConstants.USER_ID,"1",chatRequestList.get(p).getFrom()
                ,chatRequestList.get(p).getFromImg(),chatRequestList.get(p).getFromName(),chatRequestList.get(p).getToImg(),chatRequestList.get(p).getToName(),chatRequestList.get(p).getLastMessage(),"","",chatRequestList.get(p).getFromFriendStatus(),"1");

        myRef.child(chatRequestList.get(p).getFrom()).child(AppConstants.USER_ID).setValue(requstChat);
        myRef.child(AppConstants.USER_ID).child(chatRequestList.get(p).getFrom()).setValue(requstChatSender);

        ReceiveRequest();
    }

    private void friendRequestAccept(String otherUserId) {

        new Mvvm().followUsers(requireActivity(),AppConstants.USER_ID,otherUserId,"friendRequest").observe(requireActivity(), new Observer<SendOtpRoot>() {
            @Override
            public void onChanged(SendOtpRoot sendOtpRoot) {

                Log.i("FriendReq",otherUserId);
                Log.i("FriendReq",AppConstants.USER_ID);
            }


        });


    }


    @Override
    public void callback(String otherUserId, String poistion) {

        if(!otherUserId.isEmpty() && chatRequestList.get(Integer.parseInt(poistion)).getType().equalsIgnoreCase("0")){
            ChatRequestDialogBox(poistion);

        }else if(!otherUserId.isEmpty() && chatRequestList.get(Integer.parseInt(poistion)).getType().equalsIgnoreCase("1")){
            Bundle bundle = new Bundle();
            bundle.putString("otherUserId",otherUserId);
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.messagesFragment,bundle);
        }else{
            if(getContext() != null){
            }

        }
    }

    private void onBackPressed(View view) {
        if(backPressed==0) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        if (i == KeyEvent.KEYCODE_BACK) {
                            getActivity().onBackPressed();
                            return true;
                        }
                    }
                    return false;
                }
            });
        }else{
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        if (i == KeyEvent.KEYCODE_BACK) {
                            ChatFragment chatFragment = new ChatFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt("backPressed", 1);
                            chatFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout,chatFragment).addToBackStack(null).commit();
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

    }



}
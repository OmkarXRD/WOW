package com.live.worldsocialintegrationapp.Fragments.ChatsFragments;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Adapters.ChatRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.RequstChat;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentChatBinding;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatFragment extends Fragment implements ChatRVAdapter.Callback {
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference ChatRequestCountRef = firebaseDatabase.getReference().child("ChatRequestCount");
    private FragmentChatBinding binding;
    private ChatRVAdapter chatRVAdapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<RequstChat> chatRequestList;
    private  int backPressed = 0;

    private int noOfRequest;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChatBinding.inflate(inflater, container, false);
        setStatusBarGradiant(requireActivity());

        if (getArguments() != null && getArguments().containsKey("backPressed")){
            backPressed=getArguments().getInt("backPressed");
        }
        return binding.getRoot();
    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.layout.setOnClickListener(view1 -> Navigation.findNavController(requireActivity(),R.id.nav_home).navigate(R.id.fragment_notification));
        binding.layout.setOnClickListener(view1 -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_home,new ChatServiceFragment()).addToBackStack(null).commit());

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        if (backPressed==0) {

            //CommonUtils.disableBottomNavigation(requireActivity());
        }else if (backPressed == 1){
//            binding.back.setOnClickListener(view1 -> );
        }

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ChatRequest");
        chatRequestList = new ArrayList<>();

        clicks(view);
        onBackPressed(view);

        chatRVAdapter = new ChatRVAdapter(requireContext(), new ArrayList<>(), ChatFragment.this);
        binding.chatRV.setAdapter(chatRVAdapter);
        noOfRequest=0;
        receiveRequests();


        //RequestCountFirebase();

    }
    private void clicks(View view) {
        binding.userRequestsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (backPressed == 0) {

                    Log.i("CHAT SCREEN","Inside 1");
                    Bundle bundle = new Bundle();
                    bundle.putInt("backPressed",0);
//                    ChatRequests.backPressed = 0;
                    Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.chatRequests,bundle);
                } else {
//                 ChatRequests.backPressed = 1;
                    Log.i("CHAT SCREEN","Inside 2");
                    ChatRequests chatRequests = new ChatRequests();
                    Bundle bundle = new Bundle();
                    bundle.putInt("backPressed",1);
                    chatRequests.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout,chatRequests).addToBackStack(null).commit();
                }
            }
        });
    }

    @Override
    public void callback(String otherUserid, String name, String image, RequstChat requstChat) {

        if (backPressed == 0) {
            Bundle bundle = new Bundle();
            bundle.putString("otherUserId", otherUserid);
            bundle.putString("otherUserImg", image);
            bundle.putString("otherUserName", name);
            bundle.putInt("backPressed", 0);
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.messagesFragment, bundle);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("otherUserId", otherUserid);
            bundle.putString("otherUserImg", image);
            bundle.putString("otherUserName", name);
            bundle.putInt("backPressed", 1);

            MessagesFragment messagesFragment = new MessagesFragment();
            messagesFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout, messagesFragment).addToBackStack(null).commit();
        }
    }

    private void receiveRequests() {

        if (CommonUtils.isNetworkConnected(requireContext())) {
            myRef.child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        chatRequestList.clear();
                        noOfRequest = 0;
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                            RequstChat requstChat = snapshot1.getValue(RequstChat.class);
                            Object typeObject = snapshot1.child("type").getValue();
                            String type = (typeObject != null) ? typeObject.toString() : "";
                            Object toValue = snapshot1.child("to").getValue();
                            String otherUserId = (toValue != null) ? toValue.toString() : "";



                            if (!otherUserId.equalsIgnoreCase(AppConstants.USER_ID) && type.equalsIgnoreCase("1")) {
                                chatRequestList.add(requstChat);
                                Log.i("ReqMsg",type + " " + otherUserId);
                            } else {
                                noOfRequest++;
                                Log.i("ReqMsg",type + " else" + otherUserId);
                            }
                        }

                        //condition to check pending friend request
                        if(noOfRequest > 0){
                            String req = String.valueOf(noOfRequest);
                            binding.reqestCountTv.setVisibility(View.VISIBLE);
                            binding.reqestCountTv.setText(req);
                        }
                        else{
                            binding.reqestCountTv.setVisibility(View.GONE);
                        }


                        if (chatRequestList.isEmpty()) {
                            binding.messageText.setVisibility(View.VISIBLE);
                        }
                        else {
                            binding.messageText.setVisibility(View.GONE);
                            chatRVAdapter.loadData(chatRequestList);
                            chatRVAdapter.notifyDataSetChanged();
                        }
                    } else {
                       binding.messageText.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            if (getContext() != null) {
                Toast.makeText(requireContext(), "Connect to network", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void onBackPressed(View view) {

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().onBackPressed();
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.homeFragment);

            }
        });



        if (backPressed == 0) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        if (i == KeyEvent.KEYCODE_BACK) {
                            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.homeFragment);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
//        else
//            if (backPressed == 22) {
//            view.requestFocus();
//
//            view.setOnKeyListener(new View.OnKeyListener() {
//                @Override
//                public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
//                        if (i == KeyEvent.KEYCODE_BACK) {
//                            return true;
//                        }
//                    }
//                    return false;
//                }
//            });
//        }
        else {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        if (i == KeyEvent.KEYCODE_BACK) {
                            getActivity().findViewById(R.id.callAcitivtyMainRL).setVisibility(View.VISIBLE);
                            getActivity().findViewById(R.id.callActivityFrameLayout).setVisibility(View.GONE);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }

    private void RequestCountFirebase() {
        ChatRequestCountRef.child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String requestCount = snapshot.child("count").getValue().toString();
                    String checkRequest = snapshot.child("checkRequest").getValue().toString();
                    binding.reqestCountTv.setVisibility(View.VISIBLE);
                   binding.reqestCountTv.setText(requestCount);
                } else {
                    binding.reqestCountTv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.gradient);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.darker_gray));
            window.setBackgroundDrawable(background);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        boolean shouldChangeStatusBarTintToDark = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = requireActivity().getWindow().getDecorView();
            if (shouldChangeStatusBarTintToDark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }
    }

    //    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding=null;
//    }
}
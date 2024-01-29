package com.live.worldsocialintegrationapp.utils;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;

public class FirebaseRemoveLiveUsers {

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference liveUsersRef = firebaseDatabase.getReference().child("liveUsersRef");
    private final DatabaseReference cleanUserCommentsRef = firebaseDatabase.getReference().child("cleanUserCommentsRef");
    private final DatabaseReference lockRef = firebaseDatabase.getReference().child("lockRef");
    private final DatabaseReference banChatRef = firebaseDatabase.getReference().child("banChatRef");
    private final DatabaseReference muteMicRef = firebaseDatabase.getReference().child("muteMicRef");
    private final DatabaseReference LuckBagRef = firebaseDatabase.getReference().child("LuckBagRef");
    private final DatabaseReference luckyDivideUsersRef = firebaseDatabase.getReference().child("luckyDivideUsersRef");
    private final DatabaseReference lockSeat = firebaseDatabase.getReference().child("lockSeat");
    private final DatabaseReference emojiRef = firebaseDatabase.getReference().child("emojiRef");
    private final DatabaseReference userLiveAnnouncement = firebaseDatabase.getReference().child("userLiveAnnouncement");
    private final DatabaseReference ref = firebaseDatabase.getReference().child("userInfo");
    Activity activity;
    String userId;

    private void removeLiveUser() {

        liveUsersRef.child(AppConstants.USER_ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String liveId = snapshot.child("liveId").getValue().toString();
                    String hostId = snapshot.child("hostId").getValue().toString();
                    String liveType = snapshot.child("liveType").getValue().toString();
                    //Toast.makeText(activity.getApplicationContext(), "snapshot liveId " + liveId, Toast.LENGTH_SHORT).show();
//                    endLiveApi(liveId, hostId, liveType);
                } else {
                    String liveId = App.getSharedpref().getString("liveId");
                    String liveType = App.getSharedpref().getString("liveType");
                    String hostId = App.getSharedpref().getString("hostId");

                    if (!liveId.equalsIgnoreCase("") && !liveType.equalsIgnoreCase("") && !hostId.equalsIgnoreCase("")) {
                        //Toast.makeText(activity.getApplicationContext(), "shardPref liveId", Toast.LENGTH_SHORT).show();
//                        endLiveApi(liveId, hostId, liveType);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    private void endLiveApi(String liveId, String hostId, String liveType,Activity activity) {
//        new Mvvm().endLiveCall(activity, liveId).observe(, new Observer<SendOtpRoot>() {
//            @Override
//            public void onChanged(SendOtpRoot sendOtpRoot) {
//                if (sendOtpRoot != null) {
//                    if (sendOtpRoot.getStatus().equalsIgnoreCase("1")) {
//                        try {
//                            ref.child(userId).child(liveType).removeValue();
//                            liveUsersRef.child(AppConstants.USER_ID).removeValue();
//
//
//                            muteMicRef.child(hostId).removeValue();
//                            userLiveAnnouncement.child(hostId).removeValue();
//                            cleanUserCommentsRef.child(hostId).removeValue();
//                            LuckBagRef.child(hostId).removeValue();
//                            emojiRef.child(hostId).removeValue();
//                            luckyDivideUsersRef.child(hostId).removeValue();
//                            lockSeat.child(hostId).removeValue();
//                            ref.child(userId).child(liveType).removeValue();
//
//                            Toast.makeText(activity, "Live ended", Toast.LENGTH_SHORT).show();
//
//                        } catch (Exception e) {
//                        }
//                        App.getSharedpref().saveString("liveId", "");
//                        App.getSharedpref().saveString("liveType", "");
//                        App.getSharedpref().saveString("hostId", "");
//                    }
//                } else {
//                    Toast.makeText(activity, "Technical issue", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//
//        });
//    }

}

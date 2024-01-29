package com.live.worldsocialintegrationapp.Firebase;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavDeepLinkBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Activites.MainActivity;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.Objects;
import java.util.Random;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMessagingServ";
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    private final int NOTIFICATION_ID = 10;
    private NotificationChannel channel = null;
    private Uri defaultSound;
    private Notification notification;
    private NotificationChannel mChannel;
    private Intent notificationIntent;
    private Intent voiceCutIntent;
    private String checkType = "chatNotification";
    String bigContent;


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

//        Log.d("NOTIFICATION", "onReceived: " + remoteMessage.getData().get(URLBuilder.Parameter.title.toString()) + " key " + Objects.requireNonNull(remoteMessage.getData().get(URLBuilder.Parameter.type.toString())) + " " + remoteMessage.getData().get(URLBuilder.Parameter.message.toString())
//                + "token " + remoteMessage.getData().get(URLBuilder.Parameter.token.toString()) + "toke " + remoteMessage.getData().get(URLBuilder.Parameter.toke.toString())
//                + "image " + remoteMessage.getData().get(URLBuilder.Parameter.image.toString())
//                + "hostType " + remoteMessage.getData().get(URLBuilder.Parameter.hostType.toString())
//                + "liveUserId " + remoteMessage.getData().get(URLBuilder.Parameter.liveUserId.toString())
//                + "channelName " + remoteMessage.getData().get(URLBuilder.Parameter.channelName.toString())
//                + "archivedDate " + remoteMessage.getData().get(URLBuilder.Parameter.archivedDate.toString())
//                + "endTime " + remoteMessage.getData().get(URLBuilder.Parameter.endTime.toString())
//                + "toliveCountken " + remoteMessage.getData().get(URLBuilder.Parameter.toliveCountken.toString())
//                + "live_password " + remoteMessage.getData().get(URLBuilder.Parameter.live_password.toString())
//                + "Liveimage " + remoteMessage.getData().get(URLBuilder.Parameter.Liveimage.toString())
//                + "imageText " + remoteMessage.getData().get(URLBuilder.Parameter.imageText.toString())
//                + "imageTitle " + remoteMessage.getData().get(URLBuilder.Parameter.imageTitle.toString())
//                + "live_hideUnhideStatus " + remoteMessage.getData().get(URLBuilder.Parameter.live_hideUnhideStatus.toString())
//                + "liveUser_name " + remoteMessage.getData().get(URLBuilder.Parameter.liveUser_name.toString())
//                + "liveUser_username " + remoteMessage.getData().get(URLBuilder.Parameter.liveUser_username.toString())
//                + "liveUser_dob " + remoteMessage.getData().get(URLBuilder.Parameter.liveUser_dob.toString())
//                + "liveUser_gender " + remoteMessage.getData().get(URLBuilder.Parameter.liveUser_gender.toString())
//                + " followerId " + remoteMessage.getData().get(URLBuilder.Parameter.followerId.toString())
//                + " follower_name " + remoteMessage.getData().get(URLBuilder.Parameter.follower_name.toString())
//                + " follower_dob " + remoteMessage.getData().get(URLBuilder.Parameter.follower_dob.toString())
//                + " follower_username " + remoteMessage.getData().get(URLBuilder.Parameter.follower_username.toString())
//                + " follower_gender " + remoteMessage.getData().get(URLBuilder.Parameter.follower_gender.toString())
//
//        );


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            setBookingOreoNotification(
                    remoteMessage.getData().get(URLBuilder.Parameter.title.toString()),
                    remoteMessage.getData().get(URLBuilder.Parameter.message.toString()),
                    Objects.requireNonNull(remoteMessage.getData().get(URLBuilder.Parameter.type.toString())),
                    "",
                    bigContent, remoteMessage.getData().get(URLBuilder.Parameter.notifyBy.toString()),remoteMessage.getData().get(URLBuilder.Parameter.notifyByImage.toString()),
                    remoteMessage.getData().get(URLBuilder.Parameter.notifyById.toString()));

//            setBookingOreoNotification(
//                    remoteMessage.getData().get(URLBuilder.Parameter.title.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.message.toString()),
//                    Objects.requireNonNull(remoteMessage.getData().get(URLBuilder.Parameter.type.toString())),
//                    "",
//                    bigContent,
//            URLBuilder.Parameter.notifyBy.toString(),URLBuilder.Parameter.notifyByImage.toString(),
//                    URLBuilder.Parameter.notifyById.toString(),
//                    remoteMessage.getData().get(URLBuilder.Parameter.channelName.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.liveUserId.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.liveUserId.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.hostType.toString()),
//                    "live",
//                    remoteMessage.getData().get(URLBuilder.Parameter.token.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.liveUser_username.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.liveUserId.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.imageTitle.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.Liveimage.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.image.toString()),
//                    "live",
//                    remoteMessage.getData().get(URLBuilder.Parameter.liveUser_dob.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.liveUser_gender.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.follower_name.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.follower_gender.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.follower_username.toString()),
//                    remoteMessage.getData().get(URLBuilder.Parameter.followerId.toString())
//            );

            Log.d("MYFIREBASE","chatnotification1: "+remoteMessage.getData().get(URLBuilder.Parameter.notifyBy.toString()));


        } else {

            showNotification(remoteMessage.getData().get(URLBuilder.Parameter.title.toString()),
                    remoteMessage.getData().get(URLBuilder.Parameter.message.toString()), "",
                    Objects.requireNonNull(remoteMessage.getData().get(URLBuilder.Parameter.type.toString())), "", bigContent, URLBuilder.Parameter.notifyBy.toString(), URLBuilder.Parameter.notifyByImage.toString(),
                    URLBuilder.Parameter.notifyById.toString());

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private void showNotification(String title, String message, String sx, String type, String image, String bigContent, String notific, String notifyByImage, String notifyById) {

        Log.d("NOTIFICATION", "showNotificaion: " + type + " " + message + " " + title);
        Intent intent = null;
        if (!type.equalsIgnoreCase("")) {
            intent = new Intent(this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(HomeActivity.data_key, "1");
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_IMMUTABLE);
        defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        final NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Random random = new Random();
        final int m = random.nextInt(9999 - 1000) + 1000;


        if (!type.equalsIgnoreCase("")) {

            if (type.equalsIgnoreCase(URLBuilder.Type.followUnfollow.toString())) {
                final Notification.Builder builder = new Notification.Builder(getApplicationContext());

                Notification.BigTextStyle textStyle = new Notification.BigTextStyle(builder);

                if (image.equalsIgnoreCase("")) {

                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.wow_icon);

                    notificationManager.notify(m, builder.build());

                } else if (type.equalsIgnoreCase(URLBuilder.Type.chatRequest.toString())) {

                    Log.d("MYFIREBASE","chatnotification1: "+" no2");
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.wow_icon);

                    notificationManager.notify(m, builder.build());

                } else if (type.equalsIgnoreCase(URLBuilder.Type.userLive.toString())) {

                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.wow_icon);

                    notificationManager.notify(m, builder.build());

                } else {
                    Notification.BigPictureStyle pictureStyle = new Notification.BigPictureStyle(builder);
                    builder.setStyle(pictureStyle
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setSmallIcon(R.drawable.wow_icon);
                }
            } else {


                final Notification.Builder builder = new Notification.Builder(getApplicationContext());
                Notification.BigTextStyle textStyle = new Notification.BigTextStyle(builder);
                if (image.equalsIgnoreCase("")) {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
//                             .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.wow_icon);

                    notificationManager.notify(m, builder.build());
                } else {
                    Notification.BigPictureStyle pictureStyle = new Notification.BigPictureStyle(builder);
                    builder.setStyle(pictureStyle
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setAutoCancel(true)
//                             .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setSmallIcon(R.drawable.wow_icon);

                    Log.d("MYFIREBASE","chatnotification1: "+" no1");


                }
            }

        }   if (!type.equalsIgnoreCase("")) {

            if (type.equalsIgnoreCase(URLBuilder.Type.family_request_accept.toString())) {
                final Notification.Builder builder = new Notification.Builder(getApplicationContext());

                Notification.BigTextStyle textStyle = new Notification.BigTextStyle(builder);

                if (image.equalsIgnoreCase("")) {

                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.wow_icon);

                    notificationManager.notify(m, builder.build());

                } else if (type.equalsIgnoreCase(URLBuilder.Type.chatRequest.toString())) {

                    Log.d("MYFIREBASE","chatnotification1: "+" no2");
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.wow_icon);

                    notificationManager.notify(m, builder.build());

                } else if (type.equalsIgnoreCase(URLBuilder.Type.userLive.toString())) {

                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.wow_icon);

                    notificationManager.notify(m, builder.build());

                } else {
                    Notification.BigPictureStyle pictureStyle = new Notification.BigPictureStyle(builder);
                    builder.setStyle(pictureStyle
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setSmallIcon(R.drawable.wow_icon);
                }
            } else {


                final Notification.Builder builder = new Notification.Builder(getApplicationContext());
                Notification.BigTextStyle textStyle = new Notification.BigTextStyle(builder);
                if (image.equalsIgnoreCase("")) {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .bigText(sx)
                                    .setBigContentTitle("Chat Notification")
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
//                             .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setSmallIcon(R.drawable.wow_icon);

                    notificationManager.notify(m, builder.build());
                } else {
                    Notification.BigPictureStyle pictureStyle = new Notification.BigPictureStyle(builder);
                    builder.setStyle(pictureStyle
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setAutoCancel(true)
//                             .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSound(defaultSound)
                            .setSmallIcon(R.drawable.wow_icon);

                    Log.d("MYFIREBASE","chatnotification1: "+" no1");


                }
            }

        }

        else {
            notification = new NotificationCompat.Builder(this)
                    .setGroup(type)
                    .setContentText(message)
                    .setContentTitle(title)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.wow_icon)
                    .setAutoCancel(true)
                    .setVibrate(new long[]{200, 200, 200, 200})
                    .setSound(defaultSound)
                    .build();
            notificationManager.notify(m, notification);
            Log.d("MYFIREBASE","chatnotification1: "+" no2");
        }
    }

    public void setNotification(String title, String body) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "2")
                .setSmallIcon(R.drawable.wow_icon)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(101, builder.build());
        Log.d("MYFIREBASE","chatnotification1: "+" no3");
    }


//    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
//    private void setBookingOreoNotification(String title, String message, String type, String image, String bigContent, String notifyBy,String notifyByImage,String notifyById,String channelName, String userId, String liveHostIds,
//                                            String liveType,
//                                            String liveStatus,
//                                            String token,
//                                            String name1,
//                                            String liveHostId,
//                                            String broadTitle,
//                                            String liveimage,
//                                            String userProfileImage,
//                                            String status,
//                                            String dob,
//                                            String gender,
//                                            String userDob,
//                                            String UserGender,
//                                            String foller_username,
//                                            String followerId) {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private void setBookingOreoNotification(String title, String message, String type, String image, String bigContent, String notifyBy, String notifyByImage, String notifyById) {


        PendingIntent pendingIntent = null;
        Intent intent = null;

        if (!type.equalsIgnoreCase("")) {
            Log.d("MYFIREBASESERIVE", "Type " + type);

            if (type.equalsIgnoreCase(URLBuilder.Type.followUnfollow.toString())) {

                intent = new Intent(this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(HomeActivity.data_key, "1");

                pendingIntent = PendingIntent.getActivity(this, 101,
                        intent, PendingIntent.FLAG_IMMUTABLE);

            } else if (type.equalsIgnoreCase(URLBuilder.Type.chatRequest.toString())) {

                Bundle bundle = new Bundle();
                bundle.putString("data_key", "1");

                pendingIntent = new NavDeepLinkBuilder(this)
                        .setComponentName(HomeActivity.class)
                        .setGraph(R.navigation.home_graph)
                        .setDestination(R.id.chatRequests)
                        .setArguments(bundle)
                        .createPendingIntent();

                Log.d("MYFIREBASE","chatnotification1: "+" no3");

            } else if (type.equalsIgnoreCase(URLBuilder.Type.family_request_accept.toString())) {

                Bundle bundle = new Bundle();
                bundle.putString("data_key", "1");

                pendingIntent = new NavDeepLinkBuilder(this)
                        .setComponentName(HomeActivity.class)
                        .setGraph(R.navigation.home_graph)
                        .setDestination(R.id.chatRequests)
                        .setArguments(bundle)
                        .createPendingIntent();

                Log.d("MYFIREBASE","chatnotification1: "+" no3");

            } else if (type.equalsIgnoreCase(URLBuilder.Type.userLive.toString())) {


                intent = new Intent(this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(HomeActivity.data_key, "1");


//                App.getSingletone().setLiveType("");
//
//                intent.putExtra("channelName", channelName);
//
//                intent.putExtra("liveHostIds", userId);
//                intent.putExtra("liveType", "multiLive");
//                intent.putExtra("liveStatus", "host");
//                intent.putExtra("token", token);
//
//                if(liveHostId.equalsIgnoreCase(AppConstants.USER_ID)){
//
//                    intent.putExtra("name",name1);
//                    intent.putExtra("userId", userId);
//                }else{
//                    intent.putExtra("name",foller_username);
//                    intent.putExtra("userId", followerId);
//                }
//
//                intent.putExtra("liveHostId", userId);
//
//                if (broadTitle != null && !broadTitle.isEmpty()) {
//                    intent.putExtra("broadTitle", broadTitle);
//                } else {
//                    if(liveHostId.equalsIgnoreCase(AppConstants.USER_ID)){
//                        intent.putExtra("broadTitle", name1);
//                    }else{
//                        intent.putExtra("broadTitle", foller_username);
//                    }
//
//                }
//
//                if (liveimage.isEmpty()) {
//
//                    intent.putExtra("image", userProfileImage);
//                } else {
//                    intent.putExtra("image",liveimage);
//                }
//                intent.putExtra("status", "1");
//
//                intent.putExtra("dob", CommonUtils.ageCalcualte(dob));
//                intent.putExtra("gender",gender);
//
//                intent.putExtra("userDob", CommonUtils.ageCalcualte(userDob));
//                intent.putExtra("UserGender", UserGender);


                pendingIntent = PendingIntent.getActivity(this, 101,
                        intent, PendingIntent.FLAG_IMMUTABLE);

            } else if (type.equalsIgnoreCase(URLBuilder.Type.chatNotification.toString())) {

                Log.d("MYFIREBASE","name "+notifyBy +" id "+notifyById +" notifyByImage "+notifyByImage);

                Bundle bundle = new Bundle();
                bundle.putString("data_key", "1");
                bundle.putString("otherUserId",notifyById);
                bundle.putString("otherUserImg", notifyByImage);
                bundle.putString("otherUserName", notifyBy);
                bundle.putString("notification", "1");

                pendingIntent = new NavDeepLinkBuilder(this)
                        .setComponentName(HomeActivity.class)
                        .setGraph(R.navigation.home_graph)
                        .setDestination(R.id.messagesFragment)
                        .setArguments(bundle)
                        .createPendingIntent();
            }

        } else {
            Log.d("MYFIREBASESERIVE", "Type null");
        }


// Sets an ID for the notification, so it can be updated.
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        CharSequence name = "@MyPocketHealth ";// The user-visible name of the channel.

        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

        }
        final Notification.Builder builder = new Notification.Builder(getApplicationContext());
// Create a notification and set the notification channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notification = new Notification.Builder(this)
                    .setGroup(type)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.wow_icon)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setChannelId(CHANNEL_ID)
                    .build();

            if (!type.equalsIgnoreCase("")) {

                if (type.equalsIgnoreCase(URLBuilder.Type.followUnfollow.toString())) {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.wow_icon);

                } else if (type.equalsIgnoreCase(URLBuilder.Type.chatRequest.toString())) {

                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.wow_icon);
                } else if (type.equalsIgnoreCase(URLBuilder.Type.family_request_accept.toString())) {

                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.wow_icon);
                } else if (type.equalsIgnoreCase(URLBuilder.Type.userLive.toString())) {

                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.wow_icon);
                } else if (type.equalsIgnoreCase(URLBuilder.Type.chatNotification.toString())) {

                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
                            .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.wow_icon);
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(bigContent)
                                    .setSummaryText("Notification"))
                            .setContentTitle(title)
                            .setContentText(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setChannelId(CHANNEL_ID)
//                        .setContentIntent(pendingIntent)
                            .setGroup(type)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.wow_icon);
                }
            }

        }
        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationManager.createNotificationChannel(mChannel);
        }

// Issue the notification.
        Random random = new Random();
        final int m = random.nextInt(9999 - 1000) + 1000;

        if (!type.equalsIgnoreCase("")) {

            if (type.equalsIgnoreCase(URLBuilder.Type.followUnfollow.toString())) {
                if (image.equalsIgnoreCase("")) {
                    mNotificationManager.notify(m, builder.build());
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(message)
                                    .setSummaryText("Notification"))
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSmallIcon(R.drawable.wow_icon);
                }
            } else if (type.equalsIgnoreCase(URLBuilder.Type.chatRequest.toString())) {

                if (image.equalsIgnoreCase("")) {
                    mNotificationManager.notify(m, builder.build());
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(message)
                                    .setSummaryText("Notification"))
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSmallIcon(R.drawable.wow_icon);
                }
            } else if (type.equalsIgnoreCase(URLBuilder.Type.userLive.toString())) {

                if (image.equalsIgnoreCase("")) {
                    mNotificationManager.notify(m, builder.build());
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(message)
                                    .setSummaryText("Notification"))
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSmallIcon(R.drawable.wow_icon);
                }
            } else if (type.equalsIgnoreCase(URLBuilder.Type.family_request_accept.toString())) {

                if (image.equalsIgnoreCase("")) {
                    mNotificationManager.notify(m, builder.build());
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(message)
                                    .setSummaryText("Notification"))
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSmallIcon(R.drawable.wow_icon);
                }
            } else {
                if (image.equalsIgnoreCase("")) {
                    mNotificationManager.notify(m, builder.build());
                } else {
                    builder.setStyle(new Notification.BigTextStyle(builder)
                                    .setBigContentTitle(message)
                                    .setSummaryText("Notification"))
                            .setAutoCancel(true)
//                        .setContentIntent(pendingIntent)
                            .setGroup(message)
                            .setVibrate(new long[]{200, 200, 200, 200})
                            .setSmallIcon(R.drawable.wow_icon);

                }
            }

        } else {
            mNotificationManager.notify(m, notification);

        }
    }

    public class LatestFirebaseMessagingService extends FirebaseMessagingService {


        @Override
        public void onNewToken(String mToken) {
            super.onNewToken(mToken);
        }

        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);

        }
    }
}




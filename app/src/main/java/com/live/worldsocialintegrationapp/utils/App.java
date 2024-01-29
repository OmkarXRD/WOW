package com.live.worldsocialintegrationapp.utils;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.openvcall.model.AGEventHandler;
import com.live.worldsocialintegrationapp.agora.openvcall.model.CurrentUserSettings;
import com.live.worldsocialintegrationapp.agora.openvcall.model.EngineConfig;
import com.live.worldsocialintegrationapp.agora.openvcall.model.MyEngineEventHandler;
import com.live.worldsocialintegrationapp.room.AppDatabase;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.google.GoogleEmojiProvider;


import io.agora.rtc.Constants;
import io.agora.rtc.RtcEngine;

public class App extends Application {

    private static App instance;
    private static SharedPref sharedpref;
    Context context;

    public static Singleton singleton;
    private RtcEngine mRtcEngine;
    private EngineConfig mConfig;
    private MyEngineEventHandler mEventHandler;
    private CurrentUserSettings mVideoSettings = new CurrentUserSettings();

    @Override
    public void onCreate() {

        super.onCreate();

        instance = this;
        context = getApplicationContext();
        sharedpref = new SharedPref(context);
        singleton = new Singleton();

        createRtcEngine();
        EmojiManager.install(new GoogleEmojiProvider());

    }

    public CurrentUserSettings userSettings() {
        return mVideoSettings;
    }

    public RtcEngine rtcEngine() {
        return mRtcEngine;
    }

    public EngineConfig config() {
        return mConfig;
    }

    public void addEventHandler(AGEventHandler handler) {
        mEventHandler.addEventHandler(handler);
    }

    public void remoteEventHandler(AGEventHandler handler) {
        mEventHandler.removeEventHandler(handler);
    }

    public static Singleton getSingletone() {
        return singleton;
    }

    public static SharedPref getSharedpref() {
        return sharedpref;
    }

    public static App getAppContext() {
        return instance;
    }

    private void createRtcEngine() {
//        Context context = getApplicationContext();
//        String appId = context.getString(R.string.agora_app_id);
//        if (TextUtils.isEmpty(appId)) {
//            throw new RuntimeException("NEED TO use your App ID, get your own ID at https://dashboard.agora.io/");
//        }
//        mEventHandler = new MyEngineEventHandler();
//
//        try {
//            mRtcEngine = RtcEngine.create(context, appId, mEventHandler);
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            throw new RuntimeException("NEED TO check rtc sdk init fatal error");
//        }
//
//        mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);
//        mRtcEngine.enableVideo();
//
//        mRtcEngine.enableAudioVolumeIndication(200, 3, false);
//        mConfig = new EngineConfig();


        Context context = getApplicationContext();
        String appId = context.getString(R.string.agora_app_id);
        if (TextUtils.isEmpty(appId)) {
            throw new RuntimeException("NEED TO use your App ID, get your own ID at https://dashboard.agora.io/");
        }
        mEventHandler = new MyEngineEventHandler();

        try {
            mRtcEngine = RtcEngine.create(context, appId, mEventHandler);

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException("NEED TO check rtc sdk init fatal error");
        }


        mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);
        mRtcEngine.enableVideo();

        mRtcEngine.enableAudioVolumeIndication(200, 3, false);
        mConfig = new EngineConfig();

    }


}

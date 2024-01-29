package com.live.worldsocialintegrationapp.utils;

public class Singleton {

    private  String liveType="";

    private boolean isStop=false;

    public String getLiveType() {
        return liveType;
    }

    public void setLiveType(String liveType) {
        this.liveType = liveType;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}

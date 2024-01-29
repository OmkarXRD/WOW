package com.live.worldsocialintegrationapp.ModelClasses;

public class ChatModel {

    private String name;
    private String message;
    private String msgId;
    private String from;
    private String to;
    private String type;
    private String msgType = "";
    private String messageUrl = "";
    private String videoThumbnaiil = "";
    private String filePostion = "";
    private String liveKey = "";
    private String liveExtraMsg = "";
    private String time = "";
    private String date = "";
    private String car = "";
    private String carId = "";
    private String frameId = "";
    private String frame = "";
    private int type2 = 0;
    private String galleryTheme = "";
    private String galleryId = "";
    private String themeId = "";
    private String themeImage = "";

    private boolean claim = false;

    public boolean isClaim() {
        return claim;
    }

    public void setClaim(boolean claim) {
        this.claim = claim;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getThemeImage() {
        return themeImage;
    }

    public void setThemeImage(String themeImage) {
        this.themeImage = themeImage;
    }

    public String getGalleryTheme() {
        return galleryTheme;
    }

    public void setGalleryTheme(String galleryTheme) {
        this.galleryTheme = galleryTheme;
    }

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public int getType2() {
        return type2;
    }

    public void setType2(int type2) {
        this.type2 = type2;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLiveExtraMsg() {
        return liveExtraMsg;
    }

    public void setLiveExtraMsg(String liveExtraMsg) {
        this.liveExtraMsg = liveExtraMsg;
    }

    public String getLiveKey() {
        return liveKey;
    }

    public void setLiveKey(String liveKey) {
        this.liveKey = liveKey;
    }

    public String getFilePostion() {
        return filePostion;
    }

    public void setFilePostion(String filePostion) {
        this.filePostion = filePostion;
    }

    public String getVideoThumbnaiil() {
        return videoThumbnaiil;
    }

    public void setVideoThumbnaiil(String videoThumbnaiil) {
        this.videoThumbnaiil = videoThumbnaiil;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgoraToken() {
        return agoraToken;
    }

    public void setAgoraToken(String agoraToken) {
        this.agoraToken = agoraToken;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    private String agoraToken;
    private String channelName;

    public ChatModel() {
    }

    public ChatModel(String name, String message, String msgId, String from, String to) {
        this.name = name;
        this.message = message;
        this.msgId = msgId;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

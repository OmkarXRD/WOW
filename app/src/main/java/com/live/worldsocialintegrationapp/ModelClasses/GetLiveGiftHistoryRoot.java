package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

import java.io.Serializable;
import java.util.ArrayList;
@Keep
public class GetLiveGiftHistoryRoot implements Serializable {
    public String success;
    public String message;
    public ArrayList<Detail> details;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Detail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
    }

    public class Detail{
        public String diamond;
        public String senderId;
        public String receiverId;
        public String liveId;
        public String giftId;
        public String sender_name;
        public String sender_username;
        public String receiver_name;
        public String receiver_username;
        public String senderImage;
        public String receiverImage;


        public String getDiamond() {
            return diamond;
        }

        public void setDiamond(String diamond) {
            this.diamond = diamond;
        }

        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
        }

        public String getLiveId() {
            return liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
        }

        public String getGiftId() {
            return giftId;
        }

        public void setGiftId(String giftId) {
            this.giftId = giftId;
        }

        public String getSender_name() {
            return sender_name;
        }

        public void setSender_name(String sender_name) {
            this.sender_name = sender_name;
        }

        public String getSender_username() {
            return sender_username;
        }

        public void setSender_username(String sender_username) {
            this.sender_username = sender_username;
        }

        public String getReceiver_name() {
            return receiver_name;
        }

        public void setReceiver_name(String receiver_name) {
            this.receiver_name = receiver_name;
        }

        public String getReceiver_username() {
            return receiver_username;
        }

        public void setReceiver_username(String receiver_username) {
            this.receiver_username = receiver_username;
        }

        public String getSenderImage() {
            return senderImage;
        }

        public void setSenderImage(String senderImage) {
            this.senderImage = senderImage;
        }

        public String getReceiverImage() {
            return receiverImage;
        }

        public void setReceiverImage(String receiverImage) {
            this.receiverImage = receiverImage;
        }
    }

}

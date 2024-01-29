package com.live.worldsocialintegrationapp.ModelClasses.GiftWall;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootGiftWall {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

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

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }
    public class Detail {

        @SerializedName("diamond")
        @Expose
        private String diamond;
        @SerializedName("senderId")
        @Expose
        private String senderId;
        @SerializedName("receiverId")
        @Expose
        private String receiverId;
        @SerializedName("sender_name")
        @Expose
        private String senderName;
        @SerializedName("sender_username")
        @Expose
        private String senderUsername;
        @SerializedName("receiver_name")
        @Expose
        private String receiverName;
        @SerializedName("receiver_username")
        @Expose
        private String receiverUsername;
        @SerializedName("senderImage")
        @Expose
        private String senderImage;

        @SerializedName("receiverImage")
        @Expose
        private String  receiverImage;

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

        public String getSenderName() {
            return senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getSenderUsername() {
            return senderUsername;
        }

        public void setSenderUsername(String senderUsername) {
            this.senderUsername = senderUsername;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getReceiverUsername() {
            return receiverUsername;
        }

        public void setReceiverUsername(String receiverUsername) {
            this.receiverUsername = receiverUsername;
        }
    }
}
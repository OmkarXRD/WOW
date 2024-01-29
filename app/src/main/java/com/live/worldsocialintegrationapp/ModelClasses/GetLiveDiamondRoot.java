package com.live.worldsocialintegrationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetLiveDiamondRoot implements Serializable {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("total")
    @Expose
    private String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


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
        @SerializedName("sender_name")
        @Expose
        private String senderName;
        @SerializedName("sender_username")
        @Expose
        private String senderUsername;
        @SerializedName("sender_dob")
        @Expose
        private String senderDob;
        @SerializedName("sender_gender")
        @Expose
        private String senderGender;
        @SerializedName("receiverId")
        @Expose
        private String receiverId;
        @SerializedName("receiver_name")
        @Expose
        private String receiverName;
        @SerializedName("receiver_username")
        @Expose
        private String receiverUsername;
        @SerializedName("receiver_dob")
        @Expose
        private String receiverDob;
        @SerializedName("receiver_gender")
        @Expose
        private String receiverGender;
        @SerializedName("receiver_img")
        @Expose
        private String receiverImg;
        @SerializedName("sender_img")
        @Expose
        private String senderImg;

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

        public String getSenderDob() {
            return senderDob;
        }

        public void setSenderDob(String senderDob) {
            this.senderDob = senderDob;
        }

        public String getSenderGender() {
            return senderGender;
        }

        public void setSenderGender(String senderGender) {
            this.senderGender = senderGender;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
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

        public String getReceiverDob() {
            return receiverDob;
        }

        public void setReceiverDob(String receiverDob) {
            this.receiverDob = receiverDob;
        }

        public String getReceiverGender() {
            return receiverGender;
        }

        public void setReceiverGender(String receiverGender) {
            this.receiverGender = receiverGender;
        }

        public String getReceiverImg() {
            return receiverImg;
        }

        public void setReceiverImg(String receiverImg) {
            this.receiverImg = receiverImg;
        }

        public String getSenderImg() {
            return senderImg;
        }

        public void setSenderImg(String senderImg) {
            this.senderImg = senderImg;
        }

    }
}

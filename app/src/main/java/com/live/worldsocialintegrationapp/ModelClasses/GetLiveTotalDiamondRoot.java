package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
@Keep
public class GetLiveTotalDiamondRoot implements Serializable {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private Details details;

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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public class Details {

        @SerializedName("diamond")
        @Expose
        private String diamond;
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

        public String getDiamond() {
            return diamond;
        }

        public void setDiamond(String diamond) {
            this.diamond = diamond;
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

    }


}

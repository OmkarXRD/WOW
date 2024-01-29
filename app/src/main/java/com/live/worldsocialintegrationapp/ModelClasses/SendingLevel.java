package com.live.worldsocialintegrationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendingLevel {

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

        @SerializedName("sandColor")
        @Expose
        private String sandColor;
        @SerializedName("sendLevel")
        @Expose
        private String sendLevel;
        @SerializedName("sendExp")
        @Expose
        private String sendExp;
        @SerializedName("sendStart")
        @Expose
        private Integer sendStart;
        @SerializedName("sendEnd")
        @Expose
        private Integer sendEnd;
        @SerializedName("requiredExperience")
        @Expose
        private Integer requiredExperience;
        @SerializedName("receiveRequiredExperience")
        @Expose
        private Integer receiveRequiredExperience;
        @SerializedName("reciveColor")
        @Expose
        private String reciveColor;
        @SerializedName("reciveLevel")
        @Expose
        private String reciveLevel;
        @SerializedName("reciveExp")
        @Expose
        private String reciveExp;
        @SerializedName("reciveStart")
        @Expose
        private Integer reciveStart;
        @SerializedName("reciveEnd")
        @Expose
        private Integer reciveEnd;
        @SerializedName("sandBgImage")
        @Expose
        private String sandBgImage;
        @SerializedName("reciveBgImage")
        @Expose
        private String reciveBgImage;

        public String getSandBgImage() {
            return sandBgImage;
        }

        public void setSandBgImage(String sandBgImage) {
            this.sandBgImage = sandBgImage;
        }

        public String getReciveBgImage() {
            return reciveBgImage;
        }

        public void setReciveBgImage(String reciveBgImage) {
            this.reciveBgImage = reciveBgImage;
        }

        public String getSandColor() {
            return sandColor;
        }

        public void setSandColor(String sandColor) {
            this.sandColor = sandColor;
        }

        public String getSendLevel() {
            return sendLevel;
        }

        public void setSendLevel(String sendLevel) {
            this.sendLevel = sendLevel;
        }

        public String getSendExp() {
            return sendExp;
        }

        public void setSendExp(String sendExp) {
            this.sendExp = sendExp;
        }

        public Integer getSendStart() {
            return sendStart;
        }

        public void setSendStart(Integer sendStart) {
            this.sendStart = sendStart;
        }

        public Integer getSendEnd() {
            return sendEnd;
        }

        public Integer getRequiredExperience() {
            return requiredExperience;
        }

        public Integer getReceiveRequiredExperience() {
            return receiveRequiredExperience;
        }

        public void setSendEnd(Integer sendEnd) {
            this.sendEnd = sendEnd;
        }

        public String getReciveColor() {
            return reciveColor;
        }

        public void setReciveColor(String reciveColor) {
            this.reciveColor = reciveColor;
        }

        public String getReciveLevel() {
            return reciveLevel;
        }

        public void setReciveLevel(String reciveLevel) {
            this.reciveLevel = reciveLevel;
        }

        public String getReciveExp() {
            return reciveExp;
        }

        public void setReciveExp(String reciveExp) {
            this.reciveExp = reciveExp;
        }

        public Integer getReciveStart() {
            return reciveStart;
        }

        public void setReciveStart(Integer reciveStart) {
            this.reciveStart = reciveStart;
        }

        public Integer getReciveEnd() {
            return reciveEnd;
        }

        public void setReciveEnd(Integer reciveEnd) {
            this.reciveEnd = reciveEnd;
        }

    }

}

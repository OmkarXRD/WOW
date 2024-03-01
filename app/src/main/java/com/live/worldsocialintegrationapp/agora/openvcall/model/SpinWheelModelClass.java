package com.live.worldsocialintegrationapp.agora.openvcall.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Keep
public class SpinWheelModelClass {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details;

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

    @Keep
    public class Detail {
        @SerializedName("coins")
        @Expose
        private String coins;
        @SerializedName("gift")
        @Expose
        private String gift;
        @SerializedName("diamonds")
        @Expose
        private String diamonds;
        @SerializedName("frame")
        @Expose
        private String frame;
        @SerializedName("entryEffect")
        @Expose
        private String entryEffect;
        public String getCoins() {
            return coins;
        }

        public void setCoins(String coins) {
            this.coins = coins;
        }

        public String getDiamonds() {
            return diamonds;
        }

        public void setDiamonds(String diamonds) {
            this.diamonds = diamonds;
        }

        public String getFrame() {
            return frame;
        }

        public void setFrame(String frame) {
            this.frame = frame;
        }
        public String getEntryEffect() {
            return entryEffect;
        }

        public void setEntryEffect(String entryEffect) {
            this.entryEffect = entryEffect;
        }

        public String getGift() {
            return gift;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

    }
}

package com.live.worldsocialintegrationapp.agora.openvcall.model;


import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.EventGift;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.Privilege;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.SoundGift;
import com.live.worldsocialintegrationapp.agora.openvcall.model.GiftType.Trick;

import java.util.List;
@Keep
public class PrimeGiftModel {

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

    @Keep
    public class Details {

        @SerializedName("Privilege")
        @Expose
        private List<Privilege> privilege = null;
        @SerializedName("Trick")
        @Expose
        private List<Trick> trick = null;
        @SerializedName("EventGifts")
        @Expose
        private List<EventGift> eventGifts = null;
        @SerializedName("SoundGifts")
        @Expose
        private List<SoundGift> soundGifts = null;

        public List<Privilege> getPrivilege() {
            return privilege;
        }

        public void setPrivilege(List<Privilege> privilege) {
            this.privilege = privilege;
        }

        public List<Trick> getTrick() {
            return trick;
        }

        public void setTrick(List<Trick> trick) {
            this.trick = trick;
        }

        public List<EventGift> getEventGifts() {
            return eventGifts;
        }

        public void setEventGifts(List<EventGift> eventGifts) {
            this.eventGifts = eventGifts;
        }

        public List<SoundGift> getSoundGifts() {
            return soundGifts;
        }

        public void setSoundGifts(List<SoundGift> soundGifts) {
            this.soundGifts = soundGifts;
        }

    }

}

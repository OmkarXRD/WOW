package com.live.worldsocialintegrationapp.ModelClasses.LiveGallery;

import androidx.annotation.Keep;

import java.util.ArrayList;

@Keep
public class GetLiveGalleryRoot {
    public int status;
    public String message;
    public ArrayList<Detail> details;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

        public String id;
        public String coins;
        public String validity;
        public boolean purStatus;

        public String getRemainingDays() {
            return remainingDays;
        }

        public void setRemainingDays(String remainingDays) {
            this.remainingDays = remainingDays;
        }

        public String remainingDays;

        public boolean isPurStatus() {
            return purStatus;
        }

        public void setPurStatus(boolean purStatus) {
            this.purStatus = purStatus;
        }

        public String getCoins() {
            return coins;
        }

        public void setCoins(String coins) {
            this.coins = coins;
        }

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}

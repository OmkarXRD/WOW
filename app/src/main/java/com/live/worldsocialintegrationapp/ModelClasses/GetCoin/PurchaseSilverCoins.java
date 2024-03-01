package com.live.worldsocialintegrationapp.ModelClasses.GetCoin;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class PurchaseSilverCoins implements Serializable {
    public String success;
    public String message;
    public Details details;


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

    public class Details{

        public String id;
        public String userId;
        public String coinValue;
        public String created;
        public String updated;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCoinValue() {
            return coinValue;
        }

        public void setCoinValue(String coinValue) {
            this.coinValue = coinValue;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }


}

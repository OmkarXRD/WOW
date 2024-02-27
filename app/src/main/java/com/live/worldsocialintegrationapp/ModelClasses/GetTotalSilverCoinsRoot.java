package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
public class GetTotalSilverCoinsRoot {

    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("details")
    @Expose
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


        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("userId")
        @Expose
        public String userId;
        @SerializedName("coinValue")
        @Expose
        public String coinValue;
        @SerializedName("created")
        @Expose
        public String created;
        @SerializedName("updated")
        @Expose
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

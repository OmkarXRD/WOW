package com.live.worldsocialintegrationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KickOutLiveRoot {

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
        @SerializedName("kickBy")
        @Expose
        public String kickBy;
        @SerializedName("liveId")
        @Expose
        public String liveId;
        @SerializedName("kickTo")
        @Expose
        public String kickTo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKickBy() {
            return kickBy;
        }

        public void setKickBy(String kickBy) {
            this.kickBy = kickBy;
        }

        public String getLiveId() {
            return liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
        }

        public String getKickTo() {
            return kickTo;
        }

        public void setKickTo(String kickTo) {
            this.kickTo = kickTo;
        }
    }
}

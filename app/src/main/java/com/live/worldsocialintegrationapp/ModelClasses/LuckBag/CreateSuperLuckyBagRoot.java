package com.live.worldsocialintegrationapp.ModelClasses.LuckBag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateSuperLuckyBagRoot {

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

    public class Details{

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("userId")
        @Expose
        private String userId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}

package com.live.worldsocialintegrationapp.ModelClasses.LuckBag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HitLuckyBagCoinsRoot {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    @SerializedName("details")
    @Expose
    private Details details;
    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public class Details {


        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("deductId")
        @Expose
        private String deductId;
        @SerializedName("deductType")
        @Expose
        private String deductType;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("perShare_coins")
        @Expose
        private String perShareCoins;
        @SerializedName("imageDp")
        @Expose
        private String imageDp;

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

        public String getDeductId() {
            return deductId;
        }

        public void setDeductId(String deductId) {
            this.deductId = deductId;
        }

        public String getDeductType() {
            return deductType;
        }

        public void setDeductType(String deductType) {
            this.deductType = deductType;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPerShareCoins() {
            return perShareCoins;
        }

        public void setPerShareCoins(String perShareCoins) {
            this.perShareCoins = perShareCoins;
        }

        public String getImageDp() {
            return imageDp;
        }

        public void setImageDp(String imageDp) {
            this.imageDp = imageDp;
        }

    }
}

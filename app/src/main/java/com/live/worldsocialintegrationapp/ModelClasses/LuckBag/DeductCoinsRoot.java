package com.live.worldsocialintegrationapp.ModelClasses.LuckBag;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
public class DeductCoinsRoot {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private Details details;

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
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("deduct_coins")
        @Expose
        private String deductCoins;
        @SerializedName("userCount")
        @Expose
        private String userCount;
        @SerializedName("perShare")
        @Expose
        private String perShare;
        @SerializedName("deductType")
        @Expose
        private String deductType;
        @SerializedName("userIds")
        @Expose
        private String userIds;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("name")
        @Expose
        private String name;
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

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getDeductCoins() {
            return deductCoins;
        }

        public void setDeductCoins(String deductCoins) {
            this.deductCoins = deductCoins;
        }

        public String getUserCount() {
            return userCount;
        }

        public void setUserCount(String userCount) {
            this.userCount = userCount;
        }

        public String getPerShare() {
            return perShare;
        }

        public void setPerShare(String perShare) {
            this.perShare = perShare;
        }

        public String getDeductType() {
            return deductType;
        }

        public void setDeductType(String deductType) {
            this.deductType = deductType;
        }

        public String getUserIds() {
            return userIds;
        }

        public void setUserIds(String userIds) {
            this.userIds = userIds;
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

        public String getImageDp() {
            return imageDp;
        }

        public void setImageDp(String imageDp) {
            this.imageDp = imageDp;
        }

    }
}

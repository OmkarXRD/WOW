package com.live.worldsocialintegrationapp.ModelClasses.LiveGallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SendGalleryRoot {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("uploaded")
    @Expose
    private String uploaded = null;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public class Detail{


        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("otherUserId")
        @Expose
        private String otherUserId;
        @SerializedName("galleryPermissionId")
        @Expose
        private String galleryPermissionId;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("rewardType")
        @Expose
        private String rewardType;
        @SerializedName("dateFrom")
        @Expose
        private String dateFrom;
        @SerializedName("dateTo")
        @Expose
        private String dateTo;
        @SerializedName("luckyId")
        @Expose
        private String luckyId;
        @SerializedName("themeId")
        @Expose
        private String themeId;
        @SerializedName("frameId")
        @Expose
        private String frameId;
        @SerializedName("image")
        @Expose
        private String image;

        public String getLuckyId() {
            return luckyId;
        }

        public void setLuckyId(String luckyId) {
            this.luckyId = luckyId;
        }

        public String getThemeId() {
            return themeId;
        }

        public void setThemeId(String themeId) {
            this.themeId = themeId;
        }

        public String getFrameId() {
            return frameId;
        }

        public void setFrameId(String frameId) {
            this.frameId = frameId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

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

        public String getOtherUserId() {
            return otherUserId;
        }

        public void setOtherUserId(String otherUserId) {
            this.otherUserId = otherUserId;
        }

        public String getGalleryPermissionId() {
            return galleryPermissionId;
        }

        public void setGalleryPermissionId(String galleryPermissionId) {
            this.galleryPermissionId = galleryPermissionId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRewardType() {
            return rewardType;
        }

        public void setRewardType(String rewardType) {
            this.rewardType = rewardType;
        }

        public String getDateFrom() {
            return dateFrom;
        }

        public void setDateFrom(String dateFrom) {
            this.dateFrom = dateFrom;
        }

        public String getDateTo() {
            return dateTo;
        }

        public void setDateTo(String dateTo) {
            this.dateTo = dateTo;
        }

    }


}

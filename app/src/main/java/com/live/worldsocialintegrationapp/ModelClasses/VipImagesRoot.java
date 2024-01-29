package com.live.worldsocialintegrationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VipImagesRoot {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("arjun")
    @Expose
    private Integer arjun;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

    public Integer getArjun() {
        return arjun;
    }

    public void setArjun(Integer arjun) {
        this.arjun = arjun;
    }

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

    public class Detail {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("vip_image")
        @Expose
        private String vipImage;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("is_applied")
        @Expose
        private Boolean isApplied;

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public Boolean getApplied() {
            return isApplied;
        }

        public void setApplied(Boolean applied) {
            isApplied = applied;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVipImage() {
            return vipImage;
        }

        public void setVipImage(String vipImage) {
            this.vipImage = vipImage;
        }

    }

}


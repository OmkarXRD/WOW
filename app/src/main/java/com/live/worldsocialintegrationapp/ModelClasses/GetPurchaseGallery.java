package com.live.worldsocialintegrationapp.ModelClasses;

import java.util.ArrayList;

public class GetPurchaseGallery {

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
        public String userId;
        public String galleryPermissionId;
        public String image;
        public String price;
        public String dateFrom;
        public String dateTo;
        public boolean isApplied;
        public String themeId;
        public String frameIMage;
        public String type;
        public String exp_time;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getExp_time() {
            return exp_time;
        }

        public void setExp_time(String exp_time) {
            this.exp_time = exp_time;
        }

        public String getThemeId() {
            return themeId;
        }

        public void setThemeId(String themeId) {
            this.themeId = themeId;
        }

        public String getFrameIMage() {
            return frameIMage;
        }

        public void setFrameIMage(String frameIMage) {
            this.frameIMage = frameIMage;
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

        public String getGalleryPermissionId() {
            return galleryPermissionId;
        }

        public void setGalleryPermissionId(String galleryPermissionId) {
            this.galleryPermissionId = galleryPermissionId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public boolean isApplied() {
            return isApplied;
        }

        public void setApplied(boolean applied) {
            isApplied = applied;
        }
    }

}

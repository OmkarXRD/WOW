package com.live.worldsocialintegrationapp.ModelClasses;

import java.io.Serializable;

public class UserReportRoot implements Serializable {
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
        public String userReport_catId;
        public String userReport_SubcatId;
        public String userId;
        public String otherUserId;
        public String created;
        public String updated;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserReport_catId() {
            return userReport_catId;
        }

        public void setUserReport_catId(String userReport_catId) {
            this.userReport_catId = userReport_catId;
        }

        public String getUserReport_SubcatId() {
            return userReport_SubcatId;
        }

        public void setUserReport_SubcatId(String userReport_SubcatId) {
            this.userReport_SubcatId = userReport_SubcatId;
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

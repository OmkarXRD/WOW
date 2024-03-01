package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@Keep

    public class GetFamilyDetails {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("details")
        @Expose
        private List<Detail> details;

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

        public class Detail {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("family_level_id")
            @Expose
            private String familyLevelId;
            @SerializedName("level_name")
            @Expose
            private String levelName;
            @SerializedName("main_image")
            @Expose
            private String mainImage;
            @SerializedName("rank_medal")
            @Expose
            private String rankMedal;
            @SerializedName("members")
            @Expose
            private String members;
            @SerializedName("memberImage")
            @Expose
            private String memberImage;
            @SerializedName("admin")
            @Expose
            private String admin;
            @SerializedName("adminImage")
            @Expose
            private String adminImage;
            @SerializedName("exclusive_frames")
            @Expose
            private String exclusiveFrames;
            @SerializedName("exclusive_background")
            @Expose
            private String exclusiveBackground;
            @SerializedName("exclusive_gift")
            @Expose
            private String exclusiveGift;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFamilyLevelId() {
                return familyLevelId;
            }

            public void setFamilyLevelId(String familyLevelId) {
                this.familyLevelId = familyLevelId;
            }

            public String getLevelName() {
                return levelName;
            }

            public void setLevelName(String levelName) {
                this.levelName = levelName;
            }

            public String getMainImage() {
                return mainImage;
            }

            public void setMainImage(String mainImage) {
                this.mainImage = mainImage;
            }

            public String getRankMedal() {
                return rankMedal;
            }

            public void setRankMedal(String rankMedal) {
                this.rankMedal = rankMedal;
            }

            public String getMembers() {
                return members;
            }

            public void setMembers(String members) {
                this.members = members;
            }

            public String getMemberImage() {
                return memberImage;
            }

            public void setMemberImage(String memberImage) {
                this.memberImage = memberImage;
            }

            public String getAdmin() {
                return admin;
            }

            public void setAdmin(String admin) {
                this.admin = admin;
            }

            public String getAdminImage() {
                return adminImage;
            }

            public void setAdminImage(String adminImage) {
                this.adminImage = adminImage;
            }

            public String getExclusiveFrames() {
                return exclusiveFrames;
            }

            public void setExclusiveFrames(String exclusiveFrames) {
                this.exclusiveFrames = exclusiveFrames;
            }

            public String getExclusiveBackground() {
                return exclusiveBackground;
            }

            public void setExclusiveBackground(String exclusiveBackground) {
                this.exclusiveBackground = exclusiveBackground;
            }

            public String getExclusiveGift() {
                return exclusiveGift;
            }

            public void setExclusiveGift(String exclusiveGift) {
                this.exclusiveGift = exclusiveGift;
            }

        }
    }



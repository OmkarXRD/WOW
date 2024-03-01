package com.live.worldsocialintegrationapp.ModelClasses.Family;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class Joiner implements Serializable {

    public String familyMemberId;
    public String familyId;
    public String userId;
    public String type;
    public String status;
    public String name;
    public String UserProfileImage;
    public String is_admin;
    public String contribution;
    public boolean show_status;

    public boolean isShow_status() {
        return show_status;
    }

    public void setShow_status(boolean show_status) {
        this.show_status = show_status;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public String getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(String familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserProfileImage() {
        return UserProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        UserProfileImage = userProfileImage;
    }
}

package com.live.worldsocialintegrationapp.ModelClasses;

import androidx.annotation.Keep;

import java.io.Serializable;
import java.util.ArrayList;
@Keep
public class VipRoot implements Serializable {

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
        public String coins;
        public String batch;
        public String vipicon;
        public String uniqueframes;
        public String entranceeffect;
        public String getthiscar;
        public String friends;
        public String following;
        public String coinsPerDay;
        public String colorfullMessage;
        public String flyingComment;
        public String hdeCountryAndOnlineTime;
        public String exclusiveGifts;
        public String preventFromBeingKicked;
        public String antiBan;
        public String valid;
        public String vipIconImage;
        public String uniqueFrameImage;
        public String entranceEffectImage;
        public String getThisCarImage;
        public String friendsImage;
        public String followingFriends;
        public String coinsImage;
        public String mainImage;
        public String colorMessageImage;
        public String flyingCommentImage;
        public String exclusiveGiftImage;
        public boolean vip_status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCoins() {
            return coins;
        }

        public void setCoins(String coins) {
            this.coins = coins;
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getVipicon() {
            return vipicon;
        }

        public void setVipicon(String vipicon) {
            this.vipicon = vipicon;
        }

        public String getUniqueframes() {
            return uniqueframes;
        }

        public void setUniqueframes(String uniqueframes) {
            this.uniqueframes = uniqueframes;
        }

        public String getEntranceeffect() {
            return entranceeffect;
        }

        public void setEntranceeffect(String entranceeffect) {
            this.entranceeffect = entranceeffect;
        }

        public String getGetthiscar() {
            return getthiscar;
        }

        public void setGetthiscar(String getthiscar) {
            this.getthiscar = getthiscar;
        }

        public String getFriends() {
            return friends;
        }

        public void setFriends(String friends) {
            this.friends = friends;
        }

        public String getFollowing() {
            return following;
        }

        public void setFollowing(String following) {
            this.following = following;
        }

        public String getCoinsPerDay() {
            return coinsPerDay;
        }

        public void setCoinsPerDay(String coinsPerDay) {
            this.coinsPerDay = coinsPerDay;
        }

        public String getColorfullMessage() {
            return colorfullMessage;
        }

        public void setColorfullMessage(String colorfullMessage) {
            this.colorfullMessage = colorfullMessage;
        }

        public String getFlyingComment() {
            return flyingComment;
        }

        public void setFlyingComment(String flyingComment) {
            this.flyingComment = flyingComment;
        }

        public String getHdeCountryAndOnlineTime() {
            return hdeCountryAndOnlineTime;
        }

        public void setHdeCountryAndOnlineTime(String hdeCountryAndOnlineTime) {
            this.hdeCountryAndOnlineTime = hdeCountryAndOnlineTime;
        }

        public String getExclusiveGifts() {
            return exclusiveGifts;
        }

        public void setExclusiveGifts(String exclusiveGifts) {
            this.exclusiveGifts = exclusiveGifts;
        }

        public String getPreventFromBeingKicked() {
            return preventFromBeingKicked;
        }

        public void setPreventFromBeingKicked(String preventFromBeingKicked) {
            this.preventFromBeingKicked = preventFromBeingKicked;
        }

        public String getAntiBan() {
            return antiBan;
        }

        public void setAntiBan(String antiBan) {
            this.antiBan = antiBan;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }

        public String getVipIconImage() {
            return vipIconImage;
        }

        public void setVipIconImage(String vipIconImage) {
            this.vipIconImage = vipIconImage;
        }

        public String getUniqueFrameImage() {
            return uniqueFrameImage;
        }

        public void setUniqueFrameImage(String uniqueFrameImage) {
            this.uniqueFrameImage = uniqueFrameImage;
        }

        public String getEntranceEffectImage() {
            return entranceEffectImage;
        }

        public void setEntranceEffectImage(String entranceEffectImage) {
            this.entranceEffectImage = entranceEffectImage;
        }

        public String getGetThisCarImage() {
            return getThisCarImage;
        }

        public void setGetThisCarImage(String getThisCarImage) {
            this.getThisCarImage = getThisCarImage;
        }

        public String getFriendsImage() {
            return friendsImage;
        }

        public void setFriendsImage(String friendsImage) {
            this.friendsImage = friendsImage;
        }

        public String getFollowingFriends() {
            return followingFriends;
        }

        public void setFollowingFriends(String followingFriends) {
            this.followingFriends = followingFriends;
        }

        public String getCoinsImage() {
            return coinsImage;
        }

        public void setCoinsImage(String coinsImage) {
            this.coinsImage = coinsImage;
        }

        public String getMainImage() {
            return mainImage;
        }

        public void setMainImage(String mainImage) {
            this.mainImage = mainImage;
        }

        public String getColorMessageImage() {
            return colorMessageImage;
        }

        public void setColorMessageImage(String colorMessageImage) {
            this.colorMessageImage = colorMessageImage;
        }

        public String getFlyingCommentImage() {
            return flyingCommentImage;
        }

        public void setFlyingCommentImage(String flyingCommentImage) {
            this.flyingCommentImage = flyingCommentImage;
        }

        public String getExclusiveGiftImage() {
            return exclusiveGiftImage;
        }

        public void setExclusiveGiftImage(String exclusiveGiftImage) {
            this.exclusiveGiftImage = exclusiveGiftImage;
        }

        public boolean isVip_status() {
            return vip_status;
        }

        public void setVip_status(boolean vip_status) {
            this.vip_status = vip_status;
        }
    }
}

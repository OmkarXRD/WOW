package com.live.worldsocialintegrationapp.ModelClasses;

import java.io.Serializable;
import androidx.annotation.Keep;

@Keep
public class SendOtpRoot  implements Serializable {

    public String success;
    public String message;
    public String otp;
    public String status;
    public String isRegistered;
    public boolean likeUnLikestatus;
    public int likeCount;

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLikeUnLikestatus() {
        return likeUnLikestatus;
    }

    public void setLikeUnLikestatus(boolean likeUnLikestatus) {
        this.likeUnLikestatus = likeUnLikestatus;
    }

    public String getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(String isRegistered) {
        this.isRegistered = isRegistered;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int commentId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }


}

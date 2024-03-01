package com.live.worldsocialintegrationapp.ModelClasses.DailyTask;

import androidx.annotation.Keep;

import java.util.ArrayList;

@Keep
public class GetDailyTaskRoot {

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

        public String reward;
        public String rewardType;
        public String daytype;
        public int day;

        public String getReward() {
            return reward;
        }

        public void setReward(String reward) {
            this.reward = reward;
        }

        public String getRewardType() {
            return rewardType;
        }

        public void setRewardType(String rewardType) {
            this.rewardType = rewardType;
        }

        public String getDaytype() {
            return daytype;
        }

        public void setDaytype(String daytype) {
            this.daytype = daytype;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }
    }
}

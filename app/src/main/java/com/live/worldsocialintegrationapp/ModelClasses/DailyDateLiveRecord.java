package com.live.worldsocialintegrationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyDateLiveRecord {
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("details")
        @Expose
        private Details details;

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

        public Details getDetails() {
            return details;
        }

        public void setDetails(Details details) {
            this.details = details;
        }

    public class Details {

        @SerializedName("total_live_time_today")
        @Expose
        private Integer totalLiveTimeToday;
        @SerializedName("gifts_recieved_today")
        @Expose
        private Integer giftsRecievedToday;
        @SerializedName("valid_days")
        @Expose
        private Integer validDays;

        public Integer getTotalLiveTimeToday() {
            return totalLiveTimeToday;
        }

        public void setTotalLiveTimeToday(Integer totalLiveTimeToday) {
            this.totalLiveTimeToday = totalLiveTimeToday;
        }

        public Integer getGiftsRecievedToday() {
            return giftsRecievedToday;
        }

        public void setGiftsRecievedToday(Integer giftsRecievedToday) {
            this.giftsRecievedToday = giftsRecievedToday;
        }

        public Integer getValidDays() {
            return validDays;
        }

        public void setValidDays(Integer validDays) {
            this.validDays = validDays;
        }

    }
}

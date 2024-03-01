package com.live.worldsocialintegrationapp.ModelClasses.DailyTask;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class SetDailyTaskRoot implements Serializable {

    public int status;
    public String message;
    public Details details;

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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public class Details{
        public String id;
        public String userId;
        public String day1;
        public Object day2;
        public String day3;
        public String day4;
        public Object day5;
        public Object day6;
        public Object day7;
        public String dateFrom;
        public String dateTo;
        public String updated;

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

        public String getDay1() {
            return day1;
        }

        public void setDay1(String day1) {
            this.day1 = day1;
        }

        public Object getDay2() {
            return day2;
        }

        public void setDay2(Object day2) {
            this.day2 = day2;
        }

        public String getDay3() {
            return day3;
        }

        public void setDay3(String day3) {
            this.day3 = day3;
        }

        public String getDay4() {
            return day4;
        }

        public void setDay4(String day4) {
            this.day4 = day4;
        }

        public Object getDay5() {
            return day5;
        }

        public void setDay5(Object day5) {
            this.day5 = day5;
        }

        public Object getDay6() {
            return day6;
        }

        public void setDay6(Object day6) {
            this.day6 = day6;
        }

        public Object getDay7() {
            return day7;
        }

        public void setDay7(Object day7) {
            this.day7 = day7;
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

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }
}

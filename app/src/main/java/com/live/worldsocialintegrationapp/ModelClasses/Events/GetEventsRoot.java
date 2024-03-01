package com.live.worldsocialintegrationapp.ModelClasses.Events;

import androidx.annotation.Keep;

import java.io.Serializable;
import java.util.ArrayList;

@Keep
public class GetEventsRoot implements Serializable {

    public String status;
    public String message;
    public ArrayList<Detail> details;

    public String getSuccess() {
        return status;
    }

    public void setSuccess(String status) {
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

    public class  Detail{

        public String id;
        public String event_topic;
        public String eventCreaterId;
        public String description;
        public String event_startTime;
        public String event_Type;
        public String event_image;
        public String eventSubscriber_counts;
        public String created_at;
        public String updated_at;
        public String name;
        public String dob;
        public String username;
        public boolean subscriberStatus;
        public String imageDp;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEvent_topic() {
            return event_topic;
        }

        public void setEvent_topic(String event_topic) {
            this.event_topic = event_topic;
        }

        public String getEventCreaterId() {
            return eventCreaterId;
        }

        public void setEventCreaterId(String eventCreaterId) {
            this.eventCreaterId = eventCreaterId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEvent_startTime() {
            return event_startTime;
        }

        public void setEvent_startTime(String event_startTime) {
            this.event_startTime = event_startTime;
        }

        public String getEvent_Type() {
            return event_Type;
        }

        public void setEvent_Type(String event_Type) {
            this.event_Type = event_Type;
        }

        public String getEvent_image() {
            return event_image;
        }

        public void setEvent_image(String event_image) {
            this.event_image = event_image;
        }

        public String getEventSubscriber_counts() {
            return eventSubscriber_counts;
        }

        public void setEventSubscriber_counts(String eventSubscriber_counts) {
            this.eventSubscriber_counts = eventSubscriber_counts;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isSubscriberStatus() {
            return subscriberStatus;
        }

        public void setSubscriberStatus(boolean subscriberStatus) {
            this.subscriberStatus = subscriberStatus;
        }

        public String getImageDp() {
            return imageDp;
        }

        public void setImageDp(String imageDp) {
            this.imageDp = imageDp;
        }
    }
}

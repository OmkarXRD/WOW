package com.live.worldsocialintegrationapp.Root;

import com.live.worldsocialintegrationapp.ModelClasses.AllPopularUsers.Detail;

import java.util.List;

public class CountryRoot {

    public String success;
    public String message;
    public List<CountryRootDetails> details;

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

    public List<CountryRootDetails> getDetails() {

        return details;
    }

    public void setDetails(List<CountryRootDetails> details) {

        this.details = details;
    }

}

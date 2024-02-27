package com.live.worldsocialintegrationapp.agora.openvcall.model;

import androidx.annotation.Keep;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
@Keep
public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> backgroundImageUrl = new MutableLiveData<>();

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl.setValue(backgroundImageUrl);
    }

    public LiveData<String> getBackgroundImageUrl() {
        return backgroundImageUrl;
    }
}

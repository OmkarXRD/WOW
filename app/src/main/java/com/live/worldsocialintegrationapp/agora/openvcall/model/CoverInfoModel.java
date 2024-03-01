package com.live.worldsocialintegrationapp.agora.openvcall.model;

import androidx.annotation.Keep;

@Keep
public class CoverInfoModel {

    String coverInfoLiveTitle;

    public CoverInfoModel() {
    }

    public CoverInfoModel(String coverInfoLiveTitle) {
        this.coverInfoLiveTitle = coverInfoLiveTitle;
    }

    public String getCoverInfoLiveTitle() {
        return coverInfoLiveTitle;
    }

    public void setCoverInfoLiveTitle(String coverInfoLiveTitle) {
        this.coverInfoLiveTitle = coverInfoLiveTitle;
    }
}

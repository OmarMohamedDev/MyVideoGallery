package com.omarmohamed.myvideogallery.domain;

import android.app.Application;

public class AnalyticsManager {

    private Application app;

    public AnalyticsManager(Application app) {
        this.app = app;
    }

    public void registerAppEnter() {
        //Do whatever you want when the app is started
    }
}

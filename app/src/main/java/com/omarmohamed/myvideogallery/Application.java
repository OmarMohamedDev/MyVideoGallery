package com.omarmohamed.myvideogallery;

import android.content.Context;

import com.omarmohamed.myvideogallery.component.AppComponent;
import com.omarmohamed.myvideogallery.component.DaggerAppComponent;
import com.omarmohamed.myvideogallery.domain.AnalyticsManager;
import com.omarmohamed.myvideogallery.modules.AppModule;

import javax.inject.Inject;

public class Application extends android.app.Application {

    @Inject
    AnalyticsManager analyticsManager;
    private AppComponent component;

    public static Application get(Context context) {
        return (Application) context.getApplicationContext();
    }

    @Override public void onCreate() {
        super.onCreate();
        setupGraph();
        analyticsManager.registerAppEnter();
    }

    private void setupGraph() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public AppComponent component() {
        return component;
    }
}

package com.omarmohamed.myvideogallery;

import android.app.Application;
import android.content.Context;

import com.omarmohamed.myvideogallery.component.AppComponent;
import com.omarmohamed.myvideogallery.component.DaggerAppComponent;
import com.omarmohamed.myvideogallery.domain.AnalyticsManager;
import com.omarmohamed.myvideogallery.modules.AppModule;

import javax.inject.Inject;

public class App extends Application {

    private AppComponent component;

    @Inject
    AnalyticsManager analyticsManager;

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

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}

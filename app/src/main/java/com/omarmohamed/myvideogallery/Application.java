package com.omarmohamed.myvideogallery;

import android.content.Context;

import com.omarmohamed.myvideogallery.component.AppComponent;
import com.omarmohamed.myvideogallery.component.DaggerAppComponent;
import com.omarmohamed.myvideogallery.modules.AppModule;

import javax.inject.Inject;

public class Application extends android.app.Application {

    //todo: change visibility to private to component again
    @Inject
    public AppComponent component;

    public static Application get(Context context) {
        return (Application) context.getApplicationContext();
    }

    @Override public void onCreate() {
        super.onCreate();
        setupGraph();
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

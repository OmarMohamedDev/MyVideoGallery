package com.omarmohamed.myvideogallery.modules;

import com.omarmohamed.myvideogallery.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public android.app.Application provideApplication() {
        return application;
    }
}

package com.omarmohamed.myvideogallery.component;

import com.omarmohamed.myvideogallery.Application;
import com.omarmohamed.myvideogallery.domain.AnalyticsManager;
import com.omarmohamed.myvideogallery.domain.DomainModule;
import com.omarmohamed.myvideogallery.interactors.FindItemsInteractor;
import com.omarmohamed.myvideogallery.interactors.InteractorsModule;
import com.omarmohamed.myvideogallery.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                DomainModule.class,
                InteractorsModule.class
        }
)
public interface AppComponent {
    void inject(Application application);

    AnalyticsManager getAnalyticsManager();
    FindItemsInteractor getFindItemsInteractor();
}
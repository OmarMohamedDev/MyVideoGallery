package com.omarmohamed.myvideogallery.component;

import com.omarmohamed.myvideogallery.Application;
import com.omarmohamed.myvideogallery.domain.DomainModule;
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
    //Todo: uncomment here retrieval of data from persistent storage layer
    // FindItemsInteractor getFindItemsInteractor();
}
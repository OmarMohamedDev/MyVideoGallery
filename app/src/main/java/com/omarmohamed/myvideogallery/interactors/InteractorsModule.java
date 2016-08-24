package com.omarmohamed.myvideogallery.interactors;

import com.omarmohamed.myvideogallery.adapter.RestApiAdapter;
import com.omarmohamed.myvideogallery.api.MyApi;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module
public class InteractorsModule {

    @Provides public FindItemsInteractor provideFindItemsInteractor(MyApi myApi) {
        return new FindItemsInteractorImpl(myApi);
    }

    @Provides public MyApi provideMyApi(RestAdapter restAdapter) {
        return restAdapter.create(MyApi.class);
    }

    @Provides public RestAdapter provideRestAdapter() {
        return RestApiAdapter.getInstance();
    }

}

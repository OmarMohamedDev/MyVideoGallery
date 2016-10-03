package com.omarmohamed.myvideogallery.interactors;

import com.omarmohamed.myvideogallery.models.VideoModel;
import com.omarmohamed.myvideogallery.ui.main.OnFinishedListener;

import java.util.List;

import javax.inject.Inject;

import retrofit.client.Response;

public class FindItemsInteractorImpl implements FindItemsInteractor {
    @Override
    public void findItems(OnFinishedListener listener) {

    }
    //Todo: Implement here retrieval of data from persistent storage layer
  //  MyApi getPetsApi;

    @Inject
    public FindItemsInteractorImpl(MyApi getPetsApi) {
        this.getPetsApi = getPetsApi;
    }

    //TODO: Customize the code below in order to permit to it to manipulate properly the local datastorage instead of rest responses
    @Override public void findItems(final OnFinishedListener listener) {


        getPetsApi.getPets(new Callback<List<VideoModel>>() {

            @Override
            public void success(List<VideoModel> videoModelList, Response response) {
                listener.onFinished(videoModelList);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                retrofitError.printStackTrace();
            }
        });

    }
}

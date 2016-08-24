package com.omarmohamed.myvideogallery.interactors;

import com.omarmohamed.myvideogallery.api.MyApi;
import com.omarmohamed.myvideogallery.models.VideoModel;
import com.omarmohamed.myvideogallery.ui.main.OnFinishedListener;

import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FindItemsInteractorImpl implements FindItemsInteractor {

    MyApi getPetsApi;

    @Inject public FindItemsInteractorImpl(MyApi getPetsApi) {
        this.getPetsApi = getPetsApi;
    }

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

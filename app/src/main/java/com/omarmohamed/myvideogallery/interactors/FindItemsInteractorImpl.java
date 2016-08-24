package com.omarmohamed.myvideogallery.interactors;

import com.omarmohamed.myvideogallery.api.MyApi;
import com.omarmohamed.myvideogallery.models.PetModel;
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


        getPetsApi.getPets(new Callback<List<PetModel>>() {

            @Override
            public void success(List<PetModel> petModelList, Response response) {
                listener.onFinished(petModelList);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                retrofitError.printStackTrace();
            }
        });

    }
}

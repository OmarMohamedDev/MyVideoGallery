package com.omarmohamed.myvideogallery.ui.main;

import com.omarmohamed.myvideogallery.interactors.FindItemsInteractor;
import com.omarmohamed.myvideogallery.models.VideoModel;

import java.util.List;

public class MainPresenterImpl implements MainPresenter, OnFinishedListener {

    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;

    public MainPresenterImpl(MainView mainView, FindItemsInteractor findItemsInteractor) {
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;
    }

    @Override public void onResume() {
        mainView.showProgress();
        findItemsInteractor.findItems(this);
    }

    @Override
    public void onFinished(List<VideoModel> items) {
        mainView.hideProgress();
        mainView.setItems(items);
    }
}

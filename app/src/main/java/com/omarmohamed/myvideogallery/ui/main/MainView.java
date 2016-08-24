package com.omarmohamed.myvideogallery.ui.main;

import com.omarmohamed.myvideogallery.models.VideoModel;

import java.util.List;

public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<VideoModel> items);

}

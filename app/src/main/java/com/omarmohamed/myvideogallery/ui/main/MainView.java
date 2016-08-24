package com.omarmohamed.myvideogallery.ui.main;

import com.omarmohamed.myvideogallery.models.PetModel;

import java.util.List;

public interface MainView {

    public void showProgress();

    public void hideProgress();

    public void setItems(List<PetModel> items);

}

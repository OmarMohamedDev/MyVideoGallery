package com.omarmohamed.myvideogallery.modules;

import com.omarmohamed.myvideogallery.adapter.RecyclerViewAdapter;
import com.omarmohamed.myvideogallery.ui.main.MainPresenter;
import com.omarmohamed.myvideogallery.ui.main.MainPresenterImpl;
import com.omarmohamed.myvideogallery.ui.main.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides public MainView provideView() {
        return view;
    }

    @Provides
    public MainPresenter providePresenter(MainView mainView/*, FindItemsInteractor findItemsInteractor*/) {
        return new MainPresenterImpl(mainView/*, findItemsInteractor*/);
    }

    @Provides
    public RecyclerViewAdapter provideRecyclerViewAdapter (){
        return new RecyclerViewAdapter();
    }
}

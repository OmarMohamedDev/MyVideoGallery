package com.omarmohamed.myvideogallery.component;

import com.omarmohamed.myvideogallery.ActivityScope;
import com.omarmohamed.myvideogallery.modules.MainModule;
import com.omarmohamed.myvideogallery.ui.main.MainActivity;
import com.omarmohamed.myvideogallery.ui.main.MainPresenter;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity activity);
    MainPresenter getMainPresenter();
}
package com.omarmohamed.myvideogallery.ui.common;

import android.app.Activity;
import android.os.Bundle;

import com.omarmohamed.myvideogallery.App;
import com.omarmohamed.myvideogallery.component.AppComponent;

public abstract class BaseActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent((AppComponent) App.get(this).component());
    }


    protected abstract void setupComponent(AppComponent appComponent);
}

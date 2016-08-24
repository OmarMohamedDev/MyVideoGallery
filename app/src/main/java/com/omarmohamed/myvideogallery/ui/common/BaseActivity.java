package com.omarmohamed.myvideogallery.ui.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.omarmohamed.myvideogallery.Application;
import com.omarmohamed.myvideogallery.R;
import com.omarmohamed.myvideogallery.component.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupComponent(Application.get(this).component());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    protected abstract void setupComponent(AppComponent appComponent);
}

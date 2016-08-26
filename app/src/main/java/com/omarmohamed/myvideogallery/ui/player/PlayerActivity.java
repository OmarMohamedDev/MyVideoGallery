package com.omarmohamed.myvideogallery.ui.player;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.omarmohamed.myvideogallery.R;
import com.omarmohamed.myvideogallery.component.AppComponent;
import com.omarmohamed.myvideogallery.ui.common.BaseActivity;

/**
 * Created by omarmohamed on 26/08/2016.
 */
//TODO: Refactor to adapt to model view presenter
public class PlayerActivity extends BaseActivity {
    private static final String TAG = PlayerActivity.class.getSimpleName();

    private ImageView mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mPlayButton = (ImageView) findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerActivity.this, ExoPlayerActivity.class);
                intent.putExtra(ExoPlayerActivity.EXTRA_URL, getString(R.string.video_url));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }
}
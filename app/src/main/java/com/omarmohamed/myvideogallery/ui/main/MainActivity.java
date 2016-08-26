package com.omarmohamed.myvideogallery.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.afollestad.materialcamera.MaterialCamera;
import com.omarmohamed.myvideogallery.R;
import com.omarmohamed.myvideogallery.adapter.RecyclerViewAdapter;
import com.omarmohamed.myvideogallery.component.AppComponent;
import com.omarmohamed.myvideogallery.component.DaggerMainComponent;
import com.omarmohamed.myvideogallery.models.VideoModel;
import com.omarmohamed.myvideogallery.modules.MainModule;
import com.omarmohamed.myvideogallery.ui.common.BaseActivity;
import com.omarmohamed.myvideogallery.ui.player.PlayerActivity;
import com.omarmohamed.myvideogallery.utils.Constants;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter presenter;

    @InjectView(R.id.recycler)
    RecyclerView recyclerView;

    @InjectView(R.id.progress)
    ProgressBar progressBar;

    @Inject
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        //Setting up the actionbar TODO: Check if for MVP is the right place to do this
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_record) {
                    startRecorder();
                }
                return false;
            }
        });
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }


    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }


    @Override protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<VideoModel> items) {
        if(adapter != null){
            adapter.setItems(items);
            recyclerView.setAdapter(adapter);
        }
    }

    //TODO:Handle differently the onclick method and how to make the best choice for MVP pattern
    public void startPlayer(View view) {
        startActivity(new Intent(this, PlayerActivity.class));
    }

    public void startRecorder() {


        File saveFolder = new File(Environment.getExternalStorageDirectory(), "MaterialCamera Sample");
        //TODO: Check write permissions
        if (!saveFolder.mkdirs())
            throw new RuntimeException("Unable to create save directory, make sure WRITE_EXTERNAL_STORAGE permission is granted.");

        new MaterialCamera(this)                               // Constructor takes an Activity
                .allowRetry(true)                                  // Whether or not 'Retry' is visible during playback
                .autoSubmit(true)                                 // Whether or not user is allowed to playback videos after recording. This can affect other things, discussed in the next section.
                .saveDir(Constants.Recorder.VIDEO_DIRECTORY)          // The folder recorded videos are saved to
                .primaryColorAttr(R.attr.colorPrimary)             // The theme color used for the camera, defaults to colorPrimary of Activity in the constructor
                .showPortraitWarning(false)                         // Whether or not a warning is displayed if the user presses record in portrait orientation
                .defaultToFrontFacing(true)                       // Whether or not the camera will initially show the front facing camera
                .retryExits(false)                                 // If true, the 'Retry' button in the playback screen will exit the camera instead of going back to the recorder
                .restartTimerOnRetry(false)                        // If true, the countdown timer is reset to 0 when the user taps 'Retry' in playback
                .continueTimerInPlayback(false)                    // If true, the countdown timer will continue to go down during playback, rather than pausing.
                .videoEncodingBitRate(1024000)                     // Sets a custom bit rate for video recording.
                .audioEncodingBitRate(50000)                       // Sets a custom bit rate for audio recording.
                .videoFrameRate(24)                                // Sets a custom frame rate (FPS) for video recording.
                .qualityProfile(MaterialCamera.QUALITY_HIGH)       // Sets a quality profile, manually setting bit rates or frame rates with other settings will overwrite individual quality profile settings
                .videoPreferredHeight(720)                         // Sets a preferred height for the recorded video output.
                .videoPreferredAspect(4f / 3f)                     // Sets a preferred aspect ratio for the recorded video output.
                .maxAllowedFileSize(1024 * 1024 * 5)               // Sets a max file size of 5MB, recording will stop if file reaches this limit. Keep in mind, the FAT file system has a file size limit of 4GB.
                .iconRecord(R.drawable.mcam_action_capture)        // Sets a custom icon for the button used to start recording
                .iconStop(R.drawable.mcam_action_stop)             // Sets a custom icon for the button used to stop recording
                .iconFrontCamera(R.drawable.mcam_camera_front)     // Sets a custom icon for the button used to switch to the front camera
                .iconRearCamera(R.drawable.mcam_camera_rear)       // Sets a custom icon for the button used to switch to the rear camera
                .iconPlay(R.drawable.evp_action_play)              // Sets a custom icon used to start playback
                .iconPause(R.drawable.evp_action_pause)            // Sets a custom icon used to pause playback
                .iconRestart(R.drawable.evp_action_restart)        // Sets a custom icon used to restart playback
                .labelRetry(R.string.mcam_retry)                   // Sets a custom button label for the button used to retry recording, when available
                .labelUseVideo(R.string.mcam_use_video)            // Sets a custom button label for the button used to confirm a recording
                .countdownMillis(Constants.Recorder.VIDEO_MAX_DURATION_IN_MILLISECONDS) //Max duration for the video
                .start(Constants.Recorder.CAMERA_RQ);                                 // Starts the camera activity, the result will be sent back to the current Activity
    }


}

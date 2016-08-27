package com.omarmohamed.myvideogallery.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.afollestad.materialcamera.MaterialCamera;
import com.google.android.exoplayer.ExoPlayer;
import com.omarmohamed.myvideogallery.R;
import com.omarmohamed.myvideogallery.adapter.RecyclerViewAdapter;
import com.omarmohamed.myvideogallery.component.AppComponent;
import com.omarmohamed.myvideogallery.component.DaggerMainComponent;
import com.omarmohamed.myvideogallery.models.VideoModel;
import com.omarmohamed.myvideogallery.modules.MainModule;
import com.omarmohamed.myvideogallery.ui.common.BaseActivity;
import com.omarmohamed.myvideogallery.ui.player.PlayerActivity;
import com.omarmohamed.myvideogallery.utils.Constants;
import com.omarmohamed.myvideogallery.utils.Utilities;

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

    //TODO:Handle differently the onclick method and how to make the best choice for MVP pattern (DECOUPLE)
    public void startPlayer(View view) {
        startActivity(new Intent(this, PlayerActivity.class));
    }

    public void startRecorder() {
        File saveFolder = null;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Request permission to save videos in external storage
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.Recorder.PERMISSION_RQ);
        }

        //Setting up the saving folder
        saveFolder = new File(Environment.getExternalStorageDirectory(), Constants.Recorder.VIDEO_DIRECTORY);

        //TODO: SET PROPERLY THE SAVE DIR AVOIDING THE CRASH IF POSSIBILE
        new MaterialCamera(this)                               // Constructor takes an Activity
//                .allowRetry(true)                                  // Whether or not 'Retry' is visible during playback
//                .autoSubmit(true)                                 // Whether or not user is allowed to playback videos after recording. This can affect other things, discussed in the next section.
//                .saveDir(saveFolder)          // The folder recorded videos are saved to
//                .primaryColorAttr(R.attr.colorPrimary)             // The theme color used for the camera, defaults to colorPrimary of Activity in the constructor
                .showPortraitWarning(false)                         // Whether or not a warning is displayed if the user presses record in portrait orientation
                .defaultToFrontFacing(true)                       // Whether or not the camera will initially show the front facing camera
//                .retryExits(false)                                 // If true, the 'Retry' button in the playback screen will exit the camera instead of going back to the recorder
//                .restartTimerOnRetry(false)                        // If true, the countdown timer is reset to 0 when the user taps 'Retry' in playback
//                .continueTimerInPlayback(false)                    // If true, the countdown timer will continue to go down during playback, rather than pausing.
//                .videoEncodingBitRate(1024000)                     // Sets a custom bit rate for video recording.
//                .audioEncodingBitRate(50000)                       // Sets a custom bit rate for audio recording.
//                .videoFrameRate(24)                              // Sets a custom frame rate (FPS) for video recording.
//                .qualityProfile(MaterialCamera.QUALITY_HIGH)       // Sets a quality profile, manually setting bit rates or frame rates with other settings will overwrite individual quality profile settings
//                .videoPreferredHeight(720)                         // Sets a preferred height for the recorded video output.
//                .videoPreferredAspect(4f / 3f)                     // Sets a preferred aspect ratio for the recorded video output.
//                .maxAllowedFileSize(1024 * 1024 * 5)               // Sets a max file size of 5MB, recording will stop if file reaches this limit. Keep in mind, the FAT file system has a file size limit of 4GB.
//                .iconRecord(R.drawable.mcam_action_capture)        // Sets a custom icon for the button used to start recording
//                .iconStop(R.drawable.mcam_action_stop)             // Sets a custom icon for the button used to stop recording
//                .iconFrontCamera(R.drawable.mcam_camera_front)     // Sets a custom icon for the button used to switch to the front camera
//                .iconRearCamera(R.drawable.mcam_camera_rear)       // Sets a custom icon for the button used to switch to the rear camera
//                .iconPlay(R.drawable.evp_action_play)              // Sets a custom icon used to start playback
//                .iconPause(R.drawable.evp_action_pause)            // Sets a custom icon used to pause playback
//                .iconRestart(R.drawable.evp_action_restart)        // Sets a custom icon used to restart playback
//                .labelRetry(R.string.mcam_retry)                   // Sets a custom button label for the button used to retry recording, when available
//                .labelUseVideo(R.string.mcam_use_video)            // Sets a custom button label for the button used to confirm a recording
                .countdownMillis(Constants.Recorder.VIDEO_MAX_DURATION_IN_MILLISECONDS) //Max duration for the video
                .start(Constants.Recorder.CAMERA_RQ);                                 // Starts the camera activity, the result will be sent back to the current Activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Received recording or error from MaterialCamera
        if (requestCode == Constants.Recorder.CAMERA_RQ) {

            if (resultCode == RESULT_OK) {
                final File file = new File(data.getData().getPath());


                //Retrieving data from the file and creating the relative model
                VideoModel videoObject = createVideoModel(file);
                //

                Toast.makeText(this, "Saved to: " + data.getDataString(), Toast.LENGTH_LONG).show();
            } else if (data != null) {
                Exception e = (Exception) data.getSerializableExtra(MaterialCamera.ERROR_EXTRA);
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            // App was denied WRITE_EXTERNAL_STORAGE permission
            Toast.makeText(this, getString(R.string.write_permissions_not_granted), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Method that get a video as file parameter and returns a VideoModel object
     *
     * @param file the video file
     * @return the VideoModel object
     */
    private VideoModel createVideoModel(File file) {
        VideoModel videoModel = new VideoModel();

        //TODO: Set the data properly
        ExoPlayer player = ExoPlayer.Factory.newInstance(2);

//        DataSource dataSource = new DefaultUriDataSource(this, null, getUserAgent(this, "ExoPlayerExample"));
//        SampleSource sampleSource = new SingleSampleSource(Uri.fromFile(file), dataSource, MediaFormat.createVideoFormat(file.getName(), Constants.Recorder.MIME_VIDEO_CODEC_H264,  MediaFormat.NO_VALUE,
//                MediaFormat.NO_VALUE, C.UNKNOWN_TIME_US, 1280, 720, null));
//        //SampleSource sampleSource = new SingleSampleSource(Uri.fromFile(file), dataSource, MediaFormat.createVideoFormat(Constants.Recorder.MIME_VIDEO_CODEC_H264, 1280, 720));
//     //   SampleSource sampleSource = new FrameworkSampleSource(this,Uri.fromFile(file), null);
//        TrackRenderer videoRenderer = new MediaCodecVideoTrackRenderer(this,
//                sampleSource, MediaCodecSelector.DEFAULT, MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
//        TrackRenderer audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource, MediaCodecSelector.DEFAULT);
//        player.prepare(videoRenderer, audioRenderer);
//        player.getDuration();
//        player.get
        //TODO:Refactor all this part and clean it up
        MediaPlayer mp = MediaPlayer.create(this, Uri.fromFile(file));
        int duration = mp.getDuration();
        mp.release();
        /*convert millis to appropriate time*/
        String durationString = Utilities.fromMillisecondsToSeconds(duration);


        videoModel.setTitle(file.getName());
        videoModel.setPath(file.getAbsolutePath());
        videoModel.setThumbnail(ThumbnailUtils.createVideoThumbnail(videoModel.getPath(), MediaStore.Video.Thumbnails.MINI_KIND));


        videoModel.setTimestamp(Utilities.fromMillisecondsToSeconds((int) System.currentTimeMillis()));
        videoModel.setDuration(durationString);
        videoModel.setCreationTime(Utilities.fromMillisecondsToSeconds((int) SystemClock.elapsedRealtime())); //TODO:Check how to retrieve it
        //


        return videoModel;
    }


}

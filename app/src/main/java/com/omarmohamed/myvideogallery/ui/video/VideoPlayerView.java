package com.omarmohamed.myvideogallery.ui.video;

/**
 * Created by omarmohamed on 26/08/2016.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.google.android.exoplayer.ExoPlayer;

/**
 * Use this view to communicate with controls and listen to player state
 */
public class VideoPlayerView extends AbsVideoPlayer implements ControlsListener {

    private PlayerListener mPlayerListener;
    private EndedListener mEndedListener;
    private boolean mShouldSetDuration;
    private Runnable mUpdateProgress = new Runnable() {
        @Override
        public void run() {
            if (mPlayerListener == null || mPlayerController == null) return;

            mPlayerListener.onUpdateProgress(mPlayerController.getCurrentPosition() / (float) mPlayerController.getDuration());
            mHandler.postDelayed(mUpdateProgress, 1000);
        }
    };

    public VideoPlayerView(Context context) {
        this(context, null, 0);
    }

    public VideoPlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mShouldSetDuration = true;
    }

    public void setPlayerListener(PlayerListener listener) {
        mPlayerListener = listener;
    }

    public void setEndedListener(EndedListener listener) {
        mEndedListener = listener;
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        Log.v(EXOPLAYER_LISTENER, "onPlayerStateChanged");
        switch (playbackState) {
            case ExoPlayer.STATE_BUFFERING:
                mPlayerListener.onBufferingStart();
                break;
            case ExoPlayer.STATE_READY:
                mPlayerListener.onBufferingComplete();
                if (mShouldSetDuration) {
                    mPlayerListener.onSetDuration(mExoPlayer.getDuration());
                    mPlayerListener.onUpdateProgress(0);
                    mHandler.post(mUpdateProgress);
                    mShouldSetDuration = false;
                }
                break;
            case ExoPlayer.STATE_ENDED:
                mEndedListener.onEnded();
                break;
        }
    }

    @Override
    public void onPause() {
        if (mPlayerController != null) {
            mPlayerController.pause();
        }
    }

    @Override
    public void onPlay() {
        if (mPlayerController != null) {
            mPlayerController.start();
        }
    }

    @Override
    public void onSeekTo(float percentComplete) {
        if (mPlayerController != null && mExoPlayer != null) {
            mPlayerController.seekTo((int) (mExoPlayer.getDuration() * percentComplete));
        }
    }

    @Override
    public void onControlsHidden() {
        mHandler.removeCallbacks(mUpdateProgress);
    }

    @Override
    public void onControlsShown() {
        mHandler.removeCallbacks(mUpdateProgress);
        mHandler.post(mUpdateProgress);
    }
}

package com.omarmohamed.myvideogallery.ui.video;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.omarmohamed.myvideogallery.R;

/**
 * Created by omarmohamed on 26/08/2016.
 */

public class VideoControlsView extends RelativeLayout implements PlayerListener {

    private static final String TIMER_FORMAT = "%s / %s";
    private static final int ONE_MINUTE = 60;
    private static final int ONE_HOUR = ONE_MINUTE * 60;

    private ControlsListener mListener;

    private ImageView mPlayButton;
    private ProgressBar mBuffering;
    private SeekBar mSeekBar;
    private TextView mTimer;

    private int mDurationSec;
    private String mTimeFormat;

    public VideoControlsView(Context context) {
        this(context, null);
    }

    public VideoControlsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoControlsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View v = LayoutInflater.from(context).inflate(R.layout.video_controls, this, true);
        mPlayButton = (ImageView) v.findViewById(R.id.play_button);
        mBuffering = (ProgressBar) v.findViewById(R.id.loading_indicator);
        mSeekBar = (SeekBar) v.findViewById(R.id.seekbar);
        mTimer = (TextView) v.findViewById(R.id.progress_timer);
        mDurationSec = -1;
        setViewListeners();
        showBuffering();
    }

    private void setViewListeners() {
        mPlayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener == null) return;

                mPlayButton.setActivated(!mPlayButton.isActivated());
                if (mPlayButton.isActivated()) {
                    mListener.onPause();
                } else {
                    mListener.onPlay();
                }
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mListener != null) {
                    mListener.onSeekTo(seekBar.getProgress() / (float) seekBar.getMax());
                }
            }
        });
    }

    private void showBuffering() {
        mPlayButton.setVisibility(GONE);
        mBuffering.setVisibility(VISIBLE);
    }

    private void hideBuffering() {
        mBuffering.setVisibility(GONE);
        mPlayButton.setVisibility(VISIBLE);
    }

    public boolean isBuffering() {
        return mBuffering.getVisibility() == VISIBLE;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (mListener == null) return;

        if (visibility == GONE || visibility == INVISIBLE) {
            mListener.onControlsHidden();
        } else {
            mListener.onControlsShown();
        }
    }

    public void setControlsListener(ControlsListener listener) {
        mListener = listener;
    }

    @Override
    public void onBufferingStart() {
        showBuffering();
    }

    @Override
    public void onBufferingComplete() {
        hideBuffering();
    }

    @Override
    public void onSetDuration(long durationMs) {
        mDurationSec = (int) (durationMs / 1000);
        if (mDurationSec >= 10 * ONE_HOUR) {
            mTimeFormat = "%02d:%02d:%02d";
        } else if (mDurationSec >= ONE_HOUR) {
            mTimeFormat = "%1d:%02d:%02d";
        } else if (mDurationSec >= 10 * ONE_MINUTE) {
            mTimeFormat = "%02d:%02d";
        } else {
            mTimeFormat = "%1d:%02d";
        }
    }

    @Override
    public void onUpdateProgress(float percentComplete) {
        mSeekBar.setProgress((int) (mSeekBar.getMax() * percentComplete));
        if (mDurationSec > 0) {
            mTimer.setText(String.format(TIMER_FORMAT, formatTime((int) (mDurationSec * percentComplete)),
                    formatTime(mDurationSec)));
        }
    }

    private String formatTime(int timeSec) {
        if (TextUtils.isEmpty(mTimeFormat)) return "00:00";

        int hours = timeSec / ONE_HOUR;
        int remainder = timeSec % ONE_HOUR;
        int minutes = remainder / ONE_MINUTE;
        int seconds = remainder % ONE_MINUTE;
        if (mTimeFormat.length() > 9) {
            return String.format(mTimeFormat, hours, minutes, seconds);
        } else {
            return String.format(mTimeFormat, minutes, seconds);
        }
    }
}
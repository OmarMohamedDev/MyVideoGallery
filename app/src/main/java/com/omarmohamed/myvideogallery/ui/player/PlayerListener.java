package com.omarmohamed.myvideogallery.ui.player;

/**
 * Created by omarmohamed on 26/08/2016.
 */

public interface PlayerListener {

    void onBufferingStart();

    void onBufferingComplete();

    void onSetDuration(long durationMs);

    void onUpdateProgress(float percentComplete);
}

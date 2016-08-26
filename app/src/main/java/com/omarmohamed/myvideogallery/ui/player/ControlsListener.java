package com.omarmohamed.myvideogallery.ui.player;

/**
 * Interface that define signature for methods that can control the video flow
 */

public interface ControlsListener {

    void onPause();

    void onPlay();

    void onSeekTo(float percentComplete);

    void onControlsHidden();

    void onControlsShown();
}

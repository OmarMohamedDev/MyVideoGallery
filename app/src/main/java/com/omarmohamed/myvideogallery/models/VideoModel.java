package com.omarmohamed.myvideogallery.models;

import android.graphics.Bitmap;

/**
 * Model class that represent a Recorder object
 *
 * @author OmarMohamedDev
 */
public class VideoModel {

    /**
     * Title of the video
     */
    private String mTitle;

    /**
     * Timestamp of the video that represent the date/time when the video was recorded
     */
    private String mTimestamp;

    /**
     * Duration of the video
     */
    private String mDuration;

    /**
     * Time that the app used to elaborate the recorded video
     */
    private String mCreationTime;

    /**
     * Bitmap that contain the video thumbnail that will be displayed in the list
     */
    private Bitmap mThumbnail;

    /**
     * Internal path to the video
     */
    private String mPath;


    //Getters and Setters
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String mDuration) {
        this.mDuration = mDuration;
    }

    public String getCreationTime() {
        return mCreationTime;
    }

    public void setCreationTime(String mCreationTime) {
        this.mCreationTime = mCreationTime;
    }

    public Bitmap getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(Bitmap mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String mPath) {
        this.mPath = mPath;
    }
}

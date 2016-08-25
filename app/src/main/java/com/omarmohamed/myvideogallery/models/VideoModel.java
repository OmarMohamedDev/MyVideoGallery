package com.omarmohamed.myvideogallery.models;

/**
 * Model class that represent a Video object
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


    //Getters and Setters
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTimestamp() {
        return mTimestamp;
    }

    public void setmTimestamp(String mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public String getmDuration() {
        return mDuration;
    }

    public void setmDuration(String mDuration) {
        this.mDuration = mDuration;
    }

    public String getmCreationTime() {
        return mCreationTime;
    }

    public void setmCreationTime(String mCreationTime) {
        this.mCreationTime = mCreationTime;
    }
}

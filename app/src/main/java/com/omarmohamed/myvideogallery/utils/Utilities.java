package com.omarmohamed.myvideogallery.utils;

import java.util.concurrent.TimeUnit;

/**
 * Class that contains utilities methods for different part of the application
 * Created by omarmohamed on 26/08/2016.
 */

public class Utilities {

    /**
     * Class that contains utilities methods for Camera object
     */
//    public static class Camera{
//        /** A safe way to get an instance of the Camera object. */
//        public static Camera getCameraInstance(){
//            Camera c = null;
//            try {
//                c = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT); // attempt to get a Camera instance
//            }
//            catch (Exception e){
//                // Camera is not available (in use or does not exist)
//                e.printStackTrace();
//            }
//            return c; // returns null if camera is unavailable
//        }
//
//
//        /** Create a File for saving an image or video */
//        public static File getOutputMediaFile(String videoTitle){
//            // To be safe, you should check that the SDCard is mounted
//            // using Environment.getExternalStorageState() before doing this.
//
//            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_PICTURES), Constants.Recorder.VIDEO_DIRECTORY);
//            // This location works best if you want the created images to be shared
//            // between applications and persist after your app has been uninstalled.
//
//            // Create the storage directory if it does not exist
//            if (! mediaStorageDir.exists()){
//                if (! mediaStorageDir.mkdirs()){
//                    Log.d(TAG, "failed to create directory");
//                    return null;
//                }
//            }
//
//            // Create a media file name that contains all the information in it.
//            // So that we don't need to do any kind of database
//            String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
//            return new File(mediaStorageDir.getPath() + File.separator +
//                    "VID" + Constants.Recorder.TITLE_SEPARATOR + timeStamp + Constants.Recorder.TITLE_SEPARATOR +
//                    videoTitle + ".mp4");
//        }
//    }

    /**
     * Simple method that return a string that represent the number of seconds giving milliseconds as parameter
     *
     * @param mill milliseconds
     * @return the value in seconds as String
     */
    public static String fromMillisecondsToSeconds(int mill) {
        return String.format("%d",
                TimeUnit.MILLISECONDS.toSeconds(mill) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mill)
                        ));

    }

}

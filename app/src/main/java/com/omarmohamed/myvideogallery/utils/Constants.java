package com.omarmohamed.myvideogallery.utils;

/**
 * Utility class that contains all the constants used inside the app (regexp, generic constants, urls, etc.)
 *
 * @author OmarMohamedDev
 */
public class Constants {

    /**
     * Class that contains all the constants that are related to the video objects
     */
    public static class Recorder {

        public static final String VIDEO_DIRECTORY = "MyVideoGallery";
        //Title video separators
        public static final String VIDEO_TITLE_SEPARATOR = "_";
        public static final String VIDEO_TITLE_FILE_SPACE = "-";
        public static final String VIDEO_TITLE_READABLE_SPACE = " ";
        /**
         * Constant needed to instantiate camera instance of the Material Camera library
         */
        public static final int CAMERA_RQ = 6969;
        /**
         * Constants used to check if the write on external storage permission was granted properly
         */
        public final static int PERMISSION_RQ = 84;
        /**
         * Max duration arbitrarily set for a video
         */
        public static int VIDEO_MAX_DURATION_IN_MILLISECONDS = 20000;

        public static String MIME_VIDEO_CODEC_H264 = "type='video/mp4; codecs=\"avc1.42E01E, mp4a.40.2\"'";
    }

    /**
     * Class that contains all the constants used by the player of the app
     */
    public static class Player {
        public static final String EXOPLAYER_LISTENER = "ExoPlayer.Listener";
        public static final String SAMPLE_SOURCE_LISTENER = "SampleSource.Listener";
        public static final String TRACK_RENDERER_LISTENER = "TrackRenderer.Listener";
        public static final String VIDEO_TRACK_LISTENER = "VideoTrack.Listener";
        public static final String AUDIO_TRACK_LISTENER = "AudioTrack.Listener";
        public static final String SURFACE_HOLDER_CALLBACK = "SurfaceHolder.Callback";
        public static final String EXTRA_URI = "extra_uri";
    }

    /**
     * Class that contains all the constants related to the network connectivity
     */
    public static class Network {

        /**
         * Base URL for the web-services called inside the app
         */
        public static String BASE_URL = "http://petstore.swagger.io/v2";


    }

}

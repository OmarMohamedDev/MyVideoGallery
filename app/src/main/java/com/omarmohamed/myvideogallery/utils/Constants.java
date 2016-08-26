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
    public static class Video {

        public static final String VIDEO_DIRECTORY = "MyVideoGallery";
        //Title video separators
        public static final String VIDEO_TITLE_SEPARATOR = "_";
        public static final String VIDEO_TITLE_FILE_SPACE = "-";
        public static final String VIDEO_TITLE_READABLE_SPACE = " ";
        /**
         * Max duration arbitrarily set for a video
         */
        public static int VIDEO_MAX_DURATION_IN_MILLISECONDS = 20000;
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

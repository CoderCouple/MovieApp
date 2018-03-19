package com.android.movieapp;

import android.content.pm.ActivityInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aaditya Deowanshi
 *
 * This class is responsible for keeping application configuration as constants.
 */
public class Config {

    //--------------------------------------------------------------------------------
    // App general configurations
    //--------------------------------------------------------------------------------
    public static final boolean DEBUG = true;

    public static final int ORIENTATION_PORTRAIT    = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    public static final int ORIENTATION_LANDSCAPE   = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    public static final int ORIENTATION_SENSOR      = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
    public static final int ORIENTATION_DEFAULT     = ORIENTATION_PORTRAIT;

    //--------------------------------------------------------------------------------
    // API related constants/configurations - used in ApiModule
    //--------------------------------------------------------------------------------
    public static final String API_BASE_URL_PRODUCTION = "https://api.themoviedb.org/3/";
    public static final String API_BASE_URL_MOCK = "";

    //Image Base url
    public static final String POSTER_BASE_PATH = "http://image.tmdb.org/t/p/w185";
    public static final String BACKDROP_BASE_PATH = "http://image.tmdb.org/t/p/w500";
    
    // Active base url
    public static final String API_BASE_URL = API_BASE_URL_PRODUCTION;

    // Common http headers required to be added by interceptor
    public static final Map<String, String> API_HEADERS = new HashMap<String, String>() {{
        put("User-Agent", "Android-Movie");
        put("Content-Type", "application/json");
    }};

    //MovieDb Keys
    public static final String APP_KEY = "845c702a19ceb0992d8987387636667c";

}

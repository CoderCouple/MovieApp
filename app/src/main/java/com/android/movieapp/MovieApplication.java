package com.android.movieapp;

import android.app.Application;

import com.android.movieapp.injection.Injector;
import com.android.movieapp.module.movie.util.GenreUtil;

import timber.log.Timber;

/**
 * @author Aaditya Deowanshi
 */
public class MovieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Create application component to make it ready for the injection
        Injector.createApplicationComponent(this);

        // Plant Timber tree for Logging
        Timber.plant(new Timber.DebugTree());
        GenreUtil.loadGenreMap(getApplicationContext());
    }

}

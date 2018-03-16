package com.android.movieapp.injection.module;

import com.android.movieapp.module.movie.presenter.MoviePresenter;
import com.android.movieapp.module.movie.presenter.MoviePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Provides all task module dependencies.
 *
 * @author Aaditya Deowanshi
 */

@Module
public class MovieModule {

    @Provides
    public MoviePresenter provideTaskListPresenter() {
        return new MoviePresenterImpl();
    }

}

package com.android.movieapp.injection.module;

import com.android.movieapp.module.movie.presenter.ReviewPresenter;
import com.android.movieapp.module.movie.presenter.ReviewPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Provides all task module dependencies.
 *
 * @author Aaditya Deowanshi
 */
@Module
public class ReviewModule {

    @Provides
    public ReviewPresenter provideTaskListPresenter() {
        return new ReviewPresenterImpl();
    }

}

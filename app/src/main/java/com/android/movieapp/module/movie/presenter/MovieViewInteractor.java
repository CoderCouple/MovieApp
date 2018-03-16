package com.android.movieapp.module.movie.presenter;

import com.android.movieapp.module.base.ViewInteractor;
import com.android.movieapp.module.movie.model.Movie;
import com.android.movieapp.module.movie.model.MovieResponse;

import java.util.List;


/**
 * @author Aaditya Deowanshi
 */
public interface MovieViewInteractor extends ViewInteractor {

    void showProgress();

    void hideProgress();

    void onResult(MovieResponse response);

}

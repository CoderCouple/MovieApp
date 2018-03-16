package com.android.movieapp.module.movie.presenter;

import com.android.movieapp.module.base.Presenter;

/**
 * @author Aaditya Deowanshi
 */
public interface MoviePresenter extends Presenter<MovieViewInteractor> {

    void getPopularMovies(int pageNum);

    void getUpComingMovies(int pageNum);


}

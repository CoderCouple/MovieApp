package com.android.movieapp.module.movie.presenter;

import com.android.movieapp.Config;
import com.android.movieapp.data.api.ApiObserver;
import com.android.movieapp.data.api.MovieDbService;
import com.android.movieapp.injection.Injector;
import com.android.movieapp.module.base.BaseNetworkPresenter;
import com.android.movieapp.module.common.util.Bakery;
import com.android.movieapp.module.movie.model.MovieResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * @author Aaditya Deowanshi
 */

/**
 * Presenter Implementation to call movieDb services
 */
public class MoviePresenterImpl extends BaseNetworkPresenter<MovieViewInteractor>
        implements MoviePresenter {

    @Inject MovieDbService movieDbService;
    @Inject Bakery bakery;

    public MoviePresenterImpl() {
        Injector.component().inject(this);
    }

    @Override
    public void getPopularMovies(int pageNum) {
        Observable<Response<MovieResponse>> popularMovieObservable = movieDbService.getPopularMovie(Config.APP_KEY, pageNum);

        getViewInteractor().showProgress();
        subscribeForNetwork(popularMovieObservable, new ApiObserver<Response<MovieResponse>>() {

            @Override
            public void onResponse(Response<MovieResponse> response) {
                // Checks for response code
                if (response.code() != 200){
                    bakery.toastLong(response.message());
                    return;
                }

                //sends response to activity
                getViewInteractor().hideProgress();
                getViewInteractor().onResult(response.body());
            }

            @Override
            public void onError(Throwable e) {
                bakery.toastLong(e.getMessage());
            }});
    }

    @Override
    public void getUpComingMovies(int pageNum) {
        Observable<Response<MovieResponse>> upComingMovieObservable = movieDbService.getUpComingMovie(Config.APP_KEY, pageNum);

        getViewInteractor().showProgress();
        subscribeForNetwork(upComingMovieObservable, new ApiObserver<Response<MovieResponse>>() {
            @Override
            public void onError(Throwable e) {
                bakery.toastLong(e.getMessage());
            }

            @Override
            public void onResponse(Response<MovieResponse> response) {
                // Checks for response code
                if (response.code() != 200){
                    bakery.toastLong(response.message());
                    return;
                }

                //sends response to activity
                getViewInteractor().hideProgress();
                getViewInteractor().onResult(response.body());
            }
        });
    }
}

package com.android.movieapp.module.movie.presenter;

import com.android.movieapp.Config;
import com.android.movieapp.data.api.ApiObserver;
import com.android.movieapp.data.api.MovieDbService;
import com.android.movieapp.injection.Injector;
import com.android.movieapp.module.base.BaseNetworkPresenter;
import com.android.movieapp.module.movie.model.Movie;
import com.android.movieapp.module.movie.model.MovieResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author Aaditya Deowanshi
 */
public class MoviePresenterImpl extends BaseNetworkPresenter<MovieViewInteractor>
        implements MoviePresenter {

    @Inject MovieDbService movieDbService;

    private JsonObject responseDetail;

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
                /*try {
                    responseDetail = (JsonObject) new JsonParser().parse(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Type listType = new TypeToken<List<Movie>>() {
                }.getType();
                List<Movie> movies = new Gson().fromJson(responseDetail.get("results").toString(), listType);*/

                getViewInteractor().hideProgress();
                getViewInteractor().onResult(response.body());
            }

            @Override
            public void onError(Throwable e) {

            }});


    }

    @Override
    public void getUpComingMovies(int pageNum) {
        Observable<Response<MovieResponse>> upComingMovieObservable = movieDbService.getUpComingMovie(Config.APP_KEY, pageNum);

        getViewInteractor().showProgress();
        subscribeForNetwork(upComingMovieObservable, new ApiObserver<Response<MovieResponse>>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onResponse(Response<MovieResponse> response) {

                /*try {
                    responseDetail = (JsonObject) new JsonParser().parse(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Type listType = new TypeToken<List<Movie>>() {
                }.getType();
                List<Movie> movies = new Gson().fromJson(responseDetail.get("results").toString(), listType);*/

                getViewInteractor().hideProgress();
                getViewInteractor().onResult(response.body());
            }
        });
    }
}

package com.android.movieapp.data.api;

import com.android.movieapp.module.movie.model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Aaditya Deowanshi
 */
public interface MovieDbService {

    @GET("movie/popular")
    Observable<Response<MovieResponse>> getPopularMovie(@Query("api_key") String key, @Query("page") int pageNum);

    @GET("movie/upcoming")
    Observable<Response<MovieResponse>> getUpComingMovie(@Query("api_key") String key, @Query("page") int pageNum);
}

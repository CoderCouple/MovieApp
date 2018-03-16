package com.android.movieapp.module.movie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aaditya on 3/14/18.
 */

public class Movie implements Serializable{

    @Expose @SerializedName("id")
    private int id;
    @Expose @SerializedName("title")
    private String title;
    @Expose @SerializedName("poster_path")
    private String poster;
    @Expose @SerializedName("popularity")
    private String popularity;
    @Expose @SerializedName("genre_ids")
    private List<Integer> genres;
    @Expose @SerializedName("overview")
    private String overview;
    @Expose @SerializedName("release_date")
    private String releaseDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        Movie movie = ( Movie ) obj;
        if( movie.id == id )
            flag = true;
        return flag;
    }
}

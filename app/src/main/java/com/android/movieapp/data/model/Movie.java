package com.android.movieapp.data.model;

import com.android.movieapp.module.movie.util.GenreUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Expose @SerializedName("vote_count")
    private int vote_count;
    @Expose @SerializedName("original_language")
    private String language;
    @Expose @SerializedName("backdrop_path")
    private String backDropImage;

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

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBackDropImage() {
        return backDropImage;
    }

    public void setBackDropImage(String backDropImage) {
        this.backDropImage = backDropImage;
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

    public List<String> getGenres() {
        Map<Integer, String> genreMap = GenreUtil.getGenreList();
        List<String> genreName = new ArrayList<>();
        for (int id : genres) {
            if (genreMap.containsKey(id))
                genreName.add(genreMap.get(id));
        }

        return genreName;
    }
}

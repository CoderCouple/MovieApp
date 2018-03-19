package com.android.movieapp;

import android.test.AndroidTestCase;

import com.android.movieapp.module.movie.util.GenreUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by aaditya on 3/19/18.
 */

public class MovieGenreUtilTest extends AndroidTestCase {

    public void testRetrieveMovieGenres() throws Exception {
        Map<Integer,String> genres = GenreUtil.getGenreList();
        assertNotNull("should return genre info",genres);

        for(Map.Entry<Integer, String> e: genres.entrySet()) {
            assertTrue("genre element has a valid id", e.getKey() > 0);
            assertTrue("genre element has a valid name", e.getValue().length() > 0);
        }
    }
}

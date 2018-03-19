package com.android.movieapp.module.movie.util;

import android.app.Application;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

/**
 * Created by aaditya on 3/18/18.
 */

/**
 * Utility class to read genre name from genre id.
 */
public class GenreUtil {

    static Map<Integer, String> genreMap = new HashMap<>();

    public static void loadGenreMap(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("Genre.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JSONObject obj = null;
        try {
            obj = new JSONObject(json);
            JSONArray jarray = (JSONArray) obj.getJSONArray("genres");
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jb = (JSONObject) jarray.get(i);
                int id = jb.getInt("id");
                String name = jb.getString("name");
                genreMap.put(id, name);
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    public static Map getGenreList() {
        return genreMap;
    }

}

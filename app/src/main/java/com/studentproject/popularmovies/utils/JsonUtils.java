package com.studentproject.popularmovies.utils;

import com.studentproject.popularmovies.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class JsonUtils {

    private static final String RESULTS_KEY = "results";
    private static final String TITLE_KEY = "title";
    private static final String RELEASE_DATE_KEY = "release_date";
    private static final String POSTER_KEY = "poster_path";
    private static final String VOTE_AVERAGE_KEY = "vote_average";
    private static final String PLOT_SYNOPSIS_KEY = "overview";

    public static List<Movie> getMoviesFromJson(String responseJsonString) throws JSONException{

        List<Movie> movies = new ArrayList<>();

        JSONObject topLevelJsonObject = new JSONObject(responseJsonString);
        JSONArray resultsJsonArray = topLevelJsonObject.getJSONArray(RESULTS_KEY);

        for (int i = 0; i < resultsJsonArray.length(); i++) {

            JSONObject movieJsonObject = resultsJsonArray.getJSONObject(i);

            String title = movieJsonObject.optString(TITLE_KEY);
            String releaseDate = movieJsonObject.optString(RELEASE_DATE_KEY);
            String poster = movieJsonObject.optString(POSTER_KEY);
            String voteAverage = movieJsonObject.optString(VOTE_AVERAGE_KEY);
            String plotSynopsis = movieJsonObject.optString(PLOT_SYNOPSIS_KEY);

            Movie movie = new Movie(title, releaseDate, poster, voteAverage, plotSynopsis);

            movies.add(i, movie);

        }

        return movies;


    }

}

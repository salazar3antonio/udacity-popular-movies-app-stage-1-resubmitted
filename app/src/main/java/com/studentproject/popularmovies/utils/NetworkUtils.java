package com.studentproject.popularmovies.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    // TODO: 8/7/2018 Place in User API Key
    private static final String USER_API_KEY = "PLACE_API_KEY_HERE";

    public static final String POPULAR_BASE_URL = "https://api.themoviedb.org/3/movie/popular";
    public static final String TOP_RATED_BASE_URL = "https://api.themoviedb.org/3/movie/top_rated";

    //API parameters
    private static final String API_KEY_PARAM = "api_key";
    private static final String LANGUAGE_PARAM = "language";
    private static final String PAGE_PARAM = "page";

    //Optional values
    private static final int pageNum = 1;
    private static final String languageType = "en-US";

    //Image parameters
    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String IMAGE_SIZE = "w185";

    public static URL buildMoviesUrl(String baseUrl) {

        URL url = null;

        Uri uri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, USER_API_KEY)
                .appendQueryParameter(LANGUAGE_PARAM, languageType)
                .appendQueryParameter(PAGE_PARAM, Integer.toString(pageNum))
                .build();

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.e(TAG, "URL ERROR: " + e.getMessage());
        }

        return url;

    }

    public static Uri buildMoviePosterUri(String imagePath) {

        String editedImagePath = imagePath.substring(1);

        return Uri.parse(IMAGE_BASE_URL).buildUpon()
                .appendPath(IMAGE_SIZE)
                .appendPath(editedImagePath)
                .build();

    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }



}

package com.studentproject.popularmovies;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.studentproject.popularmovies.data.Movie;
import com.studentproject.popularmovies.utils.JsonUtils;
import com.studentproject.popularmovies.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.movies_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    MovieAdapter mPopularMovieAdapter;
    MovieAdapter mTopRatedMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        URL byPopularUrl = NetworkUtils.buildMoviesUrl(NetworkUtils.POPULAR_BASE_URL);
        URL byTopRatedUrl = NetworkUtils.buildMoviesUrl(NetworkUtils.TOP_RATED_BASE_URL);

        if (isNetworkAvailableAndConnected()) {
            MovieAsyncTask movieAsyncTask = new MovieAsyncTask();
            movieAsyncTask.execute(byPopularUrl, byTopRatedUrl);
        } else {
            Toast.makeText(this, "No internet connection found.", Toast.LENGTH_LONG).show();
        }

        showProgressBar();

        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mRecyclerView.setHasFixedSize(true);

    }

    public class MovieAsyncTask extends AsyncTask<URL, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(URL... urls) {

            URL popularUrl = urls[0];
            URL topRatedUrl = urls[1];

            String popularResults;
            String topRatedResults;

            ArrayList<String> queryResults = new ArrayList<>();

            try {
                popularResults = NetworkUtils.getResponseFromHttpUrl(popularUrl);
                topRatedResults = NetworkUtils.getResponseFromHttpUrl(topRatedUrl);

                queryResults.add(popularResults);
                queryResults.add(topRatedResults);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return queryResults;
        }

        @Override
        protected void onPostExecute(ArrayList<String> movieJsonResults) {

            try {
                if (movieJsonResults.size() == 0) {
                    Toast.makeText(MainActivity.this, "No User Api Key found. \n Enter User Api Key in NetworkUtils.class", Toast.LENGTH_LONG).show();
                } else {
                    List<Movie> popularMoviesFromJson = JsonUtils.getMoviesFromJson(movieJsonResults.get(0));
                    List<Movie> topRatedMoviesFromJson = JsonUtils.getMoviesFromJson(movieJsonResults.get(1));

                    mPopularMovieAdapter = new MovieAdapter(popularMoviesFromJson);
                    mTopRatedMovieAdapter = new MovieAdapter(topRatedMoviesFromJson);

                    mRecyclerView.setAdapter(mPopularMovieAdapter);
                    showRecyclerView();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.popular_menu_item:
                mRecyclerView.setAdapter(mPopularMovieAdapter);
                return true;
            case R.id.top_rated_menu_item:
                mRecyclerView.setAdapter(mTopRatedMovieAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    public void showRecyclerView() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public boolean isNetworkAvailableAndConnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable = connectivityManager.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable && connectivityManager.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }

}

package com.studentproject.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.studentproject.popularmovies.data.Movie;
import com.studentproject.popularmovies.utils.NetworkUtils;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    public static final String MOVIE_DETAILS = "movie_details";

    private List<Movie> mMovies;

    public MovieAdapter(List<Movie> moviesData) {
        mMovies = moviesData;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_poster_list_item, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        final Movie movie = mMovies.get(position);

        Uri posterUri = NetworkUtils.buildMoviePosterUri(movie.getPoster());
        Picasso.get().load(posterUri).into(holder.mMoviePosterImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), MovieDetailActivity.class);
                intent.putExtra(MOVIE_DETAILS, movie);
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

}




package com.studentproject.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public final ImageView mMoviePosterImage;

    public MovieViewHolder(View itemView) {
        super(itemView);
        mMoviePosterImage = itemView.findViewById(R.id.iv_movie_poster);
    }


}

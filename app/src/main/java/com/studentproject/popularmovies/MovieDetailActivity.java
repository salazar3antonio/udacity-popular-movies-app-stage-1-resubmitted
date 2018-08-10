package com.studentproject.popularmovies;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studentproject.popularmovies.data.Movie;
import com.studentproject.popularmovies.utils.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie mMovie;
    @BindView(R.id.iv_detail_poster) ImageView mMoviePosterDetail;
    @BindView(R.id.tv_title_detail) TextView mTitle;
    @BindView(R.id.tv_release_date_detail) TextView mReleaseDate;
    @BindView(R.id.tv_vote_average_detail) TextView mVoteAverage;
    @BindView(R.id.tv_plot_detail) TextView mPlotSynopsis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            mMovie = bundle.getParcelable(MovieAdapter.MOVIE_DETAILS);
        }

        Uri posterUri = NetworkUtils.buildMoviePosterUri(mMovie.getPoster());
        Picasso.get().load(posterUri).into(mMoviePosterDetail);

        mTitle.setText(mMovie.getTitle());
        mReleaseDate.setText(mMovie.getReleaseDate());
        mVoteAverage.setText(mMovie.getVoteAverage());
        mPlotSynopsis.setText(mMovie.getPlotSynopsis());


    }
}

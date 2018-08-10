package com.studentproject.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String title;
    private String releaseDate;
    private String poster;
    private String voteAverage;
    private String plotSynopsis;

    public Movie(String title, String releaseDate, String poster, String voteAverage, String plotSynopsis) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.voteAverage = voteAverage;
        this.plotSynopsis = plotSynopsis;
    }

    public Movie(Parcel in) {

        title = in.readString();
        releaseDate = in.readString();
        poster = in.readString();
        voteAverage = in.readString();
        plotSynopsis = in.readString();

    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(title);
        parcel.writeString(releaseDate);
        parcel.writeString(poster);
        parcel.writeString(voteAverage);
        parcel.writeString(plotSynopsis);

    }
}

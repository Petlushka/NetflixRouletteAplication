package com.example.geekteam.netflixrouletteaplication.movieDeatils;


import android.support.v7.widget.Toolbar;

import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.List;

interface MovieDetailContract {

    interface DetailVew {

        void showMovieDetail();

    }

    interface DetailPresenter {

        List<Movie> getAllMovies();

        Movie getCurrentMovie(int position);

        void setTitle(Toolbar toolbar);

    }
}

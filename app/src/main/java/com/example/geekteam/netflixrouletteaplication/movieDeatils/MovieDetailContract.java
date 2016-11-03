package com.example.geekteam.netflixrouletteaplication.movieDeatils;


import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.List;

public interface MovieDetailContract {

    interface DetailVew {

        void showMovieDetail();

    }

    interface DetailPresenter {

        int currentMoviePosition();

        List<Movie> getAllMovies();

        Movie getCurrentMovie(int position);

    }
}

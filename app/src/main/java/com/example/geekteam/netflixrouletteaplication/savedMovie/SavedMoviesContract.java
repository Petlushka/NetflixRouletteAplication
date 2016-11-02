package com.example.geekteam.netflixrouletteaplication.savedMovie;

import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.List;


interface SavedMoviesContract {

    interface View{

        void showSavedMovies();

    }

    interface Presenter{

        List<Movie> loadSavedMovies();

        void openMovieDetail(int position);

    }
}

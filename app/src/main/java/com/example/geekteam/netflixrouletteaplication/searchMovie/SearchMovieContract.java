package com.example.geekteam.netflixrouletteaplication.searchMovie;

import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.List;

interface SearchMovieContract {

    interface SearchView{

        void showSearchMovies(List<Movie> data);

    }

    interface SearchPresenter {

        void searchMovies(String query);

        void openMovieDetail(int position);

    }
}

package com.example.geekteam.netflixrouletteaplication.searchMovie;

import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.ArrayList;
import java.util.List;

interface SearchMovieContract {

    interface SearchView{

        void showSearchMovies(List<Movie> data);

        void showProgressbar(boolean show);

    }

    interface SearchPresenter {

        void searchMovies(String query);

        void openMovieDetail(int position);

    }
}

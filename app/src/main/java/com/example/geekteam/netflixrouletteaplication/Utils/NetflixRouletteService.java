package com.example.geekteam.netflixrouletteaplication.Utils;

import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NetflixRouletteService {

    @GET("api.php")
    Call<Movie> getMovieByTitle(@Query("title") String title);

    @GET("api.php")
    Call<ArrayList<Movie>> getMovieByDirector(@Query("director") String director);
}

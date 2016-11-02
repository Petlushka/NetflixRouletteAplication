package com.example.geekteam.netflixrouletteaplication.Utils;

import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Леша Иванов on 01.11.2016.
 */

public interface NetflixRouletteService {

    @GET("api.php")
    Call<Movie> getMovieByTitle(@Query("title") String title);

    @GET("api.php")
    Call<List<Movie>> getMovieByDirector(@Query("director") String director);
}

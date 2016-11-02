package com.example.geekteam.netflixrouletteaplication.savedMovie;

import android.content.Context;
import android.content.Intent;

import com.example.geekteam.netflixrouletteaplication.data.Movie;
import com.example.geekteam.netflixrouletteaplication.movieDeatils.MovieDetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;


public class SavedMoviesPresenter implements SavedMoviesContract.Presenter {

    private Realm realm;
    private List<Movie> data = new ArrayList<>();
    private Context context;

    public SavedMoviesPresenter(Context context){
        realm = Realm.getDefaultInstance();
        this.context = context;
    }


    @Override
    public List<Movie> loadSavedMovies() {
        data = realm.where(Movie.class).findAll();
        return data;
    }

    @Override
    public void openMovieDetail(int position) {
        ArrayList<Movie> tmp = new ArrayList<>();
        for(Movie item: data){
            tmp.add(item);
        }
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("save", true);
        intent.putExtra("position", position);
        context.startActivity(intent);

    }
}

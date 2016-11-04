package com.example.geekteam.netflixrouletteaplication.savedMovie;

import android.content.Context;
import android.content.Intent;

import com.example.geekteam.netflixrouletteaplication.data.Movie;
import com.example.geekteam.netflixrouletteaplication.movieDeatils.MovieDetailActivity;

import java.util.List;

import io.realm.Realm;


class SavedMoviesPresenter implements SavedMoviesContract.Presenter {

    private Realm realm;
    private Context context;

    SavedMoviesPresenter(Context context){
        realm = Realm.getDefaultInstance();
        this.context = context;
    }


    @Override
    public List<Movie> loadSavedMovies() {
        return realm.where(Movie.class).findAll();
    }

    @Override
    public void openMovieDetail(int position) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("save", true);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }
}

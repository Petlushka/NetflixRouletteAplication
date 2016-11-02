package com.example.geekteam.netflixrouletteaplication.movieDeatils;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


class MoviePresenter implements MovieDetailContract.DetailPresenter {

    private List<Movie> data = new ArrayList<>();
    private int position;
    private boolean save;
    private Realm realm;

    MoviePresenter(Intent intent){
        data = (List<Movie>)intent.getSerializableExtra("data");
        position = intent.getIntExtra("position", -1);
        save = intent.getBooleanExtra("save", false);
        realm = Realm.getDefaultInstance();
    }


    @Override
    public int currentMoviePosition() {
        return position;
    }

    @Override
    public List<Movie> getAllMovies() {
        if(save)
            data = realm.where(Movie.class).findAll();
        return data;
    }

    @Override
    public Movie getCurrentMovie(int index) {
        return data.get(index);
    }

    public void setTitle(Toolbar toolbar){
        toolbar.setTitle(getCurrentMovie(position).getShowTitle());
    }

    public boolean isSave(){
        int id =  getCurrentMovie(position).getShowId();

        Movie tmp = realm.where(Movie.class).equalTo("showId",id).findFirst();
        return tmp != null;
    }

    public void setPosition(int position){
        this.position = position;
    }
}

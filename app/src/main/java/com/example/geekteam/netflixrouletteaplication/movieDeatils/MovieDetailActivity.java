package com.example.geekteam.netflixrouletteaplication.movieDeatils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.geekteam.netflixrouletteaplication.R;
import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MovieDetailActivity extends AppCompatActivity {

    MoviePresenter presenter;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;
    MoviePagerAdapter adapter;
    Realm realm;
    Movie currentMovie;
    boolean save = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        Intent intent = getIntent();
        presenter = new MoviePresenter(intent);
        adapter = new MoviePagerAdapter(getSupportFragmentManager(), presenter.getAllMovies());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(presenter.currentMoviePosition());
        currentMovie = presenter.getCurrentMovie(viewPager.getCurrentItem());
        presenter.setTitle(toolbar);
        save = presenter.isSave();
        invalidateOptionsMenu();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                presenter.setPosition(viewPager.getCurrentItem());
                presenter.setTitle(toolbar);
                save = presenter.isSave();
                Log.d("MyLogs", "" + save);
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_save:
                realm.beginTransaction();
                Movie movie = presenter.getCurrentMovie(viewPager.getCurrentItem());
                realm.copyToRealmOrUpdate(movie);
                realm.commitTransaction();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_save);
        if(save){
            menuItem.setVisible(false);
        } else {
            menuItem.setVisible(true);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}

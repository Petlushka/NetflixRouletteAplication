package com.example.geekteam.netflixrouletteaplication.movieDeatils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.geekteam.netflixrouletteaplication.R;
import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MovieDetailActivity extends AppCompatActivity {

    private MoviePresenter mPresenter;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;
    private MoviePagerAdapter mAdapter;
    private Realm mRealm;
    private boolean mSave = false;
    private int mPosition;
    private ArrayList<Movie> mData;
    private boolean mSavedMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Realm.init(this);
        mRealm = Realm.getDefaultInstance();
        if(savedInstanceState == null) {
            Intent intent = getIntent();
            mData = (ArrayList<Movie>) intent.getSerializableExtra("mData");
            mPosition =  intent.getIntExtra("mPosition", -1);
            mSavedMovie = intent.getBooleanExtra("mSave", false);
        } else {
            mSavedMovie = savedInstanceState.getBoolean("mSave");
            mPosition = savedInstanceState.getInt("mPosition");
            mData = (ArrayList<Movie>)savedInstanceState.getSerializable("mData");
        }
        mPresenter = new MoviePresenter(mData, mPosition, mSavedMovie);
        mAdapter = new MoviePagerAdapter(getSupportFragmentManager(), mPresenter.getAllMovies());
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(mPosition);
        mPresenter.setTitle(toolbar);
        mSave = mPresenter.isSave();
        invalidateOptionsMenu();
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPresenter.setPosition(viewPager.getCurrentItem());
                mPresenter.setTitle(toolbar);
                mSave = mPresenter.isSave();
                Log.d("MyLogs", "" + mSave);
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
                mRealm.beginTransaction();
                Movie movie = mPresenter.getCurrentMovie(viewPager.getCurrentItem());
                mRealm.copyToRealmOrUpdate(movie);
                mRealm.commitTransaction();
                mSave = mPresenter.isSave();
                invalidateOptionsMenu();
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
        if(mSave){
            menuItem.setTitle("SAVED");
        } else {
            menuItem.setTitle("SAVE");
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mPosition", viewPager.getCurrentItem());
        outState.putBoolean("mSave", mSavedMovie);
        outState.putSerializable("mData", mData);
    }
}

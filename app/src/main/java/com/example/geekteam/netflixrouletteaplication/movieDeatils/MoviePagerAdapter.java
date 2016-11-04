package com.example.geekteam.netflixrouletteaplication.movieDeatils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.List;


class MoviePagerAdapter extends FragmentPagerAdapter {

    private List<Movie> data;

    MoviePagerAdapter(FragmentManager fm, List<Movie> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Fragment getItem(int position) {
        MovieFragment fragment = new MovieFragment();
        fragment.setCurrentMovie(data.get(position));
        return fragment;
    }


}

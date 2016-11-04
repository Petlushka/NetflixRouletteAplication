package com.example.geekteam.netflixrouletteaplication.savedMovie;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.geekteam.netflixrouletteaplication.MoviesAdapter;
import com.example.geekteam.netflixrouletteaplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SavedMovieFragment extends Fragment implements SavedMoviesContract.View {


    @BindView(R.id.gvSavedMovies)
    GridView mSavedMovies;
    private SavedMoviesPresenter mMoviePresenter;


    public SavedMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        ButterKnife.bind(this, view);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mSavedMovies.setNumColumns(2);
        }

        mMoviePresenter = new SavedMoviesPresenter(getActivity());
        showSavedMovies();
        return view;
    }

    @Override
    public void showSavedMovies() {
        MoviesAdapter mMoviesAdapter = new MoviesAdapter(getActivity(), mMoviePresenter.loadSavedMovies());
        mSavedMovies.setAdapter(mMoviesAdapter);
        mSavedMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mMoviePresenter.openMovieDetail(i);
            }
        });
    }
}

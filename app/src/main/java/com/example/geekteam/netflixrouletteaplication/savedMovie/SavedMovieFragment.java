package com.example.geekteam.netflixrouletteaplication.savedMovie;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.geekteam.netflixrouletteaplication.MoviesAdapter;
import com.example.geekteam.netflixrouletteaplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedMovieFragment extends Fragment implements SavedMoviesContract.View {

    private final static int SEARCH_BY_TITLE = 1;
    private final static int SEARCH_BY_DIRECTOR = 2;
    @BindView(R.id.gvSavedMovies)
    GridView mSavedMovies;
    private Realm realm;
    private SavedMoviesPresenter mMoviePresenter;
    private MoviesAdapter mMoviesAdapter;


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
        mMoviesAdapter = new MoviesAdapter(getActivity(), mMoviePresenter.loadSavedMovies());
        mSavedMovies.setAdapter(mMoviesAdapter);
        mSavedMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mMoviePresenter.openMovieDetail(i);
            }
        });
    }
}

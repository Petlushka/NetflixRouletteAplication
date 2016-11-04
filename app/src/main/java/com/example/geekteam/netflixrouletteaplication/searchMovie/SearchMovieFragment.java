package com.example.geekteam.netflixrouletteaplication.searchMovie;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.geekteam.netflixrouletteaplication.MoviesAdapter;
import com.example.geekteam.netflixrouletteaplication.R;
import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMovieFragment extends Fragment implements SearchMovieContract.SearchView{

    private int type = 0;
    @BindView(R.id.gvSearchResult)
    GridView mSearchResult;
    @BindView(R.id.etSearch)
    EditText mSearchField;
    @BindView(R.id.pbSearch)
    ProgressBar progressBar;
    SearchMoviePresenter presenter;
    MoviesAdapter mMoviesAdapter;
    String query = null;



    public SearchMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_search_movie, container, false);
        ButterKnife.bind(this, view);


        presenter = new SearchMoviePresenter(getActivity(), type, this);
        if(query != null)
            mSearchField.setText(query);

        Log.d("myLogs", "onCreateView query - " + query + ", type - " + type);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mSearchResult.setNumColumns(2);
        }

        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = mSearchField.getText().toString().toLowerCase(Locale.getDefault());
                presenter.searchMovies(text);
            }
        });
        return view;
    }

    @Override
    public void showSearchMovies(List<Movie> data) {
        mMoviesAdapter = new MoviesAdapter(getActivity(), data);
        mSearchResult.setAdapter(mMoviesAdapter);
        mSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.openMovieDetail(i);
            }
        });

    }



    public void setType(int type) {
        this.type = type;
    }

}

package com.example.geekteam.netflixrouletteaplication.movieDeatils;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geekteam.netflixrouletteaplication.R;
import com.example.geekteam.netflixrouletteaplication.data.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieDetailContract.DetailVew {

    Movie currentMovie;
    @BindView(R.id.ivPosterDetailMovie)
    ImageView mPoster;
    @BindView(R.id.tvReleaseDetailMovie)
    TextView mRelease;
    @BindView(R.id.tvRatingDetailMovie)
    TextView mRating;
    @BindView(R.id.tvDirectorDetailMovie)
    TextView mDirector;
    @BindView(R.id.tvSummaryDetailMovie)
    TextView mSummary;


    public MovieFragment() {
        // Required empty public constructor
    }

    public void setCurrentMovie(Movie currentMovie) {
        this.currentMovie = currentMovie;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_movie_detail, container, false);
        ButterKnife.bind(this, view);
        showMovieDetail();
        return view;
    }

    @Override
    public void showMovieDetail() {
        if(currentMovie.getPoster() != null){
            Picasso.with(getActivity()).load(currentMovie.getPoster()).into(mPoster);
        }
        mRelease.setText(currentMovie.getReleaseYear());
        mDirector.setText(currentMovie.getDirector());
        mRating.setText(currentMovie.getRating());
        mSummary.setText(currentMovie.getSummary());

    }
}

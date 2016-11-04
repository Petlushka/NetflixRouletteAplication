package com.example.geekteam.netflixrouletteaplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geekteam.netflixrouletteaplication.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends BaseAdapter {

    private List<Movie> data;
    private Context context;
    @BindView(R.id.ivItemMovie)
    ImageView mPoster;
    @BindView(R.id.tvItemCategory)
    TextView mCategory;
    @BindView(R.id.tvItemDirector)
    TextView mDirector;
    @BindView(R.id.tvItemTitle)
    TextView mTitle;
    @BindView(R.id.tvItemRating)
    TextView mRating;
    @BindView(R.id.tvItemRelease)
    TextView mRelease;

    public MoviesAdapter(Context context, List<Movie> data){
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View currentView;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            currentView = inflater.inflate(R.layout.item_saved_movie, null);
        } else {
            currentView = view;
        }
        ButterKnife.bind(this, currentView);
        Movie movie = data.get(i);
        if(movie.getPoster() != null){
            Picasso.with(context).load(movie.getPoster()).into(mPoster);
        }
        mCategory.setText(movie.getCategory());
        mDirector.setText(movie.getDirector());
        mTitle.setText(movie.getShowTitle());
        mRating.setText(movie.getRating());
        mRelease.setText(movie.getReleaseYear());
        return currentView;
    }

    public void setData(List<Movie> data){
        this.data = data;
    }

}

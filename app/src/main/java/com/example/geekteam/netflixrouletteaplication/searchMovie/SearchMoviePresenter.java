package com.example.geekteam.netflixrouletteaplication.searchMovie;

import android.content.Context;
import android.content.Intent;

import com.example.geekteam.netflixrouletteaplication.Utils.NetflixRouletteService;
import com.example.geekteam.netflixrouletteaplication.data.Movie;
import com.example.geekteam.netflixrouletteaplication.movieDeatils.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchMoviePresenter implements SearchMovieContract.SearchPresenter {

    private final static int SEARCH_BY_TITLE = 1;
    private final static int SEARCH_BY_DIRECTOR = 2;

    private List<Movie> data = new ArrayList<>();
    private Context context;
    private NetflixRouletteService service;
    private int type;
    private SearchMovieContract.SearchView searchView;

    SearchMoviePresenter(Context context, int type, SearchMovieContract.SearchView searchView){
        this.context = context;
        this.searchView = searchView;
        this.type = type;
        getService();
    }

    @Override
    public void searchMovies(String query) {
    //    searchView.showProgressbar(true);
        switch (type){
            case SEARCH_BY_DIRECTOR:
                Call<List<Movie>> call = service.getMovieByDirector(query);
                call.enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        data = response.body();
                        //      searchView.showProgressbar(false);
                        if(data != null) {
                            searchView.showSearchMovies(data);
                        } else {
                            data = new ArrayList<Movie>();
                            searchView.showSearchMovies(data);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {

                    }
                });
                break;
            case SEARCH_BY_TITLE:
                Call<Movie> callTitle = service.getMovieByTitle(query);
                callTitle.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Movie tmp = response.body();
                        if(tmp != null) {
                            data.add(tmp);
                            searchView.showSearchMovies(data);
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }
                });
                break;
        }

    }

    @Override
    public void openMovieDetail(int position) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("data", (ArrayList<Movie>)data);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    public void getService(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://netflixroulette.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        service = retrofit.create(NetflixRouletteService.class);

    }
}

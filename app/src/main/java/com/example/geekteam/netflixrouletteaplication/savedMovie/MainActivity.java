package com.example.geekteam.netflixrouletteaplication.savedMovie;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import com.example.geekteam.netflixrouletteaplication.R;
import com.example.geekteam.netflixrouletteaplication.searchMovie.SearchMovieFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private final static int SEARCH_BY_TITLE = 1;
    private final static int SEARCH_BY_DIRECTOR = 2;
    private final static int SAVED_MOVIE = 0;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    FragmentManager fm;
    Realm realm;
    int currentType;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        fm = getSupportFragmentManager();
        if(savedInstanceState != null){
            currentType = savedInstanceState.getInt("fragment");
            fragment = fm.getFragment(savedInstanceState, "myFragment");
        }
        Log.d("MyLogs", "create fragment - " + currentType);
        if(fragment == null) {
            chooseFragment(currentType);
        }
        chooseTitleToolbar(currentType);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.ic_videocam);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (id){
            case R.id.saved_movies:
                SavedMovieFragment savedMovieFragment = new SavedMovieFragment();
                transaction.replace(R.id.container_main_screen, savedMovieFragment);
                currentType = SAVED_MOVIE;
                toolbar.setTitle("Saved movies");
                fragment = savedMovieFragment;
                break;
            case R.id.nav_search_title:
                SearchMovieFragment searchTitleFragment = new SearchMovieFragment();
                searchTitleFragment.setType(SEARCH_BY_TITLE);
                transaction.replace(R.id.container_main_screen, searchTitleFragment);
                currentType = SEARCH_BY_TITLE;
                toolbar.setTitle("Find movie");
                fragment = searchTitleFragment;
                break;
            case R.id.nav_search_director:
                SearchMovieFragment searchDirectorFragment = new SearchMovieFragment();
                searchDirectorFragment.setType(SEARCH_BY_DIRECTOR);
                transaction.replace(R.id.container_main_screen, searchDirectorFragment);
                currentType = SEARCH_BY_DIRECTOR;
                toolbar.setTitle("Find movies");
                fragment = searchDirectorFragment;
                break;
        }
        transaction.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("fragment", currentType);
        fm.putFragment(outState,"myFragment",fragment);
    }

    public void chooseFragment(int type){
        FragmentTransaction transaction = fm.beginTransaction();
        switch (type){
            case SAVED_MOVIE:
                SavedMovieFragment savedFragment = new SavedMovieFragment();
                transaction.add(R.id.container_main_screen, savedFragment);
                fragment = savedFragment;
                break;
            case SEARCH_BY_TITLE:
                SearchMovieFragment fragmentTitle = new SearchMovieFragment();
                fragmentTitle.setType(SEARCH_BY_TITLE);
                transaction.add(R.id.container_main_screen, fragmentTitle);
                fragment = fragmentTitle;
                break;
            case SEARCH_BY_DIRECTOR:
                SearchMovieFragment fragmentDirector = new SearchMovieFragment();
                fragmentDirector.setType(SEARCH_BY_DIRECTOR);
                transaction.add(R.id.container_main_screen, fragmentDirector);
                fragment = fragmentDirector;
                break;
        }
        transaction.commit();
    }

    public void chooseTitleToolbar(int type){
        switch (type){
            case SAVED_MOVIE:
                toolbar.setTitle(getString(R.string.saved_movies));
                break;
            case SEARCH_BY_TITLE:
                toolbar.setTitle("Find movie");
                break;
            case SEARCH_BY_DIRECTOR:
                toolbar.setTitle("Find movies");
                break;
        }

    }
}

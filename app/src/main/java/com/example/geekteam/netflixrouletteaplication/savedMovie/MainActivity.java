package com.example.geekteam.netflixrouletteaplication.savedMovie;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    FragmentManager fm;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        toolbar.setTitle(R.string.saved_movies);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.ic_videocam);
        navigationView.setNavigationItemSelectedListener(this);
        SavedMovieFragment fragment = new SavedMovieFragment();
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.container_main_screen, fragment);
        transaction.commit();
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

                toolbar.setTitle("Saved movies");
                break;
            case R.id.nav_search_title:
                SearchMovieFragment searchTitleFragment = new SearchMovieFragment();
                searchTitleFragment.setType(SEARCH_BY_TITLE);
                transaction.replace(R.id.container_main_screen, searchTitleFragment);
                toolbar.setTitle("Find movie");
                break;
            case R.id.nav_search_director:
                SearchMovieFragment searchDirectorFragment = new SearchMovieFragment();
                searchDirectorFragment.setType(SEARCH_BY_DIRECTOR);
                transaction.replace(R.id.container_main_screen, searchDirectorFragment);

                toolbar.setTitle("Find movies");
                break;
        }
        transaction.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

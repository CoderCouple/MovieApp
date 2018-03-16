package com.android.movieapp.module.movie.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.android.movieapp.R;
import com.android.movieapp.injection.Injector;
import com.android.movieapp.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends BaseActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.popular:
                    //mTextMessage.setText(R.string.popular);
                    loadPopularMovieFragment();
                    return true;
                case R.id.up_coming:
                    //mTextMessage.setText(R.string.upComing);
                    loadUpComingMovieFragment();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        Injector.component().inject(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadPopularMovieFragment();
    }

    public void loadPopularMovieFragment() {
        FragmentManager manager = getSupportFragmentManager();
        PopularFragment popularFragment = (PopularFragment) getSupportFragmentManager().findFragmentByTag("popular");
        if (popularFragment == null) {
            popularFragment = new PopularFragment();
            manager.beginTransaction().add(R.id.fragment, popularFragment, "popular").commit();
        }

        manager.beginTransaction().show(popularFragment).commit();
        UpComingFragment upComingFragment = (UpComingFragment) getSupportFragmentManager().findFragmentByTag("upComing");

        if (upComingFragment != null)
            manager.beginTransaction().hide(upComingFragment).commit();
    }

    public void loadUpComingMovieFragment() {
        FragmentManager manager = getSupportFragmentManager();
        UpComingFragment upComingFragment = (UpComingFragment) getSupportFragmentManager().findFragmentByTag("upComing");
        if (upComingFragment == null) {
            upComingFragment = new UpComingFragment();
            manager.beginTransaction().add(R.id.fragment, upComingFragment, "upComing").commit();
        }
        manager.beginTransaction().show(upComingFragment).commit();

        PopularFragment popularFragment = (PopularFragment) getSupportFragmentManager().findFragmentByTag("popular");

        if (popularFragment != null)
            manager.beginTransaction().hide(popularFragment).commit();
    }

}

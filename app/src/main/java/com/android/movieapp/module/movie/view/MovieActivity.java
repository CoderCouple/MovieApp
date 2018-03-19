package com.android.movieapp.module.movie.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.android.movieapp.R;
import com.android.movieapp.injection.Injector;
import com.android.movieapp.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main Activity show popular and upComing movie fragment.
 */
public class MovieActivity extends BaseActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    // Listener to load fragments.
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.popular:
                    loadPopularMovieFragment();
                    return true;
                case R.id.up_coming:
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
        // Loads Popular fragment in container.
        FragmentManager manager = getSupportFragmentManager();
        PopularFragment popularFragment = (PopularFragment) getSupportFragmentManager().findFragmentByTag("popular");
        if (popularFragment == null) {
            popularFragment = new PopularFragment();
            manager.beginTransaction().add(R.id.fragment, popularFragment, "popular").commit();
        }

        manager.beginTransaction().show(popularFragment).commit();

        // Hide Upcoming fragment if Popular fragment is loaded
        UpComingFragment upComingFragment = (UpComingFragment) getSupportFragmentManager().findFragmentByTag("upComing");

        if (upComingFragment != null)
            manager.beginTransaction().hide(upComingFragment).commit();
    }

    public void loadUpComingMovieFragment() {
        // Loads Upcoming fragment in container.
        FragmentManager manager = getSupportFragmentManager();
        UpComingFragment upComingFragment = (UpComingFragment) getSupportFragmentManager().findFragmentByTag("upComing");
        if (upComingFragment == null) {
            upComingFragment = new UpComingFragment();
            manager.beginTransaction().add(R.id.fragment, upComingFragment, "upComing").commit();
        }
        manager.beginTransaction().show(upComingFragment).commit();

        // Hide Popular fragment if Upcoming fragment is loaded
        PopularFragment popularFragment = (PopularFragment) getSupportFragmentManager().findFragmentByTag("popular");

        if (popularFragment != null)
            manager.beginTransaction().hide(popularFragment).commit();
    }

    /**
     * Show and hide tab layout view on scroll.
     * @param view
     */
    void toggleTabView(int view) {
        navigation.setVisibility(view);
    }

}

package com.android.movieapp.injection.component;

import com.android.movieapp.injection.module.ApiModule;
import com.android.movieapp.injection.module.AppModule;
import com.android.movieapp.injection.module.CommonModule;
import com.android.movieapp.injection.module.MovieModule;
import com.android.movieapp.module.common.util.Bakery;
import com.android.movieapp.module.common.util.ConnectivityUtil;
import com.android.movieapp.module.common.util.PreferenceUtil;
import com.android.movieapp.module.movie.presenter.MoviePresenterImpl;
import com.android.movieapp.module.movie.util.NetworkChangeReceiver;
import com.android.movieapp.module.movie.view.MovieActivity;
import com.android.movieapp.module.movie.view.PopularFragment;
import com.android.movieapp.module.movie.view.UpComingFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Aaditya Deowanshi
 */
@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class,
        CommonModule.class,
        MovieModule.class,
})
public interface AppComponent {

    // common module
    void inject(PreferenceUtil preferenceUtil);
    void inject(Bakery bakery);
    void inject(ConnectivityUtil connectivityUtil);


    // movie module
    void inject(MoviePresenterImpl moviePresenter);
    void inject(MovieActivity movieActivity);
    void inject(PopularFragment popularFragment);
    void inject(UpComingFragment upComingFragment);
    void inject(NetworkChangeReceiver networkChangeReceiver);


}

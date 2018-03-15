package com.android.movieapp.injection.component;

import com.android.movieapp.injection.module.ApiModule;
import com.android.movieapp.injection.module.AppModule;
import com.android.movieapp.injection.module.CommonModule;
import com.android.movieapp.injection.module.ReviewModule;
import com.android.movieapp.module.common.util.Bakery;
import com.android.movieapp.module.common.util.ConnectivityUtil;
import com.android.movieapp.module.common.util.PreferenceUtil;
import com.android.movieapp.module.movie.presenter.ReviewPresenterImpl;

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
        ReviewModule.class,
})
public interface AppComponent {

    // common module
    void inject(PreferenceUtil preferenceUtil);
    void inject(Bakery bakery);
    void inject(ConnectivityUtil connectivityUtil);


    // task module
    void inject(ReviewPresenterImpl reviewPresenter);

}

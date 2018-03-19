package com.android.movieapp.injection.module;

import com.android.movieapp.module.common.util.Bakery;
import com.android.movieapp.module.common.util.ConnectivityUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides all common module dependencies.
 *
 * @author Aaditya Deowanshi
 */
@Module
public class CommonModule {

    @Provides
    @Singleton
    public Bakery provideBakery() {
        return new Bakery();
    }

    @Provides
    @Singleton
    public ConnectivityUtil provideConnectivityUtil() {
        return new ConnectivityUtil();
    }

}

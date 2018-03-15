package com.android.movieapp.injection;

import android.app.Application;

import com.android.movieapp.injection.component.AppComponent;
import com.android.movieapp.injection.component.DaggerAppComponent;
import com.android.movieapp.injection.module.AppModule;


/**
 * A singleton class, which wraps building and providing of dagger component.
 *
 * @author Aaditya Deowanshi
 */
public class Injector {

    // Singleton instance
    private static Injector instance;

    // components
    private AppComponent appComponent;

    // Private constructor to make it singleton
    private Injector() {
    }

    /**
     * Get the singleton Injector instance
     *
     * @return Injector
     */
    private static Injector instance() {
        if (instance == null) {
            instance = new Injector();
        }
        
        return  instance;
    }

    /**
     * Creates application component which used of injection later.
     *
     * @param application
     */
    public static void createApplicationComponent(Application application) {
        if (instance().appComponent == null) {
            instance().appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(application))
                    .build();
        }
    }

    /**
     * Returns the component for injection.
     *
     * @return
     */
    public static AppComponent component() {
        return instance().appComponent;
    }

}

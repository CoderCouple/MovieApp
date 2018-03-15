package com.android.movieapp.module.base;

/**
 * @author Aaditya Deowanshi
 */
public interface Presenter<T extends ViewInteractor> {

    void attachViewInteractor(T viewInteractor);

    void detachViewInteractor();

}

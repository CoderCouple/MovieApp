package com.android.movieapp.data.api;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;


/**
 * Wrapper for rx.Observer with default implementation for onCompleted & onError to work with Retrofit 2.
 *
 * @author Aaditya Deowanshi
 */
public abstract class ApiObserver<T extends Response> implements Observer<T> {

    /**
     * Publish result to observer.
     *
     * @param response
     */
    public abstract void onResponse(T response);

    @Override
    public void onComplete() {
        // Default implementation, can be override accordingly.
    }

    @Override
    public void onNext(T result) {
        onResponse(result);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        d.dispose();
    }

}


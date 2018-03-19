package com.android.movieapp.module.movie.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.movieapp.injection.Injector;
import com.android.movieapp.module.common.util.Bakery;
import com.android.movieapp.module.common.util.ConnectivityUtil;

import javax.inject.Inject;

/**
 * Created by aaditya on 3/18/18.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Inject
    ConnectivityUtil connectivityUtil;
    @Inject
    Bakery bakery;

    public NetworkChangeReceiver() {
        Injector.component().inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if ( !connectivityUtil.isConnected())
            bakery.toast("Not Connected", Toast.LENGTH_LONG);
    }
}


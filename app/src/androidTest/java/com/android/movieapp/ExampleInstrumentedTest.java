package com.android.movieapp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.movieapp.data.api.MovieDbService;
import com.android.movieapp.injection.Injector;
import com.android.movieapp.module.movie.view.MovieActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import static android.support.test.espresso.action.ViewActions.click;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();


        assertEquals("com.android.movieapp", appContext.getPackageName());
    }


    @Test
    public void test_service() throws InterruptedException {
        Assert.assertNotNull(Config.APP_KEY);
    }

}

package com.udacity.gradle.builditbigger;

import android.support.annotation.Nullable;


import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.fail;


public class EndpointsAsyncTaskTestUnit implements OnRetriveJokeListiner {

    private String testJoke = null;
    private CountDownLatch latch;


    @Override
    public void OnRetrieveStarted() {

    }

    @Test
    public void AsyncTaskTest() {
        try {
            latch = new CountDownLatch(1);
            EndpointsAsyncTask.getInstance(this);
            latch.await(15, TimeUnit.SECONDS);
            assertFalse("Empty joke string", testJoke.isEmpty());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    public void OnRetrieveFinished(@Nullable String result) {
        testJoke = result;
        latch.countDown();
    }
}



package com.udacity.gradle.builditbigger;

import android.support.annotation.Nullable;

import com.udacity.gradle.builditbigger.JokeAsyncTask;
import com.udacity.gradle.builditbigger.OnRetrieveJokeListener;

import org.junit.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.fail;


class EndpointsAsyncTaskTest implements OnRetrieveJokeListener {

   private String testJoke = null;
    private CountDownLatch latch;

    @Override
    public void onRetrieveStarted() {
       // do nothing
    }

    @Test
    public void EndpointsAsyncTaskTest() {
        try {
            latch = new CountDownLatch(1);
            EndpointsAsyncTaskTest.getInstance(this);
            latch.await(15, TimeUnit.SECONDS);
            // assert that joke is not empty
            assertFalse("Empty joke string", testJoke.isEmpty());
        } catch (Exception e) {
            // get error message
            fail(e.getMessage());
        }
    }

    @Override
    public void onRetrieveFinished(@Nullable String result) {
        // when retrieving is finished
        testJoke = result;
        latch.countDown();
   }

}


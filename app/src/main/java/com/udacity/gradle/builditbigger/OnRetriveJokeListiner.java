package com.udacity.gradle.builditbigger;

import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

public interface OnRetriveJokeListiner {

    @UiThread
    void OnRetrieveStarted();

    @UiThread
    void OnRetrieveFinished(@Nullable String result);
}

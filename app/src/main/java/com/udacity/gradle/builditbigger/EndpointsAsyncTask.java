package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import com.movieapp.konwo.library.JokesActivity;

import java.io.IOException;


/**
 * A placeholder fragment containing a simple view.
 */
class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {

    private static final String TAG = EndpointsAsyncTask.class.getSimpleName();
    public static MyApi myApiService = null;
    private Context context;

    private OnRetriveJokeListiner listener;

    private  EndpointsAsyncTask(OnRetriveJokeListiner mListener) {
        listener = mListener;
    }

    public static void getInstance(OnRetriveJokeListiner listener) {
        new EndpointsAsyncTask(listener).execute();
    }

    @Override
    protected  void onPreExecute() {
        super.onPreExecute();
        listener.OnRetrieveStarted();
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }


        try {
            return myApiService.getRandomJokeService().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }

    }


    @Override
    protected void onPostExecute(String result) {
        listener.OnRetrieveFinished(result);
    }


}

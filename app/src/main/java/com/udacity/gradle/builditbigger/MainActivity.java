package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.movieapp.konwo.jokeslib.MyCoolJokes;
import com.movieapp.konwo.library.JokesActivity;


public class MainActivity extends AppCompatActivity {

    private ContentLoadingProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //retrieve joke from endpoint
    private void tellJoke() {
        EndpointsAsyncTask.getInstance(new OnRetriveJokeListiner() {
            @Override
            public void OnRetrieveStarted() {
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void OnRetrieveFinished(@Nullable String result) {
                mProgressBar.setVisibility(View.GONE);

            }
        });
    }

    //launch joke endponts
    private void startJoke(String joke) {
        Intent intent = new Intent(this, JokesActivity.class);
        intent.putExtra(JokesActivity.JOKE_KEY, joke);
        startActivity(intent);
    }

}

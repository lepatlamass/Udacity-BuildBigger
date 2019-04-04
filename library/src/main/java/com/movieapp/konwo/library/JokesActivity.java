package com.movieapp.konwo.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JokesActivity extends AppCompatActivity {
    public static final String JOKE_KEY = "jokes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container,new JokesActivityFragment()).commit();
        }
    }
}

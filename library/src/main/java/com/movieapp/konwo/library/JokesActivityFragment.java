package com.movieapp.konwo.library;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokesActivityFragment extends Fragment {


    public JokesActivityFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_jokes_activity, container, false);
        Intent intent = Objects.requireNonNull(getActivity()).getIntent();


        // Inflate the layout for this fragment
        String joke = intent.getStringExtra(JokesActivity.JOKE_KEY);
        TextView gce_result_show = root.findViewById(R.id.jokes_text_view);
        gce_result_show.setText(joke);

        return root;
    }

}

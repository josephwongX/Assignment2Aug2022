package com.example.assignment2aug2022;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.*;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FirstFragment extends Fragment {
    FloatingActionButton fab;
    public FirstFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_first,container,false);
        fab=(FloatingActionButton) view.findViewById(R.id.fab01);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Ho Ho Ho",Snackbar.LENGTH_LONG).setAction("Action",null).show();
            }
        });
        fab.show();
        return view;
    }
}
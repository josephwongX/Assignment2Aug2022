package com.example.assignment2aug2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThirdFragment extends Fragment {
    public ThirdFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_third, container, false);
        getActivity().setContentView(R.layout.fragment_third);
        SupportMapFragment mapFragment= SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.container,mapFragment).commit();
        return view;
    }
}

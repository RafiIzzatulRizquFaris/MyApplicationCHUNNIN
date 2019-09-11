package com.toughput.myapplicationchunnin.GenresFragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toughput.myapplicationchunnin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RomanceFragment extends Fragment {


    public RomanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_romance, container, false);
        return v;
    }

}

package com.bangladesh.tourism.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bangladesh.tourism.R;

/**
 * Created by hhsonet on 1/13/2016.
 */
public class TwoFragment extends Fragment {

    // Required empty public constructor


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_two, container, false);
        if (v != null) {


        }
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void onResume() {
        super.onResume();

    }


}


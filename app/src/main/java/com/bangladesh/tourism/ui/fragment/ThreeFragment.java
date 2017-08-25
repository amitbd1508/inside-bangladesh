package com.bangladesh.tourism.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rabbitshat.www.testproject.R;


/**
 * Created by hhson on 1/13/2016.
 */
public class ThreeFragment extends Fragment {


    // Required empty public constructor


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_three, container, false);
        if (v != null) {


        }
        return v;
    }

    public void onResume() {
        super.onResume();

    }


}






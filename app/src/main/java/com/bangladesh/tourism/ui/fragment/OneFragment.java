package com.bangladesh.tourism.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rabbitshat.www.testproject.Activity.LocationActivity;
import com.rabbitshat.www.testproject.R;

/**
 * Created by hhson on 1/13/2016.
 */
public class OneFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        View locationfirst = v.findViewById(R.id.locationfirst);
        if (v != null) {
            locationfirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), LocationActivity.class);
                    startActivity(intent);
                }
            });
        }
        return v;
    }
    public void onResume() {
        super.onResume();
    }
}






package com.bangladesh.tourism.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangladesh.tourism.InsideBDApplication;
import com.bangladesh.tourism.R;
import com.bangladesh.tourism.api.InsideBDApi;
import com.bangladesh.tourism.api.services.ApiService;
import com.bangladesh.tourism.api.services.LoginService;
import com.bangladesh.tourism.models.Login;
import com.bangladesh.tourism.models.Make;
import com.bangladesh.tourism.tracking.LocationTracker;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Inject
    InsideBDApi api;
    private View v;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((InsideBDApplication) context.getApplicationContext()).inject(this);

    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_main, container, false);

        LoginService loginService = (LoginService) api.getService(ApiService.SAMPLE_SERVICE);

        String lat = InsideBDApplication.instance().getLocationTracker().getLatitude() + "";
        String lng = InsideBDApplication.instance().getLocationTracker().getLongitude() + "";

        loginService.testApi().enqueue(new Callback<Make>() {
            @Override
            public void onResponse(Call<Make> call, Response<Make> response) {
                Make login = response.body();
                ((TextView)v.findViewById(R.id.tv_ip)).setText(login.makes.get(1));
            }

            @Override
            public void onFailure(Call<Make> call, Throwable t) {
                String failure = t.getMessage();
                ((TextView)v.findViewById(R.id.tv_ip)).setText(failure);
            }
        });

        return v;
    }
}

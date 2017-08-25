package com.bangladesh.tourism;

import android.app.Application;


import com.bangladesh.tourism.dependecy.AndroidModule;
import com.bangladesh.tourism.dependecy.InsideBDModule;
import com.bangladesh.tourism.tracking.LocationTracker;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Zakir on 06/03/2016.
 */
public class InsideBDApplication extends Application {

    private static InsideBDApplication instance;
    ObjectGraph objectGraph;
    private LocationTracker locationTracker;

    public static InsideBDApplication instance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
        locationTracker = new LocationTracker(this);
        instance = this;

    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    private List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(this),
                new InsideBDModule()
        );
    }

    public LocationTracker getLocationTracker() {
        return locationTracker;
    }
}

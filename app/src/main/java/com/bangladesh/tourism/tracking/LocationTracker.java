package com.bangladesh.tourism.tracking;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bangladesh.tourism.util.LocationUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by Zakir on 28/01/2016.
 */
public class LocationTracker implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
,LocationListener{

    private float bearing;

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 3000;

    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private double latitude;
    private double longitude;

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient;

    /**
     * Stores parameters for requests to the FusedLocationProviderApi.
     */
    protected LocationRequest mLocationRequest;

    public Location getlastKnownLocation() {
        if(lastKnownLocation == null) {
            return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        } else
            return lastKnownLocation;
    }

    public final Set<LocationCallback> callbacks = Sets.newHashSet();

    /**
     * Represents a geographical location.
     */
    private Location lastKnownLocation;

    private Context context;

    /**
     * Tracks the status of the location updates request. Value changes when the user presses the
     * Start Updates and Stop Updates buttons.
     */
    protected Boolean mRequestingLocationUpdates;

    public LocationTracker(Context context) {
        this.context = context;
    }

    public void start() {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
        connect();
    }

    public void stop() {
        stopLocationUpdates();
        disconnect();
    }

    /**
     * Requests location updates from the FusedLocationApi.
     */
    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    /**
     * Removes location updates from the FusedLocationApi.
     */
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    protected void connect() {
        mGoogleApiClient.connect();
    }

    protected void disconnect() {
        mGoogleApiClient.disconnect();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onConnected(Bundle bundle) {

        if (lastKnownLocation == null) {
            lastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }

        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        this.lastKnownLocation = location;
        for ( LocationCallback callback : callbacks ) {
            if ( callback.shouldReceiveLocations() ) {
                callback.onNewLocation(new TCLocation(location, bearing));
            }
        }
    }

    public double getLatitude() {
        //return 34.0219;

        if (lastKnownLocation != null) {
            latitude = lastKnownLocation.getLatitude();
        } else {
            longitude = 34.0219;
        }
        return latitude;
    }

    public double getLongitude() {
        //return -118.4814;

        if (lastKnownLocation != null) {
            longitude = lastKnownLocation.getLongitude();
        } else {
            longitude = -118.4814;
        }
        return longitude;
    }

    @Nullable
    public TCLocation getLocation() {
        if ( lastKnownLocation == null ) {
            return null;
        }
        return new TCLocation(lastKnownLocation, bearing);
    }

    @NonNull
    public TCLocation getLocationSafe() {
        if ( lastKnownLocation == null ) {
            return TCLocation.empty();
        }
        return new TCLocation(lastKnownLocation, bearing);
    }

    public interface LocationCallback
    {
        void onNewLocation(TCLocation location);
        boolean shouldReceiveLocations();
    }

    /**
     * Handles the start location update call
     */
    public void startUpdate() {
        if (!mRequestingLocationUpdates) {
            mRequestingLocationUpdates = true;
            startLocationUpdates();
        }
    }

    /**
     * Handles the stop location update call
     */
    public void stopUpdate() {
        if (mRequestingLocationUpdates) {
            mRequestingLocationUpdates = false;
            stopLocationUpdates();
        }
    }

    public void onResume() {
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    public void onPause() {
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    public static float distance(float lat1, float lng1, float lat2, float lng2)
    {
        //float[] dist = new float[1];
        //Location.distanceBetween(lat1, lng1, lat2, lng2, dist);
        //return dist[0];
        return (float) LocationUtils.haversineDist(lat1, lng1, lat2, lng2);
    }
}

package com.bangladesh.tourism.tracking;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class TCLocation implements Parcelable
{
    public final float lat;
    public final float lng;
    public final float bearing;
    public final float speed;

    public TCLocation(float lat, float lng, float bearing, float speed) {
        this.lat = lat;
        this.lng = lng;
        this.bearing = bearing;
        this.speed = speed;
    }

    public TCLocation(@NonNull Location location, float bearing) {
        this((float) location.getLatitude(), (float) location.getLongitude(), bearing, location.getSpeed());
    }

    public TCLocation(@NonNull Location location) {
        this(location, location.getBearing());
    }

    public static TCLocation empty() {
        return new TCLocation(0f, 0f, 0f, 0f);
    }


    protected TCLocation(Parcel in) {
        lat = in.readFloat();
        lng = in.readFloat();
        bearing = in.readFloat();
        speed = in.readFloat();
    }

    public static final Creator<TCLocation> CREATOR = new Creator<TCLocation>() {
        @Override
        public TCLocation createFromParcel(Parcel in) {
            return new TCLocation(in);
        }

        @Override
        public TCLocation[] newArray(int size) {
            return new TCLocation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(lat);
        dest.writeFloat(lng);
        dest.writeFloat(bearing);
        dest.writeFloat(speed);
    }
}

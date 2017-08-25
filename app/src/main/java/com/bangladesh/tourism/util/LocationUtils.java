package com.bangladesh.tourism.util;


public class LocationUtils
{
    final static public int EARTH_RADIUS = 6371000;

    /**
     * Calculates the distance in meters between two points. Android has a built-in function like this,
     * but it's way too accurate for what we need, and thus it is slower.
     *
     * @param lat Latitude 1
     * @param lng Longitude 1
     * @param lat2 Latitude 2
     * @param lng2 Longitude 2
     * @return Distance in meters
     */
    public static double haversineDist(double lat, double lng, double lat2, double lng2)
    {
        final double deltaLat = Math.toRadians(lat2 - lat);
        final double deltaLng = Math.toRadians(lng2 - lng);
        lat = Math.toRadians(lat);
        lat2 = Math.toRadians(lat2);

        final double sinDeltaLat = Math.sin(deltaLat / 2);
        final double sinDeltaLng = Math.sin(deltaLng / 2);

        final double a = sinDeltaLat * sinDeltaLat + Math.cos(lat) * Math.cos(lat2) * sinDeltaLng * sinDeltaLng;
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}

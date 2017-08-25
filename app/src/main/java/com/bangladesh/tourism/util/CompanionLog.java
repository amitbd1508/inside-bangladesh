package com.bangladesh.tourism.util;

import android.os.SystemClock;
import android.util.Log;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Logging utilities with TAG pre-set.
 */
public class CompanionLog
{
    static final String TAG = "InsideBangladesh";

    public static void d(String msg) {
        Log.d(TAG, msg);
    }
    public static void e(String msg) {
        Log.e(TAG, msg);
    }
    public static void i(String msg) {
        Log.i(TAG, msg);
    }
    public static void v(String msg) {
        Log.v(TAG, msg);
    }
    public static void w(String msg) {
        Log.w(TAG, msg);
    }

    public static void d(String msg, Throwable t) {
        Log.d(TAG, msg, t);
    }
    public static void e(String msg, Throwable t) {
        Log.e(TAG, msg, t);
    }
    public static void i(String msg, Throwable t) {
        Log.i(TAG, msg, t);
    }
    public static void v(String msg, Throwable t) {
        Log.v(TAG, msg, t);
    }
    public static void w(String msg, Throwable t) {
        Log.w(TAG, msg, t);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }
    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }
    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }
    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }
    public static void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void d(String tag, String msg, Throwable t) {
        Log.d(tag, msg, t);
    }
    public static void e(String tag, String msg, Throwable t) {
        Log.e(tag, msg, t);
    }
    public static void i(String tag, String msg, Throwable t) {
        Log.i(tag, msg, t);
    }
    public static void v(String tag, String msg, Throwable t) {
        Log.v(tag, msg, t);
    }
    public static void w(String tag, String msg, Throwable t) {
        Log.w(tag, msg, t);
    }

    static String profileLabel;
    static long profileStart;

    public static void profile(String label)
    {
        profileLabel = label;
        profileStart = SystemClock.uptimeMillis();
    }

    public static void end()
    {
        if ( profileLabel != null ) {
            CompanionLog.d("PROFILE " + profileLabel + ": " + (SystemClock.uptimeMillis() - profileStart) + "ms");
            profileLabel = null;
        }
    }

    static Map<String, Inner> loggers = Maps.newHashMap();

    public static Inner get(String tag) {
        if ( !loggers.containsKey(tag) ) {
            loggers.put(tag, new Inner(tag));
        }
        return loggers.get(tag);
    }

    public static class Inner
    {
        private String tag;

        protected Inner(String tag) {
            this.tag = tag;
        }

        public void d(String msg) {
            Log.d(tag, msg);
        }
        public void e(String msg) {
            Log.e(tag, msg);
        }
        public void i(String msg) {
            Log.i(tag, msg);
        }
        public void v(String msg) {
            Log.v(tag, msg);
        }
        public void w(String msg) {
            Log.w(tag, msg);
        }

        public void d(String msg, Throwable t) {
            Log.d(tag, msg, t);
        }
        public void e(String msg, Throwable t) {
            Log.e(tag, msg, t);
        }
        public void i(String msg, Throwable t) {
            Log.i(tag, msg, t);
        }
        public void v(String msg, Throwable t) {
            Log.v(tag, msg, t);
        }
        public void w(String msg, Throwable t) {
            Log.w(tag, msg, t);
        }
    }
}

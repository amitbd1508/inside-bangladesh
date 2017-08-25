package com.bangladesh.tourism.dependecy;

import android.content.Context;

import com.bangladesh.tourism.InsideBDApplication;
import com.bangladesh.tourism.util.annotation.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Android-specific injection module for Dagger.
 */
@Module(library = true)
public class AndroidModule
{
    private final InsideBDApplication application;

    public AndroidModule(InsideBDApplication application)
    {
        this.application = application;
    }

    @Provides
    @Singleton
    @ForApplication
    public Context provideApplicationContext()
    {
        return application;
    }
}

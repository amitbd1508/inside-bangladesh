package com.bangladesh.tourism.dependecy;

import com.bangladesh.tourism.ui.activity.MainActivity;
import com.bangladesh.tourism.ui.fragment.MainActivityFragment;

import dagger.Module;

/**
 * Created by Zakir on 06/03/2016.
 */

@Module(
        injects = {
                // put the classes that are going to use the api class as dependency
                MainActivityFragment.class

        },
        complete = true
)
public class InsideBDModule {

}

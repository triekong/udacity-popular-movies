package com.snoop.sliding_real.extras;

import android.app.Application;
import android.content.Context;

/**
 * Created by galaxywizkid on 7/5/16.
 */
public class MyApplication extends Application{

    public static final String API_KEY_THE_MOVIE_DATABASE = "d8f0d962ee167bb9ab6952c8019974e6";
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}

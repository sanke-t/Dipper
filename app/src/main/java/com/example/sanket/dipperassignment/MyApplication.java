package com.example.sanket.dipperassignment;

/**
 * Created by Sanket on 08-04-2016.
 */
import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static MyApplication getInstance(){
        return mInstance;
    }
    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

}

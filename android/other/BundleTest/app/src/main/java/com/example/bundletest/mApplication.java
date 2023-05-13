package com.example.bundletest;

import android.app.Application;
import android.util.Log;

public class mApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("mApplication", "onCreate");
    }
}

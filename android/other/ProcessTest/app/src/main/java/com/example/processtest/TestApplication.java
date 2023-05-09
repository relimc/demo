package com.example.processtest;

import android.app.Application;
import android.util.Log;

public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TestApplication", "onCreate");
    }
}

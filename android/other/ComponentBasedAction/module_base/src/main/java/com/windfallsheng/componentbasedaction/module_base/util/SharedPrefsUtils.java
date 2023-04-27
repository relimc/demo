package com.windfallsheng.componentbasedaction.module_base.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtils {

    public static void putCount(Context context, int count) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("ComponentbasedActionConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("launch_times", count);
        mEditor.apply();
    }

    public static int getCount(Context context) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("ComponentbasedActionConfig", Context.MODE_PRIVATE);
        return mSharedPreferences.getInt("launch_times", 0);
    }
}

package com.example.common

import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter

open class BaseApplication: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
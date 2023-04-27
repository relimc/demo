package com.windfallsheng.componentbasedaction.module_base.command;

import android.app.Application;
import android.content.res.Configuration;

/**
 * @Author: lzsheng
 */
public abstract class BaseApplicationHelper implements IApplicationHelper {

    protected Application mApplication;

    public BaseApplicationHelper(Application application) {
        this.mApplication = application;
    }

    @Override
    public void onTerminate() {
    }

    @Override
    public void onLowMemory() {
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
    }
}

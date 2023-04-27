package com.windfallsheng.componentbasedaction.module_base.command;

import android.content.res.Configuration;

/**
 * @Author: lzsheng
 */
public interface IApplicationHelper {

    void onCreate();

    void onTerminate();

    void onLowMemory();

    void onConfigurationChanged(Configuration configuration);
}

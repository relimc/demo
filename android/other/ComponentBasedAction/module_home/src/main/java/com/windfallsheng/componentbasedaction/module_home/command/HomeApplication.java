package com.windfallsheng.componentbasedaction.module_home.command;

import android.util.Log;

import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.command.BaseApplication;

/**
 * @Author: lzsheng
 */
public class HomeApplication extends BaseApplication {

    private final String TAG = "HomeApplication";

    @Override
    protected void initComponentSpecificService() {
        Logger.dl(TAG, "method:initComponetSpecificService#单独编译时，初始化当前组件特有的业务");
    }

    @Override
    protected void registeApplicationHelper() {
        Log.d(TAG, "method:registeApplicationHelper#单独编译时，注册相关初始化业务类");
        registerTargetApplicationHelper(HomeApplicationHelper.class.getName());
    }
}

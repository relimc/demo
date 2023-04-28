package com.windfallsheng.componentbasedaction.module_main.command;

import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.command.BaseApplication;

/**
 * @Author: lzsheng
 */
public class MainApplication extends BaseApplication {

    private final String TAG = "MainApplication";

    @Override
    protected void initComponentSpecificService() {
        Logger.dl("method:initComponentSpecificService#单独编译时，初始化当前组件特有的业务");
    }

    @Override
    protected void registeApplicationHelper() {
        Logger.dl(TAG, "method:registeApplicationHelper#单独编译时，注册相关初始化业务类");
        registerTargetApplicationHelper(MainApplicationHelper.class.getName());
    }
}

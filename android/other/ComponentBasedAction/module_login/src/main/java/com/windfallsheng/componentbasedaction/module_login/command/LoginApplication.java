package com.windfallsheng.componentbasedaction.module_login.command;

import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.command.BaseApplication;

/**
 * @Author: lzsheng
 */
public class LoginApplication extends BaseApplication {

    private final String TAG = "LoginApplication";

    @Override
    protected void initComponentSpecificService() {
        Logger.dl(TAG, "method:initComponetSpecificService#单独编译时，初始化当前组件特有的业务");
    }

    @Override
    protected void registeApplicationHelper() {
        Logger.dl(TAG, "method:registeApplicationHelper#单独编译时，注册相关初始化业务类");
        registerTargetApplicationHelper(LoginApplicationHelper.class.getName());
    }
}

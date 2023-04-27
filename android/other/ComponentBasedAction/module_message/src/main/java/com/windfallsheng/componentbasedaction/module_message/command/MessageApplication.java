package com.windfallsheng.componentbasedaction.module_message.command;

import android.util.Log;

import com.windfallsheng.componentbasedaction.module_base.command.BaseApplication;

/**
 * @Author: lzsheng
 */
public class MessageApplication extends BaseApplication {

    private final String TAG = "MessageApplication";

    @Override
    protected void initComponentSpecificService() {
        Log.d(TAG, "method:initComponetSpecificService#单独编译时，初始化当前组件特有的业务");
    }

    @Override
    protected void registeApplicationHelper() {
        Log.d(TAG, "method:registerAppHelper#单独编译时，注册相关初始化业务类");
        registerTargetApplicationHelper(MessageApplicationHelper.class.getName());
    }
}

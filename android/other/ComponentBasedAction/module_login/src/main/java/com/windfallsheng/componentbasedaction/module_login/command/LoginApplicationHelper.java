package com.windfallsheng.componentbasedaction.module_login.command;


import android.app.Application;
import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.command.BaseApplicationHelper;

/**
 * @Author: lzsheng
 */
public class LoginApplicationHelper extends BaseApplicationHelper {

    private final String TAG = "LoginApplicationHelper";

    public LoginApplicationHelper(Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        Logger.dl(TAG, "method:onCreate#不同的模式下当前组件必须的初始化业务");
    }
}

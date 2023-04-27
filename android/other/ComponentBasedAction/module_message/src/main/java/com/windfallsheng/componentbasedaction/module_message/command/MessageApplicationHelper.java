package com.windfallsheng.componentbasedaction.module_message.command;


import android.app.Application;
import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.command.BaseApplicationHelper;

/**
 * @Author: lzsheng
 */
public class MessageApplicationHelper extends BaseApplicationHelper {

    private final String TAG = "MessageApplicationHelper";

    public MessageApplicationHelper(Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        Logger.dl("method:onCreate#不同的模式下当前组件必须的初始化业务");
    }
}

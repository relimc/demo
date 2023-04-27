package com.windfallsheng.componentbasedaction.command;


import android.app.Application;
import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.command.BaseApplicationHelper;

/**
 * @Author: lzsheng
 * @CreateDate: 2020/12/16 11:30
 * @LastModifiedTime: 2020/12/16 11:30
 * @Description: app 模块作为壳工程，暂时不需要处理任何业务，可以不必实现当前类
 */
@Deprecated
public class MyApplicationHelper extends BaseApplicationHelper {

    private static final String TAG = "MyApplicationHelper";

    public MyApplicationHelper(Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        Logger.dl("method:onCreate#不同的模式下当前组件必须的初始化业务");
    }
}

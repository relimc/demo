package com.windfallsheng.componentbasedaction.command;

import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.BaseConsts;
import com.windfallsheng.componentbasedaction.module_base.command.BaseApplication;

/**
 * @Author: lzsheng
 */
public class MyApplication extends BaseApplication {

    private static final String TAG = "MyApplication";

    /**
     * 增加新的组件时，需要在此数组中添加相应的数据，保证模块上下文及其相关初始化成功；
     */
    private static final String[] APPLICATION_HELPER_NAME_LIST =
            {
//                    MyApplicationHelper.class.getName(),
                    BaseConsts.ApplicationHelperClassName.MAIN_APPLICATION_HELPER,
                    BaseConsts.ApplicationHelperClassName.HOME_APPLICATION_HELPER,
                    BaseConsts.ApplicationHelperClassName.LOGIN_APPLICATION_HELPER,
                    BaseConsts.ApplicationHelperClassName.MINE_APPLICATION_HELPER,
                    BaseConsts.ApplicationHelperClassName.MESSAGE_APPLICATION_HELPER,
                    BaseConsts.ApplicationHelperClassName.PAY_APPLICATION_HELPER
            };

    @Override
    protected void registeApplicationHelper() {
        Logger.dl(TAG, "method:registeApplicationHelper#项目合并编译时，注册所有相关初始化业务类");
        registerApplicationHelperArray(APPLICATION_HELPER_NAME_LIST);
    }
}

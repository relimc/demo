package com.windfallsheng.componentbasedaction.module_pay.command;

import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.BaseConsts;
import com.windfallsheng.componentbasedaction.module_base.command.BaseApplication;

/**
 * @Author: lzsheng
 */
public class PayApplication extends BaseApplication {

    private final String TAG = "PayApplication";
    /**
     * 当支付模块单独编译时，由于它需要依赖登录模块，需要在此数组中添加相应的数据，保证模块上下文及其相关初始化成功；
     */
    private static final String[] APPLICATION_HELPER_NAME_LIST =
            {
                    PayApplicationHelper.class.getName(),
                    BaseConsts.ApplicationHelperClassName.LOGIN_APPLICATION_HELPER
            };
    @Override
    protected void initComponentSpecificService() {
        Logger.dl(TAG, "method:initComponentSpecificService#单独编译时，初始化当前组件特有的业务");
    }

    @Override
    protected void registeApplicationHelper() {
        Logger.dl(TAG, "method:registeApplicationHelper#单独编译时，注册相关初始化业务类");
//        registerApplicationHelper(PayApplicationHelper.class.getName());
        registerApplicationHelperArray(APPLICATION_HELPER_NAME_LIST);
    }
}

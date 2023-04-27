package com.windfallsheng.componentbasedaction.module_mine.command;
import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.BaseConsts;
import com.windfallsheng.componentbasedaction.module_base.command.BaseApplication;

/**
 * @Author: lzsheng
 */
public class MineApplication extends BaseApplication {

    /**
     * 增加新的组件时，需要在此数组中添加相应的数据，保证模块上下文及其相关初始化成功；
     */
    private static final String[] APPLICATION_HELPER_NAME_LIST =
            {
                    MineApplicationHelper.class.getName(),
                    BaseConsts.ApplicationHelperClassName.LOGIN_APPLICATION_HELPER
            };
    private final String TAG = "MineApplication";

    @Override
    protected void initComponentSpecificService() {
        Logger.dl(TAG, "method:initComponetSpecificService#单独编译时，初始化当前组件特有的业务");
    }

    @Override
    protected void registeApplicationHelper() {
        Logger.dl(TAG, "method:registeApplicationHelper#项目合并编译时，注册所有相关初始化业务类");
//        registerApplicationHelper(MineApplicationHelper.class.getName());
        registerApplicationHelperArray(APPLICATION_HELPER_NAME_LIST);
    }

}

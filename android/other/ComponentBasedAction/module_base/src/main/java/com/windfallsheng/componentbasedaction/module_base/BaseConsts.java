package com.windfallsheng.componentbasedaction.module_base;

public class BaseConsts {

    /**
     * 定义所有相关类的全路径字符串变量；
     * <p>
     * 增加新的组件时，需要在此数组中添加相应的数据；
     */
    public static class ApplicationHelperClassName {

        public static final String PREFIX = "com.windfallsheng.componentbasedaction.";
        /**
         * 必须是类的全路径名
         */
        public static final String MAIN_APPLICATION_HELPER =
                PREFIX + "module_main.command.MainApplicationHelper";

        public static final String HOME_APPLICATION_HELPER =
                PREFIX + "module_home.command.HomeApplicationHelper";

        public static final String MINE_APPLICATION_HELPER =
                PREFIX + "module_mine.command.MineApplicationHelper";

        public static final String MESSAGE_APPLICATION_HELPER =
                PREFIX + "module_message.command.MessageApplicationHelper";

        public static final String LOGIN_APPLICATION_HELPER =
                PREFIX + "module_login.command.LoginApplicationHelper";

        public static final String PAY_APPLICATION_HELPER =
                PREFIX + "module_pay.command.PayApplicationHelper";

    }
}

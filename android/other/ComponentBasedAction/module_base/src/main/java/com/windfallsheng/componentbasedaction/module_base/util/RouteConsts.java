package com.windfallsheng.componentbasedaction.module_base.util;

/**
 * @Author: lzsheng
 */
public class RouteConsts {
    /**
     * 首页模块
     */
    public static class HomeModule {

        /**
         * HomeModuleService
         */
        public static final String HOME_MODULE_SERVICE = "/home/HomeModuleService";
        /**
         * HomeFragment
         */
        public static final String HOME_HOME_FRAGMENT = "/home/HomeFragment";
    }

    /**
     * 消息模块
     */
    public static class MessageModule {

        /**
         * HomeModuleService
         */
        public static final String MESSAGE_MODULE_FRAGMENT_IDENTITY_CHOICE = "/message/IdentityChoiceFragnent";
        /**
         * HomeFragment
         */
        public static final String HOME_HOME_FRAGMENT = "/home/HomeFragment";
    }

    /**
     * 支付模块
     */
    public static class PayModule {

        /**
         * PayActivity
         */
        public static final String PAY_ACTIVITY_PAY = "/pay/PayActivity";
    }

    public static final String MAIN_ACTIVITY_MAIN = "/main/MainActivity";
    //获得home模块fragment
    public static final String HOME_FRAGMENT_HOME = "/home/HomeFragment";
    //获得chat模块fragment
    public static final String MESSAGE_FRAGMENT_MESSAGE = "/message/MessageFragment";
    //获得Recom模块fragment
    public static final String MINE_FRAGMENT_MINE = "/mine/MineFragment";
    //获得Me模块fragment
    public static final String LOGIN_ACTIVITY_LOGIN = "/login/LoginActivity";
    public static final String LOGIN_ACTIVITY_FOR_RESULT = "/login/ForResultActivity";



//    //跳转到登录页面
//    public static final String Me_Login = "/me/main/login";
//    //跳转到eventBus数据接收页面
//    public static final String Me_EventBus = "/me/main/EventBus";
//    //跳转到TextOne数据接收页面
//    public static final String Me_TextOne = "/me/main/TextOne";
//    //跳转到Test公用页面
//    public static final String Me_Test = "/me/main/Test";
//    //路由拦截
//    public static final String Me_Test2 = "/me/main/Test2";
//    //跳转到webview页面
//    public static final String Me_WebView = "/me/main/WebView";
//
//    //跳转到依赖注入页面
//    public static final String Me_Inject = "/me/main/Inject";
//    /**
//     * 依赖注入使用，注意：必须实现SerializationService进行注册，
//     */
//    public static final String Home_Json_Service = "/windfallsheng/json";
//
//    //跳转ForResult
//    public static final String Chat_ForResult = "/chat/main/ForResult";
//    //模块间服务调用，调用chat模块的接口
//    public static final String Service_Chat = "/chat/service";
//    //路由拦截
//    public static final String Chat_Interceptor = "/chat/main/Interceptor";
//
//    /**
//     * 专门的分组，这里就叫做needLogin组，凡是在这个组下的，都会进行登录操作
//     */
//    public static final String NeedLogin_Test3 = "/needLogin/main/Test3";

}

package com.windfallsheng.componentbasedaction.module_base;

import com.windfallsheng.componentbasedaction.module_base.bean.UserInfo;

public class GlobalCache {

    private UserInfo mUserInfo;
    private boolean isLogin;

    private GlobalCache() {
    }

    public static GlobalCache getInstance() {
        return GlobalCache.SingletonHolder.INSTANCE;
    }

    public boolean isLogin() {
        return this.isLogin;
    }

    public void cacheUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
        if (this.mUserInfo == null) {
            isLogin = false;
        } else {
            isLogin = true;
        }
    }

    public UserInfo getUserInfo(UserInfo userInfo) {
        return this.mUserInfo;
    }

    private static class SingletonHolder {
        private static final GlobalCache INSTANCE = new GlobalCache();
    }

}

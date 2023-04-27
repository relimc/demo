package com.windfallsheng.componentbasedaction.module_base.util;

import androidx.fragment.app.Fragment;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @Author: lzsheng
 */
public class FragmentUtils {

    public static Fragment getHomeFragment() {
        return (Fragment) ARouter.getInstance()
                .build(RouteConsts.HOME_FRAGMENT_HOME)
                .navigation();
    }

    public static Fragment getMessageFragment() {
        return (Fragment) ARouter.getInstance()
                .build(RouteConsts.MESSAGE_FRAGMENT_MESSAGE)
                .navigation();
    }

    public static Fragment getMineFragment() {
        return (Fragment) ARouter.getInstance()
                .build(RouteConsts.MINE_FRAGMENT_MINE)
                .navigation();
    }

}

package com.windfallsheng.componentbasedaction.module_base.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;

public class DisplayHelper {

    private static final String XIAOMI_FULLSCREEN_GESTURE = "force_fsg_nav_bar";

    /**
     * 获取 DisplayMetrics
     *
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        int screenHeight = getDisplayMetrics(context).heightPixels;
        if (isXiaomi() && xiaomiNavigationGestureEnabled(context)) {
            screenHeight += getResourceNavHeight(context);
        }
        return screenHeight;
    }

    private static int getResourceNavHeight(Context context) {
        // 小米4没有nav bar, 而 navigation_bar_height 有值
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return -1;
    }

    /**
     * 判断是否为小米
     * https://dev.mi.com/doc/?p=254
     */
    public static boolean isXiaomi() {
        return "xiaomi".equals(Build.MANUFACTURER.toLowerCase());
    }

    public static boolean xiaomiNavigationGestureEnabled(Context context) {
        int val = Settings.Global.getInt(context.getContentResolver(), XIAOMI_FULLSCREEN_GESTURE, 0);
        return val != 0;
    }

}

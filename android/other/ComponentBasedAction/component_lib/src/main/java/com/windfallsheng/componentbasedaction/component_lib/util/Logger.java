package com.windfallsheng.componentbasedaction.component_lib.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Locale;

/**
 * @Author: lzsheng
 * <p>
 * 日志工具类
 * <p>
 * 可以全局初始化时控制输出及缓存状态
 * <p>
 * 普通的日志内容输出
 * <p>
 * 可以定位到代码位置的日志输出
 * <p>
 * 可以全局控制缓存日志内容到本地
 * <p>
 * 可以单次缓存日志内容到本地
 */
public class Logger {

    private static boolean sDebug;
    /**
     * 设置一个总开关，决定是否缓存日志，前提条件是{@link this#isDebug}为true；
     * <p>
     * 如果为false，即使用户调用了缓存日志的方法，缓存也不生效；
     * <p>
     * 如果为true，那么是否缓存日志，取决于是否调用了缓存日志的方法；
     */
    private static boolean sCacheable;
    /**
     * 缓存日志时需要传入上下文
     */
    private static Context sContext;

    public static void initDebug(boolean debug) {
        sDebug = debug;
    }

    /**
     * 在全局初始化时设置是否设置缓存， 可以作为全局缓存的开关处理；
     *
     * @param context
     * @param cacheable
     */
    public static void isCacheable(Context context, boolean cacheable) {
        if (context != null) {
            sContext = context.getApplicationContext();
        }
        sCacheable = cacheable;
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static void d(@NonNull String msg) {
        if (sDebug) {
            handleLogInfo("", Log.DEBUG, msg, false, false);
        }
    }

    public static void d(String tag, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo(tag, Log.DEBUG, msg, false, false);
        }
    }

    /**
     * 可以写入日志内容到本地文件
     *
     * @param msg
     */
    public static void dc(@NonNull String msg) {
        if (sDebug) {
            handleLogInfo("", Log.DEBUG, msg, false, true);
        }
    }

    /**
     * 可以写入日志内容到本地文件
     *
     * @param tag
     * @param msg
     */
    public static void dc(String tag, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo(tag, Log.DEBUG, msg, false, true);
        }
    }

    /**
     * 可以写入日志内容到本地文件，但只在当前调用时一次性缓存当次的日志；
     *
     * @param msg
     */
    public static void dcOneTime(Context context, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo("", Log.DEBUG, msg, false, false);
            cacheLogInfo(context, msg.trim());
        }
    }

    /**
     * 可以写入日志内容到本地文件，但只在当前调用时一次性缓存当次的日志；
     *
     * @param tag
     * @param msg
     */
    public static void dcOneTime(Context context, String tag, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo(tag, Log.DEBUG, msg, false, false);
            cacheLogInfo(context, msg.trim());
        }
    }

    /**
     * 可以定位代码行数的日志输出
     *
     * @param msg
     */
    public static void dl(@NonNull String msg) {
        if (sDebug) {
            handleLogInfo("", Log.DEBUG, msg, true, false);
        }
    }

    /**
     * 可以定位代码行数的日志输出
     *
     * @param tag
     * @param msg
     */
    public static void dl(String tag, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo(tag, Log.DEBUG, msg, true, false);
        }
    }

    /**
     * 可以定位代码行数的日志输出
     * <p>
     * 可以写入日志内容到本地文件
     *
     * @param tag
     * @param msg
     */
    public static void dlc(String tag, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo(tag, Log.DEBUG, msg, true, true);
        }
    }

    /**
     * 可以定位代码行数的日志输出
     * <p>
     * 可以写入日志内容到本地文件
     *
     * @param msg
     */
    public static void dlc(@NonNull String msg) {
        if (sDebug) {
            handleLogInfo("", Log.DEBUG, msg, true, true);
        }
    }

    /**
     * 可以定位代码行数的日志输出
     * <p>
     * 可以写入日志内容到本地文件，但只在当前调用时一次性缓存当次的日志；
     *
     * @param tag
     * @param msg
     */
    public static void dlcOneTime(Context context, String tag, @NonNull String msg) {
        if (sDebug) {
            String msgNew = handleLogInfo(tag, Log.DEBUG, msg, true, false);
            cacheLogInfo(context, msgNew);
        }
    }

    /**
     * 可以定位代码行数的日志输出
     * <p>
     * 可以写入日志内容到本地文件，但只在当前调用时一次性缓存当次的日志；
     *
     * @param msg
     */
    public static void dlcOneTime(Context context, @NonNull String msg) {
        if (sDebug) {
            String msgNew = handleLogInfo("", Log.DEBUG, msg, false, false);
            cacheLogInfo(context, msgNew);
        }
    }

    public static void v(@NonNull String msg) {
        if (sDebug) {
            handleLogInfo("", Log.VERBOSE, msg, false, false);
        }
    }

    public static void v(String tag, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo(tag, Log.VERBOSE, msg, false, false);
        }
    }

    public static void i(@NonNull String msg) {
        if (sDebug) {
            handleLogInfo("", Log.INFO, msg, false, false);
        }
    }

    public static void i(String tag, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo(tag, Log.INFO, msg, false, false);
        }
    }

    /**
     * 可以定位代码行数的日志输出
     *
     * @param msg
     */
    public static void w(@NonNull String msg) {
        if (sDebug) {
            handleLogInfo("", Log.WARN, msg, false, false);
        }
    }

    public static void w(String tag, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo(tag, Log.WARN, msg, false, false);
        }
    }

    /**
     * 可以定位代码行数的日志输出
     *
     * @param msg
     */
    public static void wl(@NonNull String msg) {
        if (sDebug) {
            handleLogInfo("", Log.WARN, msg, true, false);
        }
    }

    /**
     * 可以定位代码行数的日志输出
     *
     * @param tag
     * @param msg
     */
    public static void wl(String tag, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo(tag, Log.WARN, msg, true, false);
        }
    }

    public static void e(@NonNull String msg) {
        if (sDebug) {
            handleLogInfo("", Log.ERROR, msg, false, false);
        }
    }

    public static void e(String tag, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo(tag, Log.ERROR, msg, false, false);
        }
    }

    /**
     * 可以定位代码行数的日志输出
     *
     * @param msg
     */
    public static void el(@NonNull String msg) {
        if (sDebug) {
            handleLogInfo("", Log.ERROR, msg, true, false);
        }
    }

    /**
     * 可以定位代码行数的日志输出
     *
     * @param tag
     * @param msg
     */
    public static void el(String tag, @NonNull String msg) {
        if (sDebug) {
            handleLogInfo(tag, Log.ERROR, msg, true, false);
        }
    }

    /**
     * 所以这里使用自己分节的方式来输出足够长度的message
     * <p>
     * 按照:类名,方法名,行号等，这样的格式拼接，可以用来定位代码行，
     * <p>
     * 如：cn.xx.ui.MainActivity.onCreate(MainActivity.java:17) 定位代码行;
     *
     * @param tag
     * @param msg          void
     * @param withLocation 是否带有代码定位的日志输出
     * @param cacheable    是否将日志内容写入本地文件
     */
    private static String handleLogInfo(String tag, int level, @NonNull String msg, boolean withLocation, boolean cacheable) {
        if (TextUtils.isEmpty(tag)) {
            tag = initTag();
        }
        if (withLocation) {
            msg = buildMsgWithLocation(msg).trim();
        } else {
            msg = msg.trim();
        }
        int index = 0;
        int maxLength = 3500;
        String sub;
        while (index < msg.length()) {
            // java的字符不允许指定超过总的长度end
            if (msg.length() <= index + maxLength) {
                sub = msg.substring(index);
            } else {
                sub = msg.substring(index, index + maxLength);
            }
            index += maxLength;
            printLogInfo(tag, level, sub.trim());
        }
        // 如果开启了日志输出且需要缓存时，则缓存日志信息；
        if (sCacheable && cacheable) {
            cacheLogInfo(sContext, msg);
        }
        return msg;
    }

    private static void printLogInfo(String tag, int level, @NonNull String info) {
        switch (level) {
            case Log.ERROR:
                Log.e(tag, info);
                break;
            case Log.INFO:
                Log.i(tag, info);
                break;
            case Log.VERBOSE:
                Log.v(tag, info);
                break;
            case Log.WARN:
                Log.w(tag, info);
                break;
            case Log.DEBUG:
            default:
                Log.d(tag, info);
                break;
        }
    }

    /**
     * 没有传入 tag 参数时，默认以调用日志所在类的类名字作为 tag
     *
     * @return
     */
    private static String initTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String temp = "";
        // 当前类的调用 栈有 3 个，所以从 3 开始；
        for (int i = 3; i < trace.length; i++) {
//            Class<?> clazz = trace[i].getClass();
            temp = trace[i].getClassName();
            if (!TextUtils.equals(temp, Logger.class.getName())) {
                temp = trace[i].getFileName();
                if (temp.contains(".")) {
                    temp = temp.substring(0, temp
                            .lastIndexOf('.'));
                }
                break;
            }
        }
        return temp;
    }

    /**
     * 在日志内容头部加入 可以定位调用的代码位置的日志
     *
     * @param str
     * @return
     */
    private static String buildMsgWithLocation(String str) {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String className = "";
        String fileName = "";
        String methodName = "";
        String lineNum = "";
        StackTraceElement stackTraceElement;
        // 当前类的调用 栈有 3 个，所以从 3 开始；
        for (int i = 3; i < trace.length; i++) {
            stackTraceElement = trace[i];
            className = stackTraceElement.getClassName();
            if (!TextUtils.equals(className, Logger.class.getName())) {
                methodName = stackTraceElement.getMethodName();
                fileName = stackTraceElement.getFileName();
                lineNum = String.valueOf(stackTraceElement.getLineNumber());
                break;
            }
        }
        return String.format(Locale.US, "[Thread : %d - %s, %s.%s(%s:%s)] %s", Thread.currentThread()
                .getId(), Thread.currentThread().getName(), className, methodName, fileName, lineNum, str);
    }

    /**
     * 将日志信息写入本地文件
     *
     * @param context
     * @param msg
     */
    private static void cacheLogInfo(Context context, @NonNull String msg) {
        if (context == null) {
            el(Logger.class.getSimpleName(), "method:cacheLogInfo#return#context=" + context);
            return;
        }
        // 判断权限状态
//        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
        // 写入到本地文件
        // 省略具体代码实现...
    }

}

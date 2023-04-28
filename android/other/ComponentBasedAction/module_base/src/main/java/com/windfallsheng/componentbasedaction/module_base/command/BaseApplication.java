package com.windfallsheng.componentbasedaction.module_base.command;

import android.app.Application;
import android.content.res.Configuration;

import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.BuildConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lzsheng
 */
public abstract class BaseApplication extends MultiDexApplication {

    private static Application mApplication;
    private final String TAG = "BaseApplication";
    private boolean mDebug;
    /**
     * 各个Module中针对各个Module自身特有的初始化业务；
     */
    private List<IApplicationHelper> mApplicationHelperList = new ArrayList<>();

    public static Application getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        this.mDebug = BuildConfig.DEBUG;
        // 在基类中初始化一次各module通用的功能，
        initComponentCommonService();
        Logger.dl("method:onCreate#mApplication=" + mApplication.getClass().getName());
        // 针对各个Module自身特有的业务，在分开打包时，各个module可以重写些方法，进行初始化业务；
        initComponentSpecificService();
        // 各个Module必须要进行的初始化业务；
        handlelAllApplicationHelpersCreate();
    }

    /**
     * 初始化各个module通用的业务；
     */
    private void initComponentCommonService() {
        Logger.initDebug(mDebug);
        Logger.dl("method:initComponentCommonService#初始化各个组件公共的业务");
        // ARouter初始化相关
        if (mDebug) {
            //打印日志
            ARouter.openLog();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init(mApplication);
    }

    /**
     * 对于各个module在单独编译时，如果有必要的业务需要处理则重写此方法；
     */
    protected void initComponentSpecificService() {
        Logger.dl("method:initComponentSpecificService#初始化各个组件自身特有的业务#" + TAG + "里定义的空实现");
    }

    /**
     * 处理注册初始化功能的业务实例，及调用各实例的onCreate()方法；
     */
    private void handlelAllApplicationHelpersCreate() {
        Logger.dl("method:handlelAllApplicationHelpersCreate");
        // 首先注册具体实例；
        registeApplicationHelper();
        // 调用各实例的初始化方法
        mApplicationHelperList
                .forEach(applicationHelper -> {
                    Logger.d("method:handlelAllApplicationHelpersCreate#applicationHelper=" +
                            applicationHelper.getClass().getSimpleName());
                    applicationHelper.onCreate();
                });
    }

    /**
     * 注册各个Module中用于初始化功能的业务实例；
     */
    protected abstract void registeApplicationHelper();

    /**
     * 根据全路径名，通过反射注册相关实例；各组件单独编译时，其它组件会报异常
     *
     * @param className
     */
    protected void registerTargetApplicationHelper(String className) {
        BaseApplicationHelper applicationHelper = null;
        try {
            Class<? extends BaseApplicationHelper> clazz = (Class<? extends BaseApplicationHelper>) Class.forName(className);
            Constructor<? extends BaseApplicationHelper> constructor = clazz.getConstructor(Application.class);
            applicationHelper = constructor.newInstance(BaseApplication.this);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            Logger.el("method:registerApplicationHelper#e=" + e.getMessage());
        }
        if (applicationHelper != null) {
            mApplicationHelperList.add(applicationHelper);
        }
    }

    /**
     * 根据全路径名的数组，通过反射注册相关实例；各组件单独编译时，其它组件会报异常
     *
     * @param classNameArray
     */
    protected void registerApplicationHelperArray(String[] classNameArray) {
        for (String className : classNameArray) {
            BaseApplicationHelper applicationHelper = null;
            try {
                Class<? extends BaseApplicationHelper> clazz = (Class<? extends BaseApplicationHelper>) Class.forName(className);
                Constructor<? extends BaseApplicationHelper> constructor = clazz.getConstructor(Application.class);
                applicationHelper = constructor.newInstance(BaseApplication.this);
            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                Logger.el("method:registerapplicationHelperArray#e=" + e.getMessage());
            }
            if (applicationHelper != null) {
                mApplicationHelperList.add(applicationHelper);
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mApplicationHelperList.stream()
                .forEach(applicationHelper ->
                        applicationHelper.onTerminate());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mApplicationHelperList.stream()
                .forEach(applicationHelper ->
                        applicationHelper.onLowMemory());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mApplicationHelperList.stream()
                .forEach(applicationHelper ->
                        applicationHelper.onConfigurationChanged(newConfig));
    }
}

package com.windfallsheng.componentbasedaction.module_login.provider;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @Author: luzhaosheng
 * @CreateDate: 2020/12/16 10:38
 * @LastModifiedTime: 2020/12/16 10:38
 * @Description: 为各个模块提供可以获取appHelper的全路径名称的方法；
 */
public interface LoginProvider extends IProvider {

    String getAppHelperName();

}

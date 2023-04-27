package com.windfallsheng.componentbasedaction.module_pay.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.command.BaseApplication;
import com.windfallsheng.componentbasedaction.module_base.util.RouteConsts;
import com.windfallsheng.componentbasedaction.module_base.widgets.TitleBar;
import com.windfallsheng.componentbasedaction.module_pay.R;

/**
 * @Author: lzsheng
 */
@Route(path = RouteConsts.PayModule.PAY_ACTIVITY_PAY)
public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity_pay);
        initView();
        Logger.dl("onCreate#application=" + BaseApplication.getInstance());
    }

    private void initView() {
        TitleBar titleBar = findViewById(R.id.titlebar);
        titleBar.setTitle("PayActivity");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new PayFragment())
                .commitAllowingStateLoss();
    }
}

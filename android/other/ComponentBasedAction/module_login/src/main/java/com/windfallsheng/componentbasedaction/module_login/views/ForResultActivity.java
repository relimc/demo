package com.windfallsheng.componentbasedaction.module_login.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.windfallsheng.componentbasedaction.module_base.util.RouteConsts;
import com.windfallsheng.componentbasedaction.module_base.widgets.TitleBar;
import com.windfallsheng.componentbasedaction.module_login.R;

/**
 * @Author: lzsheng
 */
@Route(path = RouteConsts.LOGIN_ACTIVITY_FOR_RESULT)
public class ForResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_login);
        int index = getIntent().getIntExtra("index", 0);
        initView();
    }

    private void initView() {
        TitleBar titleBar = findViewById(R.id.titlebar);
        titleBar.setTitle("Template");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new LoginFragment())
                .commitAllowingStateLoss();
    }
}

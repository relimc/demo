package com.windfallsheng.componentbasedaction.module_main.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.util.SharedPrefsUtils;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.launch_activity_launch);
        init();
    }

    private void init() {
        int count = SharedPrefsUtils.getCount(this);
        Logger.dl("init#count=" + count);
        if ((count & 1) == 1) {
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LaunchActivity.this.startActivity(new Intent(LaunchActivity.this, MainActivity.class));
//                ARouter.getInstance()
//                        .build(RouteConsts.APP_MAIN_ACTIVITY)
//                        .navigation(WelcomeActivity.this);
                finish();
            }).start();
        } else {
            startActivity(new Intent(this, AdvertisingActivity.class));
            finish();
        }
        SharedPrefsUtils.putCount(this, ++count);
    }

}

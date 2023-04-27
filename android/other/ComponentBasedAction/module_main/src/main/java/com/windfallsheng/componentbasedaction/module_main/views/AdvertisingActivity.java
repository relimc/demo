package com.windfallsheng.componentbasedaction.module_main.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.windfallsheng.componentbasedaction.module_main.R;

public class AdvertisingActivity extends AppCompatActivity {

    private ImageView mSplashRoot;
    private TextView mTvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_advertising);
        initView();
    }

    private void initView() {
        mSplashRoot = (ImageView) findViewById(R.id.splash_root);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        mSplashRoot.startAnimation(animation);

        CountDownTimer timer = new CountDownTimer(4 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvTime.setText("剩余" + "(" + (millisUntilFinished / 1000) + "s)");
            }

            @Override
            public void onFinish() {
                AdvertisingActivity.this.startActivity(new Intent(AdvertisingActivity.this, MainActivity.class));
//                ARouter.getInstance()
//                        .build(RouteConsts.APP_MAIN_ACTIVITY)
//                        .navigation(AdvertisingActivity.this);
                finish();
            }
        };
        timer.start();
    }
}

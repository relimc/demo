package com.windfallsheng.componentbasedaction.module_main.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.windfallsheng.componentbasedaction.component_lib.util.Logger;
import com.windfallsheng.componentbasedaction.module_base.util.FragmentUtils;
import com.windfallsheng.componentbasedaction.module_base.util.RouteConsts;
import com.windfallsheng.componentbasedaction.module_main.R;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

@Route(path = RouteConsts.MAIN_ACTIVITY_MAIN)
public class MainActivity extends AppCompatActivity {

    int[] testColors = {0xFF455A64, 0xFF00796B, 0xFF795548, 0xFF5B4947, 0xFFF57C00};
    NavigationController mNavigationController;
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int index = getIntent().getIntExtra("index", -1);
        Logger.dl("onNewIntent#index=" + index);
        if (index != -1){
            mNavigationController.setSelect(index);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);

        Fragment homeFragment = FragmentUtils.getHomeFragment();
        Fragment messageFragment = FragmentUtils.getMessageFragment();
        Fragment mineFragment = FragmentUtils.getMineFragment();
        Logger.dl("onCreate#homeFragment=" + homeFragment);
        if (homeFragment != null) {
            fragments.add(homeFragment);
        }
        if (messageFragment != null) {
            fragments.add(messageFragment);
        }
        if (mineFragment != null) {
            fragments.add(mineFragment);
        }
        if (fragments.size() == 0) {
            Toast.makeText(MainActivity.this,
                    "没有获取到其它组件的 Fragment", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        initView();
    }

    private void initView() {
        PageNavigationView pageBottomTabLayout = findViewById(R.id.tab);
        ViewPager viewPager = findViewById(R.id.viewPager);

        mNavigationController = pageBottomTabLayout.material()

                .addItem(R.drawable.ic_ondemand_video_black_24dp, "首页", testColors[0])
                .addItem(R.drawable.ic_book_black_24dp, "消息", testColors[1])
                .addItem(R.drawable.ic_news_black_24dp, "我的", testColors[2])
                .setDefaultColor(0x89FFFFFF)//未选中状态的颜色
                .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR | MaterialMode.HIDE_TEXT)//这里可以设置样式模式，总共可以组合出4种效果
                .build();

        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager()));

        // 自动适配ViewPager页面切换
        mNavigationController.setupWithViewPager(viewPager);

        // 也可以设置Item选中事件的监听
        mNavigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }

    /**
     * 适配器
     */
    public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {


        public MainFragmentPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }
}

package com.windfallsheng.componentbasedaction.module_message.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.windfallsheng.componentbasedaction.module_base.widgets.TitleBar;
import com.windfallsheng.componentbasedaction.module_message.R;

/**
 * @Author: lzsheng
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        TitleBar titleBar = findViewById(R.id.titlebar);
        titleBar.setTitle("Template");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new MessageFragment())
                .commitAllowingStateLoss();
    }
}

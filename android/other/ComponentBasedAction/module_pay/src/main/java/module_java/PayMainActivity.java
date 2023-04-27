package module_java;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.windfallsheng.componentbasedaction.module_base.widgets.TitleBar;
import com.windfallsheng.componentbasedaction.module_pay.R;

/**
 * @Author: lzsheng
 */
public class PayMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity_main);
        initView();
    }

    private void initView() {
        TitleBar titleBar = findViewById(R.id.titlebar);
        titleBar.setTitle("PayMainActivity");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new PayMainFragment())
                .commitAllowingStateLoss();
    }
}

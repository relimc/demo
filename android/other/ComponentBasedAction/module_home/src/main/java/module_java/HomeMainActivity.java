package module_java;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.windfallsheng.componentbasedaction.module_home.R;
import com.windfallsheng.componentbasedaction.module_home.views.HomeFragment;

/**
 * @Author: lzsheng
 */
public class HomeMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main_home);
        initView();
    }

    private void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment())
                .commitAllowingStateLoss();
    }
}

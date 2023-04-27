package module_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.windfallsheng.componentbasedaction.module_base.GlobalCache;
import com.windfallsheng.componentbasedaction.module_base.util.RouteConsts;
import com.windfallsheng.componentbasedaction.module_base.widgets.LoginEvent;
import com.windfallsheng.componentbasedaction.module_pay.R;
import com.windfallsheng.componentbasedaction.module_pay.views.PayActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * @Author: lzsheng
 */
public class PayMainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mRootView;
    private Button mButton;

    public PayMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PayMainFragment newInstance(String param1, String param2) {
        PayMainFragment fragment = new PayMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.pay_fragment_main, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mButton = mRootView.findViewById(R.id.pay_button);
        mButton.setOnClickListener(v -> {
            boolean isLogin = GlobalCache.getInstance().isLogin();
            if (isLogin){
                startActivity(new Intent(getActivity(), PayActivity.class));
            } else {
                ARouter.getInstance()
                        .build(RouteConsts.LOGIN_ACTIVITY_LOGIN)
                        .navigation();
            }
        });
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent loginEvent) {
        startActivity(new Intent(getActivity(), PayActivity.class));
    }


    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
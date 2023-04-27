package com.windfallsheng.componentbasedaction.module_login.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.windfallsheng.componentbasedaction.module_base.GlobalCache;
import com.windfallsheng.componentbasedaction.module_base.bean.UserInfo;
import com.windfallsheng.componentbasedaction.module_base.widgets.LoginEvent;
import com.windfallsheng.componentbasedaction.module_login.R;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;


/**
 * @Author: lzsheng
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mRootView;
    private Button mButton;

    public LoginFragment() {
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
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        mRootView = inflater.inflate(R.layout.login_fragment_login, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mButton = mRootView.findViewById(R.id.login_button);
        mButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "开始登录", Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                UserInfo cyra = new UserInfo(UUID.randomUUID().toString(), "Cyra");
                GlobalCache.getInstance().cacheUserInfo(cyra);
                Toast.makeText(getContext(), "登录成功", Toast.LENGTH_LONG).show();
                EventBus.getDefault().post(new LoginEvent(true));
                getActivity().finish();
            }, 5 * 1000);
        });
    }
}
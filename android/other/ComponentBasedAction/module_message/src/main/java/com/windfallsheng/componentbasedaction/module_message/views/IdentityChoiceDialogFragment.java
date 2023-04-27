package com.windfallsheng.componentbasedaction.module_message.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.windfallsheng.componentbasedaction.module_base.util.DisplayHelper;
import com.windfallsheng.componentbasedaction.module_base.util.RouteConsts;
import com.windfallsheng.componentbasedaction.module_message.R;


/**
 * @Author: lzsheng
 */
@Route(path = RouteConsts.MessageModule.MESSAGE_MODULE_FRAGMENT_IDENTITY_CHOICE)
public class IdentityChoiceDialogFragment extends DialogFragment implements View.OnClickListener {

    private final String TAG = "IdentityChoiceDialogFragment";

    private Context mContext;
    private TextView mTv01;
    private TextView mTv02;
    private TextView mTv03;
    private View mRootView;

    private IdentityChoiceCompleteListener mIdentityChoiceCompleteListener;

    public static IdentityChoiceDialogFragment newInstance() {
        IdentityChoiceDialogFragment fragment = new IdentityChoiceDialogFragment();
        return fragment;
    }

    public void setIdentityChoiceCompleteListener(IdentityChoiceCompleteListener identityChoiceCompleteListener) {
        mIdentityChoiceCompleteListener = identityChoiceCompleteListener;
    }

    //    @Override
//    public void onStart() {
//        super.onStart();
//        //设置背景半透明
//        DisplayMetrics dm = new DisplayMetrics()【;
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
////        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        //getDialog().setCancelable(false);//这个会屏蔽掉返回键
////        getDialog().setCanceledOnTouchOutside(isCanceledOnTouchOutside());
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Identity_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.message_fragment_identity_choice, container, false);
        mTv01 = mRootView.findViewById(R.id.tv_01);
        mTv02 = mRootView.findViewById(R.id.tv_02);
        mTv03 = mRootView.findViewById(R.id.tv_03);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 调整布局的宽高
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mRootView.getLayoutParams();
        layoutParams.height = (int) (DisplayHelper.getScreenHeight(mContext) * 0.65);
        layoutParams.width = (int) (DisplayHelper.getScreenWidth(mContext) * 0.8);
        mRootView.setLayoutParams(layoutParams);
        FrameLayout parent = (FrameLayout) mRootView.getParent();
        parent.setBackgroundResource(R.color.transparent);
        // 设置事件
        mTv01.setOnClickListener(this);
        mTv02.setOnClickListener(this);
        mTv03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String identity = ((TextView) v).getText().toString();
        Toast.makeText(mContext, identity, Toast.LENGTH_SHORT).show();
        if (mIdentityChoiceCompleteListener != null) {
            mIdentityChoiceCompleteListener.onIdentityChoiceComplete(identity);
        }
        dismiss();
    }

    //    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        Window dialogWindow = dialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = (int) (QMUIDisplayHelper.getScreenWidth(mContext) * 0.8);
//        lp.height = (int) (QMUIDisplayHelper.getScreenHeight(mContext) * 0.6);
//        dialogWindow.setAttributes(lp);
//        return dialog;
//    }

    public interface IdentityChoiceCompleteListener {

        void onIdentityChoiceComplete(String identity);

    }

}

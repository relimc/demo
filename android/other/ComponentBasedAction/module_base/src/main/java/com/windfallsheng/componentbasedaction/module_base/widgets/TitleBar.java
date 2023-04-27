package com.windfallsheng.componentbasedaction.module_base.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.windfallsheng.componentbasedaction.module_base.R;

/**
 * @Author: lzsheng
 */
public class TitleBar extends RelativeLayout {
    private Context mContext;
    private View mRootView;
    private TextView mTvTitle;
    private ImageView mIvBack;

    public TitleBar(Context context) {
        super(context);
        init(context, null, 0);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        mRootView = LayoutInflater.from(context).inflate(R.layout.layout_title_bar, this);
        mTvTitle = findViewById(R.id.tv_title);
        mIvBack = findViewById(R.id.iv_back);
        mIvBack.setVisibility(View.GONE);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.title_bar_layout_style);
        String title = typedArray.getString(R.styleable.title_bar_layout_style_title_text);
//        mTextLabelColor = typedArray.getColor(R.styleable.title_bar_layout_style_text_label_color, Color.BLACK);
        typedArray.recycle();

        setTitle(title);
//        setRootViewBackgroundColor(mBackgroundColor);
//        setTextLabelColor(mTextLabelColor);
    }

    public TitleBar setRootViewBackgroundColor(@ColorInt int color) {
        mRootView.setBackgroundColor(color);
        return this;
    }

    public TitleBar setRootViewBackgroundColorRes(@ColorRes int resId) {
        mRootView.setBackgroundColor(mContext.getResources().getColor(resId));
        return this;
    }

    public TitleBar setRootViewBackgroundResource(@DrawableRes int backgroundRes) {
        mRootView.setBackgroundResource(backgroundRes);
        return this;
    }

    public TitleBar setTitle( CharSequence title) {
        mTvTitle.setText(title);
        return this;
    }

    public TitleBar setTitleColorRes(@StringRes int resId) {
        mTvTitle.setText(resId);
        return this;
    }

    public TitleBar initBackOnClickListener(@Nullable OnClickListener l) {
        if (l != null) {
            mIvBack.setVisibility(View.VISIBLE);
            mIvBack.setOnClickListener(l);
        }
        return this;
    }
}

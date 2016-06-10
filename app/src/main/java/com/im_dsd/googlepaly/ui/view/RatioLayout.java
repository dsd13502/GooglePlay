package com.im_dsd.googlepaly.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.im_dsd.googlepaly.R;

/**
 * Created by im_dsd on 16-6-10.
 */

public class RatioLayout extends FrameLayout {

    private float ratio;

    public RatioLayout(Context context) {
        super(context,null,0);
    }

    public RatioLayout(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public RatioLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //加载自定义属性的值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout);

        // 根据属性id获取属性值, 方式: R.styleable.名称_属性
        ratio = typedArray.getFloat(R.styleable.RatioLayout_ratio, 0);

        //释放内存
        typedArray.recycle();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RatioLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

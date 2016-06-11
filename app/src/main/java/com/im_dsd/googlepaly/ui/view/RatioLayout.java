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

        //获取宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //获取宽度的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //获取高度
        int heitgtSize = MeasureSpec.getSize(heightMeasureSpec);
        //获取高度的测量模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //在宽可以确定下来，高不确定模式，并且 ratio不为 0 时，
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY
                && ratio != 0)
        {
            //使用布局的宽度减去左右的 padding 获取的就是 imageView的宽度
            int imageWidth = widthSize - getPaddingLeft() - getPaddingRight();

            //根据比例获取imageView的高度
            int imageHeight = (int)(imageWidth / ratio);

            //推算出布局的高度
            heitgtSize = imageHeight + getPaddingBottom() + getPaddingTop();


            //根据布局高度, 推算heightMeasureSpec
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heitgtSize,MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

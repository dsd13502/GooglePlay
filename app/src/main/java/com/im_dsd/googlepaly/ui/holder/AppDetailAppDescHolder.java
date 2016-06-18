package com.im_dsd.googlepaly.ui.holder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.AppDetailBean;
import com.im_dsd.googlepaly.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by im_dsd on 16-6-18.
 */

public class AppDetailAppDescHolder extends BaseHolder<AppDetailBean> {
    public static final String TAG = "AppDetailAppDescHolder";
    @Bind(R.id.tv_detail_des)
    TextView tvDetailDes;
    @Bind(R.id.tv_detail_author)
    TextView tvDetailAuthor;
    @Bind(R.id.iv_arrow)
    ImageView ivArrow;
    @Bind(R.id.rl_detail_toggle)
    RelativeLayout rlDetailToggle;
    private ViewGroup.LayoutParams mParams;
    private boolean isOpen = false;
    private int mShortHeight;
    private int mLongHeight;

    @Override
    public View setItemView() {
        View view = UIUtils.inflate(R.layout.layout_detail_desinfo);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void refreshView(final AppDetailBean data) {
        if (data != null)
        {

            tvDetailAuthor.setText(data.getAuthor());

            tvDetailDes.post(new Runnable() {


                @Override
                public void run() {
                    mParams = tvDetailDes.getLayoutParams();
                    mShortHeight = getShortHeight(data.getDes());
                    mLongHeight = getLongHeight(data.getDes());
                    mParams.height = mShortHeight;
                    tvDetailDes.setLayoutParams(mParams);
                    tvDetailDes.setText(data.getDes());
                }
            });
        }

    }

    @OnClick({R.id.iv_arrow,R.id.rl_detail_toggle,R.id.tv_detail_des})
    public void onClick() {

        toggle();
    }

    private void toggle() {
        isOpen = !isOpen;
        ValueAnimator animator = null;
        Log.i(TAG, "toggle: mShortHeight = " + mShortHeight);
        Log.i(TAG, "toggle: mLongHeight = " + mLongHeight);

        if (isOpen)
        {
            animator = ValueAnimator.ofInt(mShortHeight,mLongHeight);

        }
        else
        {
            animator = ValueAnimator.ofInt(mLongHeight,mShortHeight);
        }

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                mParams.height = value;
                tvDetailDes.setLayoutParams(mParams);
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isOpen)
                {
                    ivArrow.setBackgroundResource(R.drawable.arrow_up);
                }
                else
                {
                    ivArrow.setBackgroundResource(R.drawable.arrow_down);
                }

                final ScrollView scrollView = getScrollView();
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.setDuration(200);
        animator.start();
    }

    public int getShortHeight(String str) {
        TextView textView = new TextView(UIUtils.getContext());
        //设置最大为7行
        textView.setMaxLines(7);
        textView.setText(str);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
        int measuredWidth = tvDetailDes.getMeasuredWidth();

        // 结合模式和具体值,定义一个宽度和高度的参数
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth,
                View.MeasureSpec.EXACTLY);// 宽度填充屏幕,已经确定, 所以是EXACTLY
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(2000,
                View.MeasureSpec.AT_MOST);// 高度不确定, 模式是包裹内容, 有多高展示多高, 所以是AT_MOST.
        // 这里的2000是高度最大值, 也可以设置为屏幕高度
        textView.measure(widthMeasureSpec,heightMeasureSpec);

        return textView.getMeasuredHeight();
    }

    public int getLongHeight(String str)
    {
        TextView textView = new TextView(UIUtils.getContext());
        textView.setText(str);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
        int measuredWidth = tvDetailDes.getMeasuredWidth();

        // 结合模式和具体值,定义一个宽度和高度的参数
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth,
                View.MeasureSpec.EXACTLY);// 宽度填充屏幕,已经确定, 所以是EXACTLY
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(2000,
                View.MeasureSpec.AT_MOST);// 高度不确定, 模式是包裹内容, 有多高展示多高, 所以是AT_MOST.
        // 这里的2000是高度最大值, 也可以设置为屏幕高度
        textView.measure(widthMeasureSpec,heightMeasureSpec);

        return textView.getMeasuredHeight();
    }

    /**
     * 获取布局中的ScrollView对象 注意: 必须保证布局中有ScrollView, 否则会陷入死循环
     *
     * @return
     */
    private ScrollView getScrollView() {
        View parent = (View) tvDetailDes.getParent();
        // 通过while循环,一层一层往上找, 直到找到ScrollView后结束
        while (!(parent instanceof ScrollView)) {
            parent = (View) parent.getParent();
        }

        return (ScrollView) parent;
    }
}

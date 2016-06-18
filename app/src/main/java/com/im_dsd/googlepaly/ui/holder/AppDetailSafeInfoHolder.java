package com.im_dsd.googlepaly.ui.holder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.AppDetailBean;
import com.im_dsd.googlepaly.http.HttpHelper;
import com.im_dsd.googlepaly.utils.BitmapHelper;
import com.im_dsd.googlepaly.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.im_dsd.googlepaly.R.drawable.subject_default;

/**
 * Created by im_dsd on 16-6-18.
 */

public class AppDetailSafeInfoHolder extends BaseHolder<AppDetailBean> {

    public static final String TAG = "AppDetailSafeInfoHolder";
    private ImageView[] ivSafes;// 安全标识的控件数组
    private LinearLayout[] llDes;// 安全描述控件数组
    private ImageView[] ivDes;// 安全描述图片控件数组
    private TextView[] tvDes;// 安全描述文字控件数组

    private LayoutParams mParams;// 安全描述整体控件布局参数
    private boolean isExpanded = false;

    @Bind(R.id.iv_arrow)
    ImageView ivArrow;
    @Bind(R.id.rl_des_root)
    RelativeLayout rlDesRoot;
    @Bind(R.id.ll_des_root)
    LinearLayout llDesRoot;
    private BitmapUtils mBitmapUtils;
    private int llDesRootMeasuredHeight;

    @Override
    public View setItemView() {
        View view = UIUtils.inflate(R.layout.layout_detail_safeinfo);
        ButterKnife.bind(this, view);

        mBitmapUtils = BitmapHelper.getInstance();
        mBitmapUtils.configDefaultLoadingImage(subject_default);

        mParams = llDesRoot.getLayoutParams();
        mParams.height = 0;
        //设置高度为0，将布局隐藏起来
        llDesRoot.setLayoutParams(mParams);


        ivSafes = new ImageView[4];
        ivSafes[0] = (ImageView) view.findViewById(R.id.iv_safe1);
        ivSafes[1] = (ImageView) view.findViewById(R.id.iv_safe2);
        ivSafes[2] = (ImageView) view.findViewById(R.id.iv_safe3);
        ivSafes[3] = (ImageView) view.findViewById(R.id.iv_safe4);

        llDes = new LinearLayout[4];
        llDes[0] = (LinearLayout) view.findViewById(R.id.ll_des1);
        llDes[1] = (LinearLayout) view.findViewById(R.id.ll_des2);
        llDes[2] = (LinearLayout) view.findViewById(R.id.ll_des3);
        llDes[3] = (LinearLayout) view.findViewById(R.id.ll_des4);

        ivDes = new ImageView[4];
        ivDes[0] = (ImageView) view.findViewById(R.id.iv_des1);
        ivDes[1] = (ImageView) view.findViewById(R.id.iv_des2);
        ivDes[2] = (ImageView) view.findViewById(R.id.iv_des3);
        ivDes[3] = (ImageView) view.findViewById(R.id.iv_des4);

        tvDes = new TextView[4];
        tvDes[0] = (TextView) view.findViewById(R.id.tv_des1);
        tvDes[1] = (TextView) view.findViewById(R.id.tv_des2);
        tvDes[2] = (TextView) view.findViewById(R.id.tv_des3);
        tvDes[3] = (TextView) view.findViewById(R.id.tv_des4);

        return view;
    }

    @Override
    public void refreshView(AppDetailBean data) {

          if (data != null)
          {
              List<AppDetailBean.SafeInfo> safeInfoList = data.getSafe();
              if (safeInfoList != null)
              {
                  for(int i = 0; i <4; i ++)
                  {
                      if (i < safeInfoList.size())
                      {
                          AppDetailBean.SafeInfo safeInfo = safeInfoList.get(i);
                          ivSafes[i].setVisibility(View.VISIBLE);
                          llDes[i].setVisibility(View.VISIBLE);

                          tvDes[i].setText(safeInfo.getSafeDes());
                          mBitmapUtils.display(ivSafes[i], HttpHelper.URL
                                  + "image?name=" + safeInfo.getSafeUrl());
                          mBitmapUtils.display(ivDes[i], HttpHelper.URL
                                  + "image?name=" + safeInfo.getSafeDesUrl());
                      }
                      else
                      {
                          ivSafes[i].setVisibility(View.GONE);
                          llDes[i].setVisibility(View.GONE);
                      }
                  }

                  llDesRoot.measure(0,0);
                  //获取实际高度
                  llDesRootMeasuredHeight = llDesRoot.getMeasuredHeight();
                  Log.i(TAG, "refreshView: llDesRootMeasuredHeight = " + llDesRootMeasuredHeight) ;
              }
          }
    }

    @OnClick({R.id.iv_arrow,R.id.rl_des_root})
    public void onClick() {

        toggle();
        Log.i(TAG, "onClick: toogle");
    }

    //开关
    private void toggle() {

        ValueAnimator valueAnimator = null;

        if (isExpanded) {
            // 收起描述信息
            isExpanded = false;

            // 初始化按指定值变化的动画器, 布局高度从mDesRootHeight变化到0,此方法调用,并开启动画之后,
            // 会将最新的高度值不断回调在onAnimationUpdate方法中,在onAnimationUpdate中更新布局高度
            valueAnimator = ValueAnimator.ofInt(llDesRootMeasuredHeight, 0);
        } else {
            // 展开描述信息
            isExpanded = true;
//            Log.i(TAG, "toggle:   isExpanded = true");
            // 初始化按指定值变化的动画器, 布局高度从0变化到mDesRootHeight
            valueAnimator = ValueAnimator.ofInt(0, llDesRootMeasuredHeight);
        }

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                Integer animatedValue = (Integer) animation.getAnimatedValue();
                Log.i(TAG, "onAnimationUpdate: animatedValue = " + animatedValue);
                mParams.height = animatedValue;
                llDesRoot.setLayoutParams(mParams);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isExpanded)
                {
                    ivArrow.setBackgroundResource(R.drawable.arrow_up);
                }
                else
                {
                    ivArrow.setBackgroundResource(R.drawable.arrow_down);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimator.setDuration(200);
        valueAnimator.start();
    }
}

package com.im_dsd.googlepaly.ui.fragment;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.im_dsd.googlepaly.http.protocol.HostProtocol;
import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.ui.view.MyFlowLayout;
import com.im_dsd.googlepaly.utils.DrawableUtils;
import com.im_dsd.googlepaly.utils.UIUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 *排行
 * Created by im_dsd on 16-6-3.
 */
public class HotFragment extends BaseFragment {

    private ArrayList<String> mDataList;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View OnCreateSuccessView() {
        //为了使布局可以上下滑动，需要使用ScrollView
        ScrollView scrollView = new ScrollView(UIUtils.getContext());
        int padding = UIUtils.dip2px(10);
        //设置ScrollView的边距
        scrollView.setPadding(padding,padding,padding,padding);

        //初始化自定义控件
        MyFlowLayout flowLayout = new MyFlowLayout(UIUtils.getContext());

        for (final String str :
                mDataList) {
            TextView tv = new TextView(UIUtils.getContext());
            tv.setText(str);
            //设置字体颜色为白色
            tv.setTextColor(Color.WHITE);
            //设置字体居中
            tv.setGravity(Gravity.CENTER);
            //设置文字的padding
            tv.setPadding(padding,padding,padding,padding);

            //设置文字随机的颜色
            Random random = new Random();
            int r = 30 + random.nextInt(210);
            int g = 30 + random.nextInt(210);
            int b = 30 + random.nextInt(210);


            int color = 0xffcecece;// 按下后偏白的背景色

            // 根据默认颜色和按下颜色, 生成圆角矩形的状态选择器
            Drawable selector = DrawableUtils.getStateListDrawable(
                    Color.rgb(r, g, b), color, UIUtils.dip2px(6));

            tv.setBackground(selector);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Toast.makeText(UIUtils.getContext(), str,
                                Toast.LENGTH_SHORT).show();
                }
            });
            flowLayout.addView(tv);

        }

        scrollView.addView(flowLayout);
        return scrollView;
    }

    @Override
    public LoadingPage.ResultState OnLoadDate() {

        HostProtocol protocol = new HostProtocol();
        mDataList = protocol.getData(0);

        return check(mDataList);
    }


}

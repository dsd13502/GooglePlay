package com.im_dsd.googlepaly.ui.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.http.HttpHelper;
import com.im_dsd.googlepaly.utils.BitmapHelper;
import com.im_dsd.googlepaly.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

import static com.im_dsd.googlepaly.R.drawable.indicator_normal;
import static com.im_dsd.googlepaly.R.drawable.indicator_selected;

/**
 * Created by im_dsd on 16-6-17.
 */

public class HomeHeaderHolder extends BaseHolder<ArrayList<String>> {

    private  ArrayList<String> mDataList;
    public static final String TAG = "HomeHeaderHolder";
    private ViewPager mViewPager;
    private LinearLayout mIndicator;
    private int mPosition = 0;

    @Override
    public View setItemView() {

        //初始化轮播条的根布局
        RelativeLayout relativeLayout = new RelativeLayout(UIUtils.getContext());

        AbsListView.LayoutParams relativeLayoutParams = new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT,
               UIUtils.dip2px(150));
        //设置根部布局的位置
        relativeLayout.setLayoutParams(relativeLayoutParams);


        //初始化轮播条布局
        mViewPager = new ViewPager(UIUtils.getContext());
        //设置轮播条大小
        RelativeLayout.LayoutParams viewPagerParams = new  RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        mViewPager.setLayoutParams(viewPagerParams);


        //【1】初始化轮播指示器
        mIndicator = new LinearLayout(UIUtils.getContext());
        //【2】设置轮播指示器大小
        RelativeLayout.LayoutParams indicatorParams = new  RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        //【3】为 indicator 设置规则
        indicatorParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        indicatorParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        //【4】添加布局参数
        mIndicator.setLayoutParams(indicatorParams);
        //【5】设置 mIndicator padding
        int padding = UIUtils.dip2px(5);
        mIndicator.setPadding(padding,padding,padding,padding);


        //将轮播条布局添加到根布局中
        relativeLayout.addView(mViewPager);
        //将mIndicator添加到根布局中
        relativeLayout.addView(mIndicator);



        return relativeLayout;
    }

    @Override
    public void refreshView(ArrayList<String> data) {

        mViewPager.setAdapter(new MyAdapter(data));
        mViewPager.setCurrentItem(10000 * mDataList.size());


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < mDataList.size(); i++)
        {

            ImageView view = new ImageView(UIUtils.getContext());

            if (i == 0) {
                Log.i(TAG, "refreshView: i==0");
                view.setImageResource(indicator_selected);
            } else {
                view.setImageResource(R.drawable.indicator_normal);
                params.leftMargin = UIUtils.dip2px(3);// 设置圆点间距
            }

            mIndicator.addView(view, params);
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % mDataList.size();

                ImageView currentView = (ImageView) mIndicator.getChildAt(position);

                currentView.setImageResource(indicator_selected);

                if (position != mPosition)
                {
                    ImageView LastView = (ImageView) mIndicator.getChildAt(mPosition);

                    LastView.setImageResource(indicator_normal);

                    mPosition = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //开始轮播。
        new RunnableTask().start();
    }

    class MyAdapter extends PagerAdapter
    {


        private BitmapUtils mBitmapUtils;

        public MyAdapter(ArrayList<String> dataList) {
            mDataList = dataList;
          //  mBitmapUtils.configDefaultLoadingImage(subject_default);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //初始化
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            position = position % mDataList.size();

            ImageView imageView = new ImageView(UIUtils.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

//            ViewPager.LayoutParams params = new ViewPager.LayoutParams();
//            imageView.setLayoutParams();
            mBitmapUtils = BitmapHelper.getInstance();

            String url = HttpHelper.URL + "image?name=" + mDataList.get(position);

           // Log.i(TAG, "instantiateItem: url " + url);
            mBitmapUtils.display(imageView, url);

            container.addView(imageView);

            return imageView;
        }

        //销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View)object);
        }
    }

    class RunnableTask implements Runnable {

        public void start() {
            // 移除之前遗留的任务(handler只有一个,但HomeFragment有可能多次被创建,
            // 从而导致消息被重复发送,所以需要先把之前的消息移除掉)
            UIUtils.getHandler().removeCallbacksAndMessages(null);
            // 发送延时2秒的任务
            UIUtils.getHandler().postDelayed(this, 2000);
        }

        @Override
        public void run() {
            // 跳到viewpager下一个页面
            int currentItem = mViewPager.getCurrentItem();
            currentItem++;
            mViewPager.setCurrentItem(currentItem);

            // 继续发送延时两秒的任务, 形成闭环, 达到循环执行的效果
            UIUtils.getHandler().postDelayed(this, 2000);
        }

    }
}

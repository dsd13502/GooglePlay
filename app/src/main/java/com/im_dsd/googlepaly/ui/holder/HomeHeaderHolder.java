package com.im_dsd.googlepaly.ui.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.im_dsd.googlepaly.http.HttpHelper;
import com.im_dsd.googlepaly.utils.BitmapHelper;
import com.im_dsd.googlepaly.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by im_dsd on 16-6-17.
 */

public class HomeHeaderHolder extends BaseHolder<ArrayList<String>> {

    private  ArrayList<String> mDataList;
    public static final String TAG = "HomeHeaderHolder";
    private ViewPager mViewPager;



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



        //将轮播条布局
        relativeLayout.addView(mViewPager);


        return relativeLayout;
    }

    @Override
    public void refreshView(ArrayList<String> data) {

        mViewPager.setAdapter(new MyAdapter(data));
        mViewPager.setCurrentItem(10000 * mDataList.size());
        //开始轮播。
        new RunnableTask().start();
    }

    class MyAdapter extends PagerAdapter
    {


        private BitmapUtils mBitmapUtils;

        public MyAdapter(ArrayList<String> dataList) {
            mDataList = dataList;
           // mBitmapUtils.configDefaultLoadingImage(R.drawable.subject_default);
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

            Log.i(TAG, "instantiateItem: url " + url);
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

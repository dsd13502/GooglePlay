package com.im_dsd.googlepaly.ui.fragment;

import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.im_dsd.googlepaly.http.protocol.RecommendProtocol;
import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.ui.view.fly.StellarMap;
import com.im_dsd.googlepaly.utils.UIUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * 推荐模块
 * Created by im_dsd on 16-6-3.
 */
public class RecommendFragment extends BaseFragment {


    public static final String TAG = "RecommendFragment";
    private ArrayList<String> mDataList;
    private RecommendAdapter mAdapter;

    @Override
    public View OnCreateSuccessView() {

        //初始化飞入飞出自定义控件
        StellarMap stellarMap = new StellarMap(UIUtils.getContext());

        //设置内部文字距边距为10dp
        int padding = UIUtils.dip2px(10);
        stellarMap.setInnerPadding(padding,padding,padding,padding);

        //设置数据源
        stellarMap.setAdapter(mAdapter);

        //定义展示规则，9行6列（具体一随机结果为准）
        stellarMap.setRegularity(6,9);

        //设置默认组为第0组
        stellarMap.setGroup(0,true);

        return stellarMap;
    }

    @Override
    public LoadingPage.ResultState OnLoadDate() {

        RecommendProtocol protocol = new RecommendProtocol();
        mDataList = protocol.getData(0);
        mAdapter = new RecommendAdapter();

        return check(mDataList);
    }

    class RecommendAdapter implements StellarMap.Adapter
    {

        //返回组的数量
        @Override
        public int getGroupCount() {
            return 2;
        }

        //计算每个组下面返回的孩子个数
        @Override
        public int getCount(int group) {
            int count;

//            Log.i(TAG, "getCount: mDataList.size() = " + mDataList.size());

            count = mDataList.size() / getGroupCount();

            //将除不尽的数据加到最后一个组中
            if (group == getGroupCount() -1)
            {
                count += mDataList.size() % getGroupCount();
            }

            return count;
        }

        @Override
        public View getView(int group, int position, View convertView) {

            Log.i(TAG, "getView: group" + group);

            //每次滑动 position 会从0开始重新计数，所以不是第一组时，需要加上前几页的总数
            if (group > 0)
            {
                position += group * getCount(group - 1);
            }

            TextView view = new TextView(UIUtils.getContext());
            view.setText(mDataList.get(position));

            //设置随机文字大小
            Random random = new Random();
            int size = 16 + random.nextInt(10);// 产生16-25的随机数
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);// 以sp为单位设置文字大小

            //设置随机文字颜色
            int r = 30 + random.nextInt(210);// 产生30-239的随机颜色, 绕过0-29,240-255的值,避免颜色过暗或者过亮
            int b = 30 + random.nextInt(210);
            int g = 30 + random.nextInt(210);

            view.setTextColor(Color.rgb(r,b,g));
            return view;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (! isZoomIn)
            {
                //下一组
                if (group < getGroupCount() -1)
                {
                    return  ++ group;
                }
                else
                {
                    return 0;// 如果没有下一页了,就跳到第一组
                }
            }
            else
            {
                // 上一组
                if (group > 0) {
                    return --group;
                } else {
                    return getGroupCount() - 1;// 如果没有上一页了,就跳到最后一组
                }
            }
        }
    }
}

package com.im_dsd.googlepaly.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

import com.im_dsd.googlepaly.domain.SubjectBean;
import com.im_dsd.googlepaly.http.protocol.SubjectProtocol;
import com.im_dsd.googlepaly.ui.adapter.MyBaseAdapter;
import com.im_dsd.googlepaly.ui.holder.BaseHolder;
import com.im_dsd.googlepaly.ui.holder.SubjectHolder;
import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by im_dsd on 16-6-3.
 */
public class SubjectFragment extends BaseFragment {


    private MyAdapter mMyAdapter;
    private ArrayList<SubjectBean> mDataList;

    @Override
    public View OnCreateSuccessView() {

        ListView listView = new ListView(UIUtils.getContext());

        //设置选择后，背景无颜色变化
        listView.setSelector(new ColorDrawable());
        //去掉分割线
        listView.setDivider(null);
        //避免滑动黑边
        listView.setCacheColorHint(Color.TRANSPARENT);

        listView.setAdapter(mMyAdapter);

        return listView;
    }

    @Override
    public LoadingPage.ResultState OnLoadDate() {

        //获取数据
        final SubjectProtocol subjectProtocol = new SubjectProtocol();
        //获取第一页数据
        mDataList = subjectProtocol.getData(0);

        mMyAdapter = new MyAdapter(mDataList);

        //设置加载跟多的监听事件
        mMyAdapter.setOnLoadMoreDataListener(new MyBaseAdapter.OnLoadMoreDataListener() {
            @Override
            public ArrayList OnLoadMoreData() {
                ArrayList<SubjectBean> loadMoreData
                        = subjectProtocol.getData(mDataList.size());
                return loadMoreData;
            }
        });
        return check(mDataList);
    }


    class MyAdapter extends MyBaseAdapter<SubjectBean>
    {

        /**
         * @param mArrayListData 需要使用的数据的集合
         */
        public MyAdapter(ArrayList<SubjectBean> mArrayListData) {
            super(mArrayListData);
        }

        @Override
        public BaseHolder<SubjectBean> getHolder(int position) {
            return new SubjectHolder();
        }
    }
}

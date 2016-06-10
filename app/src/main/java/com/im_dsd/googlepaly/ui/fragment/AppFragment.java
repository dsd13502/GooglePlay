package com.im_dsd.googlepaly.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

import com.im_dsd.googlepaly.domain.AppBean;
import com.im_dsd.googlepaly.http.protocol.AppFragmentProtocol;
import com.im_dsd.googlepaly.ui.adapter.MyBaseAdapter;
import com.im_dsd.googlepaly.ui.holder.AppFragmentHolder;
import com.im_dsd.googlepaly.ui.holder.BaseHolder;
import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by im_dsd on 16-6-3.
 */
public class AppFragment extends BaseFragment {


    private ListView mListView;
    private ArrayList<AppBean> mListData;
    private AppAdapter appAdapter;

    @Override
    public View OnCreateSuccessView() {

        mListView = new ListView(UIUtils.getContext());

        //设置选择后，背景无颜色变化
        mListView.setSelector(new ColorDrawable());
        //去掉分割线
        mListView.setDivider(null);
        //避免滑动黑边
        mListView.setCacheColorHint(Color.TRANSPARENT);

        mListView.setAdapter(appAdapter);

        return mListView;
    }

    @Override
    public LoadingPage.ResultState OnLoadDate() {
        final AppFragmentProtocol protocol = new AppFragmentProtocol();

        mListData = protocol.getData(0);

        appAdapter = new AppAdapter(mListData);

        appAdapter.setOnLoadMoreDataListener(new MyBaseAdapter.OnLoadMoreDataListener() {
            @Override
            public ArrayList OnLoadMoreData() {

                ArrayList<AppBean> loadMoreData = protocol.getData(mListData.size());

                return loadMoreData;
            }
        });
        return check(mListData);
    }


    class AppAdapter extends MyBaseAdapter<AppBean> {
        /**
         * @param mArrayListData 需要使用的数据的集合
         */
        public AppAdapter(ArrayList<AppBean> mArrayListData) {
            super(mArrayListData);
        }

        @Override
        public BaseHolder getHolder() {
            return new AppFragmentHolder();
        }
    }
}

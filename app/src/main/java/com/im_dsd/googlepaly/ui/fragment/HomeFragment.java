package com.im_dsd.googlepaly.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.im_dsd.googlepaly.domain.HomeBean;
import com.im_dsd.googlepaly.http.protocol.HomeProtocol;
import com.im_dsd.googlepaly.ui.adapter.MyBaseAdapter;
import com.im_dsd.googlepaly.ui.holder.BaseHolder;
import com.im_dsd.googlepaly.ui.holder.HomeHolder;
import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by im_dsd on 16-6-3.
 */
public class HomeFragment extends BaseFragment {


    public static final String TAG = "HomeFragment";
    private ListView mListViw = null;
    private ArrayList<HomeBean.AppInfo> mDataList = null;
    private HomeAdapter mHomeAdapter;

    @Override
    public View OnCreateSuccessView() {

        Log.i(TAG,"OnCreateSuccessView");
        mListViw = new ListView(UIUtils.getContext());
        mListViw.setAdapter(mHomeAdapter);

        //设置选择后，背景无颜色变化
        mListViw.setSelector(new ColorDrawable());
        //去掉分割线
        mListViw.setDivider(null);
        //避免滑动黑边
        mListViw.setCacheColorHint(Color.TRANSPARENT);
        return mListViw;
    }

    @Override
    public LoadingPage.ResultState OnLoadDate() {

        //加载数据
        final HomeProtocol protocol = new HomeProtocol();

        HomeBean data = protocol.getData(0);
        if (data != null)
        {
            mDataList = data.getList();
        }
        else
        {
            Log.i(TAG, "OnLoadDate: data == null");
        }


        mHomeAdapter = new HomeAdapter(mDataList);


        mHomeAdapter.setOnLoadMoreDataListener(new MyBaseAdapter.OnLoadMoreDataListener() {
            @Override
            public ArrayList OnLoadMoreData() {

                ArrayList<HomeBean.AppInfo> loadMoreList =
                         protocol.getData(mDataList.size()).getList();

                return loadMoreList;
            }
        });
        return check(mDataList);
    }


    class HomeAdapter extends MyBaseAdapter<HomeBean.AppInfo>
    {

        /**
         *
         * @param arrayList 需要使用到的数据的集合
         */
        public HomeAdapter(ArrayList arrayList) {
            super(arrayList);
        }

        @Override
        public BaseHolder getHolder() {
            return new HomeHolder();
        }


    }


}

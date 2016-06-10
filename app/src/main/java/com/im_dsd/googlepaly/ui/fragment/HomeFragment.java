package com.im_dsd.googlepaly.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.im_dsd.googlepaly.domain.AppInfoBean;
import com.im_dsd.googlepaly.http.protocol.HomeFragmentProtocol;
import com.im_dsd.googlepaly.ui.adapter.MyBaseAdapter;
import com.im_dsd.googlepaly.ui.holder.BaseHolder;
import com.im_dsd.googlepaly.ui.holder.HomeFragmentHolder;
import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by im_dsd on 16-6-3.
 */
public class HomeFragment extends BaseFragment {


    public static final String TAG = "HomeFragment";
    private ListView mListViw = null;
    private ArrayList<AppInfoBean.AppInfo> mDataList = null;
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
        final HomeFragmentProtocol protocol = new HomeFragmentProtocol();

        AppInfoBean data = protocol.getData(0);
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

                ArrayList<AppInfoBean.AppInfo> loadMoreList =
                         protocol.getData(mDataList.size()).getList();
                SystemClock.sleep(1000);

                return loadMoreList;
            }
        });
        return check(mDataList);
    }


    class HomeAdapter extends MyBaseAdapter<AppInfoBean.AppInfo>
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
            return new HomeFragmentHolder();
        }


    }


}

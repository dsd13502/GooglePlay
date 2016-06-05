package com.im_dsd.googlepaly.ui.fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

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
    private ArrayList<String> list = null;
    private HomeAdapter mHomeAdapter;

    @Override
    public View OnCreateSuccessView() {

        Log.i(TAG,"OnCreateSuccessView");
        mListViw = new ListView(UIUtils.getContext());
        mListViw.setAdapter(mHomeAdapter);
        return mListViw;
    }

    @Override
    public LoadingPage.ResultState OnLoadDate() {

        Log.i(TAG,"OnLoadDate");
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++)
        {
            list.add("模拟数据 " + i);
        }
        mHomeAdapter = new HomeAdapter(list);


        mHomeAdapter.setOnLoadMoreDataListener(new MyBaseAdapter.OnLoadMoreDataListener() {
            @Override
            public ArrayList OnLoadMoreData() {

                ArrayList<String> loadMoreList = new ArrayList<>();
                for (int i = 0; i < 10; i++)
                {
                    loadMoreList.add("加载更多数据 " + i);
                }
                SystemClock.sleep(1000);

                return loadMoreList;
            }
        });
        return LoadingPage.ResultState.STATE_SUCCESS;
    }


    class HomeAdapter extends MyBaseAdapter<String>
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

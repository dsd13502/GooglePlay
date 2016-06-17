package com.im_dsd.googlepaly.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.im_dsd.googlepaly.domain.HomeBean;
import com.im_dsd.googlepaly.http.protocol.HomeProtocol;
import com.im_dsd.googlepaly.ui.adapter.MyBaseAdapter;
import com.im_dsd.googlepaly.ui.holder.BaseHolder;
import com.im_dsd.googlepaly.ui.holder.HomeHeaderHolder;
import com.im_dsd.googlepaly.ui.holder.HomeHolder;
import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.ui.view.MyListView;
import com.im_dsd.googlepaly.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by im_dsd on 16-6-3.
 */
public class HomeFragment extends BaseFragment {


    public static final String TAG = "HomeFragment";
    private MyListView mListViw = null;
    private ArrayList<HomeBean.AppInfo> mDataList = null;
    private HomeAdapter mHomeAdapter;
    private HomeHeaderHolder mHomeHeaderHolder;
    private ArrayList<String> mPictureList = null;


    @Override
    public View OnCreateSuccessView() {

        Log.i(TAG,"OnCreateSuccessView");
        mListViw = new MyListView(UIUtils.getContext());
        mListViw.setAdapter(mHomeAdapter);

        //添加头布局
        mListViw.addHeaderView(mHomeHeaderHolder.getItemRootView());

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
            mPictureList = data.getPicture();

            //初始化头布局Holder
            mHomeHeaderHolder = new HomeHeaderHolder();
            //初始化头布局Holder的数据
            mHomeHeaderHolder.setData(mPictureList);

            mHomeAdapter = new HomeAdapter(mDataList);
            mHomeAdapter.setOnLoadMoreDataListener(new MyBaseAdapter.OnLoadMoreDataListener() {
                @Override
                public ArrayList OnLoadMoreData() {

                    ArrayList<HomeBean.AppInfo> loadMoreList =
                            protocol.getData(mDataList.size()).getList();

                    return loadMoreList;
                }
            });
        }
        else
        {
            Log.i(TAG, "OnLoadDate: data == null");
            UIUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UIUtils.getContext(), "网络异常请检查网络", Toast.LENGTH_SHORT).show();
                }
            });
        }


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
        public BaseHolder getHolder(int position) {
            return new HomeHolder();
        }


    }


}

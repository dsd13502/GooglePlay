package com.im_dsd.googlepaly.ui.fragment;

import android.util.Log;
import android.view.View;

import com.im_dsd.googlepaly.domain.CategoryBean;
import com.im_dsd.googlepaly.http.protocol.CategoryProtocol;
import com.im_dsd.googlepaly.ui.adapter.MyBaseAdapter;
import com.im_dsd.googlepaly.ui.holder.BaseHolder;
import com.im_dsd.googlepaly.ui.holder.CategoryHolder;
import com.im_dsd.googlepaly.ui.holder.TitleHolder;
import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.ui.view.MyListView;
import com.im_dsd.googlepaly.utils.UIUtils;

import java.util.ArrayList;

/**
 * 分类
 * Created by im_dsd on 16-6-3.
 */
public class CategoryFragment extends BaseFragment {


    public static final String TAG = "CategoryFragment";

    private ArrayList<String> mTitleList;
    private ArrayList<CategoryBean> mDataList;

    @Override
    public View OnCreateSuccessView() {

        MyListView myListView = new MyListView(UIUtils.getContext());

        myListView.setAdapter(new CategoryAdapter(mDataList));

        return myListView;
    }

    @Override
    public LoadingPage.ResultState OnLoadDate() {

        CategoryProtocol protocol = new CategoryProtocol();

        mDataList = protocol.getData(0);

        Log.i(TAG, "OnLoadDate: mDataList.size()  " + mDataList.size());
        return check(mDataList);
    }

    class CategoryAdapter extends MyBaseAdapter<CategoryBean>
    {


        private int position;

        @Override
        public boolean hasLoadMore() {
            //去掉加载更多功能
            return false;
        }


        @Override
        public int getInnerType(int position) {

            if (getItem(position).isTitle())
            {
                return  super.getInnerType(position) + 1;
            }
            else
            {

                return super.getInnerType(position);

            }

        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 1;
        }



        /**
         * @param mArrayListData 需要使用的数据的集合
         */
        public CategoryAdapter(ArrayList<CategoryBean> mArrayListData) {
            super(mArrayListData);

        }

        @Override
        public BaseHolder<CategoryBean> getHolder(int position) {



            Log.i(TAG, "getHolder: position" + position);
            if (getItem(position).isTitle())
            {
                return  new TitleHolder();
            }
            else
            {

                return new CategoryHolder();
            }

        }
    }
}

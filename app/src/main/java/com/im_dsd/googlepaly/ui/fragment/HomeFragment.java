package com.im_dsd.googlepaly.ui.fragment;

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
    private ListView mListViw = new ListView(UIUtils.getContext());
    private ArrayList<String> list = new ArrayList<>();

    @Override
    public View OnCreateSuccessView() {

        Log.i(TAG,"OnCreateSuccessView");
        mListViw.setAdapter(new HomeAdapter(list));
        return mListViw;
    }

    @Override
    public LoadingPage.ResultState OnLoadDate() {

        for (int i = 0; i < 20; i++)
        {
            list.add("模拟数据 " + i);
        }

        return LoadingPage.ResultState.STATE_SUCCESS;
    }


    class HomeAdapter extends MyBaseAdapter<String>
    {

        public HomeAdapter(ArrayList arrayList) {
            super(arrayList);
        }

        @Override
        public BaseHolder getHolder() {
            return new HomeFragmentHolder();
        }
    }

}

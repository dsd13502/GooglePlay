package com.im_dsd.googlepaly.ui.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.im_dsd.googlepaly.ui.holder.BaseHolder;

import java.util.ArrayList;

/**
 * 万能的BaseAapter
 * Created by im_dsd on 16-6-4.
 */

public abstract class MyBaseAdapter<T>  extends BaseAdapter {

    private static final String TAG = "MyBaseAdapter";
    private final ArrayList<T> arrayList;

    /**
     *
     * @param arrayList 类型为T的数据
     */
    public MyBaseAdapter(ArrayList<T> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public T getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        BaseHolder viewHolder = null;

        if (convertView != null) {
            view = convertView;
            viewHolder = ((BaseHolder) view.getTag());
        } else {
            viewHolder = getHolder();
        }

        Log.i(TAG,"getItem(position)" + getItem(position));
        //设置数据
        viewHolder.setData(getItem(position));

        //设置布局
        view = viewHolder.getItemRootView();

        return view;
    }


    public abstract BaseHolder<T> getHolder();
}
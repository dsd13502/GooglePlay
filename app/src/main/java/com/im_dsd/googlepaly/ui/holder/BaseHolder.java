package com.im_dsd.googlepaly.ui.holder;

import android.view.View;

/**
 * viewHolder的基类
 * Created by im_dsd on 16-6-4.
 */

public abstract class BaseHolder<T> {

    private final View mItemRootView;
    private T mData;

    public BaseHolder() {
        mItemRootView = setItemView();
        //设置Tag
        mItemRootView.setTag(this);
    }

    /**
     * 设置Item的布局，
     *
     * @return Item的布局
     */
    public abstract View setItemView();

    /**
     * 获取Item的根布局
     *
     * @return
     */
    public View getItemRootView() {
        return mItemRootView;
    }

    /**
     * 设置数据
     * @param data
     */
    public void setData(T data) {
        mData = data;
        refreshView(data);
    }

    /**
     * 获取数据
     * @return
     */
    public T getData()
    {
        return mData;
    }
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * 根据数据刷新界面,为各个控件设置数据
     * @param data
     */
    public abstract void refreshView(T data);


}

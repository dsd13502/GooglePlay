package com.im_dsd.googlepaly.utils;

import com.lidroid.xutils.BitmapUtils;

/**
 * 获取BitmapUtils的单例模式，保证多个模块共用一个BitmapUtils对象,避免内存溢出
 * Created by im_dsd on 16-6-10.
 */


public class BitmapHelper  {

    //饿汉模式
    private BitmapHelper(){}

    private static BitmapUtils mBitmapHelper = new BitmapUtils(UIUtils.getContext());

    public static BitmapUtils getInstance()
    {
        return mBitmapHelper;
    }

}

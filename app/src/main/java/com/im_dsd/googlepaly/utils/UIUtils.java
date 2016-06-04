package com.im_dsd.googlepaly.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;

import com.im_dsd.googlepaly.global.GooglePlayApplication;

/**
 * Created by im_dsd on 16-5-30.
 */
public class UIUtils {

    public static final String TAG = "UIUtils";
    private static Runnable runnable;


    /**
     * 获取在GooglePalyApplication中获取的Context
     *
     * @return context
     */
    public static Context getContext() {
        return GooglePlayApplication.getContext();
    }

    /**
     * @return
     */
    public static Handler getHandler() {
        return GooglePlayApplication.getHandler();
    }

    /**
     * 获取主现成ID
     *
     * @return
     */
    public static int getMainThreadId() {
        return GooglePlayApplication.getMainThreadId();
    }

    public static int dip2px(float dp) {

        float density = getContext().getResources().getDisplayMetrics().density;
        Log.i(TAG, "density: "+density);
        return (int) (density * dp);
    }

    /**
     * 通过id获取颜色的状态选择器
     *
     * @param colorId 颜色选择器的id
     * @return 颜色的状态选择器
     */
    public static ColorStateList getColorStateList(int colorId) {
        return getContext().getResources().getColorStateList(colorId);
    }

    /**
     * 通过id获取drawable资源文件
     *
     * @param drawableId 资源Id
     * @return rawable资源文件
     */
    public static Drawable getDrawable(int drawableId) {
        return getContext().getResources().getDrawable(drawableId);
    }

    /**
     * 对于inflate的封装
     * @param resource layout的ID
     * @return View.inflate(getContext(), resource,null);
     */
    public static View inflate(@LayoutRes int resource)
    {
        return View.inflate(getContext(), resource,null);
    }

    public static String[] getStringArrayResources(int id)
    {
        return getContext().getResources().getStringArray(id);
    }

    public static String getStringResources(int id)
    {
        return  getContext().getResources().getString(id);
    }

    public static boolean isRunOnUiThrad()
    {
        return getMainThreadId() == android.os.Process.myTid();
    }



    public static void runOnUiThread(Runnable runnable)
    {

        if (isRunOnUiThrad())
        {
            runnable.run();
        }
        else
        {
            getHandler().post(runnable);
        }
    }


}

package com.im_dsd.googlepaly.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.im_dsd.googlepaly.utils.CrashHandler;


/**
 * Created by im_dsd on 16-5-30.
 */
public class GooglePlayApplication extends Application {

    private static  Context context;
    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        handler = new Handler();
        //获取主线程id
        mainThreadId = android.os.Process.myTid();

        //补货异常
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }


    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

}

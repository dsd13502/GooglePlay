package com.im_dsd.googlepaly.manager;

import com.im_dsd.googlepaly.domain.DownloadInfoBean;

import java.util.ArrayList;

/**
 * 下载管理器-单例
 * 使用观察者模式进行构建
 * Created by im_dsd on 16-6-19.
 */

public class DownLoadManager {
    public static final int STATE_NONE = 0;// 下载未开始
    public static final int STATE_WAITING = 1;// 等待下载
    public static final int STATE_DOWNLOAD = 2;// 正在下载
    public static final int STATE_PAUSE = 3;// 下载暂停
    public static final int STATE_ERROR = 4;// 下载失败
    public static final int STATE_SUCCESS = 5;// 下载成功
    public static final String TAG = "DownLoadManager";

    //所有观察者的集合
    private ArrayList<DownloadObserver> mObserver = new ArrayList<DownloadObserver>();


    //【2】注册观察者
    /**
     * 注册观察者
     * @param downloadObserver
     */
    public synchronized void registerObserver(DownloadObserver downloadObserver)
    {
        if (!mObserver.contains(downloadObserver))
        {
            if (downloadObserver != null)
            {
                mObserver.add(downloadObserver);
            }
            else
            {

                new RuntimeException(TAG +" : registerObserver() -> downloadObserver 不能为 null");
            }

        }
    }
    //【3】销毁观察者

    /**
     * 销毁观察者
     * @param downloadObserver
     */
    public synchronized void unregisterObserver(DownloadObserver downloadObserver)
    {
        if (!mObserver.contains(downloadObserver))
        {
            if (downloadObserver != null)
            {
                mObserver.remove(downloadObserver);
            }
            else
            {

                new RuntimeException(TAG +" : unregisterObserver() -> downloadObserver 不能为 null");
            }

        }
    }
    //【4】通知下载状态变化

    /**
     * 通知下载状态变化
     * @param downloadInfo
     */
    public synchronized void notifyDownloadStateChanged(DownloadInfoBean downloadInfo)
    {
        for (DownloadObserver observer : mObserver) {
            observer.onDownloadStateChanged(downloadInfo);
        }
    }

    /**
     * 通知下载进度变化
     * @param downloadInfo
     */
    //【5】通知下载进度变化
    public synchronized void notifyDownloadProgressChanged(DownloadInfoBean downloadInfo)
    {
        for (DownloadObserver observer : mObserver) {
            observer.onDownloadProgessChanged(downloadInfo);
        }
    }

    //【1】定义观察者接口
    /**
     * 观察者接口
     */
    public interface DownloadObserver{
        /**
         * 下载状态发生变化
         */
        public void onDownloadStateChanged(DownloadInfoBean downloadInfo);
        /**
         * 下载进度发生变化
         */
        public void onDownloadProgessChanged(DownloadInfoBean downloadInfo);
    }
}

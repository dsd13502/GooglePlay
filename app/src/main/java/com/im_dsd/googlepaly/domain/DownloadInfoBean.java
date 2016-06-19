package com.im_dsd.googlepaly.domain;

import android.os.Environment;

import com.im_dsd.googlepaly.manager.DownLoadManager;

import java.io.File;

/**
 * 下载对象封装
 * Created by im_dsd on 16-6-19.
 * @author im_dsd
 */

public class DownloadInfoBean {
    private  final  static String TAG = "DownloadInfoBean";

    public String id;// apk唯一标识
    public long size;// apk大小
    public String downloadUrl;// 下载链接
    public String name;// apk名称
    public int currentState;// 当前下载状态
    public long currentPos;// 当前下载位置
    public String path;// apk下载在本地的路径

    private static final String GOOGLEMARKET = "googlemarket";// 文件夹名称
    private static final String DOWNLOAD = "download";// 子文件夹名称

    /**
     * 根据应用信息，拷贝出一个下载对象
     * @param appInfo 从首页点击时的对象信息
     * @return 将相关信息拷贝完成的 DownloadInfoBean
     */
    public  DownloadInfoBean copy(HomeBean.AppInfo appInfo)
    {
        DownloadInfoBean infoBean = new DownloadInfoBean();
        infoBean.id = String.valueOf(appInfo.getId());
        infoBean.size = appInfo.getSize();
        infoBean.downloadUrl = appInfo.getDownloadUrl();
        infoBean.name = appInfo.getName();
        infoBean.currentPos = 0;
        infoBean.currentState = DownLoadManager.STATE_NONE;
        infoBean.path = getFilePath(appInfo.getName());
        return infoBean;
    }

    /**
     * 获取apk文件的本地下载路径
     */
    public  String getFilePath(String name)
    {
        StringBuffer sb = new StringBuffer();
        String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        sb.append(sdcardPath);
        sb.append(File.separator);
        sb.append(GOOGLEMARKET);
        sb.append(File.separator);

        if (createDir(sb.toString()))
        {
            return sb.toString();
        }
        else
        {
            new RuntimeException(TAG + "创建文件夹异常");
            return null;
        }
    }
    /**
     * 获取当前下载进度
     */
    public float getProgress()
    {
        if (size == 0) {
            return 0;
        }

        return (float) currentPos / size;
    }
    /**
     * 创建文件夹
     */

    private boolean createDir(String path)
    {
        File file = new File(path);
        if (!file.exists())
        {
            return file.mkdirs();
        }

        return true;
    }
}

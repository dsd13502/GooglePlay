package com.im_dsd.googlepaly.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.AppDetailBean;
import com.im_dsd.googlepaly.http.protocol.AppDetailProtocol;
import com.im_dsd.googlepaly.ui.holder.AppDetailAppInfoHolder;
import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.utils.ConstantValuesUtils;
import com.im_dsd.googlepaly.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AppDetailActivity extends BaseActivity {
    public static final String TAG = "AppDetailActivity";
    @Bind(R.id.fll_detail_app_info)
    FrameLayout flAppDetailAppInfo;
    @Bind(R.id.fl_detail_safe_info)
    FrameLayout flAppDetailSafeInfo;
    @Bind(R.id.hsv_app_detail_pics)
    HorizontalScrollView hsvAppDetailPics;
    @Bind(R.id.fl_app_detail_des)
    FrameLayout flAppDetailDes;
    @Bind(R.id.ll_app_detail_root_view)
    LinearLayout llAppDetailRootView;
    @Bind(R.id.activity_app_detail)
    ScrollView activityAppDetail;
    private LoadingPage mLoadingPage;
    private String mPackageName;
    private AppDetailBean mData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoadingPage = new LoadingPage(UIUtils.getContext()) {
            @Override
            public View OnCreateSuccessView() {

                return AppDetailActivity.this.OnCreateSuccessView();
            }

            @Override
            public ResultState OnLoadDate() {

                return AppDetailActivity.this.OnLoadDate();
            }
        };

        mPackageName = getIntent().getStringExtra(
                ConstantValuesUtils.TO_APP_DETAIL_ACTIVITY_BY_PACKAGE_NAME);

        setContentView(mLoadingPage);

        mLoadingPage.LoadDate();

    }

    private LoadingPage.ResultState OnLoadDate() {

        AppDetailProtocol protocol = new AppDetailProtocol(mPackageName);
        mData = protocol.getData(0);

        Log.i(TAG, "OnLoadDate: mData.getName" + mData.getName());
        if (mData != null) {
            return LoadingPage.ResultState.STATE_SUCCESS;
        } else {
            return LoadingPage.ResultState.STATE_ERROR;
        }
    }

    private View OnCreateSuccessView() {
        View rootView = UIUtils.inflate(R.layout.activity_app_detail);

        ButterKnife.bind(this, rootView);

        // 初始化应用详情信息
        initAppInfo();
        // 初始化安全相关信息
        initSafe();
        // 初始化图片信息
        initPics();
        // 初始化描述信息
        initDesc();
        // 初始化下载布局
        initDownload();

        return rootView;
    }

    /**
     *初始化下载布局
     */
    private void initDownload() {

    }

    /**
     * 初始化描述信息
     */
    private void initDesc() {
    }

    /**
     *  初始化图片信息
     */
    private void initPics() {
    }

    /**
     * 初始化安全相关信息
     */
    private void initSafe() {
    }

    /**
     * 初始化AppInfo部分的布局，以及数据
     */
    private void initAppInfo() {

        final AppDetailAppInfoHolder holder = new AppDetailAppInfoHolder();
        holder.setData(mData);
        flAppDetailAppInfo.addView(holder.getItemRootView());

    }


}

package com.im_dsd.googlepaly.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.AppDetailBean;
import com.im_dsd.googlepaly.http.protocol.AppDetailProtocol;
import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.utils.ConstantValuesUtils;
import com.im_dsd.googlepaly.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AppDetailActivity extends BaseActivity {
    public static final String TAG = "AppDetailActivity";
    @Bind(R.id.fl_app_detail_appInfo)
    FrameLayout flAppDetailAppInfo;
    @Bind(R.id.fl_app_detail_safeInfo)
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


    }

    private LoadingPage.ResultState OnLoadDate() {

        AppDetailProtocol protocol = new AppDetailProtocol(mPackageName);
        mData = protocol.getData(0);

        if (mData != null) {
            return LoadingPage.ResultState.STATE_SUCCESS;
        } else {
            return LoadingPage.ResultState.STATE_ERROR;
        }
    }

    private View OnCreateSuccessView() {
        View rootView = UIUtils.inflate(R.layout.layout_app_detail_appInfo);
        ButterKnife.bind(this,rootView);
        initAppInfo();
        return rootView;
    }

    //初始化AppInfo部分的布局，以及数据
    private void initAppInfo() {

    }
}

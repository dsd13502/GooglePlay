package com.im_dsd.googlepaly.ui.holder;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.AppDetailBean;
import com.im_dsd.googlepaly.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by im_dsd on 16-6-18.
 */

public class AppDetailDownloadHolder extends BaseHolder<AppDetailBean> {

    @Bind(R.id.btn_fav)
    Button btnFav;
    @Bind(R.id.btn_share)
    Button btnShare;
    @Bind(R.id.btn_download)
    Button btnDownload;
    @Bind(R.id.fl_download)
    FrameLayout flDownload;

    @Override
    public View setItemView() {
        View view = UIUtils.inflate(R.layout.layout_detail_download);

        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void refreshView(AppDetailBean data) {

    }

    @OnClick({R.id.btn_fav, R.id.btn_share, R.id.btn_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fav:
                break;
            case R.id.btn_share:
                break;
            case R.id.btn_download:
                break;
        }
    }
}

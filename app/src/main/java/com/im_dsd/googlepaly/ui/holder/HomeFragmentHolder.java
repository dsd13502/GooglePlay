package com.im_dsd.googlepaly.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.im_dsd.googlepaly.domain.AppInfoBean;
import com.im_dsd.googlepaly.utils.UIUtils;

/**
 * Created by im_dsd on 16-6-5.
 */

public class HomeFragmentHolder extends  BaseHolder<AppInfoBean.AppInfo> {

    private TextView tv;

    @Override
    public View setItemView() {
        tv =  new TextView(UIUtils.getContext());
        return tv;
    }

    @Override
    public void refreshView(AppInfoBean.AppInfo data) {
        tv.setText(data.getName());
    }

}

package com.im_dsd.googlepaly.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.utils.UIUtils;

/**
 * Created by im_dsd on 16-6-3.
 */
public class RecommendFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(UIUtils.getContext());
        tv.setText("RecommendFragment");
        return tv;
    }

    @Override
    public View OnCreateSuccessView() {
        return null;
    }

    @Override
    public LoadingPage.ResultState OnLoadDate() {
        return null;
    }
}

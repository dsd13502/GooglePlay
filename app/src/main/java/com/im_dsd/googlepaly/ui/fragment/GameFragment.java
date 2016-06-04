package com.im_dsd.googlepaly.ui.fragment;

import android.view.View;

import com.im_dsd.googlepaly.ui.view.LoadingPage;

/**
 * Created by im_dsd on 16-6-3.
 */
public class GameFragment extends BaseFragment {

    @Override
    public View OnCreateSuccessView() {
        return null;
    }

    @Override
    public LoadingPage.ResultState OnLoadDate() {
        return LoadingPage.ResultState.STATE_EMPTY;
    }
}

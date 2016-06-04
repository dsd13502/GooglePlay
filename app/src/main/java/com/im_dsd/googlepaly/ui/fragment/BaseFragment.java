package com.im_dsd.googlepaly.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.im_dsd.googlepaly.ui.view.LoadingPage;
import com.im_dsd.googlepaly.utils.UIUtils;

/**
 * Created by im_dsd on 16-6-3.
 */
public abstract class BaseFragment extends Fragment {

    private final Context context;

    public BaseFragment()
    {
        context = UIUtils.getContext();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    private LoadingPage initView() {
        LoadingPage loadingPage = new LoadingPage(context){

            @Override
            public View OnCreateSuccessView() {
                return BaseFragment.this.OnCreateSuccessView();
            }

            @Override
            public ResultState OnLoadDate() {

                return BaseFragment.this.OnLoadDate();
            }
        };

        return loadingPage;
    }

    public abstract  View  OnCreateSuccessView();
    public abstract LoadingPage.ResultState OnLoadDate();

}

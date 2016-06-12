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

import java.util.List;

/**
 * Created by im_dsd on 16-6-3.
 */
public abstract class BaseFragment extends Fragment {

    public static final String TAG = "BaseFragment";
    private final Context context;
    private LoadingPage mLoadingPage;

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

        mLoadingPage = new LoadingPage(context){

            @Override
            public View OnCreateSuccessView() {
                return BaseFragment.this.OnCreateSuccessView();
            }

            @Override
            public ResultState OnLoadDate() {

                return BaseFragment.this.OnLoadDate();
            }
        };



        return mLoadingPage;
    }

    /**
     * 校验数据的合法性,返回相应的状态
     * @param data
     * @return
     */
    public LoadingPage.ResultState check(Object data) {
        if (data != null) {
            if (data instanceof List) {//判断当前对象是否是一个集合
                List list = (List) data;
                if (!list.isEmpty()) {//数据不为空,访问成功
                    return LoadingPage.ResultState.STATE_SUCCESS;
                } else {//空集合
                    return LoadingPage.ResultState.STATE_EMPTY;
                }
            }
        }

        return LoadingPage.ResultState.STATE_ERROR;
    }

    /**
     * 载入数据
     */
    public void  LoadDate()
    {
        if (mLoadingPage != null)
        {
            mLoadingPage.LoadDate();
        }
    }
    public abstract  View  OnCreateSuccessView();

    /**
     * 加载数据
     * @return 加载数据之后的状态
     */
    public abstract LoadingPage.ResultState OnLoadDate();

}

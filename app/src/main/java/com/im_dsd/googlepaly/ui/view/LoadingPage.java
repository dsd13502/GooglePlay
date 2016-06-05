package com.im_dsd.googlepaly.ui.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.utils.UIUtils;

/**
 * Created by im_dsd on 16-6-3.
 */
public abstract class LoadingPage extends FrameLayout{

    private static final int STATE_UNLOAD = 0;// 未加载
    private static final int STATE_LOADING = 1;// 正在加载
    private static final int STATE_LOAD_EMPTY = 2;// 数据为空
    private static final int STATE_LOAD_ERROR = 3;// 加载失败
    private static final int STATE_LOAD_SUCCESS = 4;// 访问成功
    public static final String TAG = "LoadingPage";

    private int mCurrentState = STATE_UNLOAD;// 当前状态

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;

    public LoadingPage(Context ctx)
    {
        super(ctx);
        initView();

    }

    private  void initView()
    {
        //初始化正在加载布局
        if (mLoadingView == null)
        {
            mLoadingView = onCreateLoadingView();
            addView(mLoadingView);
        }

        //初始化为空布局
        if (mEmptyView == null)
        {
            mEmptyView = onCreateEmptyView();
            addView(mEmptyView);
        }

        //初始化错误布局
        if (mErrorView == null)
        {
            mErrorView = OnCreateErrorView();
            addView(mErrorView);
        }

        showRightPager();
        LoadDate();

    }

    /**
     * 根据当前的状态判断需要显示的布局
     */
    private void showRightPager() {
        if (mEmptyView != null)
        {
            mEmptyView.setVisibility((mCurrentState != STATE_LOAD_EMPTY) ? GONE : VISIBLE);

        }

        if (mErrorView != null)
        {
            mErrorView.setVisibility((mCurrentState != STATE_LOAD_ERROR) ? GONE : VISIBLE);

        }

        if (mLoadingView != null)
        {
            mLoadingView.setVisibility((mCurrentState == STATE_LOADING || mCurrentState == STATE_UNLOAD)
                    ?  VISIBLE : GONE );
            Log.i(TAG, "mCurrentState: " + mCurrentState);

        }

        //初始加载成功布局
        if (mSuccessView == null && mCurrentState == STATE_LOAD_SUCCESS)
        {
            if (OnCreateSuccessView() != null)
            {
                mSuccessView = OnCreateSuccessView();
                ViewGroup parent = (ViewGroup) mSuccessView.getParent();
                if (parent != null)
                {
                    parent.removeAllViews();
                }
                addView(mSuccessView);

            }

        }

        if (mSuccessView != null)
        {
            mSuccessView.setVisibility((mCurrentState != STATE_LOAD_SUCCESS) ? GONE : VISIBLE);
        }

    }

    private  void LoadDate()
    {
        // 状态归零
        if (mCurrentState == STATE_LOAD_EMPTY
                || mCurrentState == STATE_LOAD_ERROR
                || mCurrentState == STATE_LOAD_SUCCESS) {
            mCurrentState = STATE_UNLOAD;
        }
        //开始载入数据
        if (mCurrentState == STATE_UNLOAD)
        {

            //开启线程加载数据
             new Thread()
            {
                @Override
                public void run() {
                    super.run();
                    final ResultState state = OnLoadDate();
                    // 必须在主线程更新界面
                    UIUtils.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (state != null) {
                                // 更新当前状态
                                mCurrentState = state.getState();
                                // 更新当前页面
                                showRightPager();
                            }
                        }
                    });
                }
            }.start();

        }

    }
    /**
     * 正在加载
     * @return 正在加载布局
     */
    private View onCreateLoadingView()
    {
        return UIUtils.inflate(R.layout.layout_loading);
    }

    /**
     * 内容为空
     * @return 内容为空时候的布局
     */
    private View onCreateEmptyView()
    {
        return  UIUtils.inflate(R.layout.layout_empty);
    }

    /**
     * 加载错误
     * @return 加载错误的时候的布局
     */
    private  View OnCreateErrorView()
    {
        return UIUtils.inflate(R.layout.layout_error);
    }

    /**
     * 加载成功
     * @return 加载成功时候的布局
     */
    public abstract View OnCreateSuccessView();

    /**
     * 加载数据，需要用户自己实现，当返回为  STATE_SUCCESS 时加载OnCreateSuccessView()
     * @return 加载数据后的状态
     */
    public abstract ResultState OnLoadDate();


    /**
     * 加载数据时，返回结果的状态
     */
    public enum  ResultState
    {
        /**
         * 访问成功
         */
        STATE_SUCCESS(STATE_LOAD_SUCCESS), // 访问成功
        /**
         * 数据为空
         */
        STATE_EMPTY(STATE_LOAD_EMPTY), // 数据为空
        /**
         * 访问失败
         */
        STATE_ERROR(STATE_LOAD_ERROR);// 访问失败



        int state;
        ResultState(int state)
        {
            this.state = state;
        }

        public int getState()
        {
            return this.state;
        }
    }
}

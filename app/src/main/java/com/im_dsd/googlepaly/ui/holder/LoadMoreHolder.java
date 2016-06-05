package com.im_dsd.googlepaly.ui.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.utils.UIUtils;

/**
 * 加载更多的Holder，他只管界面的展示，不用实现具体的业务逻辑
 * Created by im_dsd on 16-6-5.
 */

public class LoadMoreHolder extends BaseHolder<Integer> {

    public static final int TYPE_HAS_MORE = 0;// 可以加载更多
    public static final int TYPE_NO_MORE = 1;// 不能加载更多
    public static final int TYPE_LOAD_ERROR = 2;// 加载更多失败

    private LinearLayout llLoadingMore;
    private TextView tvLoadError;

    public LoadMoreHolder(boolean hasMore) {
        // 将加载类型以数据的方式设置进去
        setData(hasMore ? TYPE_HAS_MORE : TYPE_NO_MORE);
    }


    @Override
    public View setItemView() {
        View view = View.inflate(UIUtils.getContext(),
                R.layout.holder_load_more, null);
        llLoadingMore = (LinearLayout) view.findViewById(R.id.ll_loading_more);
        tvLoadError = (TextView) view.findViewById(R.id.tv_load_error);
        return view;
    }

    @Override
    public void refreshView(Integer data) {
        // 根据当前状态,来更新界面展示
        switch (data) {
            case TYPE_HAS_MORE:
                llLoadingMore.setVisibility(View.VISIBLE);
                tvLoadError.setVisibility(View.GONE);
                break;
            case TYPE_NO_MORE:
                llLoadingMore.setVisibility(View.GONE);
                tvLoadError.setVisibility(View.GONE);
                break;
            case TYPE_LOAD_ERROR:
                llLoadingMore.setVisibility(View.GONE);
                tvLoadError.setVisibility(View.VISIBLE);
                break;
        }

    }

}

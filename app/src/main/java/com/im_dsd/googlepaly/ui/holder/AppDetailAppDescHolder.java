package com.im_dsd.googlepaly.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.AppDetailBean;
import com.im_dsd.googlepaly.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by im_dsd on 16-6-18.
 */

public class AppDetailAppDescHolder extends BaseHolder<AppDetailBean> {
    @Bind(R.id.tv_detail_des)
    TextView tvDetailDes;
    @Bind(R.id.tv_detail_author)
    TextView tvDetailAuthor;
    @Bind(R.id.iv_arrow)
    ImageView ivArrow;
    @Bind(R.id.rl_detail_toggle)
    RelativeLayout rlDetailToggle;

    @Override
    public View setItemView() {
        View view = UIUtils.inflate(R.layout.layout_detail_desinfo);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void refreshView(AppDetailBean data) {

    }

    @OnClick(R.id.iv_arrow)
    public void onClick() {
    }
}

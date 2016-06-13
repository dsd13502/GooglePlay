package com.im_dsd.googlepaly.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.CategoryBean;
import com.im_dsd.googlepaly.http.HttpHelper;
import com.im_dsd.googlepaly.utils.BitmapHelper;
import com.im_dsd.googlepaly.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by im_dsd on 16-6-13.
 */

public class CategoryHolder extends BaseHolder<CategoryBean> {


    @Bind(R.id.iv_icon1)
    ImageView ivIcon1;
    @Bind(R.id.tv_name1)
    TextView tvName1;
    @Bind(R.id.ll_grid1)
    LinearLayout llGrid1;
    @Bind(R.id.iv_icon2)
    ImageView ivIcon2;
    @Bind(R.id.tv_name2)
    TextView tvName2;
    @Bind(R.id.ll_grid2)
    LinearLayout llGrid2;
    @Bind(R.id.iv_icon3)
    ImageView ivIcon3;
    @Bind(R.id.tv_name3)
    TextView tvName3;
    @Bind(R.id.ll_grid3)
    LinearLayout llGrid3;
    private BitmapUtils mBitmapUtils;

    public CategoryHolder(CategoryBean.CategoryInfo data) {

        super();

        tvName1.setText(data.getName1());
        tvName2.setText(data.getName2());
        tvName3.setText(data.getName3());

        mBitmapUtils.display(ivIcon1,HttpHelper.URL + "image?name="
                + data.getUrl1());
        mBitmapUtils.display(ivIcon2,HttpHelper.URL + "image?name="
                + data.getUrl2());
        mBitmapUtils.display(ivIcon3,HttpHelper.URL + "image?name="
                + data.getUrl3());
    }

    @Override
    public View setItemView() {
        View view = UIUtils.inflate(R.layout.list_item_category);

        ButterKnife.bind(this,view);

        mBitmapUtils = BitmapHelper.getInstance();
        mBitmapUtils.configDefaultLoadingImage(R.drawable.ic_default);

        return view;
    }

    @Override
    public void refreshView(CategoryBean data) {

    }


    @OnClick({R.id.iv_icon1, R.id.ll_grid2, R.id.ll_grid3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_icon1:
                break;
            case R.id.ll_grid2:
                break;
            case R.id.ll_grid3:
                break;
        }
    }
}

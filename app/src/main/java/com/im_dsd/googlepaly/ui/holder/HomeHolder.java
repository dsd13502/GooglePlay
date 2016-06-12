package com.im_dsd.googlepaly.ui.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.HomeBean;
import com.im_dsd.googlepaly.http.HttpHelper;
import com.im_dsd.googlepaly.utils.BitmapHelper;
import com.im_dsd.googlepaly.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by im_dsd on 16-6-5.
 */

public class HomeHolder extends BaseHolder<HomeBean.AppInfo> {


    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.rb_star)
    RatingBar rbStar;
    @Bind(R.id.tv_size)
    TextView tvSize;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    private BitmapUtils mBitmapHelper;
    private View itemView;

    @Override
    public View setItemView() {

        itemView = UIUtils.inflate(R.layout.list_item_home);

        //绑定 inflate
        ButterKnife.bind(this, itemView);

        return itemView;
    }

    @Override
    public void refreshView(HomeBean.AppInfo data) {

        //存储单位的自动转换。
        String size = Formatter.formatFileSize(UIUtils.getContext(), data.getSize());

        tvName.setText(data.getName());
        rbStar.setRating(data.getStars());
        tvSize.setText(size);
        tvDesc.setText(data.getDes());

        //获取图片的url。
        String url = HttpHelper.URL + "image?name="
                + data.getIconUrl();
        //获取BitmapUtils的封装。
        mBitmapHelper = BitmapHelper.getInstance();
        mBitmapHelper.display(ivIcon, url);


    }

}

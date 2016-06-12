package com.im_dsd.googlepaly.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.SubjectBean;
import com.im_dsd.googlepaly.http.HttpHelper;
import com.im_dsd.googlepaly.utils.BitmapHelper;
import com.im_dsd.googlepaly.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by im_dsd on 16-6-11.
 */

public class SubjectHolder extends BaseHolder<SubjectBean> {

    private android.widget.ImageView ivpic;
    private android.widget.TextView tvdes;

    @Override
    public View setItemView() {
        View view = UIUtils.inflate(R.layout.list_item_subject);
        this.tvdes = (TextView) view.findViewById(R.id.tv_des);
        this.ivpic = (ImageView) view.findViewById(R.id.iv_pic);

        return view;
    }

    @Override
    public void refreshView(SubjectBean data) {
        tvdes.setText(data.getDes());
        BitmapUtils bitmapHelper = BitmapHelper.getInstance();

        //获取图片的url。
        String url = HttpHelper.URL + "image?name="
                + data.getUrl();

        bitmapHelper.display(ivpic,url);
    }
}

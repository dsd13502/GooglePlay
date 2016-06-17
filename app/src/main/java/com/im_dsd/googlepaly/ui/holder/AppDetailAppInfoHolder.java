package com.im_dsd.googlepaly.ui.holder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.AppDetailBean;
import com.im_dsd.googlepaly.http.HttpHelper;
import com.im_dsd.googlepaly.utils.BitmapHelper;
import com.im_dsd.googlepaly.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.im_dsd.googlepaly.R.drawable.subject_default;

/**
 * Created by im_dsd on 16-6-17.
 */

public class AppDetailAppInfoHolder extends BaseHolder<AppDetailBean> {
    public static final String TAG = "AppDetailAppInfoHolder";
    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.rb_star)
    RatingBar rbStar;
    @Bind(R.id.tv_download_num)
    TextView tvDownloadNum;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_size)
    TextView tvSize;

    private BitmapUtils mBitmapUtils ;
    @Override
    public View setItemView() {
        View view = UIUtils.inflate(R.layout.layout_app_detail_appInfo);
        ButterKnife.bind(this,view);
        mBitmapUtils =  BitmapHelper.getInstance();
        mBitmapUtils.configDefaultLoadingImage(subject_default);
        return view;
    }

    @Override
    public void refreshView(AppDetailBean data) {

        if (data != null)
        {
            mBitmapUtils.display(ivIcon, HttpHelper.URL + "image?name=" + data.getIconUrl());
            tvName.setText(data.getName());
            rbStar.setRating((float) data.getStars());
            tvDownloadNum.setText(data.getDownloadNum());
            tvVersion.setText(data.getVersion());
            tvSize.setText(data.getSize());
        }
        else
        {
            Log.i(TAG, "refreshView: date == null");
        }

    }
}

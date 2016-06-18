package com.im_dsd.googlepaly.ui.holder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.im_dsd.googlepaly.R;
import com.im_dsd.googlepaly.domain.AppDetailBean;
import com.im_dsd.googlepaly.http.HttpHelper;
import com.im_dsd.googlepaly.utils.BitmapHelper;
import com.im_dsd.googlepaly.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import static com.im_dsd.googlepaly.R.drawable.subject_default;

/**
 * Created by im_dsd on 16-6-18.
 */

public class AppDetailPicsInfoHolder extends BaseHolder<AppDetailBean> {

    public static final String TAG = "AppDetailPicsInfoHolder";
    private ImageView[] ivList;
    private BitmapUtils mBitmapUtils;

    @Override
    public View setItemView() {
        View view = UIUtils.inflate(R.layout.layout_detail_picinfo);

        mBitmapUtils = BitmapHelper.getInstance();
        mBitmapUtils.configDefaultLoadingImage(subject_default);

        ivList= new ImageView[5];
        ivList[4] = (ImageView) view.findViewById(R.id.iv_pic5);
        ivList[3] = (ImageView) view.findViewById(R.id.iv_pic4);
        ivList[2] = (ImageView) view.findViewById(R.id.iv_pic3);
        ivList[1] = (ImageView) view.findViewById(R.id.iv_pic2);
        ivList[0] = (ImageView) view.findViewById(R.id.iv_pic1);
        return view;
    }

    @Override
    public void refreshView(AppDetailBean data) {
        if (data != null)
        {
            List<String> screen = data.getScreen();
            if (screen != null)
            {
                for (int i = 0; i < 5; i ++)
                {
                    if (i < screen.size())
                    {
                        ivList[i].setVisibility(View.VISIBLE);
                        String url = HttpHelper.URL + "image?name=" + screen.get(i);

                        Log.i(TAG, "refreshView: url = " + url);
                        mBitmapUtils.display(ivList[i],url);
                    }
                    else
                    {
                        ivList[i].setVisibility(View.GONE);
                    }
                }
            }
            else
            {
                Log.i(TAG, "data.getScreen() = null" );
                //noinspection ThrowableInstanceNeverThrown
                new Exception("AppDetailPicsInfoHolder ->> data.getScreen() = null");
            }
        }
    }
}

package com.im_dsd.googlepaly.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.im_dsd.googlepaly.utils.ConstantValuesUtils;
import com.im_dsd.googlepaly.utils.UIUtils;

/**
 * Created by im_dsd on 16-6-17.
 */

public class AppDetailActivity  extends BaseActivity{

    public static final String TAG = "AppDetailActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {

        String packageName = getIntent().getStringExtra(
                ConstantValuesUtils.TO_APP_DETAIL_ACTIVITY_BY_PACKAGE_NAME);

        TextView textView = new TextView(UIUtils.getContext());
        textView.setText(packageName);
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(textView);
    }


}

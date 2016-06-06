package com.im_dsd.googlepaly.http.protocol;

import com.google.gson.Gson;
import com.im_dsd.googlepaly.domain.AppInfoBean;

/**
 * Created by im_dsd on 16-6-6.
 */

public class HomeFragmentProtocol extends BaseProtocol<AppInfoBean> {
    @Override
    protected AppInfoBean parseJson(String json) {
        Gson gson = new Gson();

        AppInfoBean appInfo = gson.fromJson(json, AppInfoBean.class);

        return appInfo;
    }

    @Override
    public String getKey() {
        return "home";
    }

    @Override
    public String getParams() {
        return "";
    }
}

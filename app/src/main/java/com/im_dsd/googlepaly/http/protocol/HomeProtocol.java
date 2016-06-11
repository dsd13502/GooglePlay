package com.im_dsd.googlepaly.http.protocol;

import com.google.gson.Gson;
import com.im_dsd.googlepaly.domain.HomeBean;

/**
 * Created by im_dsd on 16-6-6.
 */

public class HomeProtocol extends BaseProtocol<HomeBean> {
    @Override
    protected HomeBean parseJson(String json) {
        Gson gson = new Gson();

        HomeBean appInfo = gson.fromJson(json, HomeBean.class);

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

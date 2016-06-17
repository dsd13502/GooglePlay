package com.im_dsd.googlepaly.http.protocol;

import com.google.gson.Gson;
import com.im_dsd.googlepaly.domain.AppDetailBean;

/**
 * Created by im_dsd on 16-6-17.
 */

public class AppDetailProtocol extends BaseProtocol<AppDetailBean> {

    private final String packageName;

    public AppDetailProtocol(String packageName)
    {
        this.packageName = packageName;
    }
    @Override
    protected AppDetailBean parseJson(String json) {
        Gson gson = new Gson();
        AppDetailBean appDetailBean = gson.fromJson(json, AppDetailBean.class);
        return appDetailBean;
    }

    @Override
    public String getKey() {
        return "detail";
    }

    @Override
    public String getParams() {
        return "&packageName=" + packageName;
    }
}

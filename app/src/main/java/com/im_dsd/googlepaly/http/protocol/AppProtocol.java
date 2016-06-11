package com.im_dsd.googlepaly.http.protocol;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.im_dsd.googlepaly.domain.AppBean;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * AppFragment的网络数据中转站
 * Created by im_dsd on 16-6-6.
 */

public class AppProtocol extends BaseProtocol<ArrayList<AppBean>> {
    @Override
    protected ArrayList<AppBean> parseJson(String json) {

        //创建gson用于解析。
        Gson gson = new Gson();
        //用于保存数据的数组。
        ArrayList<AppBean> list = new ArrayList<AppBean>();

        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonParser parse = new JsonParser();
        JsonElement jsonElement = parse.parse(json);

        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray;
        if (jsonElement.isJsonArray()) {

            jsonArray = jsonElement.getAsJsonArray();
            AppBean appInfo;

            //遍历JsonArray对象
            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                //JsonElement转换为JavaBean对象
                JsonElement next = (JsonElement) iterator.next();
                appInfo = gson.fromJson(next, AppBean.class);
                list.add(appInfo);
            }
        }

        return list;
    }

    @Override
    public String getKey() {
        return "app";
    }

    @Override
    public String getParams() {
        return "";
    }
}

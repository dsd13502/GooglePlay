package com.im_dsd.googlepaly.http.protocol;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.im_dsd.googlepaly.domain.SubjectBean;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by im_dsd on 16-6-11.
 */

public class SubjectProtocol extends BaseProtocol<ArrayList<SubjectBean>>{
    @Override
    protected ArrayList<SubjectBean> parseJson(String json) {

        //1.解析json字符串
        JsonParser jsonParser = new JsonParser();
        //2.获取JsonElement用于转换成 JsonArray
        JsonElement jsonElement = jsonParser.parse(json);

        //3.将 jsonElement转换成 jsonArray
        JsonArray asJsonArray = jsonElement.getAsJsonArray();

        //4.获取jsonArray中的元素
        Iterator<JsonElement> iterator = asJsonArray.iterator();

        //5.准备解析
        Gson gson = new Gson();
        ArrayList<SubjectBean> list = new ArrayList<SubjectBean>();

        //6.开始遍历，解析
        while (iterator.hasNext())
        {
            SubjectBean fromJson = gson.fromJson(iterator.next(), SubjectBean.class);
            list.add(fromJson);
        }
        return list;
    }

    @Override
    protected String getParams() {
        return "";
    }

    @Override
    public String getKey() {
        return "subject";
    }
}

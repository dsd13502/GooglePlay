package com.im_dsd.googlepaly.http.protocol;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by im_dsd on 16-6-12.
 */

public class RecommendProtocol extends BaseProtocol<ArrayList<String>> {

    public static final String TAG = "RecommendProtocol";

    /**
     * 解析Json字符串
     * @param json 数据
     * @return 解析好的json数据，异常时返回空
     */
    @Override
    protected ArrayList<String> parseJson(String json) {

        ArrayList<String> list = new ArrayList<String>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0 ; i < jsonArray.length(); i ++)
            {
                String jsonString = jsonArray.getString(i);
                list.add(jsonString);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "parseJson: json解析错误");
            return null;
        }

        return list;
    }

    @Override
    protected String getParams() {
        return "";
    }

    @Override
    public String getKey() {
        return "recommend";
    }
}

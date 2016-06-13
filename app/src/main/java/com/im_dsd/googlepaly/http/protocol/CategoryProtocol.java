package com.im_dsd.googlepaly.http.protocol;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.im_dsd.googlepaly.domain.CategoryBean;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by im_dsd on 16-6-13.
 */

public class CategoryProtocol extends BaseProtocol<ArrayList<CategoryBean>> {

    @Override
    protected ArrayList<CategoryBean> parseJson(String json) {

        ArrayList<CategoryBean> list = new ArrayList<CategoryBean>();
        Gson gson = new Gson();

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);

        JsonArray asJsonArray = jsonElement.getAsJsonArray();
        Iterator<JsonElement> iterator = asJsonArray.iterator();

        while (iterator.hasNext())
        {
            JsonElement element = iterator.next();
            CategoryBean fromJson = gson.fromJson(element, CategoryBean.class);
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
        return "category";
    }
}

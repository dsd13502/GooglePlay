package com.im_dsd.googlepaly.http.protocol;

import com.im_dsd.googlepaly.domain.CategoryBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by im_dsd on 16-6-13.
 */

public class CategoryProtocol extends BaseProtocol<ArrayList<CategoryBean>> {

    public static final String TAG = "CategoryProtocol";

    @Override
    protected ArrayList<CategoryBean> parseJson(String json) {

        ArrayList<CategoryBean> list = new ArrayList<CategoryBean>();

        try {
            JSONArray jsonArray = new JSONArray(json);
           for (int j = 0; j < jsonArray.length(); j ++)
           {
               JSONObject jsonObject = (JSONObject)jsonArray.get(j);

               if (jsonObject.has("title")) {

                   String title = jsonObject.getString("title");
                   CategoryBean categoryBean = new CategoryBean();
                   categoryBean.setTitle(title);
                   categoryBean.setTitle(true);

                   list.add(categoryBean);
               }

               if (jsonObject.has("infos")) {
                   JSONArray array = jsonObject.getJSONArray("infos");

                   for (int i = 0; i < array.length(); i++) {

                       CategoryBean categoryBean = new CategoryBean();
                       JSONObject object = array.getJSONObject(i);

                       categoryBean.setName1(object.getString("name1"));
                       categoryBean.setName2(object.getString("name2"));
                       categoryBean.setName3(object.getString("name3"));

                       categoryBean.setUrl1(object.getString("url1"));
                       categoryBean.setUrl2(object.getString("url2"));
                       categoryBean.setUrl3(object.getString("url3"));

                       categoryBean.setTitle(false);

                       list.add(categoryBean);

                   }


               }
           }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        /***********原有的错误代码***************/
//        ArrayList<CategoryBean> list = new ArrayList<CategoryBean>();j
//        Gson gson = new Gson();
//
//        JsonParser jsonParser = new JsonParser();
//        JsonElement jsonElement = jsonParser.parse(json);
//
//        JsonArray asJsonArray = jsonElement.getAsJsonArray();
//        Iterator<JsonElement> iterator = asJsonArray.iterator();
//
//        while (iterator.hasNext())
//        {
//            JsonElement element = iterator.next();
//
//            JsonObject jsonObject = element.getAsJsonObject();
//
//            //如果是标题复制一份出来，让他自己单独一个房间，方便判断，取值。
//            if(jsonObject.has("title"))
//            {
//                String title = jsonObject.get("title").getAsString();
//                Log.i(TAG, "parseJson: " + title);
//                CategoryBean categoryBean = new CategoryBean();
//                categoryBean.setTitle(title);
//                categoryBean.setTitle(true);
//                list.add(categoryBean);
//
//            }
//
//            CategoryBean fromJson = gson.fromJson(element, CategoryBean.class);
//
//            list.add(fromJson);



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

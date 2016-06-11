package com.im_dsd.googlepaly.http.protocol;

import android.util.Log;

import com.im_dsd.googlepaly.http.HttpHelper;
import com.im_dsd.googlepaly.utils.IOUtils;
import com.im_dsd.googlepaly.utils.StringUtils;
import com.im_dsd.googlepaly.utils.UIUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.im_dsd.googlepaly.ui.fragment.BaseFragment.TAG;


/**
 * Created by im_dsd on 16-6-6.
 * @param <T> 返回的数据类型，基本上是一个集合。
 *
 */
public abstract class BaseProtocol<T> {

    /**
     * 获取数据
     * @param index  分页请求数据的起始位置，每次返回20个数据。
     * @return 解析好的数据
     */
    public T getData(int index)
    {
        //获取本地缓存
        String result = getCache(index);

        if(result == null)
        {
            //本地没有缓存，网络加载
            String dataFromServer = getDataFromServer(index);
            if (!StringUtils.isEmpty(dataFromServer))
            {
                result = dataFromServer;
            }

        }

       if (!StringUtils.isEmpty(result)){
           Log.i(TAG, "getData: parseJson(result)");
           return parseJson(result);
       }

        return null;

    }


    /**
     * 进行json数据的解析
     * @param json 数据
     * @return 带有数据地类的实例
     */
    protected abstract T parseJson(String json);

    /**
     * 从服务器端获取数据
     * @param index  分页请求数据的起始位置，每次返回20个数据。
     * @return 从网络获取的数据，null：没有数据，错误或异常。
     */
    private String getDataFromServer(int index) {

        //从服务器端获取的数据
        HttpHelper.HttpResult httpResult =  HttpHelper.get(HttpHelper.URL + getKey()
                + "?index=" + index + getParams());

        if (httpResult != null)
        {
            String result = httpResult.getString();

            if (!StringUtils.isEmpty(result))
            {
                setCache(index,result);
                return result;
            }
        }

        return null;
    }

    /**
     * 设置缓存
     * @param json json数据
     */
    private void setCache(int index,String json) {

        //获取应用缓存文件夹 /data/data～～～
        File cacheDir = UIUtils.getContext().getCacheDir();
       
        //设置缓存文件，使用当前数据的链接地址作为文件名。
        File cacheFile = new File(cacheDir,index + getKey() + getParams());

        FileWriter writer = null;
        try {
            writer = new FileWriter(cacheFile);

            //在文件第一行增加有效时间。
            long validTime = System.currentTimeMillis() + (30 * 60 * 1000);
            writer.write(validTime + "\n");

            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.close(writer);
        }
    }

    /**
     * 获取缓存数据
     * @return 缓存数据,返回null表示没有数据，错误或者异常。
     */
    private String getCache(int index)
    {
        //获取应用缓存文件夹 /data/data～～～
        File cacheDir = UIUtils.getContext().getCacheDir();

        //设置缓存文件，使用当前数据的链接地址作为文件名。
        File cacheFile = new File(cacheDir,index + getKey() + getParams());

        //如果文件存在
        if (cacheFile.exists())
        {
            FileReader fileReader = null;
            BufferedReader reader = null;
            StringBuffer sb = null;
            try {
                fileReader = new FileReader(cacheFile);
                reader = new BufferedReader(fileReader);
                //读取有效时间
                String validTime = reader.readLine();


                if (System.currentTimeMillis() < Long.parseLong(validTime))
                {
                    String line = null;
                    sb = new StringBuffer();

                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                    }

                    return sb.toString();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                IOUtils.close(fileReader);
                IOUtils.close(reader);
            }

        }

        return null;
    }

    /**
     * 获取网络接口的具体参数,每个页面都不一样,必须由子类实现
     * @return 具体参数
     */
    protected abstract String getParams();

    /**
     * 获取网络接口的具体地址,每个页面都不一样,必须由子类实现
     * @return 具体地址
     */
    public abstract String getKey();



}

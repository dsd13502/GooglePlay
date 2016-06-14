package com.im_dsd.googlepaly.domain;

import java.util.List;

/**
 * Created by im_dsd on 16-6-14.
 */

public class HomeDetailBean {


    /**
     * author : 亚马逊
     * date : 2014-05-30
     * des : 货
     * downloadNum : 100万+
     * downloadUrl : app/cn.amazon.mShop.android/cn.amazon.mShop.android.apk
     * iconUrl : app/cn.amazon.mShop.android/icon.jpg
     * id : 1608378
     * name : 亚马逊购物
     * packageName : cn.amazon.mShop.android
     * safe : []
     * screen : ["app/cn.amazon.mShop.android/screen0.jpg","app/cn.amazon.mShop.android/screen1.jpg","app/cn.amazon.mShop.android/screen2.jpg","app/cn.amazon.mShop.android/screen3.jpg","app/cn.amazon.mShop.android/screen4.jpg"]
     * size : 7351495
     * stars : 3.5
     * version : 2.10.0
     */

    private String author;
    private String date;
    private String des;
    private String downloadNum;
    private String downloadUrl;
    private String iconUrl;
    private int id;
    private String name;
    private String packageName;
    private int size;
    private double stars;
    private String version;
    private List<?> safe;
    private List<String> screen;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(String downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<?> getSafe() {
        return safe;
    }

    public void setSafe(List<?> safe) {
        this.safe = safe;
    }

    public List<String> getScreen() {
        return screen;
    }

    public void setScreen(List<String> screen) {
        this.screen = screen;
    }
}

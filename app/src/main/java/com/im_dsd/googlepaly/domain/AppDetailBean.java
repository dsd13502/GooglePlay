package com.im_dsd.googlepaly.domain;

import java.util.List;

/**
 * Created by im_dsd on 16-6-14.
 */

public class AppDetailBean {


    /**
     * author : 随身移动
     * date : 2014-06-12
     * des : w
     * downloadNum : 1000万+
     * downloadUrl : app/cn.etouch.ecalendar/cn.etouch.ecalendar.apk
     * iconUrl : app/cn.etouch.ecalendar/icon.jpg
     * id : 1641339
     * name : 中华万年历日历
     * packageName : cn.etouch.ecalendar
     * safe : [{"safeDes":"已通过安智市场安全检测，请放心使用","safeDesColor":0,"safeDesUrl":"app/cn.etouch.ecalendar/safeDesUrl0.jpg","safeUrl":"app/cn.etouch.ecalendar/safeIcon0.jpg"},{"safeDes":"无任何形式的广告","safeDesColor":0,"safeDesUrl":"app/cn.etouch.ecalendar/safeDesUrl1.jpg","safeUrl":"app/cn.etouch.ecalendar/safeIcon1.jpg"}]
     * screen : ["app/cn.etouch.ecalendar/screen0.jpg","app/cn.etouch.ecalendar/screen1.jpg","app/cn.etouch.ecalendar/screen2.jpg","app/cn.etouch.ecalendar/screen3.jpg"]
     * size : 5098427
     * stars : 4.5
     * version : 4.5.1
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
    /**
     * safeDes : 已通过安智市场安全检测，请放心使用
     * safeDesColor : 0
     * safeDesUrl : app/cn.etouch.ecalendar/safeDesUrl0.jpg
     * safeUrl : app/cn.etouch.ecalendar/safeIcon0.jpg
     */

    private List<SafeInfo> safe;
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

    public List<SafeInfo> getSafe() {
        return safe;
    }

    public void setSafe(List<SafeInfo> safe) {
        this.safe = safe;
    }

    public List<String> getScreen() {
        return screen;
    }

    public void setScreen(List<String> screen) {
        this.screen = screen;
    }

    public static class SafeInfo {
        private String safeDes;
        private int safeDesColor;
        private String safeDesUrl;
        private String safeUrl;

        public String getSafeDes() {
            return safeDes;
        }

        public void setSafeDes(String safeDes) {
            this.safeDes = safeDes;
        }

        public int getSafeDesColor() {
            return safeDesColor;
        }

        public void setSafeDesColor(int safeDesColor) {
            this.safeDesColor = safeDesColor;
        }

        public String getSafeDesUrl() {
            return safeDesUrl;
        }

        public void setSafeDesUrl(String safeDesUrl) {
            this.safeDesUrl = safeDesUrl;
        }

        public String getSafeUrl() {
            return safeUrl;
        }

        public void setSafeUrl(String safeUrl) {
            this.safeUrl = safeUrl;
        }
    }
}

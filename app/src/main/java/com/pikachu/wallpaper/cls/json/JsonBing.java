package com.pikachu.wallpaper.cls.json;


public class JsonBing {

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BingBean getBing() {
        return bing;
    }

    public void setBing(BingBean bing) {
        this.bing = bing;
    }

    /**
     * status : 1
     * bing : {"url":"http://s.cn.bing.net/th?id=OHR.MunnarMist_ZH-CN8816703625_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp","copyright":"雾气环绕的森林，喀拉拉邦慕那尔市，印度 (© Ahammed Riswan/EyeEm/Getty Images)"}
     */

    private Integer status;
    private BingBean bing;


    public static class BingBean {
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        /**
         * url : http://s.cn.bing.net/th?id=OHR.MunnarMist_ZH-CN8816703625_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp
         * copyright : 雾气环绕的森林，喀拉拉邦慕那尔市，印度 (© Ahammed Riswan/EyeEm/Getty Images)
         */

        private String url;
        private String copyright;
    }
}

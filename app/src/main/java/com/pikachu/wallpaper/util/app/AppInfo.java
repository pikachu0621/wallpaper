package com.pikachu.wallpaper.util.app;

import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;

public class AppInfo {


    public static final String APP_LOG = "test_t";




    ////////配置//////
    public static final long DEFAULT_AUTO_TIME = 5500; //轮播时间
    public static final float APP_HOME_ITEM_PRO = 0.06F; //图片缩小比例
    public static final int APP_HOME_TAB_NUMBER = 8; //首页Tab个数  -1不限制


    ////////////    API     ///////


    //用于加载小说列表
    public static final String APP_API_IMAGE_LIST = "https://api.ihansen.org/img/detail?page={page}&index=&orderBy={tabs}&tag={tag}&favorites=";
    public static final String APP_API_TABS_LIST ="https://photo.ihansen.org";

    public static String getUrl(JsonHomeTabsList jsonHomeTabsList, int page){
        String replace = APP_API_IMAGE_LIST.replace("{page}", "" + page);
        if (jsonHomeTabsList.isTagTab())
            replace = replace.replace("{tabs}","").replace("{tag}",jsonHomeTabsList.getTagE() == null ? "" : ""+jsonHomeTabsList.getTagE());
        else
            replace = replace.replace("{tabs}",jsonHomeTabsList.getTagE() == null ? "" : ""+jsonHomeTabsList.getTagE()).replace("{tag}","");
        return replace;
    }

    public static String getUrl(String tag, int page,boolean isTagTab){
        JsonHomeTabsList jsonHomeTabsList = new JsonHomeTabsList();
        jsonHomeTabsList.setTagE(tag);
        jsonHomeTabsList.setTagTab(isTagTab);
        return getUrl(jsonHomeTabsList,page);
    }


    //https://api.ihansen.org/img/detail?page=0&index=&orderBy=&tag=landscape&favorites=
    //https://api.ihansen.org/img/detail?page=0&index=&orderBy=&tag=city&favorites=
    //https://api.ihansen.org/img/detail?page=1&index=&orderBy=&tag=%E6%B9%96%E6%B0%B4&favorites=



}

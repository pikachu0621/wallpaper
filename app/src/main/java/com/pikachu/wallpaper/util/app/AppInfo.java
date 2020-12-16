package com.pikachu.wallpaper.util.app;

import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;

public class AppInfo {


    public static final String APP_LOG = "test_t";




    ////////      APP 配置     //////

    //首页
    public static final long APP_HOME_F1_AUTO_TIME = 6;             //首页轮播时间                   可选值   (0,Max]  单位 秒
    public static final String APP_HOME_F1_AUTO_TAG = "likes";      //首页轮播内容Tag                可选值   today,likes
    public static final int APP_HOME_F1_AUTO_PAGER = 100;           //首页轮播内容页数 （随机数）      可选值   [1,100]
    public static final int APP_HOME_F1_TAB_NUMBER = 8;             //首页Tab个数                   可选值   [0,Max]   -1不限制
    public static final int APP_HOME_F1_ITEM_STYLE = 1;             //列表样式                      可选值   0(带下巴), 1(不带), 2(不带下载收藏按钮)

    //分类
    public static final int APP_HOME_F2_ITEM_NUMBER = 3;            //分类一行几个item               可选值   1，2，3，4.....
    public static final int APP_HOME_F2_RM = 5;                     //分类 热门分类有几个                  可选值   [1,MAX]

    //我的
    public static final long APP_HOME_F3_AUTO_TIME = 6;             //我的 轮播时间                                   可选值   (0,Max]  单位 秒
    public static final String APP_HOME_F3_AUTO_TAG = "today";      //我的 轮播内容Tag                                可选值   today,likes
    public static final int APP_HOME_F3_AUTO_PAGER = 100;           //我的 轮播内容页数 （随机数）                      可选值   [1,100]
    public static final int APP_HOME_F3_ITEM_STYLE = 2;             //我的 推荐列表样式                                可选值   0(带下巴), 1(不带)， 2(不带下载收藏按钮)
    public static final int APP_HOME_F3_TJ_PAGER = 4000;            //我的 推荐数据页（从这个值内随机一个数开始逐加）      可选值   [1, 42034]

    //全局
    public static final int APP_HOME_IMAGE_PX = 1;                  //列表图片清晰度                   可选值   0低  1高  2超高  3原图
    public static final float APP_HOME_ITEM_PRO = 0.08F;            //图片缩小比例                     可选值   (0,1]
    public static final int APP_ANIMATION_TIME = 400;               //动画时间                         可选值   (0,Max]  单位 毫秒
    public static final String APP_AUTHOR_NAME = "Pikachu";         //作者名                          可选值   [String]








    ////////////    API     ///////
    //用于加载小说列表
    public static final String APP_API_IMAGE_LIST = "https://api.ihansen.org/img/detail?page={page}&index=&orderBy={tabs}&tag={tag}&favorites=";
    public static final String APP_API_TABS_LIST ="https://photo.ihansen.org";
    //必应每日一图
    public static final String APP_API_DAY_ONE_IMAGE = "https://api.no0a.cn/api/bing/0";

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




}

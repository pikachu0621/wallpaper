/**
 * 1 .图片阅览器      by：Pikachu_WeChat
 * 2 .最后提交日      2020/12/20
 * 3 .禁止用于任何商业用途
 * 4 .api来自 api.ihansen.org
 * <p>
 * <p>
 * <p>
 * <p>
 * 由于时间原因还未完成的功能
 * 下载，收藏，历史记录，图片预览进度条，设置
 */
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
    public static final int APP_HOME_F1_ITEM_STYLE = 1;             //首页列表样式                   可选值   0(带下巴), 1(不带下巴), 2(不带下巴，下载收藏按钮)
    public static final int APP_HOME_F1_ITEM_NUMBER = 2;            //首页每行几个item               可选值   [0,max]

    //分类
    public static final int APP_HOME_F2_ITEM_NUMBER = 3;            //分类一行几个item               可选值   1，2，3，4.....
    public static final int APP_HOME_F2_RM = 5;                     //分类 热门分类有几个             可选值   [1,MAX]

    //我的
    public static final long APP_HOME_F3_AUTO_TIME = 6;             //我的 轮播时间                                   可选值   (0,Max]  单位 秒
    public static final String APP_HOME_F3_AUTO_TAG = "today";      //我的 轮播内容Tag                                可选值   today,likes
    public static final int APP_HOME_F3_AUTO_PAGER = 100;           //我的 轮播内容页数 （随机数）                      可选值   [1,100]
    public static final int APP_HOME_F3_ITEM_STYLE = 2;             //我的 推荐列表样式                                可选值   0(带下巴), 1(不带下巴)， 2(不带下巴，下载收藏按钮)
    public static final int APP_HOME_F3_ITEM_NUMBER = 2;            //我的 每行几个item                               可选值   [0,max]
    public static final int APP_HOME_F3_TJ_PAGER = 4000;            //我的 推荐数据页（从这个值内随机一个数开始逐加）      可选值   [1, 42034]

    //图片查看  (图片预览 进度没写)
    public static final boolean APP_LOOK_GSN = true;                //是否开启背景高斯模糊效果          可选值   false 关 ，true 开
    public static final int APP_LOOK_GSN_R = 20;                    //高斯模糊 度                     可选值   [0,100]

    //分类搜索
    public static final int APP_SEARCH_ITEM_STYLE = 2;             //列表样式                        可选值   0(带下巴), 1(不带下巴), 2(不带下巴，下载收藏按钮)
    public static final int APP_SEARCH_ITEM_NUMBER = 2;            //每行几个item                    可选值   [0,max]


    //全局
    public static final int APP_HOME_IMAGE_PX = 1;                  //列表图片清晰度                   可选值   0低  1高  2超高  3原图
    public static final float APP_HOME_ITEM_PRO = 0.08F;            //图片缩小比例                     可选值   (0,1]
    public static final int APP_ANIMATION_TIME = 400;               //动画时间                        可选值   (0,Max]  单位 毫秒
    public static final String APP_AUTHOR_NAME = "Pikachu";         //作者名                          可选值   [String]


    ///////////////////////////////////////////  Activity  Key of Data ///////////////////////////
    //Activity跳转
    public static final String APP_KEY_LOOK_IMAGE = "APP_START_LOOK_IMAGE";
    public static final String APP_KEY_OTHER_STR = "APP_KEY_OTHER_STR";


    ////////////    API     ///////
    //用于加载图片列表
    public static final String APP_API_IMAGE_LIST = "https://api.ihansen.org/img/detail?page={page}&index=&orderBy={tabs}&tag={tag}&favorites=";
    public static final String APP_API_TABS_LIST = "https://photo.ihansen.org";
    //必应每日一图
    public static final String APP_API_DAY_ONE_IMAGE = "https://api.no0a.cn/api/bing/0";

    public static String getUrl(JsonHomeTabsList jsonHomeTabsList, int page) {
        String replace = APP_API_IMAGE_LIST.replace("{page}", "" + page);
        if (jsonHomeTabsList.isTagTab())
            replace = replace.replace("{tabs}", "").replace("{tag}", jsonHomeTabsList.getTagE() == null ? "" : "" + jsonHomeTabsList.getTagE());
        else
            replace = replace.replace("{tabs}", jsonHomeTabsList.getTagE() == null ? "" : "" + jsonHomeTabsList.getTagE()).replace("{tag}", "");
        return replace;
    }

    public static String getUrl(String tag, int page, boolean isTagTab) {
        JsonHomeTabsList jsonHomeTabsList = new JsonHomeTabsList();
        jsonHomeTabsList.setTagE(tag);
        jsonHomeTabsList.setTagTab(isTagTab);
        return getUrl(jsonHomeTabsList, page);
    }









    /*



    /＼7　　　 ∠ /
    /　│　　 ／　／
    │　Z ＿,＜　／　　 /`ヽ
    │　　　　　ヽ　　 /　　〉
    Y　　　　　`　 /　　/
    ｲ●　､　●　　⊂⊃〈　　/
    （）　 へ　　　　|　＼〈
    >ｰ ､_　 ィ　 │ ／／
    / へ　　 /　ﾉ＜| ＼＼
    ヽ_ﾉ　　（_／　 │／／
    7　　　　　　　|／
    ＞―r￣￣`ｰ―＿丿😘



    吉祥物
    */


}

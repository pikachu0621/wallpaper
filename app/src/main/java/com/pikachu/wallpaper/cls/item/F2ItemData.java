package com.pikachu.wallpaper.cls.item;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;
import com.pikachu.wallpaper.util.app.AppInfo;

public class F2ItemData implements MultiItemEntity {



    public static int TEXT = 1,IMAGE = 2;
    public static int TEXT_MAX = AppInfo.APP_HOME_F2_ITEM_NUMBER;

    public JsonHomeTabsList getJsonHomeTabsList() {
        return jsonHomeTabsList;
    }

    public void setJsonHomeTabsList(JsonHomeTabsList jsonHomeTabsList) {
        this.jsonHomeTabsList = jsonHomeTabsList;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    private JsonHomeTabsList jsonHomeTabsList;
    private int itemType;

    public F2ItemData(JsonHomeTabsList jsonHomeTabsList, int itemType, int spanSize) {
        this.jsonHomeTabsList = jsonHomeTabsList;
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    private int spanSize;

    @Override
    public int getItemType() {
        return itemType;
    }


}

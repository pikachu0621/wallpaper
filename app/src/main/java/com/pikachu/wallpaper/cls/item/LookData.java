/**
 * 用于查看大图数据的传递
 */

package com.pikachu.wallpaper.cls.item;

import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;

import java.io.Serializable;
import java.util.List;

public class LookData implements Serializable  {


    public JsonHomeTabsList getImageTab() {
        return imageTab;
    }

    public void setImageTab(JsonHomeTabsList imageTab) {
        this.imageTab = imageTab;
    }

    public List<JsonHomeF1ImageList> getImageDataList() {
        return imageDataList;
    }

    public void setImageDataList(List<JsonHomeF1ImageList> imageDataList) {
        this.imageDataList = imageDataList;
    }

    public LookData(int page, int pointer, JsonHomeTabsList imageTab, List<JsonHomeF1ImageList> imageDataList) {
        this.page = page;
        this.pointer = pointer;
        this.imageTab = imageTab;
        this.imageDataList = imageDataList;
    }

    public LookData(){}

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }


    private int page; // 当前数据页
    private int pointer; // 当前图片指针
    private JsonHomeTabsList imageTab; // 图片Tab
    private List<JsonHomeF1ImageList> imageDataList; // 图片数据
}

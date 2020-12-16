package com.pikachu.wallpaper.cls.json;

public class JsonHomeTabsList {
    private String imageURl;
    private String tabStr;
    private String tagE;

    public boolean isTagTab() {
        return isTagTab;
    }

    public void setTagTab(boolean tagTab) {
        isTagTab = tagTab;
    }

    private boolean isTagTab;

    public JsonHomeTabsList(String imageURl, String tabStr, String tagE, boolean isTagTab) {
        this.imageURl = imageURl;
        this.tabStr = tabStr;
        this.tagE = tagE;
        this.isTagTab = isTagTab;
    }

    public JsonHomeTabsList(){}

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public String getTabStr() {
        return tabStr;
    }

    public void setTabStr(String tabStr) {
        this.tabStr = tabStr;
    }

    public String getTagE() {
        return tagE;
    }

    public void setTagE(String tagE) {
        this.tagE = tagE;
    }
}

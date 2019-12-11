package com.example.lmy.customview.RecyclerviewCheck;

/**
 * Created by guohao on 2017/9/6.
 * Description
 */

public class MyLiveList {

    private String title;
    private String id;
    public boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

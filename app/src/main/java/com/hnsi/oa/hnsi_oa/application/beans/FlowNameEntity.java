package com.hnsi.oa.hnsi_oa.application.beans;

/**
 * Created by Zheng on 2018/1/12.
 */

public class FlowNameEntity {
    private String title= "";
    private String lable= "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "FlowNameEntity{" +
                "title='" + title + '\'' +
                ", lable='" + lable + '\'' +
                '}';
    }
}

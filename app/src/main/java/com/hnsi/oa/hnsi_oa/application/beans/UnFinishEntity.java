package com.hnsi.oa.hnsi_oa.application.beans;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Zheng on 2018/1/5.
 */

public class UnFinishEntity {
    private ArrayList<FlowClassifyEntity> MenuItem;
    private String msg="";
    private boolean success;
    private ArrayList<FlowEntity> taskList;
    private int totalPage;
    private Map<String, String> urlMap;

    public ArrayList<FlowClassifyEntity> getMenuItem() {
        return MenuItem;
    }

    public void setMenuItem(ArrayList<FlowClassifyEntity> menuItem) {
        MenuItem = menuItem;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<FlowEntity> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<FlowEntity> taskList) {
        this.taskList = taskList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Map<String, String> getUrlMap() {
        return urlMap;
    }

    public void setUrlMap(Map<String, String> urlMap) {
        this.urlMap = urlMap;
    }

    @Override
    public String toString() {
        return "UnFinishEntity{" +
                "MenuItem=" + MenuItem +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                ", taskList=" + taskList +
                ", totalPage=" + totalPage +
                ", urlMap=" + urlMap +
                '}';
    }
}

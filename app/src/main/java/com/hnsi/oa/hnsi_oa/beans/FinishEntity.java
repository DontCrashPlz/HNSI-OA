package com.hnsi.oa.hnsi_oa.beans;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Zheng on 2018/1/8.
 */

public class FinishEntity {
    private String msg="";
    private boolean success;
    private ArrayList<FlowEntity> list;
    private int totalPage;
    private Map<String, String> urlMap;

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

    public ArrayList<FlowEntity> getList() {
        return list;
    }

    public void setList(ArrayList<FlowEntity> list) {
        this.list = list;
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
        return "FinishEntity{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                ", list=" + list +
                ", totalPage=" + totalPage +
                ", urlMap=" + urlMap +
                '}';
    }
}

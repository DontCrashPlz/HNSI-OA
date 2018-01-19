package com.hnsi.oa.hnsi_oa.beans;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/17.
 */

public class ApprovalEntity {
    private ArrayList<ApprovalWidgetEntity> ctlList;
    private ArrayList<ApprovalGroupEntity> groupList;
    private String msg= "";
    private boolean success;
    private String url= "";

    public ArrayList<ApprovalWidgetEntity> getCtlList() {
        return ctlList;
    }

    public void setCtlList(ArrayList<ApprovalWidgetEntity> ctlList) {
        this.ctlList = ctlList;
    }

    public ArrayList<ApprovalGroupEntity> getGroupList() {
        return groupList;
    }

    public void setGroupList(ArrayList<ApprovalGroupEntity> groupList) {
        this.groupList = groupList;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ApprovalEntity{" +
                "ctlList=" + ctlList +
                ", groupList=" + groupList +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                ", url='" + url + '\'' +
                '}';
    }
}

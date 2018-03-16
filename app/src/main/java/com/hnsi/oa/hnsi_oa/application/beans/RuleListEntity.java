package com.hnsi.oa.hnsi_oa.application.beans;

/**
 * Created by Zheng on 2018/1/31.
 */

public class RuleListEntity {
    /**
     * id : 162
     * title : 《河南省信息咨询设计研究有限公司员工考勤及休假管理办法》
     * operationTime : 2017-09-08 18:04:37.0
     */

    private int id;
    private String title= "";
    private String operationTime= "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    @Override
    public String toString() {
        return "RuleListEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", operationTime='" + operationTime + '\'' +
                '}';
    }
}

package com.hnsi.oa.hnsi_oa.beans;

/**
 * Created by Zheng on 2017/11/3.
 */

public class NewsDetailEntity {
    private String title;
    private String addTime;
    private String operatorName;
    private String operationDeptName;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperationDeptName() {
        return operationDeptName;
    }

    public void setOperationDeptName(String operationDeptName) {
        this.operationDeptName = operationDeptName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NewsDetailEntity{" +
                "title='" + title + '\'' +
                ", addTime='" + addTime + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", operationDeptName='" + operationDeptName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

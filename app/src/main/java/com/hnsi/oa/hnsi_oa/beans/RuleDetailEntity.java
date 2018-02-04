package com.hnsi.oa.hnsi_oa.beans;

/**
 * Created by Zheng on 2018/2/1.
 */

public class RuleDetailEntity {

    private String operatorName;
    private String operationTime;
    private String title;
    private String content;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RuleDetailEntity{" +
                "operatorName='" + operatorName + '\'' +
                ", operationTime='" + operationTime + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

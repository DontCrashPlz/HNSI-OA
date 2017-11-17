package com.hnsi.oa.hnsi_oa.beans;

public class NewsEntity{
    private int id;
    private int newsClass;
    private String classname;
    private String startDate;
    private String operatorName;
    private String title;
    private int viewScope;
    private int firstDeptId;
    private String operationDeptName;
    private String imgsrc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewsClass() {
        return newsClass;
    }

    public void setNewsClass(int newsClass) {
        this.newsClass = newsClass;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViewScope() {
        return viewScope;
    }

    public void setViewScope(int viewScope) {
        this.viewScope = viewScope;
    }

    public int getFirstDeptId() {
        return firstDeptId;
    }

    public void setFirstDeptId(int firstDeptId) {
        this.firstDeptId = firstDeptId;
    }

    public String getOperationDeptName() {
        return operationDeptName;
    }

    public void setOperationDeptName(String operationDeptName) {
        this.operationDeptName = operationDeptName;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "id=" + id +
                ", newsClass=" + newsClass +
                ", classname='" + classname + '\'' +
                ", startDate='" + startDate + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", title='" + title + '\'' +
                ", viewScope=" + viewScope +
                ", firstDeptId=" + firstDeptId +
                ", operationDeptName='" + operationDeptName + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                '}';
    }
}

//      {
//        "id":2942,
//        "newsClass":1,
//        "classname":"公司通知",
//        "startDate":"2017-03-27 00:00:00.0",
//        "operatorName":"马帅",
//        "title":"《关于召开2017年工作会议、员工（会员）代表大会暨第三届一次（员工）会员代表大会的通知》",
//        "viewScope":1,
//        "firstDeptId":5,
//        "operationDeptName":"综合管理部",
//        "imgsrc":null
//        }
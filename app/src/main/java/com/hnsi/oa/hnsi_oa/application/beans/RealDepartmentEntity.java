package com.hnsi.oa.hnsi_oa.application.beans;

/**
 * 真正使用的部门数据实体
 * Created by Zheng on 2017/12/29.
 */

public class RealDepartmentEntity {

    public static final int PARENT_DEPARTMENT=0;
    public static final int CHILD_DEPARTMENT=1;

    private int orgid;
    private String orgname="";
    private int parentorgid;
    private int type;
    private int num;

    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public int getParentorgid() {
        return parentorgid;
    }

    public void setParentorgid(int parentorgid) {
        this.parentorgid = parentorgid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "RealDepartmentEntity{" +
                "orgid=" + orgid +
                ", orgname='" + orgname + '\'' +
                ", parentorgid=" + parentorgid +
                ", type=" + type +
                ", num=" + num +
                '}';
    }
}

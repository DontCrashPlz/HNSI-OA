package com.hnsi.oa.hnsi_oa.application.beans;

/**
 * Created by Zheng on 2017/12/29.
 */

public class DepartmentEntity {
    private int orgid;
    private String orgname="";
    private int parentorgid;

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

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "orgid=" + orgid +
                ", orgname='" + orgname + '\'' +
                ", parentorgid=" + parentorgid +
                '}';
    }

}

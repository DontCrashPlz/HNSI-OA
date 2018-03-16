package com.hnsi.oa.hnsi_oa.application.beans;

import java.util.ArrayList;

/**
 * Created by Zheng on 2017/12/29.
 */

public class ContactEntity {
    private ArrayList<PersonEntity> empList= new ArrayList<>();
    private String msg="";
    private ArrayList<DepartmentEntity> orgList= new ArrayList<>();
    private boolean success;

    public ArrayList<PersonEntity> getEmpList() {
        return empList;
    }

    public void setEmpList(ArrayList<PersonEntity> empList) {
        this.empList = empList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<DepartmentEntity> getOrgList() {
        return orgList;
    }

    public void setOrgList(ArrayList<DepartmentEntity> orgList) {
        this.orgList = orgList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ContactEntity{" +
                "empList=" + empList +
                ", msg='" + msg + '\'' +
                ", orgList=" + orgList +
                ", success=" + success +
                '}';
    }

}

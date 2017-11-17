package com.hnsi.oa.hnsi_oa.beans;

public class UserInfo{
    //姓名
    private String empname="";
    //手机
    private String mobileno="";
    //办公电话
    private String otel="";
    //邮箱
    private String oemail="";
    //性别
    private String sex="";
    //部门id
    private int orgid;
    //部门
    private String orgname="";
    //职务
    private String posiname="";
    //人员id
    private int empid;
    //头像url
    private String headimg="";

    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getOtel() {
        return otel;
    }

    public void setOtel(String otel) {
        this.otel = otel;
    }

    public String getOemail() {
        return oemail;
    }

    public void setOemail(String oemail) {
        this.oemail = oemail;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getPosiname() {
        return posiname;
    }

    public void setPosiname(String posiname) {
        this.posiname = posiname;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "empname='" + empname + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", otel='" + otel + '\'' +
                ", oemail='" + oemail + '\'' +
                ", sex='" + sex + '\'' +
                ", orgid=" + orgid +
                ", orgname='" + orgname + '\'' +
                ", posiname='" + posiname + '\'' +
                ", empid=" + empid +
                ", headimg='" + headimg + '\'' +
                '}';
    }
}
package com.hnsi.oa.hnsi_oa.application.beans;

/**
 * Created by Zheng on 2017/12/29.
 */

public class PersonEntity {
    private String empname="";
    private String mobileno="";
    private String otel="";
    private String oemail="";
    private String sex="";
    private int orgid;
    private String orgname="";
    private String posiname="";
    private int empid;
    private String headimg="";

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

    public String getPosiname() {
        return posiname;
    }

    public void setPosiname(String posiname) {
        this.posiname = posiname;
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

    @Override
    public String toString() {
        return "PersonEntity{" +
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

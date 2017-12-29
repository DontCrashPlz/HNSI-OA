package com.hnsi.oa.hnsi_oa.beans;

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
}

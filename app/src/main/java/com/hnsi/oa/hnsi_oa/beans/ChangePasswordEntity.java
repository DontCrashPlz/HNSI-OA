package com.hnsi.oa.hnsi_oa.beans;

/**
 * Created by Zheng on 2018/1/15.
 */

public class ChangePasswordEntity {
    private boolean success;
    private String msg="";

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ChangePasswordEntity{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}

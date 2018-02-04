package com.hnsi.oa.hnsi_oa.beans;

/**
 * Created by Zheng on 2018/1/26.
 */

public class ApprovalResponseEntity {
    private String msg= "";
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ApprovalResponseEntity{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                '}';
    }
}

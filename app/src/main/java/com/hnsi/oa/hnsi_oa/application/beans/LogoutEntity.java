package com.hnsi.oa.hnsi_oa.application.beans;

/**
 * Created by Zheng on 2018/1/15.
 */

public class LogoutEntity {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "LogoutEntity{" +
                "success=" + success +
                '}';
    }
}

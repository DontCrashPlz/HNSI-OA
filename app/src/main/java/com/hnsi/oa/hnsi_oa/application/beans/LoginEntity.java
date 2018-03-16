package com.hnsi.oa.hnsi_oa.application.beans;

/**
 * 用户信息实体，用于登录后保存用户信息。
 * Created by Zheng on 2016/5/13.
 */
public class LoginEntity {

    private String msg="";

    private boolean success;

    private UserInfo userInfo= new UserInfo();

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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                ", userInfo=" + userInfo +
                '}';
    }
}

//{"msg":"登录成功",
// "success":true,
// "userInfo":{"empname":"张三","mobileno":"11","otel":null,"oemail":null,"sex":"女","orgid":8,"orgname":"软信科技子公司","posiname":"员工","empid":2,"headimg":null}}
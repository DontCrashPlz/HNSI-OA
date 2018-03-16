package com.hnsi.oa.hnsi_oa.application.beans;

/**
 * Created by Zheng on 2018/3/16.
 */

public class ExceptionEntity {
    /**
     * exception : {"code":"12101001","message":"ErrCode: 12101001\nMessage: session失效或者用户未登陆.\n","invalid":true,"loginPage":"/default/coframe/auth/login/login.jsp"}
     */
    private String code;
    private String message;
    private boolean invalid;
    private String loginPage;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    @Override
    public String toString() {
        return "ExceptionEntity{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", invalid=" + invalid +
                ", loginPage='" + loginPage + '\'' +
                '}';
    }
}

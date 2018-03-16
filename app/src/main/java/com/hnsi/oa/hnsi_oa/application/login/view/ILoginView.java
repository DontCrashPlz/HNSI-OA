package com.hnsi.oa.hnsi_oa.application.login.view;

/**
 * Created by Zheng on 2017/10/23.
 */

public interface ILoginView {
    void showProgressDialog();
    void dismissProgressDialog();
    boolean getRememberState();
    boolean getAutoState();
    String getUserName();
    String getPassword();
    void setUserName();
    void setPassword();
    void hideSoftKeyboard();
    void intoHome();
}

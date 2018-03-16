package com.hnsi.oa.hnsi_oa.application.login.model;

import com.hnsi.oa.hnsi_oa.application.interfaces.OnLoginListener;

/**
 * Created by Zheng on 2017/10/23.
 */

public interface ILoginModel {
    void doLogin(String userName, String password, OnLoginListener listener);
}

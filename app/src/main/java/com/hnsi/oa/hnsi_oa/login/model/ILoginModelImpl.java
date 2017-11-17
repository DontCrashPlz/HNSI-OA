package com.hnsi.oa.hnsi_oa.login.model;

import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.interfaces.OnLoginListener;

/**
 * Created by Zheng on 2017/10/23.
 */

public class ILoginModelImpl implements ILoginModel {
    @Override
    public void doLogin(String userName, String password, OnLoginListener listener) {
        MyApplication.getInstance().userLogin(userName,password,listener);
    }
}

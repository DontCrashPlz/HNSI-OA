package com.hnsi.oa.hnsi_oa.application.login.view;

/**
 * Created by Zheng on 2017/10/23.
 */

public interface ISettingView {
    void setIpAddress(String ipAddress);
    void setPortNumber(String portNumber);
    String getIpAddress();
    String getPortNumber();
}

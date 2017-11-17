package com.hnsi.oa.hnsi_oa.app;

import com.hnsi.oa.hnsi_oa.interfaces.OnLoginListener;

/**
 * Created by Zheng on 2017/10/20.
 */

public interface User {
    void userLogin(String userName, String password, OnLoginListener listener);
    void userLogout();
    void changePassword(String newPassword, String oldPassword);
    void getPendingList();
    void getFinishedList();
    void getApprovalDetail();
    void getApprovalHistory();
    void getMatterCount();
    void getFlowCount();
    void getMessageList();
    void getMessageDetail();
    void uploadHeadImg();
}

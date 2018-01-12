package com.hnsi.oa.hnsi_oa.app;

import com.hnsi.oa.hnsi_oa.beans.FinishEntity;
import com.hnsi.oa.hnsi_oa.beans.FlowNameResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.UnFinishEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnLoginListener;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;

/**
 * Created by Zheng on 2017/10/20.
 */

public interface User {
    void userLogin(String userName, String password, OnLoginListener listener);
    void userLogout();
    void changePassword(String newPassword, String oldPassword);
    void getPendingList(int page, OnRequestDataListener<UnFinishEntity> listener);
    void getPendingFlowList(int page, String processDefnames, OnRequestDataListener<UnFinishEntity> listener);
    void getFinishedList(int page, OnRequestDataListener<FinishEntity> listener);
    void getApprovalDetail();
    void getApprovalHistory();
    void getMatterCount();
    void getFlowCount();
    void getMessageList();
    void getMessageDetail();
    void uploadHeadImg();
    void getFlowNames(OnRequestDataListener<FlowNameResponseEntity> listener);
}

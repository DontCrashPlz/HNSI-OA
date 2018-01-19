package com.hnsi.oa.hnsi_oa.app;

import com.hnsi.oa.hnsi_oa.beans.ApprovalEntity;
import com.hnsi.oa.hnsi_oa.beans.ChangePasswordEntity;
import com.hnsi.oa.hnsi_oa.beans.FinishEntity;
import com.hnsi.oa.hnsi_oa.beans.FlowNameResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.UnFinishEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnLoginListener;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;

/**
 * Created by Zheng on 2017/10/20.
 */

public interface User {
    /**
     * 登录
     * @param userName
     * @param password
     * @param listener
     */
    void userLogin(String userName, String password, OnLoginListener listener);

    /**
     * 注销
     * @param listener
     */
    void userLogout(OnLoginListener listener);

    /**
     * 修改密码
     * @param newPassword
     * @param oldPassword
     * @param listener
     */
    void changePassword(String newPassword, String oldPassword, OnRequestDataListener<ChangePasswordEntity> listener);

    /**
     * 获取待办列表
     * @param page
     * @param listener
     */
    void getPendingList(int page, OnRequestDataListener<UnFinishEntity> listener);

    /**
     * 获取指定流程的待办列表
     * @param page
     * @param processDefnames
     * @param listener
     */
    void getPendingFlowList(int page, String processDefnames, OnRequestDataListener<UnFinishEntity> listener);

    /**
     * 获取已办列表
     * @param page
     * @param listener
     */
    void getFinishedList(int page, OnRequestDataListener<FinishEntity> listener);

    /**
     * 获取审批详情
     * @param url
     * @param workItemID
     * @param activityDefID
     * @param processInstID
     * @param listener
     */
    void getApprovalDetail(String url, String workItemID, String activityDefID, String processInstID, OnRequestDataListener<ApprovalEntity> listener);
    void getApprovalHistory();
    void getMatterCount();
    void getFlowCount();
    void getMessageList();
    void getMessageDetail();
    void uploadHeadImg();
    void getFlowNames(OnRequestDataListener<FlowNameResponseEntity> listener);
}

package com.hnsi.oa.hnsi_oa.app;

import com.hnsi.oa.hnsi_oa.beans.ApprovalEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalHistoryResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.ChangePasswordEntity;
import com.hnsi.oa.hnsi_oa.beans.FinishEntity;
import com.hnsi.oa.hnsi_oa.beans.FlowNameResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.MessageDetailEntity;
import com.hnsi.oa.hnsi_oa.beans.MessageListResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.UnFinishEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnLoginListener;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;

import java.util.Map;

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

    /**
     * 获取审批历史记录
     * @param processInstId
     * @param listener
     */
    void getApprovalHistory(int processInstId, OnRequestDataListener<ApprovalHistoryResponseEntity> listener);

    /**
     * 提交审批
     * @param url
     * @param params
     * @param listener
     */
    void commitApproval(String url, Map<String, String> params, OnRequestDataListener<ApprovalResponseEntity> listener);
    void getMatterCount();
    void getFlowCount();

    /**
     * 获取消息列表
     * @param pageIndex
     * @param listener
     */
    void getMessageList(int pageIndex, OnRequestDataListener<MessageListResponseEntity> listener);

    /**
     * 获取消息详情
     * @param id
     * @param listener
     */
    void getMessageDetail(int id, OnRequestDataListener<MessageDetailEntity> listener);
    void uploadHeadImg();
    void getFlowNames(OnRequestDataListener<FlowNameResponseEntity> listener);
}

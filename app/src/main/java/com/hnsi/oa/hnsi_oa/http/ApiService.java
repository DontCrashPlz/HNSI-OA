package com.hnsi.oa.hnsi_oa.http;

import com.hnsi.oa.hnsi_oa.beans.ApprovalEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalHistoryResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.ChangePasswordEntity;
import com.hnsi.oa.hnsi_oa.beans.ContactEntity;
import com.hnsi.oa.hnsi_oa.beans.FinishEntity;
import com.hnsi.oa.hnsi_oa.beans.FlowNameEntity;
import com.hnsi.oa.hnsi_oa.beans.FlowNameResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.LoginEntity;
import com.hnsi.oa.hnsi_oa.beans.LogoutEntity;
import com.hnsi.oa.hnsi_oa.beans.MessageDetailEntity;
import com.hnsi.oa.hnsi_oa.beans.MessageListResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsDetailEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsDetailResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsListEntity;
import com.hnsi.oa.hnsi_oa.beans.RuleDetailResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.RuleListResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.UnFinishEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Zheng on 2017/11/1.
 * baseUrl= http://192.168.1.68:80/default/
 */

public interface ApiService {

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    @GET("/default/mobile/user/com.hnsi.erp.mobile.user.LoginManager.login.biz.ext")
    Call<LoginEntity> doLogin(@Query("username")String userName,
                              @Query("password")String password);

    /**
     * 通讯录列表
     * @return
     */
    @GET("/default/mobile/user/com.hnsi.erp.mobile.user.AddressListManager.list.biz.ext")
    Call<ContactEntity> getContacts();

    /**
     * 通讯录查询
     * @param name
     * @return
     */
//    @GET("/default/mobile/user/com.hnsi.erp.mobile.user.AddressListManager.search.biz.ext")
//    Call searchContact(@Query("name")String name);

    /**
     * 注销
     * @return
     */
    @GET("/default/mobile/user/com.hnsi.erp.mobile.user.LoginManager.logout.biz.ext")
    Call<LogoutEntity> doLogout();

    /**
     * 更改密码
     * @param newPassword
     * @param oldPassword
     * @return
     */
    @GET("/default/mobile/user/com.hnsi.erp.mobile.user.LoginManager.updatePassword.biz.ext")
    Call<ChangePasswordEntity> changePassword(@Query("newPassword")String newPassword,
                                              @Query("oldPassword")String oldPassword);

    /**
     * 获取新闻列表
     * @param pageIndex
     * @param classId
     * @param type
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.NewsSearch.list.biz.ext")
    Call<NewsListEntity> getNewsList(@Query("pageIndex")int pageIndex,
                                     @Query("classId")int classId,
                                     @Query("type")int type);

    /**
     * 获取新闻详情
     * @param id
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.NewsSearch.getNews.biz.ext")
    Call<NewsDetailResponseEntity> getNewsDetail(@Query("id")int id);

    /**
     * 获取规章制度列表
     * @param pageIndex
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.RulesSearch.list.biz.ext")
    Call<RuleListResponseEntity> getRuleList(@Query("pageIndex")String pageIndex);

    /**
     * 获取规章制度详情
     * @param id
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.RulesSearch.detail.biz.ext")
    Call<RuleDetailResponseEntity> getRuleDetail(@Query("id")String id);

    /**
     * 下载规章制度附件
     * @param isOpen
     * @param fileid
     * @return
     */
    @GET("/default/erp/common/fileUpload/download.jsp")
    Call downloadRuleFiles(@Query("isOpen")boolean isOpen,
                           @Query("fileid")String fileid);

    /**
     * 获取待办列表
     * @param pageIndex
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.TaskAuditSearch.pendingList.biz.ext")
    Call<UnFinishEntity> getPendingList(@Query("pageIndex")String pageIndex);

    /**
     * 获取指定流程的待办列表
     * @param pageIndex
     * @return
     */
    @GET("/default/com.hnsi.erp.mobile.oa.TaskAuditSearch.pendingListByDef.biz.ext")
    Call<UnFinishEntity> getPendingFlowList(@Query("pageIndex")String pageIndex,
                                            @Query("processDefnames")String processDefnames);

    /**
     * 获取流程类别列表
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.TaskAuditSearch.countAllFlow.biz.ext")
    Call<FlowNameResponseEntity> getFlowNameList();

    /**
     * 获取已办列表
     * @param pageIndex
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.TaskAuditSearch.finishedList.biz.ext")
    Call<FinishEntity> getFinishedList(@Query("pageIndex")String pageIndex);

    /**
     * 获取审批详情
     * @param url
     * @param workItemID
     * @param activityDefID
     * @param processInstID
     * @return
     */
    @GET
    Call<ApprovalEntity> getApprovalDetail(@Url String url,
                                           @Query("workItemID")String workItemID,
                                           @Query("activityDefID")String activityDefID,
                                           @Query("processInstID")String processInstID);

    /**
     * 查询审批记录
     * @param processInstID
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.TaskAuditSearch.queryDisposeLog.biz.ext")
    Call<ApprovalHistoryResponseEntity> getApprovalHistory(@Query("processInstID")String processInstID);

    /**
     * 提交审批
     * @param url
     * @param params
     * @return
     */
    @POST
    Call<ApprovalResponseEntity> commitApproval(@Url String url,
                                                @QueryMap Map<String, String> params);

    /**
     * 获取待办事项数量
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.TaskSearch.count.biz.ext")
    Call getMatterCount();

    /**
     * 获取待办流程数量
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.TaskAuditSearch.count.biz.ext")
    Call getFlowCount();

    /**
     * 获取系统消息列表
     * @param pageIndex
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.TaskSearch.msgList.biz.ext")
    Call<MessageListResponseEntity> getMessageList(@Query("pageIndex")String pageIndex);

    /**
     * 获取系统消息详情
     * @param id
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.common.MsgManager.getData.biz.ext")
    Call<MessageDetailEntity> getMessageDetail(@Query("id")String id);

    /**
     * 上传头像
     * @return
     */
    @GET("/default/mobile/user/com.hnsi.erp.mobile.user.AddressListManager.upload.biz.ext")
    Call uploadHeadImg();

    /**
     * 下载附件
     * @param isOpen
     * @param fileid
     * @return
     */
    @GET("/default/mobile/oa/download.jsp")
    Call downloadFiles(@Query("isOpen")boolean isOpen,
                       @Query("fileid")String fileid);

}
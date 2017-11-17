package com.hnsi.oa.hnsi_oa.http;

import com.hnsi.oa.hnsi_oa.beans.LoginEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsDetailEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsDetailResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsListEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
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
    Call getContacts();

    /**
     * 通讯录查询
     * @param name
     * @return
     */
    @GET("/default/mobile/user/com.hnsi.erp.mobile.user.AddressListManager.search.biz.ext")
    Call searchContact(@Query("name")String name);

    /**
     * 注销
     * @return
     */
    @GET("/default/mobile/user/com.hnsi.erp.mobile.user.LoginManager.logout.biz.ext")
    Call doLogout();

    /**
     * 更改密码
     * @param newPassword
     * @param oldPassword
     * @return
     */
    @GET("/default/mobile/user/com.hnsi.erp.mobile.user.LoginManager.updatePassword.biz.ext")
    Call changePassword(@Query("newPassword")String newPassword,
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
    Call getRuleList(@Query("pageIndex")String pageIndex);

    /**
     * 获取规章制度详情
     * @param id
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.RulesSearch.detail.biz.ext")
    Call getRuleDetail(@Query("id")String id);

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
    Call getPendingList(@Query("pageIndex")String pageIndex);

    /**
     * 获取已办列表
     * @param pageIndex
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.TaskAuditSearch.finishedList.biz.ext")
    Call getFinishedList(@Query("pageIndex")String pageIndex);

    @GET
    Call getApprovalDetail(@Url String url,
                           @Query("")String param1,
                           @Query("")String param2,
                           @Query("")String param3);

    /**
     * 查询审批记录
     * @param processInstID
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.oa.TaskAuditSearch.queryDisposeLog.biz.ext")
    Call getApprovalHistory(@Query("processInstID")String processInstID);

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
    Call getMessageList(@Query("pageIndex")String pageIndex);

    /**
     * 获取系统消息详情
     * @param id
     * @return
     */
    @GET("/default/mobile/oa/com.hnsi.erp.mobile.common.MsgManager.getData.biz.ext")
    Call getMessageDetail(@Query("id")String id);

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
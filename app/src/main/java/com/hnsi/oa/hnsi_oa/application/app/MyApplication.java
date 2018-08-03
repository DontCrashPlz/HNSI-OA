package com.hnsi.oa.hnsi_oa.application.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.hnsi.oa.hnsi_oa.BuildConfig;
import com.hnsi.oa.hnsi_oa.application.beans.ApprovalEntity;
import com.hnsi.oa.hnsi_oa.application.beans.ApprovalHistoryResponseEntity;
import com.hnsi.oa.hnsi_oa.application.beans.ApprovalResponseEntity;
import com.hnsi.oa.hnsi_oa.application.beans.ChangePasswordEntity;
import com.hnsi.oa.hnsi_oa.application.beans.ContactEntity;
import com.hnsi.oa.hnsi_oa.application.beans.FinishEntity;
import com.hnsi.oa.hnsi_oa.application.beans.FlowNameResponseEntity;
import com.hnsi.oa.hnsi_oa.application.beans.LoginEntity;
import com.hnsi.oa.hnsi_oa.application.beans.LogoutEntity;
import com.hnsi.oa.hnsi_oa.application.beans.MessageDetailEntity;
import com.hnsi.oa.hnsi_oa.application.beans.MessageListResponseEntity;
import com.hnsi.oa.hnsi_oa.application.beans.NewsDetailEntity;
import com.hnsi.oa.hnsi_oa.application.beans.NewsDetailResponseEntity;
import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.application.beans.NewsListEntity;
import com.hnsi.oa.hnsi_oa.application.beans.RuleDetailEntity;
import com.hnsi.oa.hnsi_oa.application.beans.RuleDetailResponseEntity;
import com.hnsi.oa.hnsi_oa.application.beans.RuleListResponseEntity;
import com.hnsi.oa.hnsi_oa.application.beans.UnFinishEntity;
import com.hnsi.oa.hnsi_oa.application.beans.UpdateInfoEntity;
import com.hnsi.oa.hnsi_oa.application.beans.UserInfo;
import com.hnsi.oa.hnsi_oa.application.http.ApiService;
import com.hnsi.oa.hnsi_oa.application.http.NovateCookieManger;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnLoginListener;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataAndNumListener;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;

import library.apps.MyUncatchExceptionHandler;
import library.utils.LogUtil;
import library.utils.SharedPrefUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zheng on 2017/10/20.
 */

public class MyApplication extends Application implements User {

    public static final String IP_ADDRESS="ip_address";
    public static final String PORT_NUMBER="port_number";

    public UpdateInfoEntity updateInfoEntity= null;

    /**--------------------SingleInstance-----------------------*/

    private static MyApplication mSingleInstance;

    public static MyApplication getInstance(){
        return mSingleInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSingleInstance= this;

        MyUncatchExceptionHandler myUncatchExceptionHandler= MyUncatchExceptionHandler.getInstance();
        myUncatchExceptionHandler.init(this);

        initHttpInstance();
    }

    /**--------------------public UserInfo-----------------------*/

    private UserInfo mUserInfo;

    public void setUserInfo(UserInfo userInfo){
        mUserInfo= userInfo;
    }

    public UserInfo getUserInfo(){
        return mUserInfo;
    }


    /**--------------------Retrofit Config-----------------------*/

    private Retrofit mRetrofit;
    private ApiService apiService;
    private OkHttpClient mOkHttpClient;
    private String mBaseUrl;

    public String getBaseUrl(){
        if (BuildConfig.DEBUG)
            Log.d("mBaseUrl",mBaseUrl);
        if (mBaseUrl== null)
            return "http://192.168.1.68:80/";
        return mBaseUrl;
    }

    public void initHttpInstance(){

        StringBuilder builder= new StringBuilder();
        builder.append("http://");
        builder.append(SharedPrefUtils.get(this,IP_ADDRESS,"192.168.1.68"));
        builder.append(":");
        builder.append(SharedPrefUtils.get(this,PORT_NUMBER,"80"));
        builder.append("/");
        if (BuildConfig.DEBUG)
            Log.d("ipAddress",builder.toString());
        mBaseUrl= builder.toString();

        mOkHttpClient= new OkHttpClient.Builder()
                .cookieJar(new NovateCookieManger(getApplicationContext()))
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .build();
//        mOkHttpClient= new OkHttpClient.Builder()
//                .addInterceptor(new SaveCookiesInterceptor())
//                .addInterceptor(new ReadCookiesInterceptor())
//                .connectTimeout(5, TimeUnit.SECONDS)
//                .writeTimeout(5, TimeUnit.SECONDS)
//                .readTimeout(5,TimeUnit.SECONDS)
//                .build();

        mRetrofit= new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService= mRetrofit.create(ApiService.class);
    }

    public Retrofit getRetrofitInstance(){
        if (mRetrofit== null)
            initHttpInstance();
        return mRetrofit;
    }

    public ApiService getApiServiceInstance(){
        if (apiService== null)
            initHttpInstance();
        return apiService;
    }


    /**----------------------User Interface---------------------*/

    @Override
    public void userLogin(String userName, String password, final OnLoginListener listener) {
        Call<LoginEntity> loginCall= apiService.doLogin(userName,password);
        loginCall.enqueue(new Callback<LoginEntity>() {
            @Override
            public void onResponse(Call<LoginEntity> call, Response<LoginEntity> response) {
                LogUtil.e("LoginEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("LoginEntity body", response.body().toString());
                }else {
                    LogUtil.e("LoginEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    MyApplication.getInstance().setUserInfo(response.body().getUserInfo());
                    listener.onSuccessed();
                }

            }

            @Override
            public void onFailure(Call<LoginEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    @Override
    public void userLogout(final OnLoginListener listener) {
        Call<LogoutEntity> logoutCall= apiService.doLogout();
        logoutCall.enqueue(new Callback<LogoutEntity>() {
            @Override
            public void onResponse(Call<LogoutEntity> call, Response<LogoutEntity> response) {
                LogUtil.e("LogoutEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("LogoutEntity body", response.body().toString());
                }else {
                    LogUtil.e("LogoutEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed("注销失败");
                }else{
                    listener.onSuccessed();
                }
            }

            @Override
            public void onFailure(Call<LogoutEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    @Override
    public void changePassword(String newPassword, String oldPassword, final OnRequestDataListener<ChangePasswordEntity> listener) {
        Call<ChangePasswordEntity> changePasswordCall= apiService.changePassword(newPassword, oldPassword);
        changePasswordCall.enqueue(new Callback<ChangePasswordEntity>() {
            @Override
            public void onResponse(Call<ChangePasswordEntity> call, Response<ChangePasswordEntity> response) {
                LogUtil.e("ChangePasswordEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("ChangePasswordEntity body", response.body().toString());
                }else {
                    LogUtil.e("ChangePasswordEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getPendingList(int page , final OnRequestDataListener<UnFinishEntity> listener) {
        Call<UnFinishEntity> pendingListCall= apiService.getPendingList(String.valueOf(page));
        pendingListCall.enqueue(new Callback<UnFinishEntity>() {
            @Override
            public void onResponse(Call<UnFinishEntity> call, Response<UnFinishEntity> response) {
                LogUtil.e("UnFinishEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("UnFinishEntity body", response.body().toString());
                }else {
                    LogUtil.e("UnFinishEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<UnFinishEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getPendingFlowList(int page, String processDefnames, final OnRequestDataListener<UnFinishEntity> listener) {
        Call<UnFinishEntity> pendingFlowListCall= apiService.getPendingFlowList(String.valueOf(page), processDefnames);
        pendingFlowListCall.enqueue(new Callback<UnFinishEntity>() {
            @Override
            public void onResponse(Call<UnFinishEntity> call, Response<UnFinishEntity> response) {
                LogUtil.e("UnFinishEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("UnFinishEntity body", response.body().toString());
                }else {
                    LogUtil.e("UnFinishEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<UnFinishEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getFinishedList(int page , final OnRequestDataListener<FinishEntity> listener) {
        Call<FinishEntity> finishedListCall= apiService.getFinishedList(String.valueOf(page));
        finishedListCall.enqueue(new Callback<FinishEntity>() {
            @Override
            public void onResponse(Call<FinishEntity> call, Response<FinishEntity> response) {
                LogUtil.e("FinishEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("FinishEntity body", response.body().toString());
                }else {
                    LogUtil.e("FinishEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<FinishEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getApprovalDetail(
            String url,
            String workItemID,
            String activityDefID,
            String processInstID,
            final OnRequestDataListener<ApprovalEntity> listener ) {
        Call<ApprovalEntity> approvalCall= apiService.getApprovalDetail(url, workItemID, activityDefID, processInstID);
        approvalCall.enqueue(new Callback<ApprovalEntity>() {
            @Override
            public void onResponse(Call<ApprovalEntity> call, Response<ApprovalEntity> response) {
                LogUtil.e("ApprovalEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("ApprovalEntity body", response.body().toString());
                }else {
                    LogUtil.e("ApprovalEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<ApprovalEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getApprovalHistory(int processInstId, final OnRequestDataListener<ApprovalHistoryResponseEntity> listener) {
        Call<ApprovalHistoryResponseEntity> historyCall= apiService.getApprovalHistory(String.valueOf(processInstId));
        historyCall.enqueue(new Callback<ApprovalHistoryResponseEntity>() {
            @Override
            public void onResponse(Call<ApprovalHistoryResponseEntity> call, Response<ApprovalHistoryResponseEntity> response) {
                LogUtil.e("ApprovalHistoryResponseEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("ApprovalHistoryResponseEntity body", response.body().toString());
                }else {
                    LogUtil.e("ApprovalHistoryResponseEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<ApprovalHistoryResponseEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    @Override
    public void commitApproval(
            String url,
            Map<String, String> params,
            final OnRequestDataListener<ApprovalResponseEntity> listener) {
        Call<ApprovalResponseEntity> approvalCall= apiService.commitApproval(url, params);
        approvalCall.enqueue(new Callback<ApprovalResponseEntity>() {
            @Override
            public void onResponse(Call<ApprovalResponseEntity> call, Response<ApprovalResponseEntity> response) {
                LogUtil.e("ApprovalResponseEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("ApprovalResponseEntity body", response.body().toString());
                }else {
                    LogUtil.e("ApprovalResponseEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<ApprovalResponseEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getMatterCount() {

    }

    @Override
    public void getFlowCount() {

    }

    @Override
    public void getMessageList(int pageIndex, final OnRequestDataListener<MessageListResponseEntity> listener) {
        Call<MessageListResponseEntity> messageListCall= apiService.getMessageList(String.valueOf(pageIndex));
        messageListCall.enqueue(new Callback<MessageListResponseEntity>() {
            @Override
            public void onResponse(Call<MessageListResponseEntity> call, Response<MessageListResponseEntity> response) {
                LogUtil.e("MessageListResponseEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("MessageListResponseEntity body", response.body().toString());
                }else {
                    LogUtil.e("MessageListResponseEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<MessageListResponseEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getMessageDetail(int id, final OnRequestDataListener<MessageDetailEntity> listener) {
        Call<MessageDetailEntity> messageCall= apiService.getMessageDetail(String.valueOf(id));
        messageCall.enqueue(new Callback<MessageDetailEntity>() {
            @Override
            public void onResponse(Call<MessageDetailEntity> call, Response<MessageDetailEntity> response) {
                LogUtil.e("MessageDetailEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("MessageDetailEntity body", response.body().toString());
                }else {
                    LogUtil.e("MessageDetailEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<MessageDetailEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    @Override
    public void uploadHeadImg() {

    }

    @Override
    public void getFlowNames(final OnRequestDataListener<FlowNameResponseEntity> listener) {
        Call<FlowNameResponseEntity> flowNameCall= apiService.getFlowNameList();
        flowNameCall.enqueue(new Callback<FlowNameResponseEntity>() {
            @Override
            public void onResponse(Call<FlowNameResponseEntity> call, Response<FlowNameResponseEntity> response) {
                LogUtil.e("FlowNameResponseEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("FlowNameResponseEntity body", response.body().toString());
                }else {
                    LogUtil.e("FlowNameResponseEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else {
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<FlowNameResponseEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }


    /**----------------------Common Http Request---------------------*/

    public void getContacts(final OnRequestDataListener<ContactEntity> listener){
        Call<ContactEntity> contactCall= apiService.getContacts();
        contactCall.enqueue(new Callback<ContactEntity>() {
            @Override
            public void onResponse(Call<ContactEntity> call, Response<ContactEntity> response) {
                LogUtil.e("ContactEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("ContactEntity body", response.body().toString());
                }else {
                    LogUtil.e("ContactEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<ContactEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    public void searchContact(){

    }

    public void getNewsList(int pageIndex, int classId, int type, final OnRequestDataListener<List<NewsEntity>> listener){
        Call<NewsListEntity> newsCall= apiService.getNewsList(pageIndex,classId,type);
        newsCall.enqueue(new Callback<NewsListEntity>() {
            @Override
            public void onResponse(Call<NewsListEntity> call, Response<NewsListEntity> response) {
                LogUtil.e("NewsListEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("NewsListEntity body", response.body().toString());
                }else {
                    LogUtil.e("NewsListEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body().getList());
                }
            }

            @Override
            public void onFailure(Call<NewsListEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    public void getNewsListAndPageNum(int pageIndex, int classId, int type, final OnRequestDataAndNumListener<List<NewsEntity>> listener){
        Call<NewsListEntity> newsCall= apiService.getNewsList(pageIndex,classId,type);
        newsCall.enqueue(new Callback<NewsListEntity>() {
            @Override
            public void onResponse(Call<NewsListEntity> call, Response<NewsListEntity> response) {
                LogUtil.e("NewsListEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("NewsListEntity body", response.body().toString());
                }else {
                    LogUtil.e("NewsListEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body().getList(), response.body().getTotalPage());
                }
            }

            @Override
            public void onFailure(Call<NewsListEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    public void getNewsDetail(int id, final OnRequestDataListener<NewsDetailEntity> listener){
        Call<NewsDetailResponseEntity> newsDetailCall= apiService.getNewsDetail(id);
        newsDetailCall.enqueue(new Callback<NewsDetailResponseEntity>() {
            @Override
            public void onResponse(Call<NewsDetailResponseEntity> call, Response<NewsDetailResponseEntity> response) {
                LogUtil.e("NewsDetailResponseEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("NewsDetailResponseEntity body", response.body().toString());
                }else {
                    LogUtil.e("NewsDetailResponseEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else{
                    listener.onSuccessed(response.body().getNews());
                    Log.e("",response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<NewsDetailResponseEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    public void getRuleList(int pageIndex, final OnRequestDataListener<RuleListResponseEntity> listener){
        Call<RuleListResponseEntity> ruleListCall= apiService.getRuleList(String.valueOf(pageIndex));
        ruleListCall.enqueue(new Callback<RuleListResponseEntity>() {
            @Override
            public void onResponse(Call<RuleListResponseEntity> call, Response<RuleListResponseEntity> response) {
                LogUtil.e("RuleListResponseEntity onResponse", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("RuleListResponseEntity body", response.body().toString());
                }else {
                    LogUtil.e("RuleListResponseEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else if (! response.body().isSuccess()){
                    listener.onFailed(response.body().getMsg());
                }else{
                    listener.onSuccessed(response.body());
                }
            }

            @Override
            public void onFailure(Call<RuleListResponseEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });
    }

    public void getRuleDetail(int id, final OnRequestDataListener<RuleDetailEntity> listener){
        Call<RuleDetailResponseEntity> ruleDetailCall= apiService.getRuleDetail(String.valueOf(id));
        ruleDetailCall.enqueue(new Callback<RuleDetailResponseEntity>() {
            @Override
            public void onResponse(Call<RuleDetailResponseEntity> call, Response<RuleDetailResponseEntity> response) {
                LogUtil.e("RuleDetailResponseEntity", response.toString());
                if (response.isSuccessful()){
                    LogUtil.e("RuleDetailResponseEntity body", response.body().toString());
                }else {
                    LogUtil.e("RuleDetailResponseEntity error body", response.errorBody().toString());
                }

                if (response.code()!= 200){
                    listener.onFailed("ErrorCode:"+response.code()+" ErrorMessage:"+response.message());
                }else{
                    listener.onSuccessed(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<RuleDetailResponseEntity> call, Throwable t) {
                listener.onFailed(t.toString());
            }
        });

    }

    public void downloadRuleFiles(){

    }

    public void downloadFiles(){

    }


    /**----------------------else util Class---------------------*/
    /**
     * 获取当前程序的版本号
     */
    public String getVersionName() throws Exception{
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionName;
    }

}

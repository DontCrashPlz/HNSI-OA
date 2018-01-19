package com.hnsi.oa.hnsi_oa.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.hnsi.oa.hnsi_oa.BuildConfig;
import com.hnsi.oa.hnsi_oa.beans.ApprovalEntity;
import com.hnsi.oa.hnsi_oa.beans.ChangePasswordEntity;
import com.hnsi.oa.hnsi_oa.beans.ContactEntity;
import com.hnsi.oa.hnsi_oa.beans.FinishEntity;
import com.hnsi.oa.hnsi_oa.beans.FlowNameResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.LoginEntity;
import com.hnsi.oa.hnsi_oa.beans.LogoutEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsDetailEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsDetailResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsListEntity;
import com.hnsi.oa.hnsi_oa.beans.UnFinishEntity;
import com.hnsi.oa.hnsi_oa.beans.UserInfo;
import com.hnsi.oa.hnsi_oa.http.ApiService;
import com.hnsi.oa.hnsi_oa.http.NovateCookieManger;
import com.hnsi.oa.hnsi_oa.interfaces.OnLoginListener;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataAndNumListener;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.utils.SharedPrefUtils;

import java.util.List;
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

    /**--------------------SingleInstance-----------------------*/

    private static MyApplication mSingleInstance;

    public static MyApplication getInstance(){
        return mSingleInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSingleInstance= this;

        if (!BuildConfig.DEBUG){
            MyUncatchExceptionHandler myUncatchExceptionHandler= MyUncatchExceptionHandler.getInstance();
            myUncatchExceptionHandler.init(this);
        }

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
                if (BuildConfig.DEBUG)
                    Log.e("LoginEntity",response.toString());

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
                if (BuildConfig.DEBUG)
                    Log.e("LoginEntity",response.toString());

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
                if (BuildConfig.DEBUG)
                    Log.e("UnFinishEntity",response.toString());

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
                if (BuildConfig.DEBUG)
                    Log.e("UnFinishEntity",response.toString());

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
                if (BuildConfig.DEBUG)
                    Log.e("UnFinishEntity",response.toString());

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
                if (BuildConfig.DEBUG)
                    Log.e("FinishEntity",response.toString());

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
                if (BuildConfig.DEBUG)
                    Log.e("FinishEntity",response.toString());

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
    public void getApprovalHistory() {

    }

    @Override
    public void getMatterCount() {

    }

    @Override
    public void getFlowCount() {

    }

    @Override
    public void getMessageList() {

    }

    @Override
    public void getMessageDetail() {

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
                if (BuildConfig.DEBUG)
                    Log.e("NewsListEntity",response.toString());

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
                if (BuildConfig.DEBUG)
                    Log.e("NewsListEntity",response.toString());

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
                if (BuildConfig.DEBUG)
                    Log.e("NewsDetailEntity",response.toString());

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

    public void getRuleList(){

    }

    public void getRuleDetail(){

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

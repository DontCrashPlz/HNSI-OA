package com.hnsi.oa.hnsi_oa.application.login.presenter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.application.interfaces.OnLoginListener;
import com.hnsi.oa.hnsi_oa.application.login.model.ILoginModel;
import com.hnsi.oa.hnsi_oa.application.login.model.ILoginModelImpl;
import com.hnsi.oa.hnsi_oa.application.login.view.ILoginView;
import com.hnsi.oa.hnsi_oa.application.login.widget.LoginActivity;
import library.utils.SharedPrefUtils;

/**
 * Created by Zheng on 2017/10/23.
 */

@Deprecated
public class LoginPresenter {
    private ILoginView mView;
    private ILoginModel mModel;

    public LoginPresenter(ILoginView view){
        mView= view;
        mModel= new ILoginModelImpl();
    }

    /**
     * try to do Login
     */
    @SuppressLint("WrongConstant")
    public void loginApp(){

        if (mView.getUserName()== null){
            Toast.makeText((Context) mView,"请正确输入用户名！",Toast.LENGTH_SHORT).show();
            return;
        }

        if (mView.getPassword()== null){
            Toast.makeText((Context) mView,"请正确输入密码！",Toast.LENGTH_SHORT).show();
            return;
        }

//        mView.intoHome();

        mView.showProgressDialog();
        mModel.doLogin(mView.getUserName(), mView.getPassword(), new OnLoginListener() {

            @Override
            public void onSuccessed() {

                SharedPrefUtils.put((Activity)mView, LoginActivity.USERNAME, mView.getUserName());

                if (mView.getRememberState()){
                    SharedPrefUtils.put((Activity)mView, LoginActivity.IS_REMEMBER_USER, true);
                    SharedPrefUtils.put((Activity)mView, LoginActivity.PASSWORD, mView.getPassword());
                }else {
                    SharedPrefUtils.put((Activity)mView, LoginActivity.IS_REMEMBER_USER, false);
                    SharedPrefUtils.put((Activity)mView, LoginActivity.PASSWORD, "");
                }

                if (mView.getAutoState()){
                    SharedPrefUtils.put((Activity)mView, LoginActivity.IS_AUTO_LOGIN, true);
                }else {
                    SharedPrefUtils.put((Activity)mView, LoginActivity.IS_AUTO_LOGIN, false);
                }

                mView.dismissProgressDialog();
                mView.intoHome();
            }

            @Override
            public void onFailed(String throwable) {
                mView.dismissProgressDialog();
                Toast.makeText((Context) mView,throwable,Toast.LENGTH_SHORT).show();
            }

        });
    }

}

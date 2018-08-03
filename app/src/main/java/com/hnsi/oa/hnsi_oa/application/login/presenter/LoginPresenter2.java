package com.hnsi.oa.hnsi_oa.application.login.presenter;

import com.hnsi.oa.hnsi_oa.application.interfaces.OnLoginListener;
import com.hnsi.oa.hnsi_oa.application.login.model.ILoginModel;
import com.hnsi.oa.hnsi_oa.application.login.model.ILoginModelImpl;
import com.hnsi.oa.hnsi_oa.application.login.widget.LoginActivity;

import library.apps.BasePresenter;
import library.utils.SharedPrefUtils;

/**
 * Created by Zheng on 2017/10/23.
 */

public class LoginPresenter2 extends BasePresenter<LoginActivity> {

    private ILoginModel mModel;

    public LoginPresenter2(){
        mModel= new ILoginModelImpl();
    }

    /**
     * try to do Login
     */
    public void loginApp(){

        if (getView().getUserName()== null){
            getView().showToast("请正确输入用户名！");
            return;
        }

        if (getView().getPassword()== null){
            getView().showToast("请正确输入密码！");
            return;
        }

        getView().showProgressDialog();
        mModel.doLogin(getView().getUserName(), getView().getPassword(), new OnLoginListener() {

            @Override
            public void onSuccessed() {

                SharedPrefUtils.put(getView().getRealContext(), LoginActivity.USERNAME, getView().getUserName());

                if (getView().getRememberState()){
                    SharedPrefUtils.put(getView().getRealContext(), LoginActivity.IS_REMEMBER_USER, true);
                    SharedPrefUtils.put(getView().getRealContext(), LoginActivity.PASSWORD, getView().getPassword());
                }else {
                    SharedPrefUtils.put(getView().getRealContext(), LoginActivity.IS_REMEMBER_USER, false);
                    SharedPrefUtils.put(getView().getRealContext(), LoginActivity.PASSWORD, "");
                }

                if (getView().getAutoState()){
                    SharedPrefUtils.put(getView().getRealContext(), LoginActivity.IS_AUTO_LOGIN, true);
                }else {
                    SharedPrefUtils.put(getView().getRealContext(), LoginActivity.IS_AUTO_LOGIN, false);
                }

                getView().dismissProgressDialog();
                getView().intoHome();
            }

            @Override
            public void onFailed(String throwable) {
                getView().dismissProgressDialog();
                getView().showToast(throwable);
            }

        });
    }

}

package com.hnsi.oa.hnsi_oa.login.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.interfaces.OnLoginListener;
import com.hnsi.oa.hnsi_oa.login.model.ILoginModel;
import com.hnsi.oa.hnsi_oa.login.model.ILoginModelImpl;
import com.hnsi.oa.hnsi_oa.login.view.ILoginView;

/**
 * Created by Zheng on 2017/10/23.
 */

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

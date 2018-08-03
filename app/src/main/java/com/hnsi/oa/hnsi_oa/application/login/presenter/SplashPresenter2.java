package com.hnsi.oa.hnsi_oa.application.login.presenter;

import android.os.Handler;
import android.os.Message;

import com.hnsi.oa.hnsi_oa.application.login.model.ISplashModel;
import com.hnsi.oa.hnsi_oa.application.login.model.ISplashModelImpl;
import com.hnsi.oa.hnsi_oa.application.login.widget.SplashActivity;

import library.apps.BasePresenter;

/**
 * Created by Zheng on 2017/10/17.
 */

public class SplashPresenter2 extends BasePresenter<SplashActivity> {
    private ISplashModel mModel;

    public static final int NEED_UPDATE= 1;
    public static final int UNNEED_UPDATE= 2;
    public static final int UPDATE_EXCEPTION= 0;

    public SplashPresenter2(){
        mModel= new ISplashModelImpl();
    }

    /**
     * check update and enter LoginActivity
     */
    public void enterApp(){
        Handler mHandler= new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case NEED_UPDATE:
                        getView().skipToLogin(NEED_UPDATE);
                        break;
                    case UNNEED_UPDATE:
                        getView().skipToLogin(UNNEED_UPDATE);
                        break;
                    case UPDATE_EXCEPTION:
                        getView().skipToLogin(UPDATE_EXCEPTION);
                        break;
                    default:
                        getView().skipToLogin(UPDATE_EXCEPTION);
                        break;
                }
            }
        };

        mModel.checkUpdate(getView().getRealContext(), mHandler);
    }
}

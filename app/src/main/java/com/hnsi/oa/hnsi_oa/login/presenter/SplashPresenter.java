package com.hnsi.oa.hnsi_oa.login.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.hnsi.oa.hnsi_oa.login.model.ISplashModel;
import com.hnsi.oa.hnsi_oa.login.model.ISplashModelImpl;
import com.hnsi.oa.hnsi_oa.login.view.ISplashView;

/**
 * Created by Zheng on 2017/10/17.
 */

public class SplashPresenter {
    private ISplashView mView;
    private ISplashModel mModel;

    public static final int NEED_UPDATE= 1;
    public static final int UNNEED_UPDATE= 2;
    public static final int UPDATE_EXCEPTION= 0;

    public SplashPresenter(ISplashView view){
        mView= view;
        mModel= new ISplashModelImpl();
    }

    /**
     * check update and enter LoginActivity
     */
    public void enterApp(){
        @SuppressLint("HandlerLeak")
        Handler mHandler= new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case NEED_UPDATE:
                        mView.skipToLogin(NEED_UPDATE);
                        break;
                    case UNNEED_UPDATE:
                        mView.skipToLogin(UNNEED_UPDATE);
                        break;
                    case UPDATE_EXCEPTION:
                        mView.skipToLogin(UPDATE_EXCEPTION);
                        break;
                    default:
                        mView.skipToLogin(UPDATE_EXCEPTION);
                        break;
                }
            }
        };

        mModel.checkUpdate((Context) mView,mHandler);
    }
}

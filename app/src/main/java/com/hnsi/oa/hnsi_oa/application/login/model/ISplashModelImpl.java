package com.hnsi.oa.hnsi_oa.application.login.model;

import android.content.Context;
import android.os.Handler;

import com.hnsi.oa.hnsi_oa.BuildConfig;
import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import com.hnsi.oa.hnsi_oa.application.beans.UpdateInfoEntity;
import com.hnsi.oa.hnsi_oa.application.login.presenter.SplashPresenter;
import library.utils.UpdateInfoParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zheng on 2017/10/17.
 */

public class ISplashModelImpl implements ISplashModel {

    private static final String UPDATE_SERVICE= "https://app.hnsi.cn/hnti_soa/version.xml";

    @Override
    public void checkUpdate(final Context context, final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url= new URL(UPDATE_SERVICE);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(3000);
                    InputStream is= connection.getInputStream();

                    UpdateInfoEntity entity= UpdateInfoParser.getUpdataInfo(is);

                    if(entity.getVersion().equals(MyApplication.getInstance().getVersionName())){
                        handler.sendEmptyMessageDelayed(SplashPresenter.UNNEED_UPDATE,1000);
                    }else{
                        handler.sendEmptyMessageDelayed(SplashPresenter.NEED_UPDATE,1000);
                        MyApplication.getInstance().updateInfoEntity= entity;
                    }
                } catch (Exception e) {
                    if (BuildConfig.DEBUG)
                        e.printStackTrace();
                    handler.sendEmptyMessageDelayed(SplashPresenter.UPDATE_EXCEPTION,1000);
                }

            }
        }).start();
    }
}
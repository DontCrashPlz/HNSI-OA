package com.hnsi.oa.hnsi_oa.login.model;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.hnsi.oa.hnsi_oa.BuildConfig;
import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.beans.UpdateInfoEntity;
import com.hnsi.oa.hnsi_oa.login.presenter.SplashPresenter;
import com.hnsi.oa.hnsi_oa.utils.SharedPrefUtils;
import com.hnsi.oa.hnsi_oa.utils.UpdateInfoParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zheng on 2017/10/17.
 */

public class ISplashModelImpl implements ISplashModel {

    private static final String UPDATE_SERVICE= "/default/portal/appdownload/android-app/version.xml";

    @Override
    public void checkUpdate(final Context context, final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String path= "http://"
                            + SharedPrefUtils.get(context, MyApplication.IP_ADDRESS,"192.168.1.68")
                            + ":"
                            + SharedPrefUtils.get(context,MyApplication.PORT_NUMBER,"80")
                            + UPDATE_SERVICE;
                    if (BuildConfig.DEBUG)
                        Log.e("updateServiceAddress",path);

                    URL url= new URL(path);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    InputStream is= connection.getInputStream();

                    UpdateInfoEntity entity= UpdateInfoParser.getUpdataInfo(is);

                    if(entity.getVersion().equals(MyApplication.getInstance().getVersionName())){
                        handler.sendEmptyMessageDelayed(SplashPresenter.UNNEED_UPDATE,1000);
                    }else{
                        handler.sendEmptyMessageDelayed(SplashPresenter.NEED_UPDATE,1000);
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
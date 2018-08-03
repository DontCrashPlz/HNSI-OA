package com.hnsi.oa.hnsi_oa.application.login.widget;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import library.apps.BaseActivity;
import com.hnsi.oa.hnsi_oa.application.login.presenter.SplashPresenter;
import com.hnsi.oa.hnsi_oa.application.login.presenter.SplashPresenter2;
import com.hnsi.oa.hnsi_oa.application.login.view.ISplashView;

/**
 * Created by Zheng on 2017/10/16.
 */

public class SplashActivity extends BaseActivity implements ISplashView {

    public static final String UPDATE_CODE= "update_code";

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE= 1;

    private SplashPresenter2 mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.
//                FLAG_FULLSCREEN);
//        getWindow().setBackgroundDrawableResource(R.mipmap.splash);

        mPresenter= new SplashPresenter2();
        mPresenter.attachView(this);

        //是否有写入文件的权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            //如果没有权限，提示申请原因并申请权限
            Toast.makeText(this, "附件下载等功能需要写入文件权限", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }else {
            //如果有权限，进入APP
            mPresenter.enterApp();
        }

    }

    @Override
    public void skipToLogin(int updateCode) {
        Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
        intent.putExtra(UPDATE_CODE, updateCode);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode== MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE){
            if (grantResults[0]== PackageManager.PERMISSION_GRANTED){
                //如果获得权限，进入APP
                mPresenter.enterApp();
            }else {
                //如果没有获得权限，Toast提示，而后进入APP
                Toast.makeText(this, "授权失败，附件下载等功能可能受到影响！", Toast.LENGTH_LONG).show();
                mPresenter.enterApp();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!= null){
            mPresenter.detachView();
        }
    }
}

package com.hnsi.oa.hnsi_oa.login.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.widgets.BaseActivity;
import com.hnsi.oa.hnsi_oa.login.presenter.SplashPresenter;
import com.hnsi.oa.hnsi_oa.login.view.ISplashView;

/**
 * Created by Zheng on 2017/10/16.
 */

public class SplashActivity extends BaseActivity implements ISplashView {

    public static final String UPDATE_CODE= "update_code";

    private SplashPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.
                FLAG_FULLSCREEN);
        getWindow().setBackgroundDrawableResource(R.mipmap.splash);
        mPresenter= new SplashPresenter(this);
        mPresenter.enterApp();
    }

    @Override
    public void skipToLogin(int updateCode) {
        Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
        intent.putExtra(UPDATE_CODE, updateCode);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }
}

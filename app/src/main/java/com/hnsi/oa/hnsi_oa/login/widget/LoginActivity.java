package com.hnsi.oa.hnsi_oa.login.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.hnsi.oa.hnsi_oa.BuildConfig;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.utils.SharedPrefUtils;
import com.hnsi.oa.hnsi_oa.widgets.BaseActivity;
import com.hnsi.oa.hnsi_oa.login.presenter.LoginPresenter;
import com.hnsi.oa.hnsi_oa.login.view.ILoginView;
import com.hnsi.oa.hnsi_oa.main.widget.HomeActivity;

/**
 * Created by Zheng on 2017/10/18.
 */

public class LoginActivity extends BaseActivity implements ILoginView,View.OnClickListener {

    public static final String IS_REMEMBER_USER= "IS_REMEMBER_USER";
    public static final String IS_AUTO_LOGIN= "IS_AUTO_LOGIN";

    private Button mLoginBtn;
    private ImageButton mSettingBtn;

    private EditText mUserNameEt;
    private EditText mPasswordEt;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.
                FLAG_FULLSCREEN);
        Intent intent= getIntent();

        if (BuildConfig.DEBUG)
            Log.e("UPDATE_CODE",""+intent.getIntExtra(SplashActivity.UPDATE_CODE,0));

        findViews();

        mPresenter= new LoginPresenter(this);
    }

    private void findViews() {
        mLoginBtn= (Button) findViewById(R.id.login_btn_login);
        mLoginBtn.setOnClickListener(this);
        mSettingBtn= (ImageButton) findViewById(R.id.login_ibtn_setting);
        mSettingBtn.setOnClickListener(this);

        mUserNameEt= (EditText) findViewById(R.id.login_et_username);
        mPasswordEt= (EditText) findViewById(R.id.login_et_password);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public boolean getRememberState() {
        return (boolean) SharedPrefUtils.get(this,IS_REMEMBER_USER,false);
    }

    @Override
    public boolean getAutoState() {
        return (boolean) SharedPrefUtils.get(this,IS_AUTO_LOGIN,false);
    }

    @Override
    public String getUserName() {
        String mUserName= mUserNameEt.getText().toString().trim();
        if (mUserName== null
                || "".equals(mUserName)
                || "null".equals(mUserName))
            return null;
        return mUserName;
    }

    @Override
    public String getPassword() {
        String mPassword= mPasswordEt.getText().toString().trim();
        if (mPassword== null
                || "".equals(mPassword)
                || "null".equals(mPassword))
            return null;
        return mPassword;
    }

    @Override
    public void setUserName() {

    }

    @Override
    public void setPassword() {

    }

    @Override
    public void hideSoftKeyboard() {

    }

    @Override
    public void intoHome() {
        Intent intent= new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        int vId= v.getId();
        if (vId== R.id.login_btn_login ){
            mPresenter.loginApp();
        }else if(vId== R.id.login_ibtn_setting){
            Intent intent= new Intent(this,SettingActivity.class);
            startActivity(intent);
        }
    }
}

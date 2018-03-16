package com.hnsi.oa.hnsi_oa.application.login.widget;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.BuildConfig;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import com.hnsi.oa.hnsi_oa.application.beans.UpdateInfoEntity;
import com.hnsi.oa.hnsi_oa.application.login.presenter.SplashPresenter;
import library.utils.DownloadManager;
import library.utils.SharedPrefUtils;
import library.apps.BaseActivity;
import com.hnsi.oa.hnsi_oa.application.login.presenter.LoginPresenter;
import com.hnsi.oa.hnsi_oa.application.login.view.ILoginView;
import com.hnsi.oa.hnsi_oa.application.main.widget.HomeActivity;

import java.io.File;

/**
 * Created by Zheng on 2017/10/18.
 */

public class LoginActivity extends BaseActivity implements ILoginView,View.OnClickListener {

    public static final String IS_REMEMBER_USER= "IS_REMEMBER_USER";
    public static final String IS_AUTO_LOGIN= "IS_AUTO_LOGIN";

    public static final String USERNAME= "USERNAME";
    public static final String PASSWORD= "PASSWORD";

    private Button mLoginBtn;
    private ImageButton mSettingBtn;

    private EditText mUserNameEt;
    private EditText mPasswordEt;

    private CheckBox mRememberCb;
    private CheckBox mAutoLoginCb;

    private LoginPresenter mPresenter;

    private boolean isRemember;
    private boolean isAutoLogin;

    private ProgressDialog progressDialog;

    private int mUpdateCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.
                FLAG_FULLSCREEN);
        Intent intent= getIntent();

        mUpdateCode= intent.getIntExtra(SplashActivity.UPDATE_CODE,0);
        if (BuildConfig.DEBUG)
            Log.e("UPDATE_CODE",""+intent.getIntExtra(SplashActivity.UPDATE_CODE,0));
        switch (mUpdateCode){
            case SplashPresenter.NEED_UPDATE:
                if (MyApplication.getInstance().updateInfoEntity!= null){
                    showUpdateDialog(MyApplication.getInstance().updateInfoEntity);
                }
                break;
            case SplashPresenter.UNNEED_UPDATE:
                break;
            case SplashPresenter.UPDATE_EXCEPTION:
                break;
            default:
                break;
        }

        isRemember= (boolean) SharedPrefUtils.get(this, IS_REMEMBER_USER, false);
        isAutoLogin= (boolean) SharedPrefUtils.get(this, IS_AUTO_LOGIN, false);

        findViews();

        mPresenter= new LoginPresenter(this);

        if (isAutoLogin) mPresenter.loginApp();

    }

    private void findViews() {
        mLoginBtn= (Button) findViewById(R.id.login_btn_login);
        mLoginBtn.setOnClickListener(this);
        mSettingBtn= (ImageButton) findViewById(R.id.login_ibtn_setting);
        mSettingBtn.setOnClickListener(this);

        mUserNameEt= (EditText) findViewById(R.id.login_et_username);
        mUserNameEt.setText((String) SharedPrefUtils.get(this, USERNAME, ""));

        mPasswordEt= (EditText) findViewById(R.id.login_et_password);
        if (isRemember) mPasswordEt.setText((String) SharedPrefUtils.get(this, PASSWORD, ""));

        mRememberCb= (CheckBox) findViewById(R.id.login_cb_remeber_password);
        if (isRemember) mRememberCb.setChecked(true);
        mAutoLoginCb= (CheckBox) findViewById(R.id.login_cb_automatic);
        if (isAutoLogin) mAutoLoginCb.setChecked(true);

    }

    @Override
    public void showProgressDialog() {
        if (progressDialog== null){
            progressDialog= new ProgressDialog(this);
        }
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("正在登录...");
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog== null) return;
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean getRememberState() {
        return mRememberCb.isChecked();
    }

    @Override
    public boolean getAutoState() {
        return mAutoLoginCb.isChecked();
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
        String userName= (String) SharedPrefUtils.get(this, USERNAME, "");
        mUserNameEt.setText(userName);
    }

    @Override
    public void setPassword() {
        String passWord= (String) SharedPrefUtils.get(this, PASSWORD, "");
        mPasswordEt.setText(passWord);
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

    public void showUpdateDialog(final UpdateInfoEntity info){
        View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.layout_update_dialog, null);
        // 设置style 控制默认dialog带来的边距问题
        final Dialog dialog = new Dialog(LoginActivity.this, R.style.simple_dialog);
        dialog.setContentView(view);
        dialog.show();

        ((TextView)(view.findViewById(R.id.update_info_tv))).setText(info.getDescription());

        // 取消按钮监听
        view.findViewById(R.id.layout_exit_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // 确定按钮监听
        view.findViewById(R.id.layout_exit_dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                downLoadApk(info);
            }
        });

        // 设置相关位置，一定要在 show()之后
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    protected void downLoadApk(final UpdateInfoEntity info) {
        //进度条对话框
        final android.app.ProgressDialog progressDialog = new  android.app.ProgressDialog(this);
        progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("正在下载更新");
        progressDialog.show();
        new Thread(){
            @Override
            public void run() {
                try {
                    File file = DownloadManager.getFileFromServer(info.getUrl(), progressDialog);
                    //sleep(3000);
                    installApk(file);
                    progressDialog.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    progressDialog.dismiss(); //结束掉进度条对话框
                    Looper.prepare();
                    Toast.makeText(LoginActivity.this,"安装包下载失败",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e.printStackTrace();
                }
            }}.start();
    }

    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

}

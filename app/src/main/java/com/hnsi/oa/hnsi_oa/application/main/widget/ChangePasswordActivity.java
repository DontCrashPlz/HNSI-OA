package com.hnsi.oa.hnsi_oa.application.main.widget;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import com.hnsi.oa.hnsi_oa.application.beans.ChangePasswordEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;
import library.apps.BaseActivity;

/**
 * Created by Zheng on 2017/12/27.
 */

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {
    //标题头返回按钮
    ImageButton mBackBtn;
    //页面标题
    TextView mTitleTv;
    //原密码输入框
    EditText mOldPasswordEt;
    //新密码输入框
    EditText mNewPasswordEt;
    //再一次新密码输入框
    EditText mNewAgainPasswordEt;
    //完成按钮
    Button mCompleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle("修改密码");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        intiUI();
    }

    /**
     * 初始化界面
     */
    private void intiUI() {

        //加载原密码输入框
        mOldPasswordEt= (EditText) findViewById(R.id.change_password_et_old);

        //加载新密码输入框
        mNewPasswordEt= (EditText) findViewById(R.id.change_password_et_new);

        //加载新密码再一次输入框
        mNewAgainPasswordEt= (EditText) findViewById(R.id.change_password_et_new_again);

        //加载并为完成按钮设置点击事件
        mCompleteBtn= (Button) findViewById(R.id.change_password_btn_complete);
        mCompleteBtn.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

    /**
     * 设置本页面的点击事件
     * @param v 被点击的控件
     */
    @Override
    public void onClick(View v) {
        //以控件ID作为标识
        int vId=v.getId();

        switch (vId){
            case R.id.change_password_btn_complete:{//控件点击事件（完成按钮）
                String oldPassword=mOldPasswordEt.getText().toString().trim();
                String newPassword=mNewPasswordEt.getText().toString().trim();
                String newAgainPassword=mNewAgainPasswordEt.getText().toString().trim();

                if (oldPassword.length()<1 || newPassword.length()<1 || newAgainPassword.length()<1){
                    Toast.makeText(ChangePasswordActivity.this,"请确认填写有效！",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!(newPassword.equals(newAgainPassword))){
                    Toast.makeText(ChangePasswordActivity.this,"请确认两次输入的新密码一致！",Toast.LENGTH_SHORT).show();
                    return;
                }

                if( newPassword.equals(oldPassword) ){
                    Toast.makeText(ChangePasswordActivity.this,"新密码不能和旧密码相同！",Toast.LENGTH_SHORT).show();
                    return;
                }

                MyApplication.getInstance().changePassword(newPassword, oldPassword, new OnRequestDataListener<ChangePasswordEntity>() {
                    @Override
                    public void onSuccessed(ChangePasswordEntity changePasswordEntity) {
                        Toast.makeText(ChangePasswordActivity.this, changePasswordEntity.getMsg() + "，下次登录请使用新密码", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailed(String throwable) {
                        Toast.makeText(ChangePasswordActivity.this, throwable, Toast.LENGTH_SHORT).show();
                    }
                });

            }break;
        }
    }

}

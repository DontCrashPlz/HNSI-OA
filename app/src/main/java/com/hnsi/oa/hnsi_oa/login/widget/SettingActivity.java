package com.hnsi.oa.hnsi_oa.login.widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.utils.SharedPrefUtils;
import com.hnsi.oa.hnsi_oa.app.BaseActivity;
import com.hnsi.oa.hnsi_oa.login.view.ISettingView;

/**
 * Created by Zheng on 2017/10/23.
 */

public class SettingActivity extends BaseActivity implements ISettingView {

    private EditText mIpAddressEt;
    private EditText mPortNumberEt;
    private Button mCompleteBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_setting);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("设置");

        findViews();

        setIpAddress((String) SharedPrefUtils.get(
                this,
                MyApplication.IP_ADDRESS,
                "192.168.1.68"));

        setPortNumber((String) SharedPrefUtils.get(
                this,
                MyApplication.PORT_NUMBER,
                "80"));
    }

    private void findViews() {
        mIpAddressEt= (EditText) findViewById(R.id.server_et_ip_address);
        mPortNumberEt= (EditText) findViewById(R.id.server_et_ip_port);
        mCompleteBtn= (Button) findViewById(R.id.server_btn_complete);
        mCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (getIpAddress()== null){
                    Toast.makeText(
                            SettingActivity.this,
                            "请正确输入IP地址！",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (getPortNumber()== null){
                    Toast.makeText(
                            SettingActivity.this,
                            "请正确输入端口号！",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPrefUtils.put(
                        SettingActivity.this,
                        MyApplication.IP_ADDRESS,
                        getIpAddress());
                SharedPrefUtils.put(
                        SettingActivity.this,
                        MyApplication.PORT_NUMBER,
                        getPortNumber());
                Toast.makeText(
                        SettingActivity.this,
                        "设置完成！",
                        Toast.LENGTH_SHORT).show();
                MyApplication.getInstance().initHttpInstance();
                SettingActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home== item.getItemId()){
            onBackPressed();
        }
        return false;
    }

    @Override
    public void setIpAddress(String ipAddress) {
        mIpAddressEt.setText(ipAddress);
    }

    @Override
    public void setPortNumber(String portNumber) {
        mPortNumberEt.setText(portNumber);
    }

    @Override
    public String getIpAddress() {
        String ipAddress= mIpAddressEt.getText().toString().trim();
        if (ipAddress== null
                || "".equals(ipAddress)
                || "null".equals(ipAddress))
            return null;
        return ipAddress;
    }

    @Override
    public String getPortNumber() {
        String portNumber= mPortNumberEt.getText().toString().trim();
        if (portNumber== null
                || "".equals(portNumber)
                || "null".equals(portNumber))
            return null;
        return portNumber;
    }
}

package com.hnsi.oa.hnsi_oa.main.widget;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.PersonEntity;
import com.hnsi.oa.hnsi_oa.database.ConstactsInfoTableHelper;
import com.hnsi.oa.hnsi_oa.app.BaseActivity;
import com.hnsi.oa.hnsi_oa.widgets.CircleImageView;
import com.hnsi.oa.hnsi_oa.widgets.MyContactsAdapter;

/**
 * Created by Zheng on 2018/2/2.
 */

public class PersonDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    //用户头像
    private CircleImageView mUserIconCiv;
    //用户名
    private TextView mUserNameTv;
    //用户性别
    private TextView mUserSexTv;
    //用户电话
    private TextView mPhoneTv;
    //用户部门
    private TextView mDepartmentTv;
    //用户职务
    private TextView mPositionTv;
    //用户固定电话
    private TextView mLinePhoneTv;
    //用户电子邮箱
    private TextView mEmailTv;
    //发送信息按钮
    private Button mSendMsgBtn;

    private ConstactsInfoTableHelper mHelper;
    private PersonEntity mEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        int mEmpId= getIntent().getIntExtra(MyContactsAdapter.PERSON_ID_TAG, 0);
        if (mEmpId == 0) {
            Toast.makeText(this, "联系人数据无效，请刷新通讯录", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        String mEmpName = getIntent().getStringExtra(MyContactsAdapter.PERSON_NAME_TAG);
        if ("".equals(mEmpName)) {
            Toast.makeText(this, "联系人数据无效，请刷新通讯录", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle(mEmpName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mHelper = new ConstactsInfoTableHelper(this);
        mEntity = mHelper.searchByEmpid(mEmpId);
        if (mEntity == null) {
            Toast.makeText(this, "联系人数据无效，请刷新通讯录", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        initUI();
    }

    /**
     * 初始化界面
     */
    private void initUI() {

        mUserIconCiv = (CircleImageView) findViewById(R.id.personinfo_civ_user_icon);
        //mUserIconCiv.setImageBitmap();
        String headimgUrl = "http://192.168.1.68:80/"+mEntity.getHeadimg();
        if (headimgUrl!= null && !"http://192.168.1.68:80/".equals(headimgUrl) && !"null".equals(headimgUrl)) {
            Glide.with(PersonDetailActivity.this)
                    .load(headimgUrl)
                    .asBitmap()
                    .placeholder(R.mipmap.user_icon)
                    .error(R.mipmap.user_icon)
                    .into(mUserIconCiv);
        }

        mUserNameTv = (TextView) findViewById(R.id.personinfo_tv_username);
        if (mEntity.getEmpname()!= null && !"".equals(mEntity.getEmpname()) && !"null".equals(mEntity.getEmpname())) {
            mUserNameTv.setText(mEntity.getEmpname());
        } else {
            mUserNameTv.setText("未知");
        }

        mUserSexTv = (TextView) findViewById(R.id.personinfo_tv_sex);
        if (mEntity.getSex()!= null && !"".equals(mEntity.getSex()) && !"null".equals(mEntity.getSex())) {
            mUserSexTv.setText(mEntity.getSex());
        } else {
            mUserSexTv.setText("未知");
        }

        mPhoneTv = (TextView) findViewById(R.id.personinfo_tv_phone);
        if (mEntity.getMobileno()!= null && !"".equals(mEntity.getMobileno()) && !"null".equals(mEntity.getMobileno())) {
            mPhoneTv.setText(mEntity.getMobileno());
            mPhoneTv.setOnClickListener(this);
        } else {
            mPhoneTv.setText("未知");
        }

        mDepartmentTv = (TextView) findViewById(R.id.personinfo_tv_department);
        if (mEntity.getOrgname()!= null && !"".equals(mEntity.getOrgname()) && !"null".equals(mEntity.getOrgname())) {
            mDepartmentTv.setText(mEntity.getOrgname());
        } else {
            mDepartmentTv.setText("未知");
        }

        mPositionTv = (TextView) findViewById(R.id.personinfo_tv_position);
        if (mEntity.getPosiname()!= null && !"".equals(mEntity.getPosiname()) && !"null".equals(mEntity.getPosiname())) {
            mPositionTv.setText(mEntity.getPosiname());
        } else {
            mPositionTv.setText("未知");
        }

        mLinePhoneTv = (TextView) findViewById(R.id.personinfo_tv_linephone);
        if (mEntity.getOtel()!= null && !"".equals(mEntity.getOtel()) && !"null".equals(mEntity.getOtel())) {
            mLinePhoneTv.setText(mEntity.getOtel());
            mLinePhoneTv.setOnClickListener(this);
        } else {
            mLinePhoneTv.setText("未知");
        }

        mEmailTv = (TextView) findViewById(R.id.personinfo_tv_email);
        if (mEntity.getOemail()!= null && !"".equals(mEntity.getOemail()) && !"null".equals(mEntity.getOemail())) {
            mEmailTv.setText(mEntity.getOemail());
            mEmailTv.setOnClickListener(this);
        } else {
            mEmailTv.setText("未知");
        }

        mSendMsgBtn = (Button) findViewById(R.id.personinfo_btn_sendmsg);
        mSendMsgBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        Intent intent;
        switch (vId) {
            case R.id.personinfo_tv_phone: {//手机号码的点击事件
                String phoneNum= (String) ((TextView) v).getText();
                tryCall(phoneNum);
//                intent = new Intent(Intent.ACTION_CALL);
//                intent.setData(Uri.parse("tel:" + ((TextView) v).getText()));
//                startActivity(intent);
            }
            break;
            case R.id.personinfo_tv_linephone: {//固定电话的点击事件
                String phoneNum= (String) ((TextView) v).getText();
                tryCall(phoneNum);
            }
            break;
            case R.id.personinfo_tv_email: {//邮箱地址的点击事件
                Uri uri = Uri.parse("mailto:" + ((TextView) v).getText());
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(Intent.createChooser(intent, "请选择邮件类应用"));
            }
            break;
            case R.id.personinfo_btn_sendmsg: {//发送信息的点击事件
                if (!mPhoneTv.getText().equals("未知")) {
                    intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:" + mPhoneTv.getText()));
                    startActivity(intent);
                } else {
//                    Toast.makeText(PersonInfoActivity.this,"此人没有手机号码信息",Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }


    public void tryCall(String phoneNum) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callPhone(phoneNum);
        }
    }

    /**
     * 拨打电话
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(PersonDetailActivity.this, "授权成功！", Toast.LENGTH_SHORT).show();
            } else {
                // Permission Denied
                Toast.makeText(PersonDetailActivity.this, "授权失败！", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

}

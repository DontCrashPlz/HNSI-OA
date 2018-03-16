package com.hnsi.oa.hnsi_oa.application.news.widget;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import com.hnsi.oa.hnsi_oa.application.beans.MessageDetailEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;
import library.apps.BaseActivity;
import com.hnsi.oa.hnsi_oa.application.adapters.MyMessageListAdapter;

/**
 * 消息详情展示页
 * Created by Zheng on 2016/5/10.
 */
public class MessageDetailActivity extends BaseActivity{

    //消息标题
    private TextView mMsgTitleTv;
    //消息类型
    private TextView mMsgTypeTv;
    //消息发送人
    private TextView mSendNameTv;
    //消息发送部门
    private TextView mDepartmentTv;
    //消息发送时间
    private TextView mSendTimeTv;
    //消息接收人
    private TextView mReceiveNameTv;
    //消息内容
    private TextView mContentTv;

    private int messageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle("系统消息详情");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initUI();
        messageId= getIntent().getIntExtra(MyMessageListAdapter.MESSAGE_ID, 0);
        if (messageId== 0){
            Toast.makeText(this, "获取系统信息失败，请重试！", Toast.LENGTH_LONG).show();
            finish();
        }

        MyApplication.getInstance().getMessageDetail(messageId, new OnRequestDataListener<MessageDetailEntity>() {
            @Override
            public void onSuccessed(MessageDetailEntity messageDetailEntity) {
                MessageDetailEntity.DataBean bean= messageDetailEntity.getData();

                mMsgTitleTv.setText(bean.getTitle());

                int msgType= bean.getMsgType();
                if(msgType==1){
                    mMsgTypeTv.setText("普通消息");
                }else if(msgType==2){
                    mMsgTypeTv.setText("项目反馈");
                }else if(msgType==3){
                    mMsgTypeTv.setText("流程反馈");
                }

                mSendNameTv.setText(bean.getSendEmpname());

                mDepartmentTv.setText(bean.getSendDeptname());

                String startDate=bean.getSendTime().substring(0,19);
                mSendTimeTv.setText(startDate);

                mReceiveNameTv.setText(bean.getReceiveEmpname());

                mContentTv.setText(bean.getContent());
            }

            @Override
            public void onFailed(String throwable) {
                Toast.makeText(MessageDetailActivity.this, throwable, Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 初始化界面
     */
    private void initUI() {
        mMsgTitleTv= (TextView) findViewById(R.id.msg_detail_tv_title);
        mMsgTypeTv= (TextView) findViewById(R.id.msg_detail_tv_msgtype);
        mSendNameTv= (TextView) findViewById(R.id.msg_detail_tv_sendname);
        mDepartmentTv= (TextView) findViewById(R.id.msg_detail_tv_department);
        mSendTimeTv= (TextView) findViewById(R.id.msg_detail_tv_sendtime);
        mReceiveNameTv= (TextView) findViewById(R.id.msg_detail_tv_receivename);
        mContentTv= (TextView) findViewById(R.id.msg_detail_tv_content);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

}

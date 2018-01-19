package com.hnsi.oa.hnsi_oa.approval.widget;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.beans.ApprovalEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalGroupEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalWidgetEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.utils.DensityUtil;
import com.hnsi.oa.hnsi_oa.widgets.BaseActivity;
import com.hnsi.oa.hnsi_oa.widgets.MyNewsItemDecoration;
import com.hnsi.oa.hnsi_oa.widgets.MyPenddingFlowAdapter;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/19.
 */

public class ApprovalDetailActivity extends BaseActivity {

    private LinearLayout mFormAreaLly;
    private Button mCommitBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_detail);

        Intent intent= getIntent();
        String url= intent.getStringExtra(MyPenddingFlowAdapter.DETAIL_PARAM_URL);
        String workItemId= String.valueOf(intent.getIntExtra(MyPenddingFlowAdapter.DETAIL_PARAM_WORKITEMID, 0));
        String activityDefId= intent.getStringExtra(MyPenddingFlowAdapter.DETAIL_PARAM_ACTIVITYDEFID);
        String processInstId= String.valueOf(intent.getIntExtra(MyPenddingFlowAdapter.DETAIL_PARAM_PROCESSINSTID, 0));

        Log.e("",url+"--"+workItemId+"--"+activityDefId+"--"+processInstId);

        if (url== null || "".equals(url) || "null".equals(url)
                || workItemId== null || "".equals(workItemId) || "null".equals(workItemId) || "0".equals(workItemId)
                || activityDefId== null || "".equals(activityDefId) || "null".equals(activityDefId)
                || processInstId==null || "".equals(processInstId) || "null".equals(processInstId) || "0".equals(processInstId)){
            Toast.makeText(this, "流程异常，请重试！", Toast.LENGTH_LONG).show();
            finish();
        }

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("审批");
        }

        mFormAreaLly= (LinearLayout) findViewById(R.id.form_area);
        mCommitBtn= (Button) findViewById(R.id.matter_btn_commit);

        MyApplication.getInstance().getApprovalDetail(url, workItemId, activityDefId, processInstId,
                new OnRequestDataListener<ApprovalEntity>() {
            @Override
            public void onSuccessed(ApprovalEntity approvalEntity) {
                ArrayList<ApprovalGroupEntity> groups= approvalEntity.getGroupList();
                for (ApprovalGroupEntity entity : groups){
                    ArrayList<ApprovalWidgetEntity> widgets= new ArrayList<>();
                    String groupKey= entity.getGroupKey();
                    for (ApprovalWidgetEntity widget : approvalEntity.getCtlList()){
                        if (widget.getGroupKey().equals(groupKey))
                            widgets.add(widget);
                    }
                    entity.setWidgets(widgets);

                    if (entity.getWidgets().size()> 0){
                        LinearLayout.LayoutParams params1=
                                new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        DensityUtil.dp2px(ApprovalDetailActivity.this, 50));

                        TextView textView= new TextView(ApprovalDetailActivity.this);
                        textView.setLayoutParams(params1);
                        textView.setText(entity.getLabel());
                        textView.setTextColor(Color.BLACK);
                        textView.setTextSize(18);
                        textView.setGravity(Gravity.CENTER);
                        mFormAreaLly.addView(textView);

                        LinearLayout.LayoutParams params2=
                                new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT);

                        RecyclerView recyclerView= new RecyclerView(ApprovalDetailActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ApprovalDetailActivity.this));
                        recyclerView.addItemDecoration(new MyNewsItemDecoration(15));
                        recyclerView.setAdapter();
                    }

                }

            }

            @Override
            public void onFailed(String throwable) {

            }
        });

    }
}

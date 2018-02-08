package com.hnsi.oa.hnsi_oa.approval.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.beans.ApprovalEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalGroupEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalHistoryResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalWidgetEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.widgets.BaseActivity;
import com.hnsi.oa.hnsi_oa.widgets.MyApprovalDetailAdapter2;
import com.hnsi.oa.hnsi_oa.widgets.MyNewsItemDecoration;
import com.hnsi.oa.hnsi_oa.widgets.MyPenddingFlowAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Zheng on 2018/1/19.
 */

public class ApprovalDetailActivity2 extends BaseActivity {

    private RecyclerView mFormRly;
    private Button mCommitBtn;

    private HashMap<String, String> mCommitParamMap;

    private String mCommitUrl= "";

    private static final String PEND_ITEM_TAG= "pend";

    private String url= "";
    private String workItemId= "";
    private String activityDefId= "";
    private String processInstId= "";

    private int typeTag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_detail2);

        Intent intent= getIntent();
        url= intent.getStringExtra(MyPenddingFlowAdapter.DETAIL_PARAM_URL);
        workItemId= String.valueOf(intent.getIntExtra(MyPenddingFlowAdapter.DETAIL_PARAM_WORKITEMID, 0));
        activityDefId= intent.getStringExtra(MyPenddingFlowAdapter.DETAIL_PARAM_ACTIVITYDEFID);
        processInstId= String.valueOf(intent.getIntExtra(MyPenddingFlowAdapter.DETAIL_PARAM_PROCESSINSTID, 0));

        typeTag= intent.getIntExtra(MyPenddingFlowAdapter.TYPE_TAG, 0);

        if (typeTag== 0){
            Toast.makeText(this, "流程异常，请重试！", Toast.LENGTH_LONG).show();
            finish();
        }
        if (typeTag== MyPenddingFlowAdapter.TYPE_PENDDING){
            if (url== null || "".equals(url) || "null".equals(url)
                    || workItemId== null || "".equals(workItemId) || "null".equals(workItemId) || "0".equals(workItemId)
                    || activityDefId== null || "".equals(activityDefId) || "null".equals(activityDefId)
                    || processInstId==null || "".equals(processInstId) || "null".equals(processInstId) || "0".equals(processInstId)){
                Toast.makeText(this, "流程异常，请重试！", Toast.LENGTH_LONG).show();
                finish();
            }
        }else if (typeTag== MyPenddingFlowAdapter.TYPE_FINISHED){
            if (url== null || "".equals(url) || "null".equals(url)
                    || processInstId==null || "".equals(processInstId) || "null".equals(processInstId) || "0".equals(processInstId)){
                Toast.makeText(this, "流程异常，请重试！", Toast.LENGTH_LONG).show();
                finish();
            }
        }

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("审批");
        }

        mFormRly= (RecyclerView) findViewById(R.id.form_rly);
        mFormRly.setLayoutManager(new LinearLayoutManager(ApprovalDetailActivity2.this));
        mFormRly.addItemDecoration(new MyNewsItemDecoration(15));
        mFormRly.setNestedScrollingEnabled(false);

        mCommitBtn= (Button) findViewById(R.id.matter_btn_commit);

        if (typeTag== MyPenddingFlowAdapter.TYPE_FINISHED){
            handleFinished();
        }else if (typeTag== MyPenddingFlowAdapter.TYPE_PENDDING){
            handlePendding();
        }

//        if (typeTag== MyPenddingFlowAdapter.TYPE_PENDDING){
//            MyApplication.getInstance().getApprovalDetail(url, workItemId, activityDefId, processInstId, new OnRequestDataListener<ApprovalEntity>() {
//                @Override
//                public void onSuccessed(ApprovalEntity approvalEntity) {
//
//                    mCommitUrl= approvalEntity.getUrl();
//
//                    //所有控件的集合
//                    ArrayList<ApprovalWidgetEntity> allWidgets= approvalEntity.getCtlList();
//                    //所有hidden控件的集合
//                    ArrayList<ApprovalWidgetEntity> hiddenWidget= new ArrayList<>();
//
//                    for (ApprovalWidgetEntity entity : allWidgets){
//
//                        if ("hidden".equals(entity.getType())){
//                            mCommitParamMap.put(entity.getKey(), entity.getValue());
//                            hiddenWidget.add(entity);
//                            continue;
//                        }
//
//                        entity.setKey(entity.getKey().replace(".","/"));
//
//                        if (entity.isRequired()){
//                            mCommitParamMap.put(entity.getKey(), PEND_ITEM_TAG);
//                        }
//
//                    }
//                    Log.e("commitParamMap--create", mCommitParamMap.toString());
//                    //移除所有hidden类型的控件
//                    allWidgets.removeAll(hiddenWidget);
//                    Log.e("hiddenWidget", ""+hiddenWidget.size());
//                    Log.e("allWidgets", ""+allWidgets.size());
//
//                    //根据groupKey进行分组，并逐组绘制界面
//                    ArrayList<ApprovalGroupEntity> groups= approvalEntity.getGroupList();
//                    for (ApprovalGroupEntity entity : groups){
//                        ArrayList<ApprovalWidgetEntity> widgets= new ArrayList<>();
//                        String groupKey= entity.getGroupKey();
//                        for (ApprovalWidgetEntity widget : allWidgets){
//                            if (widget.getGroupKey().equals(groupKey))
//                                widgets.add(widget);
//                        }
//                        entity.setWidgets(widgets);
//
//                        if (entity.getWidgets().size()> 0){
//
//                            LinearLayout.LayoutParams params1=
//                                    new LinearLayout.LayoutParams(
//                                            ViewGroup.LayoutParams.MATCH_PARENT,
//                                            DensityUtil.dp2px(ApprovalDetailActivity2.this, 50));
//
//                            TextView textView= new TextView(ApprovalDetailActivity2.this);
//                            textView.setLayoutParams(params1);
//                            textView.setText(entity.getLabel());
//                            textView.setTextColor(Color.BLACK);
//                            textView.setTextSize(18);
//                            textView.setGravity(Gravity.CENTER);
//
//                            if (groups.indexOf(entity)> 0){
//                                mFormAreaLly.addView(createDevideView());
//                            }
//                            mFormAreaLly.addView(textView);
//                            mFormAreaLly.addView(createDevideView());
//
//                            LinearLayout.LayoutParams params2=
//                                    new LinearLayout.LayoutParams(
//                                            ViewGroup.LayoutParams.MATCH_PARENT,
//                                            ViewGroup.LayoutParams.WRAP_CONTENT);
//
//                            RecyclerView recyclerView= new RecyclerView(ApprovalDetailActivity2.this);
//                            recyclerView.setLayoutManager(new LinearLayoutManager(ApprovalDetailActivity2.this));
//                            recyclerView.setLayoutParams(params2);
//                            recyclerView.addItemDecoration(new MyNewsItemDecoration(15));
//                            recyclerView.setAdapter(new MyApprovalDetailAdapter(ApprovalDetailActivity2.this, entity.getWidgets()));
//                            mFormAreaLly.addView(recyclerView);
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailed(String throwable) {
//
//                }
//            });
//        }else if (typeTag== MyPenddingFlowAdapter.TYPE_FINISHED){
//            MyApplication.getInstance().getApprovalDetail(url, "", "", processInstId, new OnRequestDataListener<ApprovalEntity>() {
//                @Override
//                public void onSuccessed(ApprovalEntity approvalEntity) {
//                    //所有控件的集合
//                    ArrayList<ApprovalWidgetEntity> allWidgets= approvalEntity.getCtlList();
//                    //所有hidden控件的集合
//                    ArrayList<ApprovalWidgetEntity> hiddenWidget= new ArrayList<>();
//
//                    for (ApprovalWidgetEntity entity : allWidgets){
//                        if ("hidden".equals(entity.getType())){
//                            mCommitParamMap.put(entity.getKey(), entity.getValue());
//                            hiddenWidget.add(entity);
//                            continue;
//                        }
//                    }
//                    //移除所有hidden类型的控件
//                    allWidgets.removeAll(hiddenWidget);
//
//                    //根据groupKey进行分组，并逐组绘制界面
//                    ArrayList<ApprovalGroupEntity> groups= approvalEntity.getGroupList();
//                    for (ApprovalGroupEntity entity : groups){
//                        ArrayList<ApprovalWidgetEntity> widgets= new ArrayList<>();
//                        String groupKey= entity.getGroupKey();
//                        for (ApprovalWidgetEntity widget : allWidgets){
//                            if (widget.getGroupKey().equals(groupKey))
//                                widgets.add(widget);
//                        }
//                        entity.setWidgets(widgets);
//
//                        if (entity.getWidgets().size()> 0){
//
//                            LinearLayout.LayoutParams params1=
//                                    new LinearLayout.LayoutParams(
//                                            ViewGroup.LayoutParams.MATCH_PARENT,
//                                            DensityUtil.dp2px(ApprovalDetailActivity2.this, 50));
//
//                            TextView textView= new TextView(ApprovalDetailActivity2.this);
//                            textView.setLayoutParams(params1);
//                            textView.setText(entity.getLabel());
//                            textView.setTextColor(Color.BLACK);
//                            textView.setTextSize(18);
//                            textView.setGravity(Gravity.CENTER);
//
//                            if (groups.indexOf(entity)> 0){
//                                mFormAreaLly.addView(createDevideView());
//                            }
//                            mFormAreaLly.addView(textView);
//                            mFormAreaLly.addView(createDevideView());
//
//                            LinearLayout.LayoutParams params2=
//                                    new LinearLayout.LayoutParams(
//                                            ViewGroup.LayoutParams.MATCH_PARENT,
//                                            ViewGroup.LayoutParams.WRAP_CONTENT);
//
//                            RecyclerView recyclerView= new RecyclerView(ApprovalDetailActivity2.this);
//                            recyclerView.setLayoutManager(new LinearLayoutManager(ApprovalDetailActivity2.this));
//                            recyclerView.setLayoutParams(params2);
//                            recyclerView.addItemDecoration(new MyNewsItemDecoration(15));
//                            recyclerView.setAdapter(new MyApprovalDetailAdapter(ApprovalDetailActivity2.this, entity.getWidgets()));
//                            mFormAreaLly.addView(recyclerView);
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailed(String throwable) {
//
//                }
//            });
//        }
    }

    /**
     * 处理待办页面
     */
    private void handlePendding(){
        mCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("commit--url", mCommitUrl);
                Log.e("commitParamMap--commit", mCommitParamMap.toString());
                if ( "".equals(mCommitUrl) || mCommitUrl.length()== 0 || "null".equals(mCommitUrl) ){
                    Toast.makeText(ApprovalDetailActivity2.this, "提交地址无效！", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                //如果提交参数中有占位符，说明有必填项未处理
                if (mCommitParamMap.containsValue(PEND_ITEM_TAG)){
                    Toast.makeText(ApprovalDetailActivity2.this, "有未填的必填项！", Toast.LENGTH_SHORT).show();
                    return;
                }

                MyApplication.getInstance().commitApproval(
                        mCommitUrl,
                        mCommitParamMap,
                        new OnRequestDataListener<ApprovalResponseEntity>() {
                            @Override
                            public void onSuccessed(ApprovalResponseEntity approvalResponseEntity) {
                                Toast.makeText(ApprovalDetailActivity2.this, "提交成功！", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailed(String throwable) {
                                Toast.makeText(ApprovalDetailActivity2.this, throwable, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        //初始化提交参数
        mCommitParamMap= new HashMap<>();

        MyApplication.getInstance().getApprovalDetail(
                url,
                workItemId,
                activityDefId,
                processInstId,
                new OnRequestDataListener<ApprovalEntity>() {

                    @Override
                    public void onSuccessed(ApprovalEntity approvalEntity) {
                        //获取提交地址
                        mCommitUrl= approvalEntity.getUrl();
                        mApprovalEntity= approvalEntity;
                        formReady= true;
                        initFormUI();
                    }

                    @Override
                    public void onFailed(String throwable) {
                        Toast.makeText(ApprovalDetailActivity2.this, "Detail " + throwable, Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

        MyApplication.getInstance().getApprovalHistory(
                Integer.valueOf(processInstId),
                new OnRequestDataListener<ApprovalHistoryResponseEntity>() {
                    @Override
                    public void onSuccessed(ApprovalHistoryResponseEntity approvalHistoryResponseEntity) {
                        mHistoryEntity= approvalHistoryResponseEntity;
                        historyReady= true;
                        initFormUI();
                    }

                    @Override
                    public void onFailed(String throwable) {
                        Toast.makeText(ApprovalDetailActivity2.this, "History " + throwable, Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    /**
     * 处理已办页面
     */
    private void handleFinished(){
        mCommitBtn.setVisibility(View.GONE);

        MyApplication.getInstance().getApprovalDetail(
                url,
                workItemId,
                activityDefId,
                processInstId,
                new OnRequestDataListener<ApprovalEntity>() {

                    @Override
                    public void onSuccessed(ApprovalEntity approvalEntity) {
                        mApprovalEntity= approvalEntity;
                        formReady= true;
                        initFormUI();
                    }

                    @Override
                    public void onFailed(String throwable) {
                        Toast.makeText(ApprovalDetailActivity2.this, "Detail " + throwable, Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

        MyApplication.getInstance().getApprovalHistory(
                Integer.valueOf(processInstId),
                new OnRequestDataListener<ApprovalHistoryResponseEntity>() {
                    @Override
                    public void onSuccessed(ApprovalHistoryResponseEntity approvalHistoryResponseEntity) {
                        mHistoryEntity= approvalHistoryResponseEntity;
                        historyReady= true;
                        initFormUI();
                    }

                    @Override
                    public void onFailed(String throwable) {
                        Toast.makeText(ApprovalDetailActivity2.this, "History " + throwable, Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

    }

    public HashMap<String, String> getmCommitParamMap() {
        return mCommitParamMap;
    }

    /** 用于储存经办人姓名的变量 */
    private String mJingBanRenName="";
    public void setJingBanRenStr(String str){
        mJingBanRenName=str;
    }
    public String getJingBanRenStr(){
        return mJingBanRenName;
    }

    /** 用于储存经办人empid的变量 */
    private String mJingBanRenId="";
    public void setJingBanRenId(String id){
        mJingBanRenId=id;
    }
    public String getJingBanRenId(){
        return mJingBanRenId;
    }

    /** 与switch开关关联项的key */
    private String connectKey="";
    public void setConnectKey(String key){
        connectKey=key;
    }
    public String getConnectKey(){
        return connectKey;
    }

    /** 储存switch开关当前状态 */
    private String isConnect="";
    public void setIsConnect(String value){
        isConnect=value;
    }
    public String getIsConnect(){
        return isConnect;
    }

    /** 互斥开关的第一个参数 */
    private String fristMutualKey="";
    public void setFristMutualKey(String key){
        fristMutualKey=key;
    }
    public String getFristMutualKey(){
        return fristMutualKey;
    }

    /** 互斥开关的第二个参数 */
    private String secondMutualKey="";
    public void setSecondMutualKey(String key){
        secondMutualKey=key;
    }
    public String getSecondMutualKey(){
        return secondMutualKey;
    }

    /** 储存互斥开关开关当前状态 */
    private String mutualStatus="";
    public void setMutualStatus(String value){
        mutualStatus=value;
    }
    public String getMutualStatus(){
        return mutualStatus;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }


    private boolean formReady;//表格数据是否请求完成
    private boolean historyReady;//历史记录是否请求完成
    private ApprovalEntity mApprovalEntity;//表格数据实体
    private ApprovalHistoryResponseEntity mHistoryEntity;//历史记录数据实体
    /**
     * 初始化表格
     */
    private void initFormUI(){
        if (!formReady || !historyReady){
            return;
        }

        ArrayList<Object> formList= new ArrayList<>();

        ArrayList<ApprovalGroupEntity> groups= mApprovalEntity.getGroupList();
        ArrayList<ApprovalWidgetEntity> widgets= mApprovalEntity.getCtlList();

        //所有控件分组
        for ( ApprovalGroupEntity groupEntity : groups ){

            String groupKey= groupEntity.getGroupKey();
            ArrayList<ApprovalWidgetEntity> gWidgets= new ArrayList<>();

            for ( ApprovalWidgetEntity widgetEntity : widgets ){
                if (groupKey.equals(widgetEntity.getGroupKey())){

                    //放在if中执行本操作，保证每个widget只执行一次此操作，并且仅待办审批中需要此操作
                    if (typeTag== MyPenddingFlowAdapter.TYPE_PENDDING) {
                        //把widget的key转换成标准模式
                        widgetEntity.setKey(widgetEntity.getKey().replace(".","/"));
                        //如果这个widget是必填项，向提交参数中添加占位符
                        if (widgetEntity.isRequired()){
                            mCommitParamMap.put(widgetEntity.getKey(), PEND_ITEM_TAG);
                        }
                    }

                    //不显示type= hidden的控件
                    if ("hidden".equals(widgetEntity.getType())){
                        //如果这是待办审批，添加hidden控件的key-value到提交参数中
                        if (typeTag== MyPenddingFlowAdapter.TYPE_PENDDING) {
                            mCommitParamMap.put(widgetEntity.getKey(), widgetEntity.getValue());
                        }
                        continue;
                    }
                    gWidgets.add(widgetEntity);
                }
            }

            //不显示没有可显示子控件的组
            if (gWidgets.size()>0){
                formList.add(groupEntity);
                formList.addAll(gWidgets);
            }
        }

        int historyIndex = 0;

        //查找编辑组的索引，用于把历史记录组插入到编辑组前
        for (int i= 0; i<formList.size(); i++){
            Object object= formList.get(i);
            if ( object instanceof ApprovalGroupEntity && "audit".equals(((ApprovalGroupEntity) object).getGroupKey())){
                historyIndex= i;
                break;
            }
        }

        if (mHistoryEntity.getList()!= null && mHistoryEntity.getList().size()>0){
            ApprovalGroupEntity historyGroup= new ApprovalGroupEntity();
            historyGroup.setLabel("审批记录");
            formList.add( historyIndex, historyGroup);
            formList.addAll( historyIndex + 1, mHistoryEntity.getList());
        }

        MyApprovalDetailAdapter2 adapter2= new MyApprovalDetailAdapter2(this, formList);
        mFormRly.setAdapter(adapter2);

    }

}

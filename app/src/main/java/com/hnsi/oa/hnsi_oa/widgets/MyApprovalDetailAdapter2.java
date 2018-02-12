package com.hnsi.oa.hnsi_oa.widgets;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.approval.widget.AllListActivity;
import com.hnsi.oa.hnsi_oa.approval.widget.ApprovalDetailActivity;
import com.hnsi.oa.hnsi_oa.approval.widget.ApprovalDetailActivity2;
import com.hnsi.oa.hnsi_oa.approval.widget.HtmlInfoActivity;
import com.hnsi.oa.hnsi_oa.approval.widget.TableFilesActivity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalGroupEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalHistoryEntity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalWidgetEntity;
import com.hnsi.oa.hnsi_oa.beans.ListDataBean;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/19.
 */

public class MyApprovalDetailAdapter2 extends RecyclerView.Adapter<MyApprovalDetailAdapter2.ApprovalDetailViewHolder> {

    /** 简单的key-value展示 */
    private static final int ITEM_TEXT= 0x00;
    /** 意见输入框 */
    private static final int ITEM_EDIT_TEXT= 0x01;
    /** tableView附表列表 */
    private static final int ITEM_TABLE_VIEW_LIST= 0x02;
    /** 包含一段html内容 */
    private static final int ITEM_HTML= 0x03;
    /** 点击按钮从listData中选取一个值 */
    private static final int ITEM_LIST_AUDIT= 0x04;
    /** 根据value从listData中选取一个值 */
    private static final int ITEM_LIST_ELSE= 0x05;
    /** 从所有联系人中选取一个值 */
    private static final int ITEM_ALLLIST= 0x06;
    /** switch开关 */
    private static final int ITEM_SWITCH= 0x07;
    /** switch互斥开关 */
    private static final int ITEM_SWITCH_MUTEX= 0x08;
    /** 打开附件界面 */
    private static final int ITEM_TABLE_FILES= 0x09;

    /** 分组标题 */
    private static final int ITEM_GROUP_TITLE= 0x0A;
    /** 审批记录 */
    private static final int ITEM_HISTORY_0= 0x0B;
    /** 审批记录 */
    private static final int ITEM_HISTORY_1= 0x0C;
    /** 审批记录 */
    private static final int ITEM_HISTORY_2= 0x0D;
    /** 审批记录 */
    private static final int ITEM_HISTORY_3= 0x0E;



    private ApprovalDetailActivity2 mContext;
    private ArrayList<Object> mData;

    public MyApprovalDetailAdapter2(ApprovalDetailActivity2 context, ArrayList<Object> data){
        mContext= context;
        mData= data;
    }

    @Override
    public ApprovalDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = null;
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        if (viewType== ITEM_TEXT){
            mView= inflater.inflate(R.layout.item_base_text_textarea_date_int, parent, false);
            mView.setTag(ITEM_TEXT);
        }else if (viewType== ITEM_EDIT_TEXT){
            mView= inflater.inflate(R.layout.item_audit_edittext, parent, false);
            mView.setTag(ITEM_EDIT_TEXT);
        }else if (viewType== ITEM_TABLE_VIEW_LIST){
            mView= inflater.inflate(R.layout.item_tableview_list, parent, false);
            mView.setTag(ITEM_TABLE_VIEW_LIST);
        }else if (viewType== ITEM_HTML){
            mView= inflater.inflate(R.layout.item_base_html, parent, false);
            mView.setTag(ITEM_HTML);
        }else if (viewType== ITEM_LIST_AUDIT){
            mView= inflater.inflate(R.layout.item_audit_button, parent, false);
            mView.setTag(ITEM_LIST_AUDIT);
        }else if (viewType== ITEM_LIST_ELSE){
            mView= inflater.inflate(R.layout.item_base_text_textarea_date_int, parent, false);
            mView.setTag(ITEM_LIST_ELSE);
        }else if (viewType== ITEM_ALLLIST){
            mView= inflater.inflate(R.layout.item_audit_button, parent, false);
            mView.setTag(ITEM_ALLLIST);
        }else if (viewType== ITEM_SWITCH){
            mView= inflater.inflate(R.layout.item_audit_switch, parent, false);
            mView.setTag(ITEM_SWITCH);
        }else if (viewType== ITEM_SWITCH_MUTEX){
            mView= inflater.inflate(R.layout.item_audit_switch, parent, false);
            mView.setTag(ITEM_SWITCH_MUTEX);
        }else if (viewType== ITEM_TABLE_FILES){
            mView= inflater.inflate(R.layout.item_base_tablefiles, parent, false);
            mView.setTag(ITEM_TABLE_FILES);
        }else if (viewType== ITEM_GROUP_TITLE){
            mView= inflater.inflate(R.layout.item_approval_group, parent, false);
            mView.setTag(ITEM_GROUP_TITLE);
        }else if (viewType== ITEM_HISTORY_0){
            mView= inflater.inflate(R.layout.item_approval_history_decision0, parent, false);
            mView.setTag(ITEM_HISTORY_0);
        }else if (viewType== ITEM_HISTORY_1){
            mView= inflater.inflate(R.layout.item_approval_history_decision1, parent, false);
            mView.setTag(ITEM_HISTORY_1);
        }else if (viewType== ITEM_HISTORY_2){
            mView= inflater.inflate(R.layout.item_approval_history_decision2, parent, false);
            mView.setTag(ITEM_HISTORY_2);
        }else if (viewType== ITEM_HISTORY_3){
            mView= inflater.inflate(R.layout.item_approval_history_decision3, parent, false);
            mView.setTag(ITEM_HISTORY_3);
        }
        return new ApprovalDetailViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ApprovalDetailViewHolder holder, int position) {
        Object object= mData.get(position);
//        holder.drawView(object);
        switch (getItemViewType(position)){
            case ITEM_TEXT:
                ApprovalWidgetEntity entity0= (ApprovalWidgetEntity) object;
                holder.mTextNameTv.setText(entity0.getLabel());
                if(entity0.getValue()== null
                        ||"".equals(entity0.getValue())
                        ||"null".equals(entity0.getValue())){
                    holder.mTextValueTv.setText("无");
                }else{
                    holder.mTextValueTv.setText(entity0.getValue());
                }
                if (entity0.isRequired()){
                    if (entity0.getValue()== null){
                        mContext.getmCommitParamMap().put(entity0.getKey(), "null");
                    }else {
                        mContext.getmCommitParamMap().put(entity0.getKey(), entity0.getValue());
                    }
                }
                break;
            case ITEM_EDIT_TEXT:
                final ApprovalWidgetEntity entity1= (ApprovalWidgetEntity) object;

                holder.mSuggestEt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if("".equals(s.toString())){
                            mContext.getmCommitParamMap().put(entity1.getKey(), ApprovalDetailActivity2.PEND_ITEM_TAG);
                        }else{
                            mContext.getmCommitParamMap().put(entity1.getKey(), s.toString());
                        }
                    }
                });

                //如果这一项是“处理意见”，目前这个类型只有这一种
                if("wfParam/content".equals(entity1.getKey())){
                    //处理意见这一项是必填的
                    String content="" + mContext.getmCommitParamMap().get("wfParam/content");
                    if(ApprovalDetailActivity2.PEND_ITEM_TAG.equals(content)||"null".equals(content)){
                        holder.mSuggestEt.setText("");
                    }else{
                        holder.mSuggestEt.setText(content);
                    }
                }

                break;
            case ITEM_TABLE_VIEW_LIST:
                ApprovalWidgetEntity entity2= (ApprovalWidgetEntity) object;
                holder.mTableViewList.setupTableView(entity2);

                if (entity2.isRequired()){
                    mContext.getmCommitParamMap().put(entity2.getKey(), entity2.getValue());
                }

                break;
            case ITEM_HTML:
                final ApprovalWidgetEntity entity3= (ApprovalWidgetEntity) object;
                holder.mHtmlPanelRly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(mContext, HtmlInfoActivity.class);
                        intent.putExtra("htmlInfo", entity3);
                        mContext.startActivity(intent);
                    }
                });
                holder.mHtmlNameTv.setText(entity3.getLabel());

                if (entity3.isRequired()){
                    mContext.getmCommitParamMap().put(entity3.getKey(), entity3.getValue());
                }

                break;
            case ITEM_LIST_AUDIT:
                final ApprovalWidgetEntity entity4= (ApprovalWidgetEntity) object;

                holder.mButtonNameTv.setTextColor(Color.rgb(68,68,68));
                holder.mButtonButtonTv.setBackgroundColor(Color.rgb(73,119,231));

                holder.mButtonNameTv.setText(entity4.getLabel());

                //查找提交参数中是否已存有数据
                String finishValue= mContext.getmCommitParamMap().get(entity4.getKey());
                if (finishValue== null || "pend".equals(finishValue) || "null".equals(finishValue)){//如果没有已存数据
                    holder.mButtonButtonTv.setText("请点击选择");
                }else {//如果已存有数据,把已存数据录入界面
                    for (ListDataBean bean : entity4.getListData()){
                        if (finishValue.equals(bean.getValue())){
                            holder.mButtonButtonTv.setText(bean.getLabel());
                            break;//退出循环
                        }
                    }
                }

                final ArrayList<ListDataBean> listData= entity4.getListData();

                final int dataSize= listData.size();
                final CharSequence[] mLables = new CharSequence[dataSize];
                final CharSequence[] mValues = new CharSequence[dataSize];
                if (dataSize> 0){
                    for (int i= 0; i< dataSize; i++ ){
                        mLables[i]= listData.get(i).getLabel();
                        mValues[i]= listData.get(i).getValue();
                    }
                }

                holder.mButtonButtonTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "Button", Toast.LENGTH_SHORT).show();

                        if (dataSize== 0){
                            Toast.makeText(mContext, "没有可选择的数据！", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                                .setTitle(entity4.getLabel())
                                .setItems(mLables, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        //如果点的这一项对应的value不等于null，把这个value值放入参数集合中
                                        if (!"null".equals(mValues[which])) {
                                            mContext.getmCommitParamMap().put(entity4.getKey(), ""+mValues[which]);
                                        }

                                        //如果这一项是常用，把选择的这一项的lable设置为content的值
                                        if("wfParam/changyong".equals(entity4.getKey())){
                                            mContext.getmCommitParamMap().put("wfParam/content", ""+mLables[which]);
                                        }

                                        notifyDataSetChanged();
                                    }
                                });
                        builder.create();
                        builder.show();
                    }
                });

                //判断此项是否是switch的关联项
                if(mContext.getConnectKey().equals(entity4.getKey())){
                    if(mContext.getIsConnect().equals("0")){//switch当前状态为off,把此项调为不可选状态
                        holder.mButtonNameTv.setTextColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setBackgroundColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setClickable(false);
                    }
                }

                //判断此项是否是互斥开关的第一个关联项
                if(mContext.getFristMutualKey().equals(entity4.getKey())){
                    if("1".equals(mContext.getMutualStatus())){
                        holder.mButtonNameTv.setTextColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setBackgroundColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setClickable(false);
                    }
                }

                //判断此项是否是互斥开关的第二个关联项
                if(mContext.getSecondMutualKey().equals(entity4.getKey())){
                    if("0".equals(mContext.getMutualStatus())){
                        holder.mButtonNameTv.setTextColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setBackgroundColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setClickable(false);
                    }
                }

                break;
            case ITEM_LIST_ELSE:
                ApprovalWidgetEntity entity5= (ApprovalWidgetEntity) object;
                holder.mTextNameTv.setText(entity5.getLabel());
                ArrayList<ListDataBean> listDatas= entity5.getListData();
                String value= entity5.getValue();
                String label = "无";
                if(value== null
                        ||"".equals(value)
                        ||"null".equals(value)){
                    holder.mTextValueTv.setText("无");
                }else{
                    for (ListDataBean bean : listDatas){
                        if (value.equals(bean.getValue())){
                            label= bean.getLabel();
                        }
                    }
                    holder.mTextValueTv.setText(label);
                }
                if (entity5.isRequired()){
                    mContext.getmCommitParamMap().put(entity5.getKey(), entity5.getValue());
                }
                break;
            case ITEM_ALLLIST:
                ApprovalWidgetEntity entity6= (ApprovalWidgetEntity) object;

                holder.mButtonNameTv.setTextColor(Color.rgb(68,68,68));
                holder.mButtonButtonTv.setBackgroundColor(Color.rgb(73,119,231));

                holder.mButtonNameTv.setText(entity6.getLabel());

                //默认value值
                String allListValue=entity6.getValue();
                //默认value值对应的名字
                String defaultName=entity6.getPrompt();

                //从Activity中拿到经办人姓名字符串，如果是有效字符串，从控件中显示出来
                String mJingBanRenName=mContext.getJingBanRenStr();
                if("".equals(mJingBanRenName)){
                    if( allListValue!= null
                            &&!("".equals(allListValue))
                            &&!("null".equals(allListValue))
                            &&defaultName!= null
                            &&!("".equals(defaultName))
                            &&!("null".equals(defaultName))){
                        mContext.setJingBanRenStr(defaultName);
                        mContext.setJingBanRenId(allListValue);
                        mContext.getmCommitParamMap().put(entity6.getKey(), allListValue);
                        holder.mButtonButtonTv.setText(defaultName);
                    }else{
                        holder.mButtonButtonTv.setText("请点击选择");
                    }
                }else{
                    holder.mButtonButtonTv.setText(mJingBanRenName);
                    mContext.getmCommitParamMap().put(entity6.getKey(), mContext.getJingBanRenId());
                }

                holder.mButtonButtonTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, AllListActivity.class);
                        mContext.startActivityForResult(intent, 110);
                    }
                });

                //判断此项是否是switch的关联项
                if(mContext.getConnectKey().equals(entity6.getKey())){
                    if(mContext.getIsConnect().equals("0")){//switch当前状态为off,把此项调为不可选状态
                        holder.mButtonNameTv.setTextColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setBackgroundColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setClickable(false);
                    }
                }

                //判断此项是否是互斥开关的第一个关联项
                if(mContext.getFristMutualKey().equals(entity6.getKey())){
                    if("1".equals(mContext.getMutualStatus())){
                        holder.mButtonNameTv.setTextColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setBackgroundColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setClickable(false);
                    }
                }

                //判断此项是否是互斥开关的第二个关联项
                if(mContext.getSecondMutualKey().equals(entity6.getKey())){
                    if("0".equals(mContext.getMutualStatus())){
                        holder.mButtonNameTv.setTextColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setBackgroundColor(Color.rgb(177, 177, 177));
                        holder.mButtonButtonTv.setClickable(false);
                    }
                }

                break;
            case ITEM_SWITCH:
                ApprovalWidgetEntity entity7= (ApprovalWidgetEntity) object;
                holder.mSwitchNameTv.setText(entity7.getLabel());

                //如果Activity中没有存放switch的value，把默认的value值存进去
                if("".equals(mContext.getIsConnect()))
                    mContext.setIsConnect(entity7.getValue());

                //switch开关控制的控件的key
                String connectKey=entity7.getPrompt().replace(".","/");
                mContext.setConnectKey(connectKey);
                //Activity中存放的value值
                String actValue= mContext.getIsConnect();
                if("0".equals(actValue)){
                    holder.mSwitchTbtn.setChecked(false);
                    mContext.getmCommitParamMap().put(entity7.getKey(), actValue);
                }else if("1".equals(actValue)){
                    holder.mSwitchTbtn.setChecked(true);
                    mContext.getmCommitParamMap().put(entity7.getKey(), actValue);
                }

                holder.mSwitchTbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Toast.makeText(mContext, ""+ isChecked, Toast.LENGTH_SHORT).show();
                        if(isChecked){
                            mContext.setIsConnect("1");
                            notifyDataSetChanged();
                        }else{
                            mContext.setIsConnect("0");
                            notifyDataSetChanged();
                        }
                    }
                });
                break;
            case ITEM_SWITCH_MUTEX:
                ApprovalWidgetEntity entity8= (ApprovalWidgetEntity) object;
                holder.mSwitchNameTv.setText(entity8.getLabel());

                //如果Activity中没有纪录互斥开关的当前状态，放入默认值
                if("".equals(mContext.getMutualStatus())){
                    mContext.setMutualStatus(entity8.getValue());
                }

                //互斥开关控制的两个控件的key
                String mutexConnectKey= entity8.getPrompt().replace(".","/");
                //如果Activity中未存入互斥开关控制的两个控件的key值，就给它们传值
                if("".equals(mContext.getFristMutualKey())
                        ||"".equals(mContext.getSecondMutualKey())){
                    String[]keys=mutexConnectKey.split("\\|");
                    Log.e("mutualArray", ""+keys.length);
                    mContext.setFristMutualKey(keys[0]);
                    mContext.setSecondMutualKey(keys[1]);
                }

                //提交参数Map中存放的value值
                String mutexActValue= mContext.getMutualStatus();
                if("0".equals(mutexActValue)){
                    holder.mSwitchTbtn.setChecked(false);
                    mContext.getmCommitParamMap().put(entity8.getKey(), "0");
                }else if("1".equals(mutexActValue)){
                    holder.mSwitchTbtn.setChecked(true);
                    mContext.getmCommitParamMap().put(entity8.getKey(), "1");
                }

                holder.mSwitchTbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Toast.makeText(mContext, ""+ isChecked, Toast.LENGTH_SHORT).show();
                        if(isChecked){
                            mContext.setMutualStatus("1");
                            notifyDataSetChanged();
                        }else{
                            mContext.setMutualStatus("0");
                            notifyDataSetChanged();
                        }
                    }
                });
                break;
            case ITEM_TABLE_FILES:
                final ApprovalWidgetEntity entity9= (ApprovalWidgetEntity) object;
                holder.mFilesPanelRly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(mContext, TableFilesActivity.class);
                        intent.putExtra("fileinfo", entity9);
                        mContext.startActivity(intent);
                    }
                });
                holder.mFilesNameTv.setText(entity9.getLabel());
                break;
            case ITEM_GROUP_TITLE:
                ApprovalGroupEntity groupEntity= (ApprovalGroupEntity) object;
                holder.mGroupTitleTv.setText(groupEntity.getLabel());
                break;
            case ITEM_HISTORY_0:
                ApprovalHistoryEntity historyEntity0= (ApprovalHistoryEntity) object;
                holder.mHistoryNameTv.setText(historyEntity0.getName());
                holder.mHistoryTypeTv.setText(historyEntity0.getActivityName());
                String content0= historyEntity0.getContent();
                if(content0== null
                        ||"null".equals(content0)
                        ||"".equals(content0)){
                    holder.mHistoryContentTv.setText("无处理意见");
                }else{
                    holder.mHistoryContentTv.setText(content0);
                }
                holder.mHistoryTimeTv.setText(historyEntity0.getEndTime().substring(0,19));
                break;
            case ITEM_HISTORY_1:
                ApprovalHistoryEntity historyEntity1= (ApprovalHistoryEntity) object;
                holder.mHistoryNameTv.setText(historyEntity1.getName());
                holder.mHistoryTypeTv.setText(historyEntity1.getActivityName());
                String content1= historyEntity1.getContent();
                if(content1== null
                        ||"null".equals(content1)
                        ||"".equals(content1)){
                    holder.mHistoryContentTv.setText("无处理意见");
                }else{
                    holder.mHistoryContentTv.setText(content1);
                }
                holder.mHistoryTimeTv.setText(historyEntity1.getEndTime().substring(0,19));
                break;
            case ITEM_HISTORY_2:
                ApprovalHistoryEntity historyEntity2= (ApprovalHistoryEntity) object;
                holder.mHistoryNameTv.setText(historyEntity2.getName());
                holder.mHistoryTypeTv.setText(historyEntity2.getActivityName());
                String content2= historyEntity2.getContent();
                if(content2== null
                        ||"null".equals(content2)
                        ||"".equals(content2)){
                    holder.mHistoryContentTv.setText("无处理意见");
                }else{
                    holder.mHistoryContentTv.setText(content2);
                }
                holder.mHistoryTimeTv.setText(historyEntity2.getEndTime().substring(0,19));
                break;
            case ITEM_HISTORY_3:
                ApprovalHistoryEntity historyEntity3= (ApprovalHistoryEntity) object;
                holder.mHistoryNameTv.setText(historyEntity3.getName());
                holder.mHistoryTypeTv.setText(historyEntity3.getActivityName());
                String content3= historyEntity3.getContent();
                if(content3== null
                        ||"null".equals(content3)
                        ||"".equals(content3)){
                    holder.mHistoryContentTv.setText("无处理意见");
                }else{
                    holder.mHistoryContentTv.setText(content3);
                }
                holder.mHistoryTimeTv.setText(historyEntity3.getEndTime().substring(0,19));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {

        Object object= mData.get(position);
        Log.e("obj", object.toString());

        if (object instanceof ApprovalWidgetEntity){
            return discernWidget((ApprovalWidgetEntity) object);
        }else if (object instanceof ApprovalGroupEntity){
            return ITEM_GROUP_TITLE;
        }else if (object instanceof ApprovalHistoryEntity){
//            Log.e("history", ""+discernHistory((ApprovalHistoryEntity) object));
            return discernHistory((ApprovalHistoryEntity) object);
        }

        return super.getItemViewType(position);
    }

    private int discernWidget(ApprovalWidgetEntity entity){
        String widgetGroup= entity.getGroupKey();
        String widgetType= entity.getType();

        if ("text".equals(widgetType)
                || "date".equals(widgetType)
                || "int".equals(widgetType)
                || "FLOAT".equals(widgetType)
                || "datetime".equals(widgetType) ){
            return ITEM_TEXT;
        }else if ("textarea".equals(widgetType)){
            if("audit".equals(widgetGroup)){
                return ITEM_EDIT_TEXT;
            }else{
                return ITEM_TEXT;
            }
        }else if ("tableView".equals(widgetType)){
            return ITEM_TABLE_VIEW_LIST;
        }else if ("html".equals(widgetType)){
            return ITEM_HTML;
        }else if ("list".equals(widgetType)){
            if("audit".equals(widgetGroup)){
                return ITEM_LIST_AUDIT;
            }else{
                return ITEM_LIST_ELSE;
            }
        }else if ("alllist".equals(widgetType)){
            return ITEM_ALLLIST;
        }else if ("switch".equals(widgetType)){
            return ITEM_SWITCH;
        }else if ("switchmutex".equals(widgetType)){
            return ITEM_SWITCH_MUTEX;
        }else if ("tableFiles".equals(widgetType)){
            return ITEM_TABLE_FILES;
        }

        return ITEM_TEXT;
    }

    private int discernHistory(ApprovalHistoryEntity entity){
        int decision= Integer.valueOf(entity.getDecision());
        Log.e("history1", ""+decision);
        switch (decision){
            case 0:
                return ITEM_HISTORY_0;
            case 2:
                return ITEM_HISTORY_2;
            case 3:
                return ITEM_HISTORY_3;
            default:
                return ITEM_HISTORY_1;
        }
    }

    class ApprovalDetailViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextNameTv;
        private TextView mTextValueTv;

        private EditText mSuggestEt;

        private RelativeLayout mHtmlPanelRly;
        private TextView mHtmlNameTv;

        private TextView mButtonNameTv;
        private TextView mButtonButtonTv;

        private TextView mSwitchNameTv;
        private ToggleButton mSwitchTbtn;

        private RelativeLayout mFilesPanelRly;
        private TextView mFilesNameTv;

        private TextView mGroupTitleTv;

        private TextView mHistoryNameTv;
        private TextView mHistoryTypeTv;
        private TextView mHistoryContentTv;
        private TextView mHistoryTimeTv;

        private TableViewListView mTableViewList;

        public ApprovalDetailViewHolder(View itemView) {
            super(itemView);
            int viewTag= (int) itemView.getTag();
            switch (viewTag){
                case ITEM_TEXT:
                    mTextNameTv= (TextView) itemView.findViewById(R.id.item_base_name);
                    mTextValueTv= (TextView) itemView.findViewById(R.id.item_base_value);
                    break;
                case ITEM_EDIT_TEXT:
                    mSuggestEt= (EditText) itemView.findViewById(R.id.item_audit_et_suggest);
                    break;
                case ITEM_TABLE_VIEW_LIST:
                    mTableViewList= (TableViewListView) itemView.findViewById(R.id.tableview_list);
                    break;
                case ITEM_HTML:
                    mHtmlPanelRly= (RelativeLayout) itemView.findViewById(R.id.item_rly_html_panel);
                    mHtmlNameTv= (TextView) itemView.findViewById(R.id.item_base_name);
                    break;
                case ITEM_LIST_AUDIT:
                    mButtonNameTv= (TextView) itemView.findViewById(R.id.item_audit_name);
                    mButtonButtonTv= (TextView) itemView.findViewById(R.id.item_audit_button);
                    break;
                case ITEM_LIST_ELSE:
                    mTextNameTv= (TextView) itemView.findViewById(R.id.item_base_name);
                    mTextValueTv= (TextView) itemView.findViewById(R.id.item_base_value);
                    break;
                case ITEM_ALLLIST:
                    mButtonNameTv= (TextView) itemView.findViewById(R.id.item_audit_name);
                    mButtonButtonTv= (TextView) itemView.findViewById(R.id.item_audit_button);
                    break;
                case ITEM_SWITCH:
                    mSwitchNameTv= (TextView) itemView.findViewById(R.id.item_audit_name);
                    mSwitchTbtn= (ToggleButton) itemView.findViewById(R.id.item_audit_switch);
                    break;
                case ITEM_SWITCH_MUTEX:
                    mSwitchNameTv= (TextView) itemView.findViewById(R.id.item_audit_name);
                    mSwitchTbtn= (ToggleButton) itemView.findViewById(R.id.item_audit_switch);
                    break;
                case ITEM_TABLE_FILES:
                    mFilesPanelRly= (RelativeLayout) itemView.findViewById(R.id.item_rly_html_panel);
                    mFilesNameTv= (TextView) itemView.findViewById(R.id.item_base_name);
                    break;
                case ITEM_GROUP_TITLE:
                    mGroupTitleTv= (TextView) itemView.findViewById(R.id.item_group_title);
                    break;
                case ITEM_HISTORY_0:
                    mHistoryNameTv= (TextView) itemView.findViewById(R.id.item_history_name);
                    mHistoryTypeTv= (TextView) itemView.findViewById(R.id.item_history_department);
                    mHistoryContentTv= (TextView) itemView.findViewById(R.id.item_history_content);
                    mHistoryTimeTv= (TextView) itemView.findViewById(R.id.item_history_starttime);
                    break;
                case ITEM_HISTORY_1:
                    mHistoryNameTv= (TextView) itemView.findViewById(R.id.item_history_name);
                    mHistoryTypeTv= (TextView) itemView.findViewById(R.id.item_history_department);
                    mHistoryContentTv= (TextView) itemView.findViewById(R.id.item_history_content);
                    mHistoryTimeTv= (TextView) itemView.findViewById(R.id.item_history_starttime);
                    break;
                case ITEM_HISTORY_2:
                    mHistoryNameTv= (TextView) itemView.findViewById(R.id.item_history_name);
                    mHistoryTypeTv= (TextView) itemView.findViewById(R.id.item_history_department);
                    mHistoryContentTv= (TextView) itemView.findViewById(R.id.item_history_content);
                    mHistoryTimeTv= (TextView) itemView.findViewById(R.id.item_history_starttime);
                    break;
                case ITEM_HISTORY_3:
                    mHistoryNameTv= (TextView) itemView.findViewById(R.id.item_history_name);
                    mHistoryTypeTv= (TextView) itemView.findViewById(R.id.item_history_department);
                    mHistoryContentTv= (TextView) itemView.findViewById(R.id.item_history_content);
                    mHistoryTimeTv= (TextView) itemView.findViewById(R.id.item_history_starttime);
                    break;
            }
        }

//        private void drawView(Object object){
//            if (object instanceof ApprovalGroupEntity){
//                ApprovalGroupEntity groupEntity= (ApprovalGroupEntity) object;
//                mGroupTitleTv.setText(groupEntity.getLabel());
//            }else if (object instanceof ApprovalWidgetEntity){
//
//            }else if (object instanceof ApprovalHistoryEntity){
//
//            }
//        }
    }

}

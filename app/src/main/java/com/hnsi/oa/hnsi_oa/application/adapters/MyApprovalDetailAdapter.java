package com.hnsi.oa.hnsi_oa.application.adapters;

import android.content.DialogInterface;
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
import com.hnsi.oa.hnsi_oa.application.approval.widget.ApprovalDetailActivity;
import com.hnsi.oa.hnsi_oa.application.beans.ApprovalWidgetEntity;
import com.hnsi.oa.hnsi_oa.application.beans.ListDataBean;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/19.
 */

public class MyApprovalDetailAdapter extends RecyclerView.Adapter<MyApprovalDetailAdapter.ApprovalDetailViewHolder> {

    /** 简单的key-value展示 */
    private static final int ITEM_TEXT= 0;
    /** 意见输入框 */
    private static final int ITEM_EDIT_TEXT= 1;
    /** tableView附表列表 */
    private static final int ITEM_TABLE_VIEW_LIST= 2;
    /** 包含一段html内容 */
    private static final int ITEM_HTML= 3;
    /** 点击按钮从listData中选取一个值 */
    private static final int ITEM_LIST_AUDIT= 4;
    /** 根据value从listData中选取一个值 */
    private static final int ITEM_LIST_ELSE= 5;
    /** 从所有联系人中选取一个值 */
    private static final int ITEM_ALLLIST= 6;
    /** switch开关 */
    private static final int ITEM_SWITCH= 7;
    /** switch互斥开关 */
    private static final int ITEM_SWITCH_MUTEX= 8;
    /** 打开附件界面 */
    private static final int ITEM_TABLE_FILES= 9;


    private ApprovalDetailActivity mContext;
    private ArrayList<ApprovalWidgetEntity> mData;

    public MyApprovalDetailAdapter(ApprovalDetailActivity context, ArrayList<ApprovalWidgetEntity> data){
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
        }
        return new ApprovalDetailViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ApprovalDetailViewHolder holder, int position) {
        final ApprovalWidgetEntity entity= mData.get(position);
        switch (getItemViewType(position)){
            case ITEM_TEXT:
                holder.mTextNameTv.setText(entity.getLabel());
                if("null".equals(entity.getValue())){
                    holder.mTextValueTv.setText("无");
                }else{
                    holder.mTextValueTv.setText(entity.getValue());
                }
                if (entity.isRequired()){
                    mContext.getmCommitParamMap().put(entity.getKey(), entity.getValue());
                }
                break;
            case ITEM_EDIT_TEXT:
                if("wfParam/content".equals(entity.getKey())){
                    String content="" + mContext.getmCommitParamMap().get("wfParam/content");
                    if("pend".equals(content)||"null".equals(content)){
                        holder.mSuggestEt.setText("");
                    }else{
                        holder.mSuggestEt.setText(content);
                    }
                }
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
                            mContext.getmCommitParamMap().put(entity.getKey(), "pend");
                        }else{
                            mContext.getmCommitParamMap().put(entity.getKey(), s.toString());
                        }
                    }
                });
                break;
            case ITEM_TABLE_VIEW_LIST:
                break;
            case ITEM_HTML:
                holder.mHtmlPanelRly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "Html", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.mHtmlNameTv.setText(entity.getLabel());

                if (entity.isRequired()){
                    mContext.getmCommitParamMap().put(entity.getKey(), entity.getValue());
                }

                break;
            case ITEM_LIST_AUDIT:
                holder.mButtonNameTv.setText(entity.getLabel());

                //查找提交参数中是否已存有数据
                String finishValue= mContext.getmCommitParamMap().get(entity.getKey());
                if (finishValue== null || "pend".equals(finishValue) || "null".equals(finishValue)){//如果没有已存数据
                    holder.mButtonButtonTv.setText("请点击选择");
                }else {//如果已存有数据,把已存数据录入界面
                    for (ListDataBean bean : entity.getListData()){
                        if (finishValue.equals(bean.getValue())){
                            holder.mButtonButtonTv.setText(bean.getLabel());
                            break;//退出循环
                        }
                    }
                }

                final ArrayList<ListDataBean> listData= entity.getListData();

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
                                .setTitle(entity.getLabel())
                                .setItems(mLables, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        //如果点的这一项对应的value不等于null，把这个value值放入参数集合中
                                        if (!"null".equals(mValues[which])) {
                                            mContext.getmCommitParamMap().put(entity.getKey(), ""+mValues[which]);
                                        }

                                        //如果这一项是常用，把选择的这一项的lable设置为content的值
                                        if("wfParam/changyong".equals(entity.getKey())){
                                            mContext.getmCommitParamMap().put("wfParam/content", ""+mLables[which]);
                                        }

                                        notifyDataSetChanged();
                                    }
                                });
                        builder.create();
                        builder.show();
                    }
                });

                break;
            case ITEM_LIST_ELSE:
                holder.mTextNameTv.setText(entity.getLabel());
                ArrayList<ListDataBean> listDatas= entity.getListData();
                String value= entity.getValue();
                String label = "无";
                if("null".equals(value)){
                    holder.mTextValueTv.setText("无");
                }else{
                    for (ListDataBean bean : listDatas){
                        if (value.equals(bean.getValue())){
                            label= bean.getLabel();
                        }
                    }
                    holder.mTextValueTv.setText(label);
                }
                if (entity.isRequired()){
                    mContext.getmCommitParamMap().put(entity.getKey(), entity.getValue());
                }
                break;
            case ITEM_ALLLIST:
                holder.mButtonNameTv.setText(entity.getLabel());
                holder.mButtonButtonTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "AllList", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case ITEM_SWITCH:
                holder.mSwitchNameTv.setText(entity.getLabel());

                //如果Activity中没有存放switch的value，把默认的value值存进去
                if("".equals(mContext.getIsConnect()))
                    mContext.setIsConnect(entity.getValue());

                //switch开关控制的控件的key
                String connectKey=entity.getPrompt().replace(".","/");
                mContext.setConnectKey(connectKey);
                //Activity中存放的value值
                String actValue= mContext.getIsConnect();
                if("0".equals(actValue)){
                    holder.mSwitchTbtn.setChecked(false);
                    mContext.getmCommitParamMap().put(entity.getKey(), actValue);
                }else if("1".equals(actValue)){
                    holder.mSwitchTbtn.setChecked(true);
                    mContext.getmCommitParamMap().put(entity.getKey(), actValue);
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
                holder.mSwitchNameTv.setText(entity.getLabel());

                //如果Activity中没有纪录互斥开关的当前状态，放入默认值
                if("".equals(mContext.getMutualStatus())){
                    mContext.setMutualStatus(entity.getValue());
                }

                //互斥开关控制的两个控件的key
                String mutexConnectKey= entity.getPrompt().replace(".","/");
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
                    mContext.getmCommitParamMap().put(entity.getKey(), "0");
                }else if("1".equals(mutexActValue)){
                    holder.mSwitchTbtn.setChecked(true);
                    mContext.getmCommitParamMap().put(entity.getKey(), "1");
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
                holder.mFilesPanelRly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "Files", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.mFilesNameTv.setText(entity.getLabel());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {

        String widgetGroup= mData.get(position).getGroupKey();
        String widgetType= mData.get(position).getType();

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
//        }else if ("hidden".equals(widgetType)){

        }else if ("alllist".equals(widgetType)){
            return ITEM_ALLLIST;
        }else if ("switch".equals(widgetType)){
            return ITEM_SWITCH;
        }else if ("switchmutex".equals(widgetType)){
            return ITEM_SWITCH_MUTEX;
        }else if ("tableFiles".equals(widgetType)){
            return ITEM_TABLE_FILES;
        }

        return super.getItemViewType(position);
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

            }
        }
    }

}

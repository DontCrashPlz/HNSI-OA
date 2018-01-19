package com.hnsi.oa.hnsi_oa.widgets;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.approval.widget.ApprovalDetailActivity;
import com.hnsi.oa.hnsi_oa.beans.FlowEntity;

/**
 * Created by Zheng on 2018/1/8.
 */

public class MyPenddingFlowAdapter extends BaseQuickAdapter<FlowEntity, MyPenddingFlowAdapter.MyFlowViewHolder> {

    public static final String DETAIL_PARAM_URL= "url";
    public static final String DETAIL_PARAM_WORKITEMID= "workItemId";
    public static final String DETAIL_PARAM_ACTIVITYDEFID= "activityDefId";
    public static final String DETAIL_PARAM_PROCESSINSTID= "processInstId";

    public MyPenddingFlowAdapter() {
        super(R.layout.item_approval_no_complete_matter);
//        int layoutRes;
//        if (tag== FLOW_PENDDING){
//            layoutRes= R.layout.item_approval_no_complete_matter;
//        } else if (tag== FLOW_FINISHED){
//            layoutRes= R.layout.item_approval_complete_matter;
//        }
    }

    @Override
    protected void convert(MyFlowViewHolder helper, final FlowEntity item) {
        helper.mTitleTv.setText(item.getProcessInstName());
        helper.mClassTv.setText(item.getProcessChName());
        helper.mDateTv.setText(item.getStartTime());
        helper.mPanelRly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext, ApprovalDetailActivity.class);
                intent.putExtra(DETAIL_PARAM_URL, item.getFlowUrl());
                intent.putExtra(DETAIL_PARAM_WORKITEMID, item.getWorkItemID());
                intent.putExtra(DETAIL_PARAM_ACTIVITYDEFID, item.getActivityDefID());
                intent.putExtra(DETAIL_PARAM_PROCESSINSTID, item.getProcessInstID());
                mContext.startActivity(intent);
                Toast.makeText(mContext, item.getFlowUrl() +"--"+item.getWorkItemID() +"--"+ item.getActivityDefID() +"--"+ item.getProcessInstID(), Toast.LENGTH_LONG).show();
            }
        });
    }

    class MyFlowViewHolder extends BaseViewHolder{
        private RelativeLayout mPanelRly;
        private TextView mClassTv;
        private TextView mTitleTv;
        private TextView mDateTv;

        public MyFlowViewHolder(View view) {
            super(view);
            mPanelRly= (RelativeLayout) view.findViewById(R.id.item_unfinished_panel);
            mClassTv= (TextView) view.findViewById(R.id.item_unfinished_tv_class);
            mTitleTv= (TextView) view.findViewById(R.id.item_unfinished_tv_title);
            mDateTv= (TextView) view.findViewById(R.id.item_unfinished_tv_starttime);
        }
    }

}

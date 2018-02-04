package com.hnsi.oa.hnsi_oa.widgets;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.approval.widget.ApprovalDetailActivity;
import com.hnsi.oa.hnsi_oa.beans.FlowEntity;

/**
 * Created by Zheng on 2018/1/8.
 */

public class MyFinishedFlowAdapter extends BaseQuickAdapter<FlowEntity, MyFinishedFlowAdapter.MyFlowViewHolder> {

    public MyFinishedFlowAdapter() {
        super(R.layout.item_approval_complete_matter);
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
                intent.putExtra(MyPenddingFlowAdapter.DETAIL_PARAM_URL, item.getFlowUrl());
                intent.putExtra(MyPenddingFlowAdapter.DETAIL_PARAM_PROCESSINSTID, item.getProcessInstID());
                intent.putExtra(MyPenddingFlowAdapter.TYPE_TAG, MyPenddingFlowAdapter.TYPE_FINISHED);
                mContext.startActivity(intent);
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
            mPanelRly= (RelativeLayout) view.findViewById(R.id.item_finished_panel);
            mClassTv= (TextView) view.findViewById(R.id.item_finished_tv_class);
            mTitleTv= (TextView) view.findViewById(R.id.item_finished_tv_title);
            mDateTv= (TextView) view.findViewById(R.id.item_finished_tv_starttime);
        }
    }

}

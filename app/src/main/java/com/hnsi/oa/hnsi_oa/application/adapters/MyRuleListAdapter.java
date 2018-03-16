package com.hnsi.oa.hnsi_oa.application.adapters;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.beans.RuleListEntity;
import com.hnsi.oa.hnsi_oa.application.news.widget.RulesDetailActivity;


/**
 * Created by Zheng on 2018/1/29.
 */

public class MyRuleListAdapter extends BaseQuickAdapter<RuleListEntity,MyRuleListAdapter.RuleListViewHolder> {

    public static final String RULE_ID="rule_id";

    public MyRuleListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(RuleListViewHolder helper, final RuleListEntity item) {
        helper.mTitleTv.setText(item.getTitle());
        helper.mTimeTv.setText(item.getOperationTime());
        helper.mPanelRly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext, RulesDetailActivity.class);
                intent.putExtra(RULE_ID, item.getId());
                mContext.startActivity(intent);
            }
        });
    }

    class RuleListViewHolder extends BaseViewHolder{
        private RelativeLayout mPanelRly;
        private TextView mTitleTv;
        private TextView mTimeTv;

        public RuleListViewHolder(View itemView) {
            super(itemView);
            mPanelRly= (RelativeLayout) itemView.findViewById(R.id.item_rule_rly);
            mTitleTv= (TextView) itemView.findViewById(R.id.item_rule_title);
            mTimeTv= (TextView) itemView.findViewById(R.id.item_rule_time);
        }
    }
}

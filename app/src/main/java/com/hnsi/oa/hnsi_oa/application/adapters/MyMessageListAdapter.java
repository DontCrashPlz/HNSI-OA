package com.hnsi.oa.hnsi_oa.application.adapters;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.beans.MessageListEntity;
import com.hnsi.oa.hnsi_oa.application.news.widget.MessageDetailActivity;


/**
 * Created by Zheng on 2018/1/29.
 */

public class MyMessageListAdapter extends BaseQuickAdapter<MessageListEntity,MyMessageListAdapter.MessageListViewHolder> {

    public static final String MESSAGE_ID="message_id";


    public MyMessageListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(MessageListViewHolder helper, final MessageListEntity item) {
        helper.mTitleTv.setText(item.getTitle());
        helper.mDepartmentTv.setText(item.getMsgType());
        helper.mNameTv.setText(item.getSendEmpname());
        helper.mTimeTv.setText(item.getSendTime());
        helper.mPanelRly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext, MessageDetailActivity.class);
                intent.putExtra(MESSAGE_ID, item.getId());
                mContext.startActivity(intent);
            }
        });
    }

    class MessageListViewHolder extends BaseViewHolder{
        private RelativeLayout mPanelRly;
        private TextView mTitleTv;
        private TextView mDepartmentTv;
        private TextView mNameTv;
        private TextView mTimeTv;

        public MessageListViewHolder(View itemView) {
            super(itemView);
            mPanelRly= (RelativeLayout) itemView.findViewById(R.id.item_message_rly);
            mTitleTv= (TextView) itemView.findViewById(R.id.item_message_title);
            mDepartmentTv= (TextView) itemView.findViewById(R.id.item_message_department);
            mNameTv= (TextView) itemView.findViewById(R.id.item_notice_author);
            mTimeTv= (TextView) itemView.findViewById(R.id.item_notice_time);
        }
    }
}

package com.hnsi.oa.hnsi_oa.application.adapters;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.application.news.widget.NewsDetailActivity;

/**
 * Created by Zheng on 2017/10/26.
 */

public class MyNewsListAdapter extends BaseQuickAdapter<NewsEntity,MyNewsListAdapter.NewsListViewHolder> {

    public MyNewsListAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(NewsListViewHolder helper, NewsEntity item) {
        helper.mTitleTv.setText(item.getTitle());
        helper.mKindTv.setText(item.getClassname());
        helper.mTimeTv.setText(item.getStartDate().substring(0,10));
        helper.mDepartmentTv.setText(item.getOperationDeptName());
        helper.skipNewsDetail(item.getId());
    }

    class NewsListViewHolder extends BaseViewHolder{
        private RelativeLayout rly;
        private TextView mTitleTv;
        private TextView mKindTv;
        private TextView mTimeTv;
        private TextView mDepartmentTv;

        public NewsListViewHolder(View itemView) {
            super(itemView);
            rly= (RelativeLayout) itemView.findViewById(R.id.item_news_withoutimg);
            mTitleTv= (TextView) itemView.findViewById(R.id.item_news_title);
            mKindTv= (TextView) itemView.findViewById(R.id.item_news_kind);
            mTimeTv= (TextView) itemView.findViewById(R.id.item_news_time);
            mDepartmentTv= (TextView) itemView.findViewById(R.id.item_news_department);
        }

        public void skipNewsDetail(final int id){
            rly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent();
                    intent.setClass(mContext,NewsDetailActivity.class);
                    intent.putExtra(NewsDetailActivity.NEWS_ID,id);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}

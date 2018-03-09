package com.hnsi.oa.hnsi_oa.news.presenter;

import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.beans.MessageListResponseEntity;
import com.hnsi.oa.hnsi_oa.beans.RuleListResponseEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.widgets.RecyclerFragment.BasePresenter;
import com.hnsi.oa.hnsi_oa.widgets.RecyclerFragment.BaseRecyclerFragment;

/**
 * Created by Zheng on 2018/1/29.
 */

public class RuleListPresenter implements BasePresenter {

    private BaseRecyclerFragment mView;

    public RuleListPresenter(BaseRecyclerFragment view){
        mView= view;
    }

    public void dettachView(){
        mView= null;
    }

    @Override
    public void refreshData() {
        MyApplication.getInstance().getRuleList(1, new OnRequestDataListener<RuleListResponseEntity>() {
            @Override
            public void onSuccessed(RuleListResponseEntity ruleListResponseEntity) {
                if (mView== null) return;
                mView.refreshData(ruleListResponseEntity.getList(), ruleListResponseEntity.getTotalPage());
            }

            @Override
            public void onFailed(String throwable) {
                if (mView== null) return;
                mView.dataLoadFailed(throwable);
            }
        });
    }

    @Override
    public void loadMoreData(int page) {
        MyApplication.getInstance().getRuleList(page, new OnRequestDataListener<RuleListResponseEntity>() {
            @Override
            public void onSuccessed(RuleListResponseEntity ruleListResponseEntity) {
                if (mView== null) return;
                mView.loadMoreData(ruleListResponseEntity.getList());
            }

            @Override
            public void onFailed(String throwable) {
                if (mView== null) return;
                mView.dataLoadFailed(throwable);
            }
        });
    }
}

package com.hnsi.oa.hnsi_oa.application.news.presenter;

import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import com.hnsi.oa.hnsi_oa.application.beans.RuleListResponseEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;
import library.widgets.RecyclerFragment.BaseRecyclerPresenter;
import library.widgets.RecyclerFragment.BaseRecyclerFragment;

/**
 * Created by Zheng on 2018/1/29.
 */

public class RuleListPresenter implements BaseRecyclerPresenter {

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

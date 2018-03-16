package com.hnsi.oa.hnsi_oa.application.news.presenter;

import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import com.hnsi.oa.hnsi_oa.application.beans.MessageListResponseEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;
import library.widgets.RecyclerFragment.BaseRecyclerPresenter;
import library.widgets.RecyclerFragment.BaseRecyclerFragment;

/**
 * Created by Zheng on 2018/1/29.
 */

public class MessageListPresenter implements BaseRecyclerPresenter {

    private BaseRecyclerFragment mView;

    public MessageListPresenter(BaseRecyclerFragment view){
        mView= view;
    }

    public void dettachView(){
        mView= null;
    }

    @Override
    public void refreshData() {
        MyApplication.getInstance().getMessageList(1, new OnRequestDataListener<MessageListResponseEntity>() {
            @Override
            public void onSuccessed(MessageListResponseEntity messageListResponseEntity) {
                if (mView== null) return;
                mView.refreshData(messageListResponseEntity.getList(), messageListResponseEntity.getTotalPage());
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
        MyApplication.getInstance().getMessageList(page, new OnRequestDataListener<MessageListResponseEntity>() {
            @Override
            public void onSuccessed(MessageListResponseEntity messageListResponseEntity) {
                if (mView== null) return;
                mView.loadMoreData(messageListResponseEntity.getList());
            }

            @Override
            public void onFailed(String throwable) {
                if (mView== null) return;
                mView.dataLoadFailed(throwable);
            }
        });
    }
}

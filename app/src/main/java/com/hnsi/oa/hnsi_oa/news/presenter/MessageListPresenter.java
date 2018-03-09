package com.hnsi.oa.hnsi_oa.news.presenter;

import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.beans.MessageListResponseEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.widgets.RecyclerFragment.BasePresenter;
import com.hnsi.oa.hnsi_oa.widgets.RecyclerFragment.BaseRecyclerFragment;

/**
 * Created by Zheng on 2018/1/29.
 */

public class MessageListPresenter implements BasePresenter {

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

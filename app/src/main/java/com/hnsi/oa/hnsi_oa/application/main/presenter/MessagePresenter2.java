package com.hnsi.oa.hnsi_oa.application.main.presenter;

import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.application.main.model.IMessageModel;
import com.hnsi.oa.hnsi_oa.application.main.model.IMessageModelImpl;
import com.hnsi.oa.hnsi_oa.application.main.widget.MessageFragment;

import java.util.List;

import library.apps.BasePresenter;

/**
 * Created by Zheng on 2017/10/26.
 */

public class MessagePresenter2 extends BasePresenter<MessageFragment> {

    private IMessageModel mModel;

    public MessagePresenter2(){
        mModel= new IMessageModelImpl();
    }

    public void loadData(){
        getView().showProgressBar();
        mModel.requestData(1, 0, 1, new OnRequestDataListener<List<NewsEntity>>() {

            @Override
            public void onSuccessed(List<NewsEntity> newsEntities) {
                if (isViewAttached()) {
                    getView().setData(newsEntities);
                    getView().dataLoaded();
                    getView().dismissProgressBar();
                }
            }

            @Override
            public void onFailed(String throwable) {
                if (isViewAttached()) {
                    getView().dismissProgressBar();
                    getView().showToast(throwable);
                }
            }
        });
        mModel.requestData(1, 0, 2, new OnRequestDataListener<List<NewsEntity>>() {

            @Override
            public void onSuccessed(List<NewsEntity> newsEntities) {
                if (isViewAttached()) {
                    getView().setData(newsEntities);
                    getView().dataLoaded();
                    getView().dismissProgressBar();
                }
            }

            @Override
            public void onFailed(String throwable) {
                if (isViewAttached()) {
                    getView().dismissProgressBar();
                    getView().showToast(throwable);
                }
            }
        });
    }
}

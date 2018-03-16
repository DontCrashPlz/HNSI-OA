package com.hnsi.oa.hnsi_oa.application.main.presenter;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.application.beans.NewsListEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.application.main.model.IMessageModel;
import com.hnsi.oa.hnsi_oa.application.main.model.IMessageModelImpl;
import com.hnsi.oa.hnsi_oa.application.main.view.IMessageView;

import java.util.List;

/**
 * Created by Zheng on 2017/10/26.
 */

public class MessagePresenter {
    private IMessageView mView;
    private IMessageModel mModel;

    public MessagePresenter(IMessageView view){
        mView= view;
        mModel= new IMessageModelImpl();
    }

    public void dettachView(){
        mView= null;
    }

    public void loadData(){
        mView.showProgressBar();
        mModel.requestData(1, 0, 1, new OnRequestDataListener<List<NewsEntity>>() {

            @Override
            public void onSuccessed(List<NewsEntity> newsEntities) {
                if (mView== null) return;
                mView.setData(newsEntities);
                mView.dataLoaded();
                mView.dismissProgressBar();
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onFailed(String throwable) {
                if (mView== null) return;
                mView.dismissProgressBar();
                Toast.makeText(mView.getFragmentContext(),throwable,Toast.LENGTH_SHORT).show();
            }
        });
        mModel.requestData(1, 0, 2, new OnRequestDataListener<List<NewsEntity>>() {

            @Override
            public void onSuccessed(List<NewsEntity> newsEntities) {
                if (mView== null) return;
                mView.setData(newsEntities);
                mView.dataLoaded();
                mView.dismissProgressBar();
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onFailed(String throwable) {
                if (mView== null) return;
                mView.dismissProgressBar();
                Toast.makeText(mView.getFragmentContext(),throwable,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.hnsi.oa.hnsi_oa.main.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsListEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.main.model.IMessageModel;
import com.hnsi.oa.hnsi_oa.main.model.IMessageModelImpl;
import com.hnsi.oa.hnsi_oa.main.view.IMessageView;

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

    public void loadData(){
        mView.showProgressBar();
        mModel.requestData(1, 0, 1, new OnRequestDataListener<List<NewsEntity>>() {

            @Override
            public void onSuccessed(List<NewsEntity> newsEntities) {
                mView.setData(newsEntities);
                mView.dataLoaded();
                mView.dismissProgressBar();
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onFailed(String throwable) {
                mView.dismissProgressBar();
                Toast.makeText(mView.getFragmentContext(),throwable,Toast.LENGTH_SHORT).show();
            }
        });
        mModel.requestData(1, 0, 2, new OnRequestDataListener<List<NewsEntity>>() {

            @Override
            public void onSuccessed(List<NewsEntity> newsEntities) {
                mView.setData(newsEntities);
                mView.dataLoaded();
                mView.dismissProgressBar();
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onFailed(String throwable) {
                mView.dismissProgressBar();
                Toast.makeText(mView.getFragmentContext(),throwable,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

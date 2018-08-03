package com.hnsi.oa.hnsi_oa.application.news.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.application.beans.NewsDetailEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.application.news.model.INewsModel;
import com.hnsi.oa.hnsi_oa.application.news.model.INewsModelImpl;
import com.hnsi.oa.hnsi_oa.application.news.view.INewsView;


/**
 * Created by Zheng on 2017/11/3.
 */

public class NewsDetailPresenter {
    private INewsView mView;
    private INewsModel mModel;

    public NewsDetailPresenter(INewsView view){
        mView= view;
        mModel= new INewsModelImpl();
    }

    public void dettachView(){
        mView= null;
    }

    public void loadData(final int id){
        mView.showProgressBar();
        mModel.requestData(id, new OnRequestDataListener<NewsDetailEntity>() {

            @Override
            public void onSuccessed(NewsDetailEntity newsDetailEntity) {
                if (mView== null) return;
                Log.e("newsDetail", newsDetailEntity.toString());
                mView.loadData(newsDetailEntity);
                mView.dismissProgressBar();
            }

            @Override
            public void onFailed(String throwable) {
                if (mView== null) return;
                mView.dismissProgressBar();
                Toast.makeText((Context) mView,throwable,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.hnsi.oa.hnsi_oa.news.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.beans.NewsDetailEntity;
import com.hnsi.oa.hnsi_oa.database.ReadedNewsTableHelper;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.news.model.INewsModel;
import com.hnsi.oa.hnsi_oa.news.model.INewsModelImpl;
import com.hnsi.oa.hnsi_oa.news.view.INewsView;


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

            @SuppressLint("WrongConstant")
            @Override
            public void onFailed(String throwable) {
                if (mView== null) return;
                mView.dismissProgressBar();
                Toast.makeText((Context) mView,throwable,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.hnsi.oa.hnsi_oa.news.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataAndNumListener;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.news.model.INewsListModel;
import com.hnsi.oa.hnsi_oa.news.model.INewsListModelImpl;
import com.hnsi.oa.hnsi_oa.news.view.INewsListView;
import com.hnsi.oa.hnsi_oa.news.widget.NewsListFragment;

import java.util.List;

/**
 * Created by Zheng on 2017/11/14.
 */

public class NewsListPresenter {

    private INewsListView mView;
    private INewsListModel mModel;

    private int classId;
    private int type;

    public NewsListPresenter(INewsListView view){
        mView= view;
        mModel= new INewsListModelImpl();
        int viewTag= view.getFragmentTag();
        switch (viewTag){
            case 0://All News
                classId= 0;
                type= 2;
                break;
            case 1://Inside News
                classId= 3;
                type= 2;
                break;
            case 2://Outside News
                classId= 4;
                type= 2;
                break;
            case 3://All Notice
                classId= 0;
                type= 1;
                break;
            case 4://Company Notice
                classId= 1;
                type= 1;
                break;
            case 5://Department Notice
                classId= 2;
                type= 1;
                break;
        }
    }

    public void initData(){
        mModel.requestDataAndNum(1, classId, type, new OnRequestDataAndNumListener<List<NewsEntity>>() {
            @Override
            public void onSuccessed(List<NewsEntity> newsEntities , int pageNum) {
                mView.refreshData(newsEntities, pageNum);
            }

            @Override
            public void onFailed(String throwable) {
                mView.dataLoadFailed(throwable);
            }
        });
    }

    public void loadMoreData(int pageIndex){
        mModel.requestData(pageIndex, classId, type, new OnRequestDataListener<List<NewsEntity>>() {
            @Override
            public void onSuccessed(List<NewsEntity> newsEntities) {
                mView.loadMoreData(newsEntities);
            }

            @Override
            public void onFailed(String throwable) {
                mView.dataLoadFailed(throwable);
            }
        });
    }

}

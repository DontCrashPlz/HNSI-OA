package com.hnsi.oa.hnsi_oa.application.news.model;

import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataAndNumListener;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;

import java.util.List;

/**
 * Created by Zheng on 2017/11/14.
 */

public class INewsListModelImpl implements INewsListModel {
    @Override
    public void requestDataAndNum(int pageIndex, int classId, int type, OnRequestDataAndNumListener<List<NewsEntity>> listener) {
        MyApplication.getInstance().getNewsListAndPageNum(pageIndex, classId, type, listener);
    }

    @Override
    public void requestData(int pageIndex, int classId, int type, OnRequestDataListener<List<NewsEntity>> listener) {
        MyApplication.getInstance().getNewsList(pageIndex, classId, type, listener);
    }
}

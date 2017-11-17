package com.hnsi.oa.hnsi_oa.news.model;

import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.beans.NewsDetailEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;

/**
 * Created by Zheng on 2017/11/3.
 */

public class INewsModelImpl implements INewsModel {
    @Override
    public void requestData(int id, OnRequestDataListener<NewsDetailEntity> listener) {
        MyApplication.getInstance().getNewsDetail(id, listener);
    }
}

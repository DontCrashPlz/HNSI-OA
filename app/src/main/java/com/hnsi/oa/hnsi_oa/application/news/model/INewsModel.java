package com.hnsi.oa.hnsi_oa.application.news.model;

import com.hnsi.oa.hnsi_oa.application.beans.NewsDetailEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;

/**
 * Created by Zheng on 2017/11/3.
 */

public interface INewsModel {
    void requestData(int id, OnRequestDataListener<NewsDetailEntity> listener);
}

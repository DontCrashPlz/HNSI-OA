package com.hnsi.oa.hnsi_oa.news.model;

import com.hnsi.oa.hnsi_oa.beans.NewsDetailEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;

/**
 * Created by Zheng on 2017/11/3.
 */

public interface INewsModel {
    void requestData(int id, OnRequestDataListener<NewsDetailEntity> listener);
}

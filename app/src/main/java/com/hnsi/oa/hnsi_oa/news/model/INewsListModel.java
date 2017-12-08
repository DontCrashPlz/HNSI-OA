package com.hnsi.oa.hnsi_oa.news.model;

import com.hnsi.oa.hnsi_oa.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataAndNumListener;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;

import java.util.List;

/**
 * Created by Zheng on 2017/11/14.
 */

public interface INewsListModel {
    void requestData(int pageIndex, int classId, int type, OnRequestDataAndNumListener<List<NewsEntity>> listener);
}

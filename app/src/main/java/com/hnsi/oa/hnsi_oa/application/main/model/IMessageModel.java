package com.hnsi.oa.hnsi_oa.application.main.model;

import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.application.beans.NewsListEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;

import java.util.List;

/**
 * Created by Zheng on 2017/10/26.
 */

public interface IMessageModel {
    void requestData(int pageIndex, int classId, int type, OnRequestDataListener<List<NewsEntity>> listener);
}

package com.hnsi.oa.hnsi_oa.main.model;

import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.beans.NewsListEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;

import java.util.List;

/**
 * Created by Zheng on 2017/10/26.
 */

public class IMessageModelImpl implements IMessageModel {

    @Override
    public void requestData(int pageIndex, int classId, int type, OnRequestDataListener<List<NewsEntity>> listener) {
        MyApplication.getInstance().getNewsList(pageIndex, classId, type, listener);
    }

}

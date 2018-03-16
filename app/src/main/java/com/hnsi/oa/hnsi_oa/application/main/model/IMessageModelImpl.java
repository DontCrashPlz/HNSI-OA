package com.hnsi.oa.hnsi_oa.application.main.model;

import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;

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

package com.hnsi.oa.hnsi_oa.application.main.view;

import android.content.Context;

import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;

import java.util.List;

import library.apps.IShowDataView;

/**
 * Created by Zheng on 2017/10/26.
 */

public interface IMessageView extends IShowDataView {
    void setData(List<NewsEntity> list);
    void dataLoaded();
}

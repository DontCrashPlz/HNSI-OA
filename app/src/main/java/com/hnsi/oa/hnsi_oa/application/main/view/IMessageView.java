package com.hnsi.oa.hnsi_oa.application.main.view;

import android.content.Context;

import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;

import java.util.List;

/**
 * Created by Zheng on 2017/10/26.
 */

public interface IMessageView {
    void showProgressBar();
    void dismissProgressBar();
    void setData(List<NewsEntity> list);
    void dataLoaded();
    Context getFragmentContext();
}

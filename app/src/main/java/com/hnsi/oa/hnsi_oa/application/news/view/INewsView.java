package com.hnsi.oa.hnsi_oa.application.news.view;

import com.hnsi.oa.hnsi_oa.application.beans.NewsDetailEntity;

/**
 * Created by Zheng on 2017/11/3.
 */

public interface INewsView {
    void showProgressBar();
    void dismissProgressBar();
    void loadData(NewsDetailEntity entity);
}

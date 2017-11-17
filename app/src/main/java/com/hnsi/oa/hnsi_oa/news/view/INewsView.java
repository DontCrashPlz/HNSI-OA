package com.hnsi.oa.hnsi_oa.news.view;

import com.hnsi.oa.hnsi_oa.beans.NewsDetailEntity;

/**
 * Created by Zheng on 2017/11/3.
 */

public interface INewsView {
    void showProgressBar();
    void dismissProgressBar();
    void loadData(NewsDetailEntity entity);
}

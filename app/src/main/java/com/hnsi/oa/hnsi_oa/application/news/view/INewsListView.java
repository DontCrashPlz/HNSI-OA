package com.hnsi.oa.hnsi_oa.application.news.view;

import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;

import java.util.List;

/**
 * Created by Zheng on 2017/11/14.
 */

public interface INewsListView {
    void showProgressBar();
    void dismissProgressBar();
    void refreshData(List<NewsEntity> newsEntities, int pageNum);
    void loadMoreData(List<NewsEntity> newsEntities);
    void dataLoadFailed(String msg);
    void dismissEmptyTip();
    int getFragmentTag();
}

package com.hnsi.oa.hnsi_oa.news.view;

import com.hnsi.oa.hnsi_oa.beans.NewsEntity;

import java.util.List;

/**
 * Created by Zheng on 2017/11/14.
 */

public interface INewsListView {
    void showProgressBar();
    void dismissProgressBar();
    void refreshData(List<NewsEntity> newsEntities, int pageNum);
    void loadMoreData(List<NewsEntity> newsEntities);
    void dismissEmptyTip();
    int getFragmentTag();
    void dataLoaded();
    void refreshGone();
}

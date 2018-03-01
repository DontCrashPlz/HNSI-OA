package com.hnsi.oa.hnsi_oa.interfaces;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/3/1.
 */

public interface IQuickRecyclerBaseView<T> extends IBaseView {
    /**
     * add refresh data to QuickRecyclerView
     * @param refreshList
     */
    void refreshData(ArrayList<T> refreshList);

    /**
     * add load more data to QuickRecyclerView
     * @param loadMoreList
     */
    void loadMoreData(ArrayList<T> loadMoreList);

    /**
     * net server do not have valid data, show the empty view at QuickRecyclerView.
     */
    void showEmptyTip();

    /**
     * all the net data was loaded completed, set the QuickRecyclerView can not load more.
     */
    void noMoreData();

    /**
     * loaded data from net server successful already
     * This method only using for LazyloadFragment to setting the Loaded Tag.
     */
    void dataLoaded();
}

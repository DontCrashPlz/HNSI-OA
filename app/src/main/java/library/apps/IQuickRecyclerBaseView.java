package library.apps;

import java.util.ArrayList;

/**
 * 加载更多的View的公共接口
 * Created by Zheng on 2018/3/1.
 */

public interface IQuickRecyclerBaseView<T> extends IShowDataView {
    /**
     * add refresh data to QuickRecyclerView
     * @param refreshList
     * @param maxPageIndex
     */
    void refreshData(ArrayList<T> refreshList, int maxPageIndex);

    /**
     * add load more data to QuickRecyclerView
     * @param addList
     */
    void addData(ArrayList<T> addList);

    /**
     * @param errorMsg
     */
    void loadFailed(String errorMsg);

    /**
     * all the net data was loaded completed, set the QuickRecyclerView can not load more.
     */
    void noMoreData();

}

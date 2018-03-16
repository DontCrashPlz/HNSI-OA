package library.widgets.RecyclerFragment;

/**
 * Created by Zheng on 2018/1/8.
 */

public interface BaseRecyclerPresenter {
    void refreshData();
    void loadMoreData(int page);
}

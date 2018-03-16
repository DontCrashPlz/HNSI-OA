package library.widgets.RecyclerFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hnsi.oa.hnsi_oa.R;

import java.util.ArrayList;

import library.apps.IQuickRecyclerBaseView;
import library.widgets.LazyLoadFragment;

/**
 * Created by Zheng on 2018/1/8.
 */

public class BaseRecyclerFragment2<T> extends LazyLoadFragment implements
        IQuickRecyclerBaseView<T>,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener{

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private boolean isPrepared;
    private boolean isLoadedOnce;

    private int mPageIndex;
    private int mMaxPageIndex;

    private BaseQuickAdapter mAdapter;
    private RecyclerView.ItemDecoration mItemDecoration;

    protected BaseRecyclerPresenter mPresenter;

    /** 是否需要懒加载 */
    private boolean isLazyLoad;

    /**
     * 构造方法中传入adapter和分割线
     * @param adapter 决定视图样式
     * @param itemDecoration 决定列表分割线样式
     */
    public BaseRecyclerFragment2(BaseQuickAdapter adapter, RecyclerView.ItemDecoration itemDecoration){
        this(false, adapter, itemDecoration);
    }

    /**
     * 构造方法中传入adapter和分割线
     * @param isLazyLoad 决定这个Fragment是否需要懒加载，默认不需要懒加载
     * @param adapter 决定视图样式
     * @param itemDecoration 决定列表分割线样式
     */
    public BaseRecyclerFragment2(boolean isLazyLoad, BaseQuickAdapter adapter, RecyclerView.ItemDecoration itemDecoration){
        this.isLazyLoad= isLazyLoad;
        mAdapter= adapter;
        mItemDecoration= itemDecoration;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView= inflater.inflate(R.layout.fragment_news_list, container, false);
        mRefreshLayout= (SwipeRefreshLayout) mView.findViewById(R.id.swiperefresh);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorPrimary));
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView= (RecyclerView) mView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setOnLoadMoreListener(this,mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(mItemDecoration);

        mProgressBar= (ProgressBar) mView.findViewById(R.id.progressBar);
//        mEmptyTip= (TextView) mView.findViewById(R.id.empty_tip);

        isPrepared= true;

        if (isLazyLoad){//如果这是个懒加载Fragment，在当前可视的情况下调用lazyLoad方法
            if (isVisible) lazyLoad();
        }else {//如果这不是一个懒加载Fragment，直接调用onRefresh方法
            onRefresh();
        }


        return mView;
    }

    @Override
    public void lazyLoad() {

        Log.e("test", "run");
        Log.e("isLoadedOnce", ""+isLoadedOnce);
        Log.e("isPrepared", ""+isPrepared);
        Log.e("isVisible", ""+isVisible);

        if (isLoadedOnce || !isPrepared || !isVisible)
            return;

        onRefresh();
    }

    public void setPresenter(BaseRecyclerPresenter presenter){
        mPresenter= presenter;
    }

    @Override
    public void showProgressBar() {
        if (mProgressBar!= null && mProgressBar.getVisibility()== View.GONE) mProgressBar.setVisibility(View.VISIBLE);
    }

    public void dismissProgressBar() {
        if (mProgressBar!= null && mProgressBar.getVisibility()== View.VISIBLE) mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyTip() {
        if (mRecyclerView!= null && mAdapter!= null) mAdapter.setEmptyView(R.layout.layout_empty);
    }

    @Override
    public void showErrorTip(String errorMsg) {
        if (mRecyclerView!= null && mAdapter!= null) {
            mAdapter.setEmptyView(R.layout.layout_error);
            ((TextView)(mAdapter.getEmptyView().findViewById(R.id.textView))).setText(errorMsg);
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mRefreshLayout.setEnabled(false);
        mAdapter.setEnableLoadMore(false);
        mPageIndex= 1;
        mPresenter.refreshData();
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        mPageIndex+= 1;
        if (mPageIndex> mMaxPageIndex){
            noMoreData();
            return;
        }
        mRefreshLayout.setEnabled(false);
        mAdapter.setEnableLoadMore(false);
        mPresenter.loadMoreData(mPageIndex);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getRealContext() {
        return getContext();
    }

    @Override
    public void refreshData(ArrayList<T> refreshList, int maxPageIndex) {
        dismissProgressBar();

        if (mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
        }

        mRefreshLayout.setEnabled(true);
        mAdapter.setEnableLoadMore(true);

        if (refreshList== null || maxPageIndex== 0) {
            showErrorTip("刷新数据无效！");
            return;
        }

        if (refreshList.size()== 0){
            showEmptyTip();
        }else {
            mAdapter.setNewData(refreshList);
            mMaxPageIndex= maxPageIndex;
        }

        isLoadedOnce= true;
    }

    @Override
    public void addData(ArrayList<T> addList) {
        mRefreshLayout.setEnabled(true);
        mAdapter.setEnableLoadMore(true);

        if (addList== null){
            showToast("加载数据无效！");
            mAdapter.loadMoreComplete();
            return;
        }

        mAdapter.addData(addList);
        mAdapter.loadMoreComplete();
        showToast("加载了" + addList.size() + "条数据");
    }

    @Override
    public void loadFailed(String errorMsg) {
        mRefreshLayout.setEnabled(true);
        mAdapter.setEnableLoadMore(true);
        showToast(errorMsg);
    }

    @Override
    public void noMoreData() {
        mAdapter.loadMoreEnd();
    }
}

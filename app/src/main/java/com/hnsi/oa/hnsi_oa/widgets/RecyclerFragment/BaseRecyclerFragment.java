package com.hnsi.oa.hnsi_oa.widgets.RecyclerFragment;

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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.widgets.LazyLoadFragment;

import java.util.List;

/**
 * Created by Zheng on 2018/1/8.
 */

public class BaseRecyclerFragment<T> extends LazyLoadFragment implements
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

    protected BasePresenter mPresenter;

    /** 是否需要懒加载 */
    private boolean isLazyLoad;

    /**
     * 构造方法中传入adapter和分割线
     * @param adapter 决定视图样式
     * @param itemDecoration 决定列表分割线样式
     */
    public BaseRecyclerFragment(BaseQuickAdapter adapter, RecyclerView.ItemDecoration itemDecoration){
        this(false, adapter, itemDecoration);
    }

    /**
     * 构造方法中传入adapter和分割线
     * @param isLazyLoad 决定这个Fragment是否需要懒加载，默认不需要懒加载
     * @param adapter 决定视图样式
     * @param itemDecoration 决定列表分割线样式
     */
    public BaseRecyclerFragment(boolean isLazyLoad, BaseQuickAdapter adapter, RecyclerView.ItemDecoration itemDecoration){
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

    public void setPresenter(BasePresenter presenter){
        mPresenter= presenter;
    }

    public void dismissProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * 页面处理refresh的数据   call this method when refresh request successed.
     * @param entities
     * @param pageNum
     */
    public void refreshData(List<T> entities, int pageNum) {

        if (mProgressBar.getVisibility()== View.VISIBLE) dismissProgressBar();

        if (mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
        }

        mRefreshLayout.setEnabled(true);
        mAdapter.setEnableLoadMore(true);

        if (entities.size()== 0){
            mAdapter.setEmptyView(R.layout.layout_empty);
        }else {
            mAdapter.setNewData(entities);
            mMaxPageIndex= pageNum;
        }

        isLoadedOnce= true;
    }

    /**
     * 页面处理loadMore的数据   call this method when loadMore request successed.
     * @param entities
     */
    public void loadMoreData(List<T> entities) {

        mRefreshLayout.setEnabled(true);
        mAdapter.setEnableLoadMore(true);

        mAdapter.addData(entities);
        mAdapter.loadMoreComplete();
    }

    /**
     * 页面处理数据加载失败   call this method when net request failed.
     * @param msg
     */
    public void dataLoadFailed(String msg) {
        mRefreshLayout.setEnabled(true);
        mAdapter.setEnableLoadMore(true);
        Toast.makeText( this.getActivity(), msg, Toast.LENGTH_SHORT).show();
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
            mAdapter.loadMoreEnd();
            return;
        }
        mRefreshLayout.setEnabled(false);
        mAdapter.setEnableLoadMore(false);
        mPresenter.loadMoreData(mPageIndex);
    }
}

package com.hnsi.oa.hnsi_oa.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.news.presenter.NewsListPresenter;
import com.hnsi.oa.hnsi_oa.news.view.INewsListView;
import com.hnsi.oa.hnsi_oa.utils.DensityUtil;
import com.hnsi.oa.hnsi_oa.widgets.LazyLoadFragment;
import com.hnsi.oa.hnsi_oa.widgets.MyNewsItemDecoration;
import com.hnsi.oa.hnsi_oa.widgets.MyNewsListAdapter;

import java.util.List;

/**
 * Created by Zheng on 2017/11/13.
 */

public class NewsListFragment extends LazyLoadFragment implements
        INewsListView,
        BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener{

    public static final String FRAGMENT_TAG="fragment_tag";

    private boolean isPrepared;
    private boolean isLoadedOnce;

    public static final int FRAGMENT_ALL_NEWS=0;
    public static final int FRAGMENT_INSIDE_NEWS=1;
    public static final int FRAGMENT_OUTSIDE_NEWS=2;
    public static final int FRAGMENT_ALL_NOTICE=3;
    public static final int FRAGMENT_CONPANY_NOTICE=4;
    public static final int FRAGMENT_DEPARTMENT_NOTICE=5;

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mEmptyTip;

    private NewsListPresenter mPresenter;

    private MyNewsListAdapter mAdapter;
    private MyNewsItemDecoration mDecoration;

    private int mPageIndex;

    public static NewsListFragment getInstance(int tag){
        Bundle arg= new Bundle();
        arg.putInt(FRAGMENT_TAG,tag);
        NewsListFragment fragment= new NewsListFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView= inflater.inflate(R.layout.fragment_news_list,container,false);

        findViews(mView);

        mPresenter= new NewsListPresenter(this);

        isPrepared= true;

        lazyLoad();

        return mView;
    }

    private void findViews(View mView) {
        mRefreshLayout= (SwipeRefreshLayout) mView.findViewById(R.id.swiperefresh);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorPrimary));
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView= (RecyclerView) mView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter= new MyNewsListAdapter(R.layout.layout_news);
        mAdapter.setOnLoadMoreListener(this,mRecyclerView);
        mDecoration= new MyNewsItemDecoration(DensityUtil.dp2px(getContext(),14));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(mDecoration);

        mProgressBar= (ProgressBar) mView.findViewById(R.id.progressBar);
        mEmptyTip= (TextView) mView.findViewById(R.id.empty_tip);
    }

    @Override
    public void lazyLoad() {
        if (isLoadedOnce || !isPrepared || !isVisible)
            return;

        mPresenter.initData();
    }

    /**---------------------------INewsListView Interface-----------------------------*/
    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void refreshData(List<NewsEntity> newsEntities) {
        mAdapter.getData().clear();
        mAdapter.addData(newsEntities);
    }

    @Override
    public void loadMoreData(List<NewsEntity> newsEntities) {
        mAdapter.addData(newsEntities);
        mAdapter.loadMoreComplete();
        mRefreshLayout.setEnabled(true);
    }

    @Override
    public void showEmptyTip() {
        mEmptyTip.setVisibility(View.VISIBLE);
        mRefreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void dismissEmptyTip() {
        mEmptyTip.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public int getFragmentTag() {
        return getArguments().getInt(FRAGMENT_TAG);
    }

    @Override
    public void dataLoaded() {
        isLoadedOnce= true;
    }

    @Override
    public void refreshGone() {
        if (mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
            mRefreshLayout.setEnabled(true);
        }
    }


    /**---------------------------RequestLoadMoreListener Interface-----------------------------*/
    @Override
    public void onLoadMoreRequested() {
        mRefreshLayout.setEnabled(false);
        mAdapter.setEnableLoadMore(true);
        mPageIndex+= 1;
        mPresenter.loadMoreData(mPageIndex);

    }

    /**---------------------------OnRefreshListener Interface-----------------------------*/
    @Override
    public void onRefresh() {
        mRefreshLayout.setEnabled(false);
        mPageIndex= 1;
        mPresenter.initData();
    }
}

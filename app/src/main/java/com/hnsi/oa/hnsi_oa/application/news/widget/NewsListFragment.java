package com.hnsi.oa.hnsi_oa.application.news.widget;

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
import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.application.news.presenter.NewsListPresenter;
import com.hnsi.oa.hnsi_oa.application.news.view.INewsListView;
import library.widgets.LazyLoadFragment;
import com.hnsi.oa.hnsi_oa.application.widgets.MyNewsItemDecoration;
import com.hnsi.oa.hnsi_oa.application.adapters.MyNewsListAdapter;

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

//    public static final int FRAGMENT_ALL_NEWS=0;
//    public static final int FRAGMENT_INSIDE_NEWS=1;
//    public static final int FRAGMENT_OUTSIDE_NEWS=2;
//    public static final int FRAGMENT_ALL_NOTICE=3;
//    public static final int FRAGMENT_CONPANY_NOTICE=4;
//    public static final int FRAGMENT_DEPARTMENT_NOTICE=5;

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mEmptyTip;

    private NewsListPresenter mPresenter;

    private MyNewsListAdapter mAdapter;
    private MyNewsItemDecoration mDecoration;

    private int mPageIndex;
    private int mMaxPageIndex;

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

        if (isVisible) lazyLoad();

        return mView;
    }

    private void findViews(View mView) {
        mRefreshLayout= (SwipeRefreshLayout) mView.findViewById(R.id.swiperefresh);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark, null),
                getResources().getColor(R.color.colorPrimary, null));
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView= (RecyclerView) mView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter= new MyNewsListAdapter(R.layout.layout_news);
        mAdapter.setOnLoadMoreListener(this,mRecyclerView);
        mDecoration= new MyNewsItemDecoration();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(mDecoration);

        mProgressBar= (ProgressBar) mView.findViewById(R.id.progressBar);
        mEmptyTip= (TextView) mView.findViewById(R.id.empty_tip);

        showProgressBar();
    }

    @Override
    public void lazyLoad() {
        if (isLoadedOnce || !isPrepared || !isVisible)
            return;

        onRefresh();
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
    public void refreshData(List<NewsEntity> newsEntities, int pageNum) {

        if (mProgressBar.getVisibility()== View.VISIBLE) dismissProgressBar();

        if (mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
        }

        mRefreshLayout.setEnabled(true);
        mAdapter.setEnableLoadMore(true);

        if (newsEntities.size()== 0){
            mAdapter.setEmptyView(R.layout.layout_empty);
        }else {
            mAdapter.setNewData(newsEntities);
            mMaxPageIndex= pageNum;

            Log.e("mMaxPageIndex",""+mMaxPageIndex);
        }

        isLoadedOnce= true;
    }

    @Override
    public void loadMoreData(List<NewsEntity> newsEntities) {

        mRefreshLayout.setEnabled(true);
        mAdapter.setEnableLoadMore(true);

        mAdapter.addData(newsEntities);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void dataLoadFailed(String msg) {
        mRefreshLayout.setEnabled(true);
        mAdapter.setEnableLoadMore(true);
        Toast.makeText( this.getActivity(), msg, Toast.LENGTH_SHORT).show();
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


    /**---------------------------RequestLoadMoreListener Interface-----------------------------*/
    @Override
    public void onLoadMoreRequested() {
        mPageIndex+= 1;
        Log.e("tag",""+mPageIndex);
        if (mPageIndex> mMaxPageIndex){
            mAdapter.loadMoreEnd();
        }else{
            mRefreshLayout.setEnabled(false);
            mAdapter.setEnableLoadMore(false);
            mPresenter.loadMoreData(mPageIndex);
        }
    }

    /**---------------------------OnRefreshListener Interface-----------------------------*/
    @Override
    public void onRefresh() {
        mRefreshLayout.setEnabled(false);
        mAdapter.setEnableLoadMore(false);
        mPageIndex= 1;
        mPresenter.initData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter!= null){
            mPresenter.dettachView();
        }
    }

}

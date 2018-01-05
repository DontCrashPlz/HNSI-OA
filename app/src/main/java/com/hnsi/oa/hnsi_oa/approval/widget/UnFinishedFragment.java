package com.hnsi.oa.hnsi_oa.approval.widget;

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

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.approval.presenter.UnfinishedPresenter;
import com.hnsi.oa.hnsi_oa.widgets.LazyLoadFragment;

/**
 * Created by Zheng on 2018/1/5.
 */

public class UnFinishedFragment extends LazyLoadFragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private boolean isPrepared;
    private boolean isLoadedOnce;

    private UnfinishedPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView= inflater.inflate(R.layout.fragment_news_list, container, false);

        mPresenter= new UnfinishedPresenter(this);

        mRefreshLayout= (SwipeRefreshLayout) mView.findViewById(R.id.swiperefresh);
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView= (RecyclerView) mView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mProgressBar= (ProgressBar) mView.findViewById(R.id.progressBar);

        isPrepared= true;

        if (isVisible) lazyLoad();

        return mView;
    }

    @Override
    public void lazyLoad() {

        if (!isVisible || !isPrepared || isLoadedOnce){
            return;
        }

        mPresenter.refreshData();

        ((ApprovalActivity)getActivity()).initListView(new String[]{"110", "120", "122", "119"});
    }

    public void setTextViewText(String str){
    }

    @Override
    public void onRefresh() {

    }
}

package com.hnsi.oa.hnsi_oa.main.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.main.presenter.MessagePresenter;
import com.hnsi.oa.hnsi_oa.utils.DensityUtil;
import com.hnsi.oa.hnsi_oa.widgets.LazyLoadFragment;
import com.hnsi.oa.hnsi_oa.main.view.IMessageView;
import com.hnsi.oa.hnsi_oa.widgets.MyNewsItemAdapter;
import com.hnsi.oa.hnsi_oa.widgets.MyNewsItemDecoration;

import java.util.List;

/**
 * Created by Zheng on 2017/10/24.
 */

public class MessageFragment extends LazyLoadFragment implements IMessageView {

    private View mView;

    private boolean isPrepared;

    private boolean isLoadedOnce;

    private RecyclerView mRecyclerView;

    private MyNewsItemAdapter mAdapter;

    private MyNewsItemDecoration mDecoration;

    private ProgressBar mProgressBar;

    private MessagePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView= inflater.inflate(R.layout.fragment_message,container,false);

        mPresenter= new MessagePresenter(this);

        mRecyclerView= (RecyclerView) mView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mProgressBar= (ProgressBar) mView.findViewById(R.id.progressBar);

        isPrepared= true;

        lazyLoad();

        return mView;
    }

    @Override
    public void lazyLoad() {

        if (!isPrepared || !isVisible || isLoadedOnce)
            return;

        mAdapter= new MyNewsItemAdapter(getContext());
        mDecoration= new MyNewsItemDecoration(DensityUtil.dp2px(getContext(),14));

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(mDecoration);

        mPresenter.loadData();

    }

    @SuppressLint("WrongConstant")
    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void dismissProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setData(List<NewsEntity> list) {
        mAdapter.addData(list);
    }

    @Override
    public void dataLoaded() {
        isLoadedOnce= true;
    }

    @Override
    public Context getFragmentContext() {
        return this.getContext();
    }
}

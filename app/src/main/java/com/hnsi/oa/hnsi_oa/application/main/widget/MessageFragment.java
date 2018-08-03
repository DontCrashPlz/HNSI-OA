package com.hnsi.oa.hnsi_oa.application.main.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.application.main.presenter.MessagePresenter;

import library.apps.IBaseView;
import library.widgets.LazyLoadFragment;

import com.hnsi.oa.hnsi_oa.application.main.presenter.MessagePresenter2;
import com.hnsi.oa.hnsi_oa.application.main.view.IMessageView;
import com.hnsi.oa.hnsi_oa.application.adapters.MyNewsItemAdapter;
import com.hnsi.oa.hnsi_oa.application.widgets.MyNewsItemDecoration;

import java.util.List;

/**
 * Created by Zheng on 2017/10/24.
 */

public class MessageFragment extends LazyLoadFragment implements IMessageView{

    private View mView;

    private boolean isPrepared;

    private boolean isLoadedOnce;

    private RecyclerView mRecyclerView;

    private MyNewsItemAdapter mAdapter;

    private MyNewsItemDecoration mDecoration;

    private ProgressBar mProgressBar;
    private RelativeLayout mEmptyTipView;
    private RelativeLayout mErrorTipView;

    private MessagePresenter2 mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView= inflater.inflate(R.layout.fragment_message,container,false);

        mPresenter= new MessagePresenter2();
        mPresenter.attachView(this);

        mRecyclerView= (RecyclerView) mView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getRealContext()));
        mProgressBar= (ProgressBar) mView.findViewById(R.id.progressBar);

        mEmptyTipView= (RelativeLayout) mView.findViewById(R.id.empty_layout);
        mErrorTipView= (RelativeLayout) mView.findViewById(R.id.error_layout);

        isPrepared= true;

        if (isVisible) lazyLoad();

        return mView;
    }

    @Override
    public void lazyLoad() {

        if (!isPrepared || !isVisible || isLoadedOnce)
            return;

        mAdapter= new MyNewsItemAdapter(getRealContext());
        mDecoration= new MyNewsItemDecoration();

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(mDecoration);

        mPresenter.loadData();

    }

    @Override
    public void showProgressBar() {
        if (mProgressBar!= null){
            if (!mProgressBar.isShown()){
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void dismissProgressBar() {
        if (mProgressBar!= null){
            if (mProgressBar.isShown()){
                mProgressBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showEmptyTip() {
        if (mEmptyTipView!= null){
            if (!mEmptyTipView.isShown()){
                mEmptyTipView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void showErrorTip(String errorMsg) {
        if (mErrorTipView!= null){
            TextView mErrorTip= (TextView) mErrorTipView.findViewById(R.id.textView);
            mErrorTip.setText(errorMsg);
            if (!mErrorTipView.isShown()){
                mErrorTipView.setVisibility(View.VISIBLE);
            }
        }
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
    public void onDetach() {
        super.onDetach();
        if (mPresenter!= null){
            mPresenter.detachView();
        }
    }
}

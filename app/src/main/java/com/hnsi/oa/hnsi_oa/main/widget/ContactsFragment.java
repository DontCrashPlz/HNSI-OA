package com.hnsi.oa.hnsi_oa.main.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.main.presenter.ContactPresenter;
import com.hnsi.oa.hnsi_oa.main.view.IContactView;
import com.hnsi.oa.hnsi_oa.widgets.LazyLoadFragment;

/**
 * Created by Zheng on 2017/10/24.
 */

public class ContactsFragment extends LazyLoadFragment implements IContactView {

    private View mView;

    private ContactPresenter mPresenter;

    private boolean isLoadedOnce;
    private boolean isPrepared;

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView= inflater.inflate(R.layout.fragment_contact,container,false);

        mRefreshLayout= (SwipeRefreshLayout) mView.findViewById(R.id.refreshLayout);
        mRecyclerView= (RecyclerView) mView.findViewById(R.id.recyclerview);
        mProgressBar= (ProgressBar) mView.findViewById(R.id.progressBar);

        mPresenter= new ContactPresenter(this);

        isPrepared= true;

        if (isVisible) lazyLoad();

        return mView;
    }

    @Override
    public void lazyLoad() {

        if (!isPrepared || !isVisible || isLoadedOnce)
            return;

        //尝试加载数据库中数据


        mView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.setBackgroundColor(Color.YELLOW);
                isLoadedOnce= true;
            }
        },1000);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void initData() {

    }
}

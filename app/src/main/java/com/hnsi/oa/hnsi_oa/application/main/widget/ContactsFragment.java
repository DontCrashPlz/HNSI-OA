package com.hnsi.oa.hnsi_oa.application.main.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.beans.RealDepartmentEntity;
import com.hnsi.oa.hnsi_oa.application.database.DepartmentInfoTableHelper;
import com.hnsi.oa.hnsi_oa.application.main.presenter.ContactPresenter;
import com.hnsi.oa.hnsi_oa.application.main.presenter.ContactPresenter2;
import com.hnsi.oa.hnsi_oa.application.main.view.IContactView;
import library.widgets.LazyLoadFragment;
import com.hnsi.oa.hnsi_oa.application.adapters.MyDepartmentsAdapter;
import com.hnsi.oa.hnsi_oa.application.widgets.MyDepartmentsDecoration;

import java.util.ArrayList;

/**
 * Created by Zheng on 2017/10/24.
 */

public class ContactsFragment extends LazyLoadFragment implements IContactView, SwipeRefreshLayout.OnRefreshListener {

    private View mView;

    private ContactPresenter2 mPresenter;

    private boolean isLoadedOnce;
    private boolean isPrepared;

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private DepartmentInfoTableHelper mHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView= inflater.inflate(R.layout.fragment_contact,container,false);

        mRefreshLayout= (SwipeRefreshLayout) mView.findViewById(R.id.refreshLayout);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorPrimary));
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView= (RecyclerView) mView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mProgressBar= (ProgressBar) mView.findViewById(R.id.progressBar);

        mPresenter= new ContactPresenter2(getRealContext());
        mPresenter.attachView(this);

        isPrepared= true;

        if (isVisible) lazyLoad();

        return mView;
    }

    @Override
    public void lazyLoad() {

        if (!isPrepared || !isVisible || isLoadedOnce)
            return;

       initData();

    }

    @Override
    public void refreshData() {

    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {

        //读取数据库中的数据
        mHelper= new DepartmentInfoTableHelper(getContext());
        ArrayList<RealDepartmentEntity> departmentList= mHelper.queryAllDepaetment();

        //如果读取到0条数据，请求网络数据
        if (departmentList== null || departmentList.size()== 0) {
            mPresenter.loadData();
            return;
        }

        mRecyclerView.setAdapter(new MyDepartmentsAdapter(getContext(), departmentList));
        mRecyclerView.addItemDecoration(new MyDepartmentsDecoration(10, departmentList));
        mProgressBar.setVisibility(View.GONE);

        isLoadedOnce= true;

    }

    @Override
    public void onRefresh() {
        mPresenter.loadData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter!= null){
            mPresenter.detachView();
        }
    }

}

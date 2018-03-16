package com.hnsi.oa.hnsi_oa.application.approval.presenter;

import android.util.Log;

import com.hnsi.oa.hnsi_oa.application.approval.model.IApprovalModelImpl;
import com.hnsi.oa.hnsi_oa.application.beans.FinishEntity;
import com.hnsi.oa.hnsi_oa.application.beans.FlowEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;
import library.widgets.RecyclerFragment.BaseRecyclerPresenter;
import library.widgets.RecyclerFragment.BaseRecyclerFragment;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Zheng on 2018/1/8.
 */

public class FinishPresenter implements BaseRecyclerPresenter {

    private BaseRecyclerFragment mView;
    private IApprovalModelImpl mModel;

    public FinishPresenter(BaseRecyclerFragment view){
        mView= view;
        mModel= new IApprovalModelImpl();
    }

    public void detachView(){
        mView= null;
    }

    @Override
    public void refreshData() {
        mModel.requestFinishedList(1, new OnRequestDataListener<FinishEntity>() {
            @Override
            public void onSuccessed(FinishEntity finishEntity) {

                if (mView== null) return;

                ArrayList<FlowEntity> flowList= finishEntity.getList();
                Map<String, String> urlMap= finishEntity.getUrlMap();
                for (FlowEntity entity : flowList){
                    entity.setFlowUrl(urlMap.get(entity.getProcessDefName()));
                    Log.e("entity", entity.toString());
                }

                mView.refreshData(flowList, finishEntity.getTotalPage());
            }

            @Override
            public void onFailed(String throwable) {
                if (mView== null) return;
                mView.dataLoadFailed(throwable);
            }
        });
    }

    @Override
    public void loadMoreData(int page) {
        mModel.requestFinishedList(page, new OnRequestDataListener<FinishEntity>() {
            @Override
            public void onSuccessed(FinishEntity finishEntity) {

                if (mView== null) return;

                ArrayList<FlowEntity> flowList= finishEntity.getList();
                Map<String, String> urlMap= finishEntity.getUrlMap();
                for (FlowEntity entity : flowList){
                    entity.setFlowUrl(urlMap.get(entity.getProcessDefName()));
                }

                mView.loadMoreData(flowList);
            }

            @Override
            public void onFailed(String throwable) {
                if (mView== null) return;
                mView.dataLoadFailed(throwable);
            }
        });
    }
}

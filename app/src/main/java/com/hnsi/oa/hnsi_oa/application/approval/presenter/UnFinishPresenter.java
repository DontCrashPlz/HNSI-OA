package com.hnsi.oa.hnsi_oa.application.approval.presenter;

import com.hnsi.oa.hnsi_oa.application.approval.model.IApprovalModelImpl;
import com.hnsi.oa.hnsi_oa.application.approval.widget.UnFinishedFragment;
import com.hnsi.oa.hnsi_oa.application.beans.FlowEntity;
import com.hnsi.oa.hnsi_oa.application.beans.UnFinishEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;
import library.widgets.RecyclerFragment.BaseRecyclerPresenter;
import library.widgets.RecyclerFragment.BaseRecyclerFragment;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Zheng on 2018/1/8.
 */

public class UnFinishPresenter implements BaseRecyclerPresenter {

    private BaseRecyclerFragment mView;
    private IApprovalModelImpl mModel;

    public UnFinishPresenter(BaseRecyclerFragment view){
        mView= view;
        mModel= new IApprovalModelImpl();
    }

    public void detachView(){
        mView= null;
    }

    @Override
    public void refreshData() {
        mModel.requestPaddingList(1, new OnRequestDataListener<UnFinishEntity>() {
            @Override
            public void onSuccessed(UnFinishEntity unFinishEntity) {
                if (mView== null) return;
                ArrayList<FlowEntity> flowList= unFinishEntity.getTaskList();
                Map<String, String> urlMap= unFinishEntity.getUrlMap();
                for (FlowEntity entity : flowList){
                    entity.setFlowUrl(urlMap.get(entity.getProcessDefName()));
                }
                mView.refreshData(flowList, unFinishEntity.getTotalPage());
                ((UnFinishedFragment)mView).setFlowNumList(unFinishEntity.getMenuItem());
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
        mModel.requestPaddingList(page, new OnRequestDataListener<UnFinishEntity>() {
            @Override
            public void onSuccessed(UnFinishEntity unFinishEntity) {
                if (mView== null) return;
                ArrayList<FlowEntity> flowList= unFinishEntity.getTaskList();
                Map<String, String> urlMap= unFinishEntity.getUrlMap();
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

package com.hnsi.oa.hnsi_oa.approval.presenter;

import com.hnsi.oa.hnsi_oa.approval.model.IApprovalModelImpl;
import com.hnsi.oa.hnsi_oa.approval.widget.UnFinishedFragment;
import com.hnsi.oa.hnsi_oa.beans.FinishEntity;
import com.hnsi.oa.hnsi_oa.beans.FlowEntity;
import com.hnsi.oa.hnsi_oa.beans.UnFinishEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.widgets.RecyclerFragment.BasePresenter;
import com.hnsi.oa.hnsi_oa.widgets.RecyclerFragment.BaseRecyclerFragment;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Zheng on 2018/1/8.
 */

public class UnFinishPresenter implements BasePresenter {

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

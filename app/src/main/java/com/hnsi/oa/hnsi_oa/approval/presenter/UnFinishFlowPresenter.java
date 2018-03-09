package com.hnsi.oa.hnsi_oa.approval.presenter;

import com.hnsi.oa.hnsi_oa.approval.model.IApprovalModelImpl;
import com.hnsi.oa.hnsi_oa.approval.widget.UnFinishedFragment;
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

public class UnFinishFlowPresenter implements BasePresenter {

    private BaseRecyclerFragment mView;
    private IApprovalModelImpl mModel;
    private String mProcessDefnames;

    public UnFinishFlowPresenter(BaseRecyclerFragment view, String processDefnames){
        mView= view;
        mModel= new IApprovalModelImpl();
        mProcessDefnames= processDefnames;
    }

    public void detachView(){
        mView= null;
    }

    @Override
    public void refreshData() {
        mModel.requestUnFinishedFlowList(1, mProcessDefnames, new OnRequestDataListener<UnFinishEntity>() {
            @Override
            public void onSuccessed(UnFinishEntity unFinishEntity) {
                if (mView== null) return;
                ArrayList<FlowEntity> flowList= unFinishEntity.getTaskList();
                Map<String, String> urlMap= unFinishEntity.getUrlMap();
                for (FlowEntity entity : flowList){
                    entity.setFlowUrl(urlMap.get(entity.getProcessDefName()));
                }
                mView.refreshData(flowList, unFinishEntity.getTotalPage());
//                ((UnFinishedFragment)mView).setFlowNumList(unFinishEntity.getMenuItem());
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
        mModel.requestUnFinishedFlowList(page, mProcessDefnames, new OnRequestDataListener<UnFinishEntity>() {
            @Override
            public void onSuccessed(UnFinishEntity unFinishEntity) {
                if (mView== null) return;
                mView.loadMoreData(unFinishEntity.getTaskList());
            }

            @Override
            public void onFailed(String throwable) {
                if (mView== null) return;
                mView.dataLoadFailed(throwable);
            }
        });
    }
}

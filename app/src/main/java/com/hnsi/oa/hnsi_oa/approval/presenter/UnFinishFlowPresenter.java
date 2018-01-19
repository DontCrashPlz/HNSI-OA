package com.hnsi.oa.hnsi_oa.approval.presenter;

import com.hnsi.oa.hnsi_oa.approval.model.IApprovalModelImpl;
import com.hnsi.oa.hnsi_oa.approval.widget.UnFinishedFragment;
import com.hnsi.oa.hnsi_oa.beans.UnFinishEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.widgets.RecyclerFragment.BasePresenter;
import com.hnsi.oa.hnsi_oa.widgets.RecyclerFragment.BaseRecyclerFragment;

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
    @Override
    public void refreshData() {
        mModel.requestUnFinishedFlowList(1, mProcessDefnames, new OnRequestDataListener<UnFinishEntity>() {
            @Override
            public void onSuccessed(UnFinishEntity unFinishEntity) {
                mView.refreshData(unFinishEntity.getTaskList(), unFinishEntity.getTotalPage());
//                ((UnFinishedFragment)mView).setFlowNumList(unFinishEntity.getMenuItem());
            }

            @Override
            public void onFailed(String throwable) {
                mView.dataLoadFailed(throwable);
            }
        });
    }

    @Override
    public void loadMoreData(int page) {
        mModel.requestUnFinishedFlowList(page, mProcessDefnames, new OnRequestDataListener<UnFinishEntity>() {
            @Override
            public void onSuccessed(UnFinishEntity unFinishEntity) {
                mView.loadMoreData(unFinishEntity.getTaskList());
            }

            @Override
            public void onFailed(String throwable) {
                mView.dataLoadFailed(throwable);
            }
        });
    }
}

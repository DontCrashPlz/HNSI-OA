package com.hnsi.oa.hnsi_oa.approval.presenter;

import android.util.Log;

import com.hnsi.oa.hnsi_oa.approval.model.IApprovalModelImpl;
import com.hnsi.oa.hnsi_oa.approval.widget.UnFinishedFragment;
import com.hnsi.oa.hnsi_oa.beans.UnFinishEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;

/**
 * Created by Zheng on 2018/1/5.
 */

public class UnfinishedPresenter {

    private UnFinishedFragment mView;
    private IApprovalModelImpl mModel;

    public UnfinishedPresenter(UnFinishedFragment view){
        mView= view;
        mModel= new IApprovalModelImpl();
    }

    public void refreshData(){
        mModel.requestData(1, new OnRequestDataListener<UnFinishEntity>() {
            @Override
            public void onSuccessed(UnFinishEntity unFinishEntity) {

            }

            @Override
            public void onFailed(String throwable) {

            }
        });
    }

    public void loadMoreData(int page){
        mModel.requestData(page, new OnRequestDataListener<UnFinishEntity>() {
            @Override
            public void onSuccessed(UnFinishEntity unFinishEntity) {

            }

            @Override
            public void onFailed(String throwable) {

            }
        });
    }

}

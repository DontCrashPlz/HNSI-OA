package com.hnsi.oa.hnsi_oa.application.approval.model;

import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import com.hnsi.oa.hnsi_oa.application.beans.FinishEntity;
import com.hnsi.oa.hnsi_oa.application.beans.UnFinishEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;

/**
 * Created by Zheng on 2017/10/23.
 */

public class IApprovalModelImpl {
    public void requestPaddingList(int page, OnRequestDataListener<UnFinishEntity> listener){
        MyApplication.getInstance().getPendingList(page, listener);
    }

    public void requestFinishedList(int page, OnRequestDataListener<FinishEntity> listener){
        MyApplication.getInstance().getFinishedList(page, listener);
    }

    public void requestUnFinishedFlowList(int page, String processDefnames, OnRequestDataListener<UnFinishEntity> listener){
        MyApplication.getInstance().getPendingFlowList(page, processDefnames, listener);
    }

}

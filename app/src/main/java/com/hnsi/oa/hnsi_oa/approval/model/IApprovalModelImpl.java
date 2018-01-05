package com.hnsi.oa.hnsi_oa.approval.model;

import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.beans.UnFinishEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;

/**
 * Created by Zheng on 2017/10/23.
 */

public class IApprovalModelImpl {
    public void requestData(int page, OnRequestDataListener<UnFinishEntity> listener){
        MyApplication.getInstance().getPendingList(page, listener);
    }
}

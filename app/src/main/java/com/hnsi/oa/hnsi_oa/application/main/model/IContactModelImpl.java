package com.hnsi.oa.hnsi_oa.application.main.model;

import com.hnsi.oa.hnsi_oa.application.app.MyApplication;
import com.hnsi.oa.hnsi_oa.application.beans.ContactEntity;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;

/**
 * Created by Zheng on 2017/12/29.
 */

public class IContactModelImpl {
    public void requestContacts(OnRequestDataListener<ContactEntity> listener){
        MyApplication.getInstance().getContacts(listener);
    }
}

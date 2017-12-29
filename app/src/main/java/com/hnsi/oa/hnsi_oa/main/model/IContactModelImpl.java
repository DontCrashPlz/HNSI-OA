package com.hnsi.oa.hnsi_oa.main.model;

import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.beans.ContactEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;

/**
 * Created by Zheng on 2017/12/29.
 */

public class IContactModelImpl {
    public void requestContacts(OnRequestDataListener<ContactEntity> listener){
        MyApplication.getInstance().getContacts(listener);
    }
}

package com.hnsi.oa.hnsi_oa.main.presenter;

import com.hnsi.oa.hnsi_oa.beans.ContactEntity;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.main.model.IContactModelImpl;
import com.hnsi.oa.hnsi_oa.main.view.IContactView;

/**
 * Created by Zheng on 2017/12/29.
 */

public class ContactPresenter {

    private IContactView mView;
    private IContactModelImpl mModel;

    public ContactPresenter(IContactView view){
        mView= view;
        mModel= new IContactModelImpl();
    }

    public void loadContacts(){
        mModel.requestContacts(new OnRequestDataListener<ContactEntity>() {
            @Override
            public void onSuccessed(ContactEntity contactEntity) {

            }

            @Override
            public void onFailed(String throwable) {

            }
        });
    }

}

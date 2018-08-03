package com.hnsi.oa.hnsi_oa.application.main.presenter;


import android.content.Context;

import com.hnsi.oa.hnsi_oa.application.beans.ContactEntity;
import com.hnsi.oa.hnsi_oa.application.beans.DepartmentEntity;
import com.hnsi.oa.hnsi_oa.application.beans.RealDepartmentEntity;
import com.hnsi.oa.hnsi_oa.application.database.ConstactsInfoTableHelper;
import com.hnsi.oa.hnsi_oa.application.database.DepartmentInfoTableHelper;
import com.hnsi.oa.hnsi_oa.application.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.application.main.model.IContactModelImpl;
import com.hnsi.oa.hnsi_oa.application.main.widget.ContactsFragment;

import java.util.ArrayList;
import java.util.Iterator;

import library.apps.BasePresenter;

/**
 * Created by Zheng on 2017/12/29.
 */

public class ContactPresenter2 extends BasePresenter<ContactsFragment> {

    private IContactModelImpl mModel;

    private ConstactsInfoTableHelper mConstactsHelper;
    private DepartmentInfoTableHelper mDepartmentHelper;

    public ContactPresenter2(Context context){
        mModel= new IContactModelImpl();
        mConstactsHelper= new ConstactsInfoTableHelper(context);
        mDepartmentHelper= new DepartmentInfoTableHelper(context);
    }

    public void loadData(){
        mModel.requestContacts(new OnRequestDataListener<ContactEntity>() {
            @Override
            public void onSuccessed(ContactEntity contactEntity) {

                if (!isViewAttached()) return;

                //清空数据库信息
                mConstactsHelper.deleteAllContacts();
                mDepartmentHelper.deleteAllDepartments();

                //人员信息入库
                mConstactsHelper.insertAll(contactEntity.getEmpList());

                //部门信息入库
                ArrayList<DepartmentEntity> departmentEntities= contactEntity.getOrgList();
                ArrayList<RealDepartmentEntity> parentDepartments= new ArrayList<>();
                ArrayList<RealDepartmentEntity> childDepartments= new ArrayList<>();

                for (DepartmentEntity entity : departmentEntities){
                    if (entity.getParentorgid()== 1){
                        RealDepartmentEntity r= new RealDepartmentEntity();
                        r.setOrgid(entity.getOrgid());
                        r.setOrgname(entity.getOrgname());
                        r.setParentorgid(entity.getParentorgid());
                        r.setType(RealDepartmentEntity.PARENT_DEPARTMENT);
                        r.setNum(mConstactsHelper.queryContactNumByOrgid(entity.getOrgid()));

                        parentDepartments.add(r);
                    }else if (entity.getParentorgid()> 0){
                        RealDepartmentEntity r= new RealDepartmentEntity();
                        r.setOrgid(entity.getOrgid());
                        r.setOrgname(entity.getOrgname());
                        r.setParentorgid(entity.getParentorgid());
                        r.setType(RealDepartmentEntity.CHILD_DEPARTMENT);
                        r.setNum(mConstactsHelper.queryContactNumByOrgid(entity.getOrgid()));

                        childDepartments.add(r);
                    }
                }

                ArrayList<RealDepartmentEntity> realDepartmentEntities= new ArrayList<>();
                for (RealDepartmentEntity entity : parentDepartments){
                    realDepartmentEntities.add(entity);

                    for (Iterator<RealDepartmentEntity> it= childDepartments.iterator(); it.hasNext();){
                        RealDepartmentEntity entity1= it.next();
                        if (entity1.getParentorgid() == entity.getOrgid()){
                            realDepartmentEntities.add(entity1);
                            it.remove();
                        }
                    }
                }

                mDepartmentHelper.insertAll(realDepartmentEntities);

                //通知通讯录界面加载数据
                getView().initData();
            }

            @Override
            public void onFailed(String throwable) {
                if (isViewAttached()) {
                    getView().showToast(throwable);
                }
            }
        });
    }

}

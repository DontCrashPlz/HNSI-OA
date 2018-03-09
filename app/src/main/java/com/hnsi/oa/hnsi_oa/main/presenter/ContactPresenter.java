package com.hnsi.oa.hnsi_oa.main.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.beans.ContactEntity;
import com.hnsi.oa.hnsi_oa.beans.DepartmentEntity;
import com.hnsi.oa.hnsi_oa.beans.RealDepartmentEntity;
import com.hnsi.oa.hnsi_oa.database.ConstactsInfoTableHelper;
import com.hnsi.oa.hnsi_oa.database.DepartmentInfoTableHelper;
import com.hnsi.oa.hnsi_oa.interfaces.OnRequestDataListener;
import com.hnsi.oa.hnsi_oa.main.model.IContactModelImpl;
import com.hnsi.oa.hnsi_oa.main.view.IContactView;
import com.hnsi.oa.hnsi_oa.main.widget.ContactsFragment;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Zheng on 2017/12/29.
 */

public class ContactPresenter {

    private IContactView mView;
    private IContactModelImpl mModel;

    private ConstactsInfoTableHelper mConstactsHelper;
    private DepartmentInfoTableHelper mDepartmentHelper;

    private Context mContext;

    public ContactPresenter(IContactView view){
        mView= view;
        mModel= new IContactModelImpl();

        mContext=((ContactsFragment)mView).getContext();

        mConstactsHelper= new ConstactsInfoTableHelper(mContext);
        mDepartmentHelper= new DepartmentInfoTableHelper(mContext);
    }

    public void dettachView(){
        mView= null;
    }

    public void loadData(){
        mModel.requestContacts(new OnRequestDataListener<ContactEntity>() {
            @Override
            public void onSuccessed(ContactEntity contactEntity) {

                if (mView== null) return;

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
//                    for (RealDepartmentEntity entity1 : childDepartments){
//                        if (entity1.getParentorgid() == entity.getOrgid()){
//                            realDepartmentEntities.add(entity1);
//                            childDepartments.remove(entity1);
//                        }
//                    }
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
                Log.e("contact" , "successed");
                mView.initData();
            }

            @Override
            public void onFailed(String throwable) {
                if (mView== null) return;
                Toast.makeText( mContext, throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

}

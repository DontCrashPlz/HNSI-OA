package com.hnsi.oa.hnsi_oa.application.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.approval.widget.AllListActivity;
import com.hnsi.oa.hnsi_oa.application.beans.RealDepartmentEntity;
import com.hnsi.oa.hnsi_oa.application.main.widget.DepartmentActivity;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/4.
 */

public class MyDepartmentsAdapter extends RecyclerView.Adapter<MyDepartmentsAdapter.DepartmentHolder> {

    public static final String ALL_LIST_TAG= "ALL_LIST_TAG";
    public static final String DEPARTMENT_ID= "DEPARTMENT_ID";
    public static final String DEPARTMENT_NAME= "DEPARTMENT_NAME";

    private Context mContext;
    private ArrayList<RealDepartmentEntity> mData;

    public MyDepartmentsAdapter(Context context, ArrayList<RealDepartmentEntity> departmentEntities){
        mContext= context;
        mData= departmentEntities;
    }

    @Override
    public DepartmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mParentDepView= LayoutInflater.from(mContext).inflate(R.layout.item_parent_department, parent, false);
        View mChildDepView= LayoutInflater.from(mContext).inflate(R.layout.item_child_department, parent, false);
        if (viewType== 0) {
            return new DepartmentHolder(mParentDepView);
        }else if (viewType== 1){
            return new DepartmentHolder(mChildDepView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(DepartmentHolder holder, int position) {
        final String orgName= mData.get(position).getOrgname();
        holder.mDepNameTv.setText(orgName);

        final int num= mData.get(position).getNum();
        holder.mDepNumTv.setText(String.format(mContext.getString(R.string.num_of_department), String.valueOf(num)));

        final int orgId= mData.get(position).getOrgid();
        holder.mPenalRly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num== 0){
                    Toast.makeText(mContext, "该部门暂无人员", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mContext instanceof AllListActivity){
                    ((AllListActivity)mContext).showDepartmentFragment(orgId);
                    return;
                }

                Log.e("info", orgName+" "+ num+ " "+ orgId);
                Intent intent= new Intent(mContext, DepartmentActivity.class);
                intent.putExtra(DEPARTMENT_ID, orgId);
                intent.putExtra(DEPARTMENT_NAME, orgName);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    class DepartmentHolder extends RecyclerView.ViewHolder{

        private RelativeLayout mPenalRly;
        private TextView mDepNameTv;
        private TextView mDepNumTv;

        public DepartmentHolder(View itemView) {
            super(itemView);
            mPenalRly= (RelativeLayout) itemView.findViewById(R.id.item_contacts_rly);
            mDepNameTv= (TextView) itemView.findViewById(R.id.item_contacts_tv_department);
            mDepNumTv= (TextView) itemView.findViewById(R.id.item_contacts_tv_num_of_person);
        }

    }

}

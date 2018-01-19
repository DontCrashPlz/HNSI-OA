package com.hnsi.oa.hnsi_oa.widgets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hnsi.oa.hnsi_oa.beans.ApprovalWidgetEntity;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/19.
 */

public class MyApprovalDetailAdapter extends RecyclerView.Adapter<MyApprovalDetailAdapter.ApprovalDetailViewHolder> {

    private Context mContext;
    private ArrayList<ApprovalWidgetEntity> mData;

    public MyApprovalDetailAdapter(Context context, ArrayList<ApprovalWidgetEntity> data){
        mContext= context;
        mData= data;
    }

    @Override
    public ApprovalDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ApprovalDetailViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    class ApprovalDetailViewHolder extends RecyclerView.ViewHolder{

        public ApprovalDetailViewHolder(View itemView) {
            super(itemView);
        }
    }

}

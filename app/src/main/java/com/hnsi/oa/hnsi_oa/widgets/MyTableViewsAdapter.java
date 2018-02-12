package com.hnsi.oa.hnsi_oa.widgets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.approval.widget.TableViewsActivity;
import com.hnsi.oa.hnsi_oa.beans.ApprovalWidgetEntity;
import com.hnsi.oa.hnsi_oa.beans.TableFileEntity;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/2/9.
 */

public class MyTableViewsAdapter extends RecyclerView.Adapter<MyTableViewsAdapter.TableViewViewHolder> {

    private Context mContext;
    private ApprovalWidgetEntity mDatas;
    private ArrayList<String> mTableTitle;
    private ArrayList<String> mTableData;

    public MyTableViewsAdapter(Context context, ApprovalWidgetEntity data, int tableIndex){
        mContext= context;
        mDatas= data;
        if (mDatas.getTableTitle()== null || mDatas.getTableData()== null){
            Toast.makeText(mContext, "表格数据无效，请重试！", Toast.LENGTH_SHORT).show();
            ((TableViewsActivity)mContext).finish();
            return;
        }
        mTableTitle= mDatas.getTableTitle();
        mTableData= mDatas.getTableData().get(tableIndex);
        if (mTableTitle== null || mTableData== null){
            Toast.makeText(mContext, "表格数据无效，请重试！", Toast.LENGTH_SHORT).show();
            ((TableViewsActivity)mContext).finish();
            return;
        }
        if (mTableTitle.size()!= mTableData.size()){
            Toast.makeText(mContext, "表格数据无效，请重试！", Toast.LENGTH_SHORT).show();
            ((TableViewsActivity)mContext).finish();
            return;
        }
    }

    @Override
    public MyTableViewsAdapter.TableViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_base_text_textarea_date_int, parent, false);
        return new MyTableViewsAdapter.TableViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TableViewViewHolder holder, int position) {
        holder.mLabelTv.setText(mTableTitle.get(position));
        holder.mValueTv.setText(mTableData.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.getTableTitle().size();
    }

    class TableViewViewHolder extends RecyclerView.ViewHolder{
        private TextView mLabelTv;
        private TextView mValueTv;

        public TableViewViewHolder(View itemView) {
            super(itemView);
            mLabelTv=  (TextView) itemView.findViewById(R.id.item_base_name);
            mValueTv=  (TextView) itemView.findViewById(R.id.item_base_value);
        }
    }

}

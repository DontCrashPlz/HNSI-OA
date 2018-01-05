package com.hnsi.oa.hnsi_oa.widgets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.PersonEntity;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/4.
 */

public class MyContactsAdapter extends RecyclerView.Adapter<MyContactsAdapter.ContactHolder> {

    private Context mContext;
    private ArrayList<PersonEntity> mData;

    public MyContactsAdapter(Context context, ArrayList<PersonEntity> data){
        mContext= context;
        mData= data;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView= LayoutInflater.from(mContext).inflate(R.layout.item_department_detail, parent, false);
        return new ContactHolder(mView);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        holder.mUserNameTv.setText(mData.get(position).getEmpname());
        holder.mPositionTv.setText(mData.get(position).getPosiname());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ContactHolder extends RecyclerView.ViewHolder{

        private TextView mUserNameTv;
        private TextView mPositionTv;

        public ContactHolder(View itemView) {
            super(itemView);
            mUserNameTv= (TextView) itemView.findViewById(R.id.item_department_username);
            mPositionTv= (TextView) itemView.findViewById(R.id.item_department_userposition);
        }
    }
}

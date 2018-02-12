package com.hnsi.oa.hnsi_oa.widgets;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.app.MyApplication;
import com.hnsi.oa.hnsi_oa.approval.widget.AllListActivity;
import com.hnsi.oa.hnsi_oa.beans.PersonEntity;
import com.hnsi.oa.hnsi_oa.main.widget.PersonDetailActivity;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/4.
 */

public class MyContactsAdapter extends RecyclerView.Adapter<MyContactsAdapter.ContactHolder> {

    public static final String PERSON_ID_TAG= "person_id_tag";
    public static final String PERSON_NAME_TAG= "person_name_tag";


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
    public void onBindViewHolder(ContactHolder holder, final int position) {
        holder.mHeadImgTv.setVisibility(View.VISIBLE);
        holder.mHeadImgCiv.setVisibility(View.GONE);

        if("男".equals(mData.get(position).getSex())){
            holder.mHeadImgTv.setBackgroundResource(R.drawable.circle_background_blue);
        }else if("女".equals(mData.get(position).getSex())){
            holder.mHeadImgTv.setBackgroundResource(R.drawable.circle_background_pink);
        }
        String empName= mData.get(position).getEmpname();
        holder.mHeadImgTv.setText(empName.substring(empName.length() - 1));

        String headImgUrl= mData.get(position).getHeadimg();
        if (headImgUrl!= null){
            if (!"null".equals(headImgUrl) && !"".equals(headImgUrl)){
//                headImgUrl= MyApplication.getInstance().getBaseUrl() + headImgUrl;
                headImgUrl= "http://192.168.1.68:80/" + headImgUrl;
                holder.mHeadImgTv.setVisibility(View.GONE);
                holder.mHeadImgCiv.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(headImgUrl)
                        .asBitmap()
                        .placeholder(R.mipmap.user_icon)
                        .error(R.mipmap.user_icon)
                        .into(holder.mHeadImgCiv);
            }
        }
        holder.mUserNameTv.setText(empName);
        holder.mPositionTv.setText(mData.get(position).getPosiname());

        holder.mPanelRly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mContext instanceof AllListActivity){
                    ((AllListActivity)mContext).selectComplete(mData.get(position).getEmpid(), mData.get(position).getEmpname());
                    return;
                }

                Intent intent= new Intent(mContext, PersonDetailActivity.class);
                intent.putExtra(PERSON_ID_TAG, mData.get(position).getEmpid());
                intent.putExtra(PERSON_NAME_TAG, mData.get(position).getEmpname());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ContactHolder extends RecyclerView.ViewHolder{

        private RelativeLayout mPanelRly;
        private TextView mHeadImgTv;
        private CircleImageView mHeadImgCiv;
        private TextView mUserNameTv;
        private TextView mPositionTv;

        public ContactHolder(View itemView) {
            super(itemView);
            mPanelRly= (RelativeLayout) itemView.findViewById(R.id.item_department_rly);
            mHeadImgTv= (TextView) itemView.findViewById(R.id.item_circle_textview);
            mHeadImgCiv= (CircleImageView) itemView.findViewById(R.id.item_circle_imageview);
            mUserNameTv= (TextView) itemView.findViewById(R.id.item_department_username);
            mPositionTv= (TextView) itemView.findViewById(R.id.item_department_userposition);
        }
    }
}

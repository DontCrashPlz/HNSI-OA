package com.hnsi.oa.hnsi_oa.application.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.beans.NewsEntity;
import com.hnsi.oa.hnsi_oa.application.database.ReadedNewsTableHelper;
import com.hnsi.oa.hnsi_oa.application.news.widget.NewsDetailActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Zheng on 2017/10/26.
 */

public class MyNewsItemAdapter extends RecyclerView.Adapter<MyNewsItemAdapter.NewsViewHolder> {

    private Context mContext;

    private List<NewsEntity> mData;

    private ReadedNewsTableHelper mHelper;

    public MyNewsItemAdapter(Context context) {
        mContext= context;
        mData= new ArrayList<>();
        mHelper= new ReadedNewsTableHelper(context);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.mTitleTv.setText(mData.get(position).getTitle());
        holder.mKindTv.setText(mData.get(position).getClassname());
        holder.mTimeTv.setText(mData.get(position).getStartDate().substring(0,10));
        holder.mDepartmentTv.setText(mData.get(position).getOperationDeptName());
        int newId= mData.get(position).getId();
        if (mHelper.isNewsReaded(newId)){
            holder.mTitleTv.setTextColor(Color.rgb(142,143,138));
        }else {
            holder.mTitleTv.setTextColor(Color.rgb(61,62,59));
        }

        holder.skipNewsDetail(mData.get(position).getId());
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView= LayoutInflater.from(mContext).inflate(R.layout.layout_news,parent,false);
        return new NewsViewHolder(mView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rly;
        private TextView mTitleTv;
        private TextView mKindTv;
        private TextView mTimeTv;
        private TextView mDepartmentTv;

        public NewsViewHolder(View itemView) {
            super(itemView);
            rly= (RelativeLayout) itemView.findViewById(R.id.item_news_withoutimg);
            mTitleTv= (TextView) itemView.findViewById(R.id.item_news_title);
            mKindTv= (TextView) itemView.findViewById(R.id.item_news_kind);
            mTimeTv= (TextView) itemView.findViewById(R.id.item_news_time);
            mDepartmentTv= (TextView) itemView.findViewById(R.id.item_news_department);
        }

        public void skipNewsDetail(final int id){
            rly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent();
                    intent.setClass(mContext,NewsDetailActivity.class);
                    intent.putExtra(NewsDetailActivity.NEWS_ID,id);
                    mContext.startActivity(intent);
                    mHelper.insert(id);
                    notifyDataSetChanged();
                }
            });
        }
    }

    public void addData(List datas){
        mData.addAll(datas);
        Collections.sort(mData, new Comparator<NewsEntity>() {
            @Override
            public int compare(NewsEntity lhs, NewsEntity rhs) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = null;
                Date date2 = null;
                try{
                    date1=format.parse(lhs.getStartDate().substring(0, 10));
                    date2=format.parse(rhs.getStartDate().substring(0, 10));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return (int) (date2.getTime()/100000-date1.getTime()/100000);
            }
        });
        Log.e("addData",mData.toString());
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
        Log.e("clear",mData.toString());
        notifyDataSetChanged();
    }

}

package com.hnsi.oa.hnsi_oa.application.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.beans.TableFileEntity;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/2/9.
 */

public class MyTableFilesAdapter extends RecyclerView.Adapter<MyTableFilesAdapter.TableFileViewHolder> {

    private Context mContext;
    private ArrayList<TableFileEntity> mDatas;

    public MyTableFilesAdapter(Context context, ArrayList<TableFileEntity> data){
        mContext= context;
        mDatas= data;
    }

    @Override
    public MyTableFilesAdapter.TableFileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_tablefiles, parent, false);
        return new TableFileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TableFileViewHolder holder, int position) {
        TableFileEntity entity= mDatas.get(position);
        holder.mFileNameTv.setText(entity.getFileName());
        holder.mFileSizeTv.setText(entity.getFileSize());
        holder.mFileTimeTv.setText(entity.getFileTime());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class TableFileViewHolder extends RecyclerView.ViewHolder{

        private ProgressBar mProgressBar;
        private TextView mFileNameTv;
        private TextView mFileSizeTv;
        private TextView mFileTimeTv;
        private Button mDownloadBtn;

        public TableFileViewHolder(View itemView) {
            super(itemView);
            mProgressBar= (ProgressBar) itemView.findViewById(R.id.item_tablefiles_progress);
            mFileNameTv=  (TextView) itemView.findViewById(R.id.item_tablefiles_name);
            mFileSizeTv=  (TextView) itemView.findViewById(R.id.item_tablefiles_size);
            mFileTimeTv=  (TextView) itemView.findViewById(R.id.item_tablefiles_time);
            mDownloadBtn=  (Button) itemView.findViewById(R.id.item_tablefiles_open);
        }
    }

}

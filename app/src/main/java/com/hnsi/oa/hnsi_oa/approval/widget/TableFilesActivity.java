package com.hnsi.oa.hnsi_oa.approval.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.ApprovalWidgetEntity;
import com.hnsi.oa.hnsi_oa.beans.TableFileEntity;
import com.hnsi.oa.hnsi_oa.app.BaseActivity;
import com.hnsi.oa.hnsi_oa.widgets.MyNewsItemDecoration;
import com.hnsi.oa.hnsi_oa.widgets.MyTableFilesAdapter;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/2/9.
 */

public class TableFilesActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private TextView mEmptyTip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablefiles);

        ApprovalWidgetEntity entity= (ApprovalWidgetEntity) getIntent().getSerializableExtra("fileinfo");
        if (entity== null || entity.getTableData()== null ){
            Toast.makeText(this, "附件数据异常！", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(entity.getLabel());
        }

        mRecyclerView= (RecyclerView) findViewById(R.id.tablefile_list);
        mEmptyTip= (TextView) findViewById(R.id.tablefile_empty);

        ArrayList<TableFileEntity> files= new ArrayList<>();
        for (ArrayList<String> fileList : entity.getTableData()){
            if (fileList== null){
                Toast.makeText(this, "附件数据异常！", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
            TableFileEntity file= new TableFileEntity();
            file.setFileNum(fileList.get(0));
            file.setFileName(fileList.get(1));
            file.setFileTime(fileList.get(2));
            file.setFilePath(fileList.get(3));
            file.setFileSize(fileList.get(4));
            files.add(file);
        }

        if (files.size()== 0){
            mRecyclerView.setVisibility(View.GONE);
            mEmptyTip.setVisibility(View.VISIBLE);
        }else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyTip.setVisibility(View.GONE);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.addItemDecoration(new MyNewsItemDecoration());
            MyTableFilesAdapter adapter= new MyTableFilesAdapter(this, files);
            mRecyclerView.setAdapter(adapter);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }
}

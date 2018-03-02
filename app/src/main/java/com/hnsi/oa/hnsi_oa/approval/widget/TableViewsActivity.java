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
import com.hnsi.oa.hnsi_oa.app.BaseActivity;
import com.hnsi.oa.hnsi_oa.widgets.MyNewsItemDecoration;
import com.hnsi.oa.hnsi_oa.widgets.MyTableViewsAdapter;

/**
 * Created by Zheng on 2018/2/11.
 */

public class TableViewsActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private TextView mEmptyTip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableviews);

        ApprovalWidgetEntity entity= (ApprovalWidgetEntity) getIntent().getSerializableExtra("tableInfo");
        int tableIndex= getIntent().getIntExtra("tableIndex", -1);
        if (entity== null || tableIndex< 0){
            Toast.makeText(this, "附件数据异常！", Toast.LENGTH_SHORT).show();
            finish();
        }

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(entity.getLabel()+ "(第" + (tableIndex+1) + "张)");
        }

        mRecyclerView= (RecyclerView) findViewById(R.id.tableview_list);
        mEmptyTip= (TextView) findViewById(R.id.tableview_empty);

        if (entity.getTableData().size()== 0){
            mRecyclerView.setVisibility(View.GONE);
            mEmptyTip.setVisibility(View.VISIBLE);
        }else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyTip.setVisibility(View.GONE);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.addItemDecoration(new MyNewsItemDecoration());
            MyTableViewsAdapter adapter= new MyTableViewsAdapter(this, entity, tableIndex);
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

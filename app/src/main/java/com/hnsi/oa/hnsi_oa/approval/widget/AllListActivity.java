package com.hnsi.oa.hnsi_oa.approval.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.RealDepartmentEntity;
import com.hnsi.oa.hnsi_oa.database.DepartmentInfoTableHelper;
import com.hnsi.oa.hnsi_oa.app.BaseActivity;
import com.hnsi.oa.hnsi_oa.widgets.MyDepartmentsAdapter;
import com.hnsi.oa.hnsi_oa.widgets.MyDepartmentsDecoration;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/2/11.
 */

public class AllListActivity extends BaseActivity {

    private ProgressBar mProgress;
    private RecyclerView mRecyclerView;
    private DepartmentInfoTableHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alllist);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("经办人");
        }

        mProgress= (ProgressBar) findViewById(R.id.progressBar);
        mRecyclerView= (RecyclerView) findViewById(R.id.alllist_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHelper= new DepartmentInfoTableHelper(this);

        ArrayList<RealDepartmentEntity> departmentList= mHelper.queryAllDepaetment();

        //如果读取到0条数据，请求网络数据
        if (departmentList== null || departmentList.size()== 0) {
            Toast.makeText(this, "本地无通讯录数据！", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        mRecyclerView.setAdapter(new MyDepartmentsAdapter(this, departmentList));
        mRecyclerView.addItemDecoration(new MyDepartmentsDecoration(10, departmentList));
        mProgress.setVisibility(View.GONE);

    }

    public void showDepartmentFragment(int departmentId){
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentPanel, new AllListFragment(departmentId));
        transaction.addToBackStack(AllListActivity.class.getSimpleName());
        transaction.commit();
    }

    public void selectComplete(int empid, String empName){
        Intent intent=new Intent();
        intent.putExtra("empid",empid);
        intent.putExtra("empname",empName);
        setResult(120,intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return true;
    }
}

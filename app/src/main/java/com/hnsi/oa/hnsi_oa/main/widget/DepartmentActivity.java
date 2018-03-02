package com.hnsi.oa.hnsi_oa.main.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.PersonEntity;
import com.hnsi.oa.hnsi_oa.database.ConstactsInfoTableHelper;
import com.hnsi.oa.hnsi_oa.app.BaseActivity;
import com.hnsi.oa.hnsi_oa.widgets.MyContactsAdapter;
import com.hnsi.oa.hnsi_oa.widgets.MyDepartmentsAdapter;
import com.hnsi.oa.hnsi_oa.widgets.MyNewsItemDecoration;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/4.
 */

public class DepartmentActivity extends BaseActivity {

    private int orgId;
    private String orgName;

    private RecyclerView mRecyclerView;
    private ArrayList<PersonEntity> personEntities;
    private ConstactsInfoTableHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        orgId= getIntent().getIntExtra(MyDepartmentsAdapter.DEPARTMENT_ID, 0);
        if (orgId== 0){
            Toast.makeText(this, "获取数据出错，请重试！", Toast.LENGTH_LONG).show();
            finish();
        }
        orgName= getIntent().getStringExtra(MyDepartmentsAdapter.DEPARTMENT_NAME);
        if (orgName== null || "".equals(orgName)){
            Toast.makeText(this, "获取数据出错，请重试！", Toast.LENGTH_LONG).show();
            finish();
        }

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle(orgName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mHelper= new ConstactsInfoTableHelper(this);
        personEntities=  mHelper.queryAllOrgidContacts(orgId);
        if (personEntities== null || personEntities.size()== 0){
            Toast.makeText(this, "获取数据出错，请重试！", Toast.LENGTH_LONG).show();
            finish();
        }

        MyContactsAdapter adapter= new MyContactsAdapter(this, personEntities);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new MyNewsItemDecoration());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

}

package com.hnsi.oa.hnsi_oa.main.widget;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.beans.PersonEntity;
import com.hnsi.oa.hnsi_oa.database.ConstactsInfoTableHelper;
import com.hnsi.oa.hnsi_oa.app.BaseActivity;
import com.hnsi.oa.hnsi_oa.widgets.MyContactsAdapter;
import com.hnsi.oa.hnsi_oa.widgets.MyNewsItemDecoration;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/16.
 */

public class SearchContactActivity extends BaseActivity {

    private EditText mEdittext;

    private RecyclerView mRecyclerView;

    private ImageView mDeleteIv;

    private TextView mEmptyTv;

    /** 搜索产生的数据 */
    private ArrayList<PersonEntity> mSearchList;

    private ConstactsInfoTableHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle("搜索联系人");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initUI();
        setListener();

        mHelper= new ConstactsInfoTableHelper(SearchContactActivity.this);
        if (!mHelper.isDatabaseValid()){
            Toast.makeText(this, "通讯录数据库无效，请刷新通讯录！", Toast.LENGTH_LONG).show();
            finish();
        }

    }

    /**
     * 初始化界面
     */
    private void initUI() {
        mEdittext= (EditText) findViewById(R.id.activity_search_et);
        mRecyclerView= (RecyclerView) findViewById(R.id.activity_search_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDeleteIv= (ImageView) findViewById(R.id.activity_search_iv_delete);
        mEmptyTv= (TextView) findViewById(R.id.activity_search_empty);
    }

    /**
     * 设置点击事件
     */
    private void setListener() {
        mEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s== null || "".equals(s.toString()) || "null".equals(s.toString())){
                    mDeleteIv.setVisibility(View.GONE);
                }else {
                    mDeleteIv.setVisibility(View.VISIBLE);
                }

                mSearchList= mHelper.searchByName(s.toString());
                if (mSearchList.size()> 0){

                    mRecyclerView.setVisibility(View.VISIBLE);
                    mEmptyTv.setVisibility(View.GONE);

                    MyContactsAdapter adapter= new MyContactsAdapter(SearchContactActivity.this, mSearchList);
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.addItemDecoration(new MyNewsItemDecoration());
                }else {
                    mRecyclerView.setVisibility(View.GONE);
                    mEmptyTv.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mDeleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdittext.setText("");
                mDeleteIv.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}

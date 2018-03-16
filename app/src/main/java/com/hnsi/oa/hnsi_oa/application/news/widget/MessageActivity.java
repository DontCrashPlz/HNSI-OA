package com.hnsi.oa.hnsi_oa.application.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.news.presenter.MessageListPresenter;
import library.apps.BaseActivity;
import com.hnsi.oa.hnsi_oa.application.adapters.MyMessageListAdapter;
import com.hnsi.oa.hnsi_oa.application.widgets.MyNewsItemDecoration;
import library.widgets.RecyclerFragment.BaseRecyclerFragment;

/**
 * Created by Zheng on 2017/11/13.
 */

public class MessageActivity extends BaseActivity {

    private BaseRecyclerFragment mFragment;
    private MyMessageListAdapter mAdapter;

    private MessageListPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle("系统消息");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mAdapter= new MyMessageListAdapter(R.layout.item_message);
        mFragment= new BaseRecyclerFragment(mAdapter, new MyNewsItemDecoration());
        mPresenter= new MessageListPresenter(mFragment);
        mFragment.setPresenter(mPresenter);

        FragmentManager manager= getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        transaction.replace(R.id.fragment, mFragment);
        transaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!= null){
            mPresenter.dettachView();
        }
    }

}

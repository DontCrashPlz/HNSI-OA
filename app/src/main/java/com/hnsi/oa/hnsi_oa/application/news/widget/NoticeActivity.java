package com.hnsi.oa.hnsi_oa.application.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hnsi.oa.hnsi_oa.R;
import library.apps.BaseActivity;
import library.widgets.CustomTabLayout.ViewPagerTitle;

/**
 * Created by Zheng on 2017/11/13.
 */

public class NoticeActivity extends BaseActivity {

    private ViewPagerTitle mViewPagerTitle;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("公告");

        mViewPagerTitle= (ViewPagerTitle) findViewById(R.id.viewpagertitle);
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPagerTitle.initData(new String[]{"全部公告", "公司通知", "部门通知"}, mViewPager, 0);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return NewsListFragment.getInstance(position+3);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

}
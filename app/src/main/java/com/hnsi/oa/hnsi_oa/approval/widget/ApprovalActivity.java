package com.hnsi.oa.hnsi_oa.approval.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.widgets.BaseActivity;
import com.hnsi.oa.hnsi_oa.widgets.CustomTabLayout.ViewPagerTitle;

/**
 * Created by Zheng on 2017/11/13.
 */

public class ApprovalActivity extends BaseActivity {
    private ViewPagerTitle mViewPagerTitle;
    private ViewPager mViewPager;

    private DrawerLayout drawerLayout;

    private ListView mListView;

    private UnFinishedFragment fragment1= new UnFinishedFragment();
    private UnFinishedFragment fragment2= new UnFinishedFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("新闻");

        mViewPagerTitle= (ViewPagerTitle) findViewById(R.id.viewpagertitle);
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
//        mViewPager.setOffscreenPageLimit(1);
        mViewPagerTitle.initData(new String[]{"待办事项", "已办事项"}, mViewPager, 0);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position== 0){
                    return fragment1;
                }else if ((position== 1)){
                    return fragment2;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        mListView= (ListView) findViewById(R.id.listView);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

    public void initListView(final String[] strs){
        mListView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,strs));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragment1.setTextViewText(strs[position]);
                drawerLayout.closeDrawers();
            }
        });
    }

}

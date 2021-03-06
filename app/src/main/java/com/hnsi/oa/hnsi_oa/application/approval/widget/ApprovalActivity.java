package com.hnsi.oa.hnsi_oa.application.approval.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.application.approval.presenter.FinishPresenter;
import com.hnsi.oa.hnsi_oa.application.approval.presenter.UnFinishFlowPresenter;
import com.hnsi.oa.hnsi_oa.application.approval.presenter.UnFinishPresenter;
import com.hnsi.oa.hnsi_oa.application.beans.FlowClassifyEntity;
import com.hnsi.oa.hnsi_oa.application.database.FlowListTableHelper;
import library.apps.BaseActivity;
import library.widgets.CustomTabLayout.ViewPagerTitle;
import com.hnsi.oa.hnsi_oa.application.adapters.MyFinishedFlowAdapter;
import com.hnsi.oa.hnsi_oa.application.widgets.MyNewsItemDecoration;
import com.hnsi.oa.hnsi_oa.application.adapters.MyPenddingFlowAdapter;
import library.widgets.RecyclerFragment.BaseRecyclerFragment;

import java.util.ArrayList;

/**
 * Created by Zheng on 2017/11/13.
 */

public class ApprovalActivity extends BaseActivity {
    private ViewPagerTitle mViewPagerTitle;
    private ViewPager mViewPager;

    private DrawerLayout drawerLayout;

    private ListView mListView;

    private UnFinishedFragment fragment1;
    private BaseRecyclerFragment fragment2;

    private MyPenddingFlowAdapter pAdapter;
    private MyFinishedFlowAdapter fAdapter;
    private MyNewsItemDecoration mItemDecoration;

    private UnFinishPresenter mPresenter1;
    private FinishPresenter mPresenter2;
    private UnFinishFlowPresenter mPresenter3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("新闻");

        pAdapter= new MyPenddingFlowAdapter();
        fAdapter= new MyFinishedFlowAdapter();
        mItemDecoration= new MyNewsItemDecoration();

        fragment1= new UnFinishedFragment(pAdapter, mItemDecoration);
        mPresenter1= new UnFinishPresenter(fragment1);
        fragment1.setPresenter(mPresenter1);

        fragment2= new BaseRecyclerFragment(true, fAdapter, mItemDecoration);
        mPresenter2= new FinishPresenter(fragment2);
        fragment2.setPresenter(mPresenter2);

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

    public void initListView(ArrayList<FlowClassifyEntity> flows){
        mListView.setAdapter(new MyFlowNumAdapter(this, flows));
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                fragment1.setTextViewText(strs[position]);
//                fragment1.setNewPresenter(new UnFinishFlowPresenter(fragment1, ));
//                drawerLayout.closeDrawers();
//            }
//        });
    }

    class MyFlowNumAdapter extends BaseAdapter{

        private Context mContext;
        private ArrayList<FlowClassifyEntity> datas;
        private FlowListTableHelper helper;

        public MyFlowNumAdapter(Context context, ArrayList<FlowClassifyEntity> flows){
            mContext= context;
            datas= flows;
            helper= new FlowListTableHelper(context);
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View mView= LayoutInflater.from(mContext).inflate(R.layout.item_flow_num, parent, false);
            TextView mTextView= (TextView) mView.findViewById(R.id.flow_name);
            mTextView.setText(helper.getFlowName(datas.get(position).getLabel())+ "     " + datas.get(position).getNum());

            FrameLayout mPanel= (FrameLayout) mView.findViewById(R.id.flow_panel);
            mPanel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fragment1.isVisible){
                        mPresenter3= new UnFinishFlowPresenter(fragment1, datas.get(position).getLabel());
                        fragment1.setNewPresenter(mPresenter3);
                        fragment1.onRefresh();
                        drawerLayout.closeDrawers();
                    }else{
                        Toast.makeText(ApprovalActivity.this, "流程分类仅支持待办事项", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                    }
                }
            });
            return mView;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter1!= null){
            mPresenter1.detachView();
        }
        if (mPresenter2!= null){
            mPresenter2.detachView();
        }
        if (mPresenter3!= null){
            mPresenter3.detachView();
        }
    }
}

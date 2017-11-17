package com.hnsi.oa.hnsi_oa.main.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;

import com.hnsi.oa.hnsi_oa.R;
import com.hnsi.oa.hnsi_oa.widgets.BaseActivity;
import com.hnsi.oa.hnsi_oa.main.view.IHomeView;

import java.util.ArrayList;

/**
 * Created by Zheng on 2017/10/23.
 */

public class HomeActivity extends BaseActivity implements IHomeView,View.OnClickListener {

    private ViewPager mContentVp;
    private RadioButton mMsgBtn;
    private RadioButton mContactBtn;
    private RadioButton mApplyBtn;
    private RadioButton mMineBtn;

    private ArrayList<Fragment> fragments= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViews();

        onClick(mMsgBtn);
    }

    private void findViews() {
        mMsgBtn= (RadioButton) findViewById(R.id.home_tab_rbtn_msg);
        mMsgBtn.setOnClickListener(this);
        mContactBtn= (RadioButton) findViewById(R.id.home_tab_rbtn_contacts);
        mContactBtn.setOnClickListener(this);
        mApplyBtn= (RadioButton) findViewById(R.id.home_tab_rbtn_apply);
        mApplyBtn.setOnClickListener(this);
        mMineBtn= (RadioButton) findViewById(R.id.home_tab_rbtn_mine);
        mMineBtn.setOnClickListener(this);

        mContentVp= (ViewPager) findViewById(R.id.home_vp_content);
        mContentVp.setOffscreenPageLimit(3);
        fragments.add(new MessageFragment());
        fragments.add(new ContactsFragment());
        fragments.add(new ApplyFragment());
        fragments.add(new MineFragment());
        mContentVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mContentVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        onClick(mMsgBtn);
                        break;
                    case 1:
                        onClick(mContactBtn);
                        break;
                    case 2:
                        onClick(mApplyBtn);
                        break;
                    case 3:
                        onClick(mMineBtn);
                        break;
                    default:
                        throw new NullPointerException("ViewPager index Exception!");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int vId= v.getId();
        switch (vId){
            case R.id.home_tab_rbtn_msg:
                getSupportActionBar().setTitle("消息");
                if (!mMsgBtn.isChecked())
                    mMsgBtn.setChecked(true);
                if (mContentVp.getCurrentItem()!= 0)
                    mContentVp.setCurrentItem(0,false);
                break;
            case R.id.home_tab_rbtn_contacts:
                getSupportActionBar().setTitle("通讯录");
                if (!mContactBtn.isChecked())
                    mContactBtn.setChecked(true);
                if (mContentVp.getCurrentItem()!= 1)
                    mContentVp.setCurrentItem(1,false);
                break;
            case R.id.home_tab_rbtn_apply:
                getSupportActionBar().setTitle("应用");
                if (!mApplyBtn.isChecked())
                    mApplyBtn.setChecked(true);
                if (mContentVp.getCurrentItem()!= 2)
                    mContentVp.setCurrentItem(2,false);
                break;
            case R.id.home_tab_rbtn_mine:
                getSupportActionBar().setTitle("我的");
                if (!mMineBtn.isChecked())
                    mMineBtn.setChecked(true);
                if (mContentVp.getCurrentItem()!= 3)
                    mContentVp.setCurrentItem(3,false);
                break;
        }
    }
}

package com.hnsi.oa.hnsi_oa.application.main.widget;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.hnsi.oa.hnsi_oa.R;

import library.apps.BaseActivity;

/**
 * Created by Zheng on 2018/3/22.
 */

public class AboutActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setTitle("关于");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ((TextView)findViewById(R.id.about_tv_version)).setText(getVersion());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home== item.getItemId()){
            finish();
        }
        return false;
    }

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return String.format(this.getString(R.string.about_version),version);
        } catch (Exception e) {
            e.printStackTrace();
            return "未知";
        }
    }
}

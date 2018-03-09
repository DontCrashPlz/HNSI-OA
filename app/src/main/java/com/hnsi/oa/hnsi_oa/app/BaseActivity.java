package com.hnsi.oa.hnsi_oa.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hnsi.oa.hnsi_oa.app.ActivityManager;
import com.hnsi.oa.hnsi_oa.beans.LoginEntity;
import com.hnsi.oa.hnsi_oa.utils.LogUtil;

/**
 * Created by Zheng on 2017/10/16.
 */

public class BaseActivity extends AppCompatActivity {

    private final String ACTIVITY_TAG= this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e(ACTIVITY_TAG, ACTIVITY_TAG + " was Created.");
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().removeActivity(this);
        LogUtil.e(ACTIVITY_TAG, ACTIVITY_TAG + " was Destroyed.");
        super.onDestroy();
    }
}

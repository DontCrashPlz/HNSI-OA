package com.hnsi.oa.hnsi_oa.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


/**
 * Created by Zheng on 2018/3/2.
 */

public abstract class BaseMvpActivity extends BaseActivity implements IBaseView {

    private BasePresenter mPreserter;

    /**
     * Plz init this ProgressBar before use it.
     */
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPreserter= new BasePresenter();
        mPreserter.attachView(this);

    }

    public abstract void initProgressBar();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPreserter.detachView();
    }

    @Override
    public void showProgress() {
        if (mProgressBar== null || mProgressBar.isShown()){
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        if (mProgressBar== null || !mProgressBar.isShown()){
            return;
        }
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getRealContext() {
        return this;
    }
}

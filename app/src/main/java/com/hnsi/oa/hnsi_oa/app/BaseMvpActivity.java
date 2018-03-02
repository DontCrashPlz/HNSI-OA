package com.hnsi.oa.hnsi_oa.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Created by Zheng on 2018/3/2.
 */

public class BaseMvpActivity extends BaseActivity implements IBaseView {

    private BasePresenter mPreserter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPreserter= new BasePresenter();
        mPreserter.attachView(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPreserter.detachView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public Context getRealContext() {
        return this;
    }
}

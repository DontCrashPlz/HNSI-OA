package com.hnsi.oa.hnsi_oa.interfaces;

import android.content.Context;

/**
 * Created by Zheng on 2018/3/1.
 */

public interface IBaseView {
    void showProgress();
    void dismissProgress();
    void showToast(String msg);
    Context getRealContext();
}

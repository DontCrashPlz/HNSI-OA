package com.hnsi.oa.hnsi_oa.application.interfaces;

/**
 * Created by Zheng on 2017/11/3.
 */

public interface OnRequestDataListener<T> {
    void onSuccessed(T t);
    void onFailed(String throwable);
}

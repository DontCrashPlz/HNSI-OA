package com.hnsi.oa.hnsi_oa.interfaces;

/**
 * Created by Zheng on 2017/11/3.
 */

public interface OnRequestDataAndNumListener<T> {
    void onSuccessed(T t, int pageNum);
    void onFailed(String throwable);
}

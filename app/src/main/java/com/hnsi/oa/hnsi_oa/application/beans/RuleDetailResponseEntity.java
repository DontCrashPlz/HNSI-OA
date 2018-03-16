package com.hnsi.oa.hnsi_oa.application.beans;

/**
 * Created by Zheng on 2018/2/1.
 */

public class RuleDetailResponseEntity {

    private RuleDetailEntity data;

    public RuleDetailEntity getData() {
        return data;
    }

    public void setData(RuleDetailEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RuleDetailResponseEntity{" +
                "data=" + data +
                '}';
    }
}

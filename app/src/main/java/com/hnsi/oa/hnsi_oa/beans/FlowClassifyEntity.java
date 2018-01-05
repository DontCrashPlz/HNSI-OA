package com.hnsi.oa.hnsi_oa.beans;

/**
 * Created by Zheng on 2018/1/5.
 */

public class FlowClassifyEntity {
    private String label="";
    private String num="";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "FlowClassifyEntity{" +
                "label='" + label + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}

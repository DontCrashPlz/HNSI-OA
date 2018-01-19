package com.hnsi.oa.hnsi_oa.beans;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/18.
 */

public class ApprovalGroupEntity {
    private boolean audit;
    private boolean isAudit;
    private String groupKey= "";
    private String label= "";

    private ArrayList<ApprovalWidgetEntity> widgets;

    public boolean isAudit() {
        return audit;
    }

    public void setAudit(boolean audit) {
        this.audit = audit;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<ApprovalWidgetEntity> getWidgets() {
        return widgets;
    }

    public void setWidgets(ArrayList<ApprovalWidgetEntity> widgets) {
        this.widgets = widgets;
    }

    @Override
    public String toString() {
        return "ApprovalGroupEntity{" +
                "audit=" + audit +
                ", isAudit=" + isAudit +
                ", groupKey='" + groupKey + '\'' +
                ", label='" + label + '\'' +
                ", widgets=" + widgets +
                '}';
    }

}

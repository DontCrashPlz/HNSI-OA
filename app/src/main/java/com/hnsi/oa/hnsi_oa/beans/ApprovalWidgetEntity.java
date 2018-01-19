package com.hnsi.oa.hnsi_oa.beans;

import java.util.List;

/**
 * Created by Zheng on 2018/1/17.
 */

public class ApprovalWidgetEntity {
    /**
     * eventId :
     * groupKey : base
     * key : tyfy.isOffset
     * label : 是否冲借款
     * listData : [{"label":"是","value":"1"},{"label":"否","value":"0"},{"label":"银行","value":"银行"},{"label":"现金","value":"现金"}]
     * multiSelect : false
     * prompt :
     * readonly : true
     * required : false
     * selectorUrl :
     * tableData : [["1","集中勘察租车费","2000.0","2000.0",null,"2017-12-04"]]
     * tableTitle : ["序号","费用类型","预估金额","实际金额","市场信息流水号","发生时间"]
     * type : list
     * value : 0
     */

    private String eventId;
    private String groupKey;
    private String key;
    private String label;
    private boolean multiSelect;
    private String prompt;
    private boolean readonly;
    private boolean required;
    private String selectorUrl;
    private String type;
    private String value;
    private List<ListDataBean> listData;
    private List<List<String>> tableData;
    private List<String> tableTitle;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getSelectorUrl() {
        return selectorUrl;
    }

    public void setSelectorUrl(String selectorUrl) {
        this.selectorUrl = selectorUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ListDataBean> getListData() {
        return listData;
    }

    public void setListData(List<ListDataBean> listData) {
        this.listData = listData;
    }

    public List<List<String>> getTableData() {
        return tableData;
    }

    public void setTableData(List<List<String>> tableData) {
        this.tableData = tableData;
    }

    public List<String> getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(List<String> tableTitle) {
        this.tableTitle = tableTitle;
    }

    class ListDataBean {
        /**
         * label : 是
         * value : 1
         */

        private String label;
        private String value;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

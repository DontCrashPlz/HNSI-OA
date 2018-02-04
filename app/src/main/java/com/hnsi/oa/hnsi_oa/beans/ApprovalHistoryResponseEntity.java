package com.hnsi.oa.hnsi_oa.beans;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/2/4.
 */

public class ApprovalHistoryResponseEntity {
    /**
     * list : [{"id":1126756,"name":"sysadmin","userid":1,"startTime":"2018-02-02 15:36:46.164","endTime":"2018-02-02 15:36:46.164","useMinute":0,"activityName":"合同承办人","content":null,"decision":"1","isEditForm":1,"processInstId":227431}]
     * msg : null
     * success : true
     */

    private String msg;
    private boolean success;
    private ArrayList<ApprovalHistoryEntity> list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<ApprovalHistoryEntity> getList() {
        return list;
    }

    public void setList(ArrayList<ApprovalHistoryEntity> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ApprovalHistoryResponseEntity{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                ", list=" + list +
                '}';
    }
}

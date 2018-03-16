package com.hnsi.oa.hnsi_oa.application.beans;

import java.util.List;

/**
 * Created by Zheng on 2018/1/29.
 */

public class MessageListResponseEntity {

    /**
     * list : [{"id":114262,"msgType":"流程反馈","title":"test3","sendEmpname":"sysadmin","sendTime":"2018-01-22 00:00:00.0","receiveEmpid":139},{"id":114261,"msgType":"流程反馈","title":"test2","sendEmpname":"sysadmin","sendTime":"2018-01-22 00:00:00.0","receiveEmpid":139},{"id":114241,"msgType":"流程反馈","title":"test1","sendEmpname":"sysadmin","sendTime":"2018-01-22 00:00:00.0","receiveEmpid":139}]
     * msg : null
     * success : true
     * totalPage : 1
     */

    private String msg= "";
    private boolean success;
    private int totalPage;
    private List<MessageListEntity> list;

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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<MessageListEntity> getList() {
        return list;
    }

    public void setList(List<MessageListEntity> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MessageListResponseEntity{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}

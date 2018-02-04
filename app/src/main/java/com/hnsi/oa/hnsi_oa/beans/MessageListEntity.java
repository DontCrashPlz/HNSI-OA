package com.hnsi.oa.hnsi_oa.beans;

/**
 * Created by Zheng on 2018/1/29.
 */

public class MessageListEntity {

    /**
     * id : 114262
     * msgType : 流程反馈
     * title : test3
     * sendEmpname : sysadmin
     * sendTime : 2018-01-22 00:00:00.0
     * receiveEmpid : 139
     */

    private int id;
    private String msgType= "";
    private String title= "";
    private String sendEmpname= "";
    private String sendTime= "";
    private int receiveEmpid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSendEmpname() {
        return sendEmpname;
    }

    public void setSendEmpname(String sendEmpname) {
        this.sendEmpname = sendEmpname;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getReceiveEmpid() {
        return receiveEmpid;
    }

    public void setReceiveEmpid(int receiveEmpid) {
        this.receiveEmpid = receiveEmpid;
    }

    @Override
    public String toString() {
        return "MessageListEntity{" +
                "id=" + id +
                ", msgType='" + msgType + '\'' +
                ", title='" + title + '\'' +
                ", sendEmpname='" + sendEmpname + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", receiveEmpid=" + receiveEmpid +
                '}';
    }
}

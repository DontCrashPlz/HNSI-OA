package com.hnsi.oa.hnsi_oa.application.beans;

/**
 * Created by Zheng on 2018/1/31.
 */

public class MessageDetailEntity {
    /**
     * data : {"id":114262,"objId":"226422","msgType":3,"sendEmpid":1,"sendEmpname":"sysadmin","sendDeptname":"软信科技子公司","sendDeptid":8,"receiveEmpid":139,"receiveEmpname":"张开进","isRead":0,"title":"test3","content":"sysadmin发起差旅费申请流程,借调到第一设计分公司部门,金额1.0","files":null,"objInfo":"com.hnsi.erp.xmyy.clfy.clfyApply","sendTime":"2018-01-22 00:00:00.0"}
     * msg : null
     * success : true
     */

    private DataBean data;
    private String msg;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    @Override
    public String toString() {
        return "MessageDetailEntity{" +
                "data=" + data +
                ", msg=" + msg +
                ", success=" + success +
                '}';
    }

    public static class DataBean {
        /**
         * id : 114262
         * objId : 226422
         * msgType : 3
         * sendEmpid : 1
         * sendEmpname : sysadmin
         * sendDeptname : 软信科技子公司
         * sendDeptid : 8
         * receiveEmpid : 139
         * receiveEmpname : 张开进
         * isRead : 0
         * title : test3
         * content : sysadmin发起差旅费申请流程,借调到第一设计分公司部门,金额1.0
         * files : null
         * objInfo : com.hnsi.erp.xmyy.clfy.clfyApply
         * sendTime : 2018-01-22 00:00:00.0
         */

        private int msgType;
        private String sendEmpname;
        private String sendDeptname;
        private String receiveEmpname;
        private String title;
        private String content;
        private String sendTime;

        public int getMsgType() {
            return msgType;
        }

        public void setMsgType(int msgType) {
            this.msgType = msgType;
        }

        public String getSendEmpname() {
            return sendEmpname;
        }

        public void setSendEmpname(String sendEmpname) {
            this.sendEmpname = sendEmpname;
        }

        public String getSendDeptname() {
            return sendDeptname;
        }

        public void setSendDeptname(String sendDeptname) {
            this.sendDeptname = sendDeptname;
        }

        public String getReceiveEmpname() {
            return receiveEmpname;
        }

        public void setReceiveEmpname(String receiveEmpname) {
            this.receiveEmpname = receiveEmpname;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "msgType=" + msgType +
                    ", sendEmpname='" + sendEmpname + '\'' +
                    ", sendDeptname='" + sendDeptname + '\'' +
                    ", receiveEmpname='" + receiveEmpname + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", sendTime='" + sendTime + '\'' +
                    '}';
        }
    }
}

package com.hnsi.oa.hnsi_oa.beans;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/31.
 */

public class RuleListResponseEntity {
    /**
     * list : [{"id":162,"title":"《河南省信息咨询设计研究有限公司员工考勤及休假管理办法》","operationTime":"2017-09-08 18:04:37.0"},{"id":161,"title":"《河南省信息咨询设计研究有限公司员工奖惩办法》","operationTime":"2017-09-08 18:03:40.0"},{"id":142,"title":"《总承包项目管理 暂行办法（2017版）》","operationTime":"2017-09-04 16:37:16.0"},{"id":141,"title":"《生产及市场管理调研检查 暂行办法（2017版）》","operationTime":"2017-09-04 16:36:04.0"},{"id":123,"title":"《知识产权管理办法》","operationTime":"2017-03-08 10:00:17.0"},{"id":122,"title":"《员工培训管理办法》","operationTime":"2017-03-08 09:57:51.0"},{"id":121,"title":"《个人技术资格管理办法》","operationTime":"2017-03-08 09:55:25.0"},{"id":106,"title":"《河南省信息咨询设计研究有限公司财务调研与检查管理办法》","operationTime":"2017-02-20 14:44:52.0"},{"id":105,"title":"《河南省信息咨询设计研究有限公司合同管理办法》","operationTime":"2017-02-20 14:44:14.0"},{"id":104,"title":"《河南省信息咨询设计研究有限公司社会化项目营销管理办法（暂行）》","operationTime":"2017-02-20 14:43:31.0"},{"id":103,"title":"《河南省信息咨询设计研究有限公司生产任务外部协作管理办法(2015修订版)》","operationTime":"2017-02-20 14:42:45.0"},{"id":102,"title":"《河南省信息咨询设计研究有限公司投标管理办法》","operationTime":"2017-02-20 14:41:25.0"},{"id":101,"title":"《河南省信息咨询设计研究有限公司新增业务量确认管理办法（2016版）》","operationTime":"2017-02-20 14:39:39.0"},{"id":81,"title":"《公司资质证照适用范围清单》","operationTime":"2017-01-12 11:26:28.0"},{"id":41,"title":"《公司员工工卡管理规定》","operationTime":"2016-09-19 17:58:05.0"}]
     * msg : null
     * success : true
     * totalPage : 1
     */

    private String msg;
    private boolean success;
    private int totalPage;
    private ArrayList<RuleListEntity> list;

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

    public ArrayList<RuleListEntity> getList() {
        return list;
    }

    public void setList(ArrayList<RuleListEntity> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "RuleListResponseEntity{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}

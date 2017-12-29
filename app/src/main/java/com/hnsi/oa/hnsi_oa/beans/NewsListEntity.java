package com.hnsi.oa.hnsi_oa.beans;

import java.util.ArrayList;

/**
 * Created by Zheng on 2017/11/3.
 */

public class NewsListEntity {
    private String msg;
    private boolean success;
    private int totalPage;
    private ArrayList<NewsEntity> list;

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

    public ArrayList<NewsEntity> getList() {
        return list;
    }

    public void setList(ArrayList<NewsEntity> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "NewsListEntity{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}
//{
//        "list":[
//        {
//        "id":2942,
//        "newsClass":1,
//        "classname":"公司通知",
//        "startDate":"2017-03-27 00:00:00.0",
//        "operatorName":"马帅",
//        "title":"《关于召开2017年工作会议、员工（会员）代表大会暨第三届一次（员工）会员代表大会的通知》",
//        "viewScope":1,
//        "firstDeptId":5,
//        "operationDeptName":"综合管理部",
//        "imgsrc":null
//        },
//        Object{...}
//        ],
//        "msg":null,
//        "success":true,
//        "totalPage":2
//        }
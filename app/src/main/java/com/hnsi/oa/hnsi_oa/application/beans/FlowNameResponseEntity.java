package com.hnsi.oa.hnsi_oa.application.beans;

import java.util.ArrayList;

/**
 * Created by Zheng on 2018/1/12.
 */

public class FlowNameResponseEntity {
    private ArrayList<FlowNameEntity> Process;
    private int num;

    public ArrayList<FlowNameEntity> getProcess() {
        return Process;
    }

    public void setProcess(ArrayList<FlowNameEntity> process) {
        Process = process;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "FlowNameResponseEntity{" +
                "Process=" + Process +
                ", num=" + num +
                '}';
    }
}

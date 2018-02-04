package com.hnsi.oa.hnsi_oa.beans;

/**
 * Created by Zheng on 2018/2/4.
 */

public class ApprovalHistoryEntity {
    /**
     * id : 1126756
     * name : sysadmin
     * userid : 1
     * startTime : 2018-02-02 15:36:46.164
     * endTime : 2018-02-02 15:36:46.164
     * useMinute : 0
     * activityName : 合同承办人
     * content : null
     * decision : 1
     * isEditForm : 1
     * processInstId : 227431
     */

    private int id;
    private String name;
    private int userid;
    private String startTime;
    private String endTime;
    private int useMinute;
    private String activityName;
    private String content;
    private String decision;
    private int isEditForm;
    private int processInstId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getUseMinute() {
        return useMinute;
    }

    public void setUseMinute(int useMinute) {
        this.useMinute = useMinute;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public int getIsEditForm() {
        return isEditForm;
    }

    public void setIsEditForm(int isEditForm) {
        this.isEditForm = isEditForm;
    }

    public int getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(int processInstId) {
        this.processInstId = processInstId;
    }

    @Override
    public String toString() {
        return "ApprovalHistoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userid=" + userid +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", useMinute=" + useMinute +
                ", activityName='" + activityName + '\'' +
                ", content='" + content + '\'' +
                ", decision='" + decision + '\'' +
                ", isEditForm=" + isEditForm +
                ", processInstId=" + processInstId +
                '}';
    }
}

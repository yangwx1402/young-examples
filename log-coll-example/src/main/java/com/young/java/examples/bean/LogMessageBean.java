package com.young.java.examples.bean;

import java.util.List;

/**
 * Created by young.yang on 2016/11/12.
 */
public class LogMessageBean {
    private long cost;

    private LogStatus status;

    private List<String> otherFiles;

    public List<String> getOtherFiles() {
        return otherFiles;
    }

    public void setOtherFiles(List<String> otherFiles) {
        this.otherFiles = otherFiles;
    }

    public LogStatus getStatus() {
        return status;
    }

    public void setStatus(LogStatus status) {
        this.status = status;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}

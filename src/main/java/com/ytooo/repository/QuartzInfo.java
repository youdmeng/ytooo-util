package com.ytooo.repository;

import java.util.Date;

public class QuartzInfo {
    private Integer id;

    private String batchType;

    private String name;

    private String executeTime;

    private String objectName;

    private String objectMethod;

    private String batchExeType;

    private String batchDesc;

    private String threadPoolSize;

    private String queueCapacity;

    private String sectionSize;

    private String indexStart;

    private String indexEnd;

    private Date createdDate;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType == null ? null : batchType.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime == null ? null : executeTime.trim();
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName == null ? null : objectName.trim();
    }

    public String getObjectMethod() {
        return objectMethod;
    }

    public void setObjectMethod(String objectMethod) {
        this.objectMethod = objectMethod == null ? null : objectMethod.trim();
    }

    public String getBatchExeType() {
        return batchExeType;
    }

    public void setBatchExeType(String batchExeType) {
        this.batchExeType = batchExeType == null ? null : batchExeType.trim();
    }

    public String getBatchDesc() {
        return batchDesc;
    }

    public void setBatchDesc(String batchDesc) {
        this.batchDesc = batchDesc == null ? null : batchDesc.trim();
    }

    public String getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(String threadPoolSize) {
        this.threadPoolSize = threadPoolSize == null ? null : threadPoolSize.trim();
    }

    public String getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(String queueCapacity) {
        this.queueCapacity = queueCapacity == null ? null : queueCapacity.trim();
    }

    public String getSectionSize() {
        return sectionSize;
    }

    public void setSectionSize(String sectionSize) {
        this.sectionSize = sectionSize == null ? null : sectionSize.trim();
    }

    public String getIndexStart() {
        return indexStart;
    }

    public void setIndexStart(String indexStart) {
        this.indexStart = indexStart == null ? null : indexStart.trim();
    }

    public String getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(String indexEnd) {
        this.indexEnd = indexEnd == null ? null : indexEnd.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
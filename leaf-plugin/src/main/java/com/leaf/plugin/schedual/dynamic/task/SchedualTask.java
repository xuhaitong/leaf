package com.leaf.plugin.schedual.dynamic.task;

import java.io.Serializable;
import java.util.Date;

/**
 * 计划任务基础信息类
 * 
 * @author Leaf Xu
 * @date 2019-1-18 10:33:21
 *
 */
public class SchedualTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SchedualTask() {
	}

	public SchedualTask(String id, String jobName, String jobGroup, String jobStatus, String cronExpression,
			String description, String beanClass, String isConcurrent, String springId, String methodName,
			String jobType, String dataId, String delFlag, String createby, Date createdate, String updateby,
			Date updatedate, String remarks) {
		this.id = id;
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.jobStatus = jobStatus;
		this.cronExpression = cronExpression;
		this.description = description;
		this.beanClass = beanClass;
		this.isConcurrent = isConcurrent;
		this.springId = springId;
		this.methodName = methodName;
		this.jobType = jobType;
		this.dataId = dataId;
		this.delFlag = delFlag;
		this.createby = createby;
		this.createdate = createdate;
		this.updateby = updateby;
		this.updatedate = updatedate;
		this.remarks = remarks;
	}

	private String id;
	// 名称
	private String jobName;
	// 组
	private String jobGroup;
	// 状态 0 初始 ，1 进入任务队列 ，2 正在执行，3 执行完结 ，4 更新
	private String jobStatus;
	// 执行时间表达式
	private String cronExpression;
	// 描述
	private String description;
	// job类全路径名
	private String beanClass;
	// 1
	private String isConcurrent;
	// job类注册id
	private String springId;
	// job类任务方法
	private String methodName;
	// job类型 1 随军家属补助
	private String jobType;
	// job 对应业务信息主键
	private String dataId;
	// 是否删除
	private String delFlag;
	// 创建者
	private String createby;
	// 创建时间
	private java.util.Date createdate;
	// 更新者
	private String updateby;
	// 更新时间
	private java.util.Date updatedate;
	// 备注信息
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	public String getSpringId() {
		return springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public java.util.Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(java.util.Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "SchedualTask [id=" + id + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", jobStatus=" + jobStatus
				+ ", cronExpression=" + cronExpression + ", description=" + description + ", beanClass=" + beanClass
				+ ", isConcurrent=" + isConcurrent + ", springId=" + springId + ", methodName=" + methodName
				+ ", jobType=" + jobType + ", dataId=" + dataId + ", delFlag=" + delFlag + ", createby=" + createby
				+ ", createdate=" + createdate + ", updateby=" + updateby + ", updatedate=" + updatedate + ", remarks="
				+ remarks + "]";
	}

}

package com.leaf.plugin.schedual.dynamic.task;

import java.io.Serializable;

/**
 * 计划任务日志类
 * 
 * @author Leaf Xu
 * @date 2019-1-18 10:35:42
 *
 */
public class SchedualTaskLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	// 任务id
	private String taskId;
	// 执行状态 1 成功 0 失败
	private String taskStatus;
	// 失败原因
	private String failReson;
	// 任务执行描述
	private String taskDetail;
	// 创建时间
	private java.util.Date createdate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getFailReson() {
		return failReson;
	}

	public void setFailReson(String failReson) {
		this.failReson = failReson;
	}

	public String getTaskDetail() {
		return taskDetail;
	}

	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}
}

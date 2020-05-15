package com.leaf.plugin.schedual.dynamic.Constant;

import com.leaf.plugin.schedual.dynamic.task.SchedualTask;

public class SchedualTaskConstant {
	/**
	 * job 任务对象
	 */
	public static final String SCHEDUAL_JOB_DATA = "schedualJobData";

	/**
	 * job 任务工作类执行方法
	 */
	public static final String SCHEDUAL_JOB_RUN_METHOD = "run";
	public static final Class SCHEDUAL_JOB_RUN_METHOD_PARAMETER_CLASS = SchedualTask.class;

	/**
	 * job 任务执行日志记录bean
	 */
	public static final String SCHEDUAL_JOB_LOG_BEAN = "schedualTaskJobLogService";
	/**
	 * job 任务执行状态
	 */
	public static final String STATUS_RUNNING = "1";
	public static final String STATUS_NOT_RUNNING = "0";

	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";

	/**
	 * 定时任务 job group
	 */

	/**
	 * 安置
	 */
	public static final String TASK_SCHEDUAL_JOB_GROUP_ANZHI = "ANZHI";

	/**
	 * 抚恤
	 */
	public static final String TASK_SCHEDUAL_JOB_GROUP_FUXU = "FUXU";

	/**
	 * 优待
	 */
	public static final String TASK_SCHEDUAL_JOB_GROUP_YOUDAI = "YOUDAI";
}

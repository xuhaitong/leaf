package com.leaf.plugin.schedual.dynamic.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.leaf.plugin.schedual.dynamic.task.SchedualTask;
import com.leaf.plugin.util.ExecuteResult;

public interface BaseSchedualTaskService {

	/**
	 * 根据任务id获取计划任务主体信息
	 * 
	 * @param jobId
	 * @return
	 */
	SchedualTask getTaskById(String jobId);

	/**
	 * <b>新增或修改定时任务数据</b> <br>
	 * 定时任务对象必须为数据信息完整对象 ——id,jobName,jobGroup,cronExpression 等 <br>
	 * 1、根据传入的定时任务对象，根据id获取历史定时任务信息 存在历史定时任务（编辑状态） 查看历史定时任务是否在运行状态， 是 -- 剔除历史定时任务，
	 * 更新定时任务数据为新的定时任务数据 查看新的定时任务数据的状态， 若为执行状态，启用新定时任务 不存在历史定时任务（新增状态） 持久化定时任务数据
	 * 查看新的定时任务数据的状态， 若为执行状态，启用新定时任务
	 * 
	 * @param job
	 * @return ExecuteResult<Object> { success:处理状态boolean-- true 成功、false 失败,
	 *         error:错误描述 }
	 * @throws SchedulerException
	 */
	ExecuteResult<Object> saveOrUpdateTaskData(SchedualTask job) throws SchedulerException;

	/**
	 * 获取正在执行的计划任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	List<SchedualTask> getRunningJob() throws SchedulerException;

	/**
	 * 获取所有的计划任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	List<SchedualTask> getAllJob() throws SchedulerException;

	/**
	 * 更新计划任务的cron表达式
	 * 
	 * @param jobId
	 * @param cron
	 * @throws SchedulerException
	 */

	void updateCron(String jobId, String cron) throws SchedulerException;

	/**
	 * 更改任务状态 [启用/停用]
	 * 
	 * @param jobId
	 *            任务id
	 * @param cmd
	 *            1 启用 ；0 停用
	 * @throws SchedulerException
	 */
	void changeStatus(String jobId, String cmd) throws SchedulerException;

}

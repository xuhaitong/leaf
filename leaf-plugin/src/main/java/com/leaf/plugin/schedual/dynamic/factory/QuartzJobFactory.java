package com.leaf.plugin.schedual.dynamic.factory;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leaf.plugin.schedual.dynamic.Constant.SchedualTaskConstant;
import com.leaf.plugin.schedual.dynamic.task.SchedualTask;
import com.leaf.plugin.schedual.dynamic.util.TaskUtils;

/**
 * @author Leaf
 * @date 2017-9-1 09:39:37
 * @Description: 计划任务执行处 无状态
 */
public class QuartzJobFactory implements Job {
	public final Logger log = LoggerFactory.getLogger(this.getClass());

	public void execute(JobExecutionContext context) throws JobExecutionException {
		SchedualTask scheduleJob = (SchedualTask) context.getMergedJobDataMap()
				.get(SchedualTaskConstant.SCHEDUAL_JOB_DATA);
		TaskUtils.invokMethod(scheduleJob);
	}
}
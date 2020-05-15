package com.leaf.plugin.schedual.dynamic.util;

import java.lang.reflect.Method;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import com.leaf.plugin.schedual.dynamic.Constant.SchedualTaskConstant;
import com.leaf.plugin.schedual.dynamic.service.BaseSchedualTaskJobLogService;
import com.leaf.plugin.schedual.dynamic.task.SchedualTask;
import com.leaf.plugin.schedual.dynamic.task.SchedualTaskLog;
import com.leaf.plugin.util.UUIDToll;

/**
 * <b>计划任务工具类</b> <br>
 * 提供计划任务的调度接口、计划任务日志存储接口
 * 
 * @author Leaf
 * @date 2017-9-1 09:53:48
 *
 */
public class TaskUtils {
	public final static Logger log = LoggerFactory.getLogger(TaskUtils.class);

	private static BaseSchedualTaskJobLogService taskScheduleJobLogService;

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(SchedualTask scheduleJob) {
		String jobMessage = "定时任务[" + scheduleJob.getJobGroup() + "." + scheduleJob.getJobName() + "]:";
		Object object = null;
		Class clazz = null;
		if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
			try {
				object = SpringUtils.getBean(scheduleJob.getSpringId());
			} catch (BeansException e) {
				e.printStackTrace();
				saveLog(scheduleJob, 0, jobMessage + "任务调度bean[" + scheduleJob.getSpringId() + "]获取失败", e);
			}
		} else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
			try {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				saveLog(scheduleJob, 0, jobMessage + "任务调度bean[" + scheduleJob.getBeanClass() + "]获取失败", e);
			}
		} else {
			saveLog(scheduleJob, 0, jobMessage + "任务调度bean[" + scheduleJob.getSpringId() + "]获取失败");
		}
		if (object == null) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		String methodName = null;
		try {
			methodName = scheduleJob.getMethodName();
			if (StringUtils.isBlank(methodName)) {
				methodName = SchedualTaskConstant.SCHEDUAL_JOB_RUN_METHOD;
			}
			method = clazz.getMethod(methodName, SchedualTask.class);//// SchedualJobConstant.SCHEDUAL_JOB_RUN_METHOD_PARAMETER_CLASS);
		} catch (Exception e) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
			saveLog(scheduleJob, 0,
					jobMessage + "任务调度bean.method[" + scheduleJob.getSpringId() + "." + methodName + "]获取失败", e);
		}
		if (method != null) {
			jobBeanInit(object, scheduleJob);
			try {
				method.invoke(object, scheduleJob);
			} catch (Exception e) {
				e.printStackTrace();
				saveLog(scheduleJob, 0,
						jobMessage + "任务调度bean.method[" + scheduleJob.getSpringId() + "." + methodName + "]执行失败", e);
			}
		}
		saveLog(scheduleJob, 1, jobMessage + "任务调度成功执行");
		System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]----------启动成功");
	}

	private static void jobBeanInit(Object object, SchedualTask scheduleJob) {
		if (object != null) {

			String jobMessage = "定时任务[" + scheduleJob.getJobGroup() + "." + scheduleJob.getJobName() + "]:";

			Class clazz = object.getClass();
			Method initMethod = null;
			try {
				initMethod = clazz.getMethod("init");
			} catch (Exception e) {
				log.info(jobMessage + "初始化方法获取失败——" + e.getMessage());
			}

			if (initMethod != null) {
				try {
					initMethod.invoke(object);
				} catch (Exception e) {
					log.info(jobMessage + "初始化方法执行失败——" + e.getMessage());
				}
			}
		}
	}

	/**
	 * <b>获取定时任务执行日志记录bean</b> <br>
	 * 
	 * @author Leaf
	 * @return 任务执行日志记录bean class:ITaskScheduleJobLogService
	 */
	private static synchronized BaseSchedualTaskJobLogService getLogService() {
		if (taskScheduleJobLogService == null) {
			// synchronized(taskScheduleJobLogService) {
			if (taskScheduleJobLogService == null) {
				taskScheduleJobLogService = SpringUtils.getBean(SchedualTaskConstant.SCHEDUAL_JOB_LOG_BEAN);
			}
			// }
		}
		return taskScheduleJobLogService;
	}

	/**
	 * <b>记录定任务执行日志</b> <br>
	 * 传入定时任务描述信息，执行状态，执行详情描述，异常描述，持久化到数据库中
	 * 
	 * @param taskScheduleJob
	 *            定时任务描述实体
	 * @param status
	 *            定时任务执行状态 —— 1 成功 0 失败
	 * @param msg
	 *            定时任务执行信息
	 * @param e
	 *            定时任务执行异常信息
	 */
	public static void saveLog(SchedualTask taskScheduleJob, int status, String msg, Exception e) {
		BaseSchedualTaskJobLogService taskLogService = getLogService();
		if (taskLogService != null) {
			SchedualTaskLog jobLog = new SchedualTaskLog();
			jobLog.setId(UUIDToll.getUUID());
			jobLog.setTaskId(taskScheduleJob.getId());
			jobLog.setTaskStatus("" + status);
			jobLog.setTaskDetail(msg);
			if (e != null) {
				jobLog.setFailReson(e.getMessage());
			}
			jobLog.setCreatedate(Calendar.getInstance().getTime());
			taskLogService.save(jobLog);
		} else {
			log.info("动态定时任务日志记录bean获取失败");
		}
	}

	public static void saveLog(SchedualTask job, int i, String jobMessage) {
		saveLog(job, i, jobMessage, null);
	}
}

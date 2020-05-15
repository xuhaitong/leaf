package com.leaf.plugin.schedual.dynamic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.leaf.plugin.schedual.dynamic.Constant.SchedualTaskConstant;
import com.leaf.plugin.schedual.dynamic.factory.QuartzJobFactory;
import com.leaf.plugin.schedual.dynamic.factory.QuartzJobFactoryDisallowConcurrentExecution;
import com.leaf.plugin.schedual.dynamic.service.BaseSchedualTaskMapper;
import com.leaf.plugin.schedual.dynamic.service.BaseSchedualTaskService;
import com.leaf.plugin.schedual.dynamic.task.SchedualTask;
import com.leaf.plugin.schedual.dynamic.util.TaskUtils;
import com.leaf.plugin.util.ExecuteResult;

public class BaseSchedualTaskServiceImpl implements BaseSchedualTaskService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	//
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseSchedualTaskMapper schedualTaskMapper;

	/**
	 * 从数据库中取 区别于getAllJob
	 * 
	 * @return
	 */
	private List<SchedualTask> getAllTask() {
		return schedualTaskMapper.queryAllTask();
	}

	/**
	 * 添加到数据库中 区别于addJob
	 */
	private void addTask(SchedualTask job) {
		// job.setCreateTime(new Date());
		schedualTaskMapper.saveTask(job);// .insertSelective(job);
	}

	/**
	 * 暂停一个job
	 * 
	 * @param SchedualTask
	 * @throws SchedulerException
	 */
	private void pauseJob(SchedualTask SchedualTask) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(SchedualTask.getJobName(), SchedualTask.getJobGroup());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个job
	 * 
	 * @param SchedualTask
	 * @throws SchedulerException
	 */
	private void resumeJob(SchedualTask SchedualTask) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(SchedualTask.getJobName(), SchedualTask.getJobGroup());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除一个job
	 * 
	 * @param SchedualTask
	 * @throws SchedulerException
	 */
	private void deleteJob(SchedualTask taskScheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(taskScheduleJob.getJobName(), taskScheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);

		String jobMessage = "定时任务[" + taskScheduleJob.getJobGroup() + "." + taskScheduleJob.getJobName()
				+ "]:删除成功[踢出定时任务]";
		TaskUtils.saveLog(taskScheduleJob, 3, jobMessage);
	}

	/**
	 * 立即执行job
	 * 
	 * @param SchedualTask
	 * @throws SchedulerException
	 */
	private void runAJobNow(SchedualTask SchedualTask) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(SchedualTask.getJobName(), SchedualTask.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新job时间表达式
	 * 
	 * @param SchedualTask
	 * @throws SchedulerException
	 */
	private void updateJobCron(SchedualTask SchedualTask) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(SchedualTask.getJobName(), SchedualTask.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(SchedualTask.getCronExpression());

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}

	/**
	 * 添加任务
	 * 
	 * @param SchedualTask
	 * @throws SchedulerException
	 */
	private void addJob(SchedualTask job) throws SchedulerException {
		if (job == null || !SchedualTaskConstant.STATUS_RUNNING.equals(job.getJobStatus())) {
			return;
		}

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		log.debug(scheduler
				+ ".......................................................................................add");
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			Class clazz = SchedualTaskConstant.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class
					: QuartzJobFactoryDisallowConcurrentExecution.class;

			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

			jobDetail.getJobDataMap().put(SchedualTaskConstant.SCHEDUAL_JOB_DATA, job);

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
					.withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}

		String jobMessage = "定时任务[" + job.getJobGroup() + "." + job.getJobName() + "]:初始化成功";
		TaskUtils.saveLog(job, 2, jobMessage);
	}

	@PostConstruct
	private void init() throws Exception {

		this.log.info("动态定时任务，初始化任务数据");
		System.out.println("===" + this + "====");
		System.out.println("动态定时任务，初始化任务数据");
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		// 这里获取任务信息数据
		List<SchedualTask> jobList = getAllTask();
		System.out.println("动态定时任务，初始化任务数据，任务量：" + jobList.size());
		this.log.info("动态定时任务，初始化任务数据，任务量：" + jobList.size());
		for (SchedualTask job : jobList) {
			addJob(job);
		}
	}

	/**
	 * 从数据库中查询job
	 */
	@Override
	public SchedualTask getTaskById(String jobId) {
		return schedualTaskMapper.getById(jobId);// .selectByPrimaryKey(jobId);
	}

	/**
	 * 更改任务状态 [启用/停用]
	 * 
	 * @param jobId
	 *            任务id
	 * @param cmd
	 *            1 启用 ；0 停用
	 * @throws SchedulerException
	 */
	@Override
	public void changeStatus(String jobId, String cmd) throws SchedulerException {
		SchedualTask job = getTaskById(jobId);
		if (job == null) {
			return;
		}
		if (SchedualTaskConstant.STATUS_NOT_RUNNING.equals(cmd)) {
			deleteJob(job);
			job.setJobStatus(SchedualTaskConstant.STATUS_NOT_RUNNING);
		} else if (SchedualTaskConstant.STATUS_RUNNING.equals(cmd)) {
			job.setJobStatus(SchedualTaskConstant.STATUS_RUNNING);
			addJob(job);
		}
		schedualTaskMapper.update(job);// .updateByPrimaryKeySelective(job);

	}

	/**
	 * 更改任务 cron表达式
	 * 
	 * @param jobId
	 *            任务id
	 * @param cron
	 *            任务执行表达式
	 * @throws SchedulerException
	 */
	@Override
	public void updateCron(String jobId, String cron) throws SchedulerException {
		SchedualTask job = getTaskById(jobId);
		if (job == null) {
			return;
		}
		job.setCronExpression(cron);
		if (SchedualTaskConstant.STATUS_RUNNING.equals(job.getJobStatus())) {
			updateJobCron(job);
		}
		schedualTaskMapper.update(job);// .updateByPrimaryKeySelective(job);

	}

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	@Override
	public List<SchedualTask> getAllJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<SchedualTask> jobList = new ArrayList<SchedualTask>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				SchedualTask job = new SchedualTask();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDescription("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	@Override
	public List<SchedualTask> getRunningJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<SchedualTask> jobList = new ArrayList<SchedualTask>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			SchedualTask job = new SchedualTask();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setDescription("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}

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
	@Override
	public ExecuteResult<Object> saveOrUpdateTaskData(SchedualTask job) throws SchedulerException {
		ExecuteResult<Object> er = new ExecuteResult<Object>();

		String uuid = job.getId();
		if (StringUtils.isBlank(uuid)) {
			er.setError("id不能为空");
		}
		String jobStatus = job.getJobStatus();
		if (StringUtils.isBlank(jobStatus)) {
			er.setError("jobStatus任务执行状态不能为空");
		}
		SchedualTask jobBack = this.getTaskById(job.getId());
		if (jobBack == null) {// 持久化定时任务数据，
			this.addTask(job);
		} else {
			String oldJobStatus = jobBack.getJobStatus();
			if (SchedualTaskConstant.STATUS_RUNNING.equals(oldJobStatus)) {
				this.deleteJob(jobBack);
			}
			this.schedualTaskMapper.update(job);
		}
		// 根据定时任务状态，启用定时任务
		if (SchedualTaskConstant.STATUS_RUNNING.equals(jobStatus)) {
			this.addJob(job);
		}
		er.setSuccess(true);
		// String log
		log.info("{}计划任务 [{}.{}]，计划任务状态[{}]", jobBack == null ? "新增" : "更新", job.getJobGroup(), job.getJobName(),
				job.getJobStatus());
		return er;
	}

}

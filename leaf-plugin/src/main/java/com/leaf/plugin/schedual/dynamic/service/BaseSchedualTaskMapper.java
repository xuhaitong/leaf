package com.leaf.plugin.schedual.dynamic.service;

import java.util.List;

import com.leaf.plugin.schedual.dynamic.task.SchedualTask;

public interface BaseSchedualTaskMapper<T extends SchedualTask> {

	/**
	 * 
	 * @return
	 */
	// List<T> getAll();

	int save(T job);

	int update(T job);

	List<T> queryAllTask();

	void saveTask(T job);

	T getById(String jobId);

}

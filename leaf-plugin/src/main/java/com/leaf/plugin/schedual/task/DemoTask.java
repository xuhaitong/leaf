package com.leaf.plugin.schedual.task;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
//@configurable
//@EnableScheduling
public class DemoTask {

	// @Scheduled(cron="0 20 23 * * ? ") //每日 23:20 执行一次
	@Scheduled(cron = "0/5 * * * * ? ") // 每日 23:20 执行一次
	public void execute() {
		System.out.println("下发开始----------");
	}
}

package com.leaf.plugin;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.leaf.plugin.aspect.ServiceLoggerHandler;
import com.leaf.plugin.filter.RestLoggerHandler;
import com.leaf.plugin.schedual.dynamic.service.BaseSchedualTaskJobLogService;
import com.leaf.plugin.schedual.dynamic.service.impl.BaseSchedualTaskJobLogServiceImpl;

@Configuration
@ConditionalOnWebApplication
@ComponentScan
public class PluginAutoConfiguration {

	@Bean
	public RestLoggerHandler restLoggerHandler() throws Exception {
		return new RestLoggerHandler();
	}

	@Bean
	public ServiceLoggerHandler serviceLoggerHandler() throws Exception {
		return new ServiceLoggerHandler();
	}

	@Bean
	public BaseSchedualTaskJobLogService schedualTaskJobLogService() {
		return new BaseSchedualTaskJobLogServiceImpl();
	}

	// @Bean
	// public GlobalExceptionHandler globalExceptionHandler() {
	// return new GlobalExceptionHandler();
	// }
}

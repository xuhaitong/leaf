package com.leaf.log;

import com.leaf.log.aspect.LeafLogAspect;
import com.leaf.log.handler.BaseLogHandler;
import com.leaf.log.userManager.BaseUserManager;
import com.leaf.log.userManager.BaseUserManagerService;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan
@Configuration
@ConditionalOnWebApplication
public class LeafLogAutoConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(LeafLogAutoConfiguration.class);

//	@Bean
//	public LeafLogAspect leafLogAspect() {
//		return new LeafLogAspect();
//	}

	@ConditionalOnClass(Aspect.class)
	@ConditionalOnMissingBean
	@Bean
	public BaseLogHandler logHandler() {
		return BaseLogHandler.getInstance();
	}

	@Bean
	@ConditionalOnMissingBean
	public BaseUserManagerService userManager() {
		return BaseUserManager.getInstance();
	}

	/*
	 * @Bean
	 * 
	 * @ConditionalOnMissingBean public UserManagerService userManagerService() {
	 * return null;
	 * 
	 * }
	 */

}

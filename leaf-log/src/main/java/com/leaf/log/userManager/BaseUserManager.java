package com.leaf.log.userManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leaf.log.aspect.LeafLogAspect;

public abstract class BaseUserManager implements BaseUserManagerService {

	private final static Logger logger = LoggerFactory.getLogger(LeafLogAspect.class);

	/**
	 * 获取userManager实例
	 * 
	 * @return
	 */
	public static BaseUserManager getInstance() {

		return new BaseUserManager() {

			public String getUserName() {
				logger.info("getUserName--------------------");
				return null;
			}

			public String getUserId() {
				logger.info("getUserId--------------------");
				return null;
			}

			public String getUserFullName() {
				logger.info("getUserFullName--------------------");
				return null;
			}

			private void init() {
				// TODO Auto-generated method stub
				logger.info("UserManager 未定义===========  ");
			}

		};

	}
}

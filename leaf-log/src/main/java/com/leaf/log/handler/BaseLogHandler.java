package com.leaf.log.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leaf.log.annotation.LeafLog;
import com.leaf.log.model.LeafLogDetail;

public abstract class BaseLogHandler implements BaseLogHandlerService {

	private static final Logger logger = LoggerFactory.getLogger(BaseLogHandler.class);

	public static BaseLogHandler getInstance() {
		return new BaseLogHandler() {

			@Override
			public void handlerLog(ProceedingJoinPoint pjp, LeafLog leafLog, Object result, Throwable error) {
				// TODO Auto-generated method stub

			}

			@Override
			public void handlerLog(ProceedingJoinPoint pjp, LeafLog springLeafLog, Object result, Throwable error,
					LeafLogDetail log) {
				// TODO Auto-generated method stub

			}

			private void init() {
				// TODO Auto-generated method stub
				logger.info("LogHandler 未定义----");

			}

			@Override
			public void handlerLog(LeafLogDetail log) {
				// TODO Auto-generated method stub

			}
		};
	}

}

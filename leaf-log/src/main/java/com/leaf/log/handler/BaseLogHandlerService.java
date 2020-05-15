package com.leaf.log.handler;

import org.aspectj.lang.ProceedingJoinPoint;

import com.leaf.log.annotation.LeafLog;
import com.leaf.log.model.LeafLogDetail;

public interface BaseLogHandlerService {

	/**
	 * 
	 * @param pjp
	 * @param leafLog
	 * @param result
	 * @param error
	 */
	void handlerLog(ProceedingJoinPoint pjp, LeafLog leafLog, Object result, Throwable error);

	/**
	 * 
	 * @param pjp
	 * @param springLeafLog
	 * @param result
	 * @param error
	 * @param log
	 */
	void handlerLog(ProceedingJoinPoint pjp, LeafLog springLeafLog, Object result, Throwable error, LeafLogDetail log);

	/**
	 * 
	 * @param log
	 */
	public abstract void handlerLog(LeafLogDetail log);
	// void
}

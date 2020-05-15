package com.leaf.plugin.aspect;

import java.util.Date;
import java.util.concurrent.ExecutorService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;

import com.alibaba.fastjson.JSON;
import com.leaf.plugin.security.BaseUserDetails;
import com.leaf.plugin.util.SecurityUtil;
import com.leaf.plugin.util.ThreadPoolUtil;

@Aspect
@Order(-3)
public class ServiceLoggerHandler {
	
	private final static Logger logger = LoggerFactory.getLogger(ServiceLoggerHandler.class);
	
	ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	
	/*@Autowired
	private MongoTemplate mongoTemplate;*/
	
	@Pointcut(value = "@within(org.springframework.stereotype.Service)")
	public void serviceAspect() {}
	
	@Around("serviceAspect()")
	public Object serviceAspectAdvice(ProceedingJoinPoint pjp) throws Throwable {
		Object result = null; 
		Signature signature = pjp.getSignature();
		ServiceLogger serviceLog=new ServiceLogger();
		serviceLog.setClassMethod(signature.getDeclaringTypeName() + "." + signature.getName());
		serviceLog.setParameter(JSON.toJSONString(pjp.getArgs()));
		serviceLog.setDateTime(new Date());

		Authentication authentication=SecurityUtil.getAuthentication();
		
		if(authentication==null){
			serviceLog.setUserId("");
			serviceLog.setUserName("");
		} else {
			if(authentication.isAuthenticated()){
				Object obj=authentication.getPrincipal();
				if(obj instanceof String){
					serviceLog.setUserId(String.valueOf(obj));
					serviceLog.setUserName(String.valueOf(obj));
				} else {
					BaseUserDetails authUser=(BaseUserDetails)authentication.getPrincipal();
					serviceLog.setUserId(authUser.getId());
					serviceLog.setUserName(authUser.getUsername());
				}
			} else {
				serviceLog.setUserId("");
				serviceLog.setUserName("");
			}
		}
		
		
		
		try {
			result = pjp.proceed();
			serviceLog.setReturnResult("returnResult:"+JSON.toJSONString(result));
			return result;
		} catch (Throwable e) {
			serviceLog.setReturnResult("returnResult:"+e.getMessage());
			throw e;
		} finally{
			ExecutorService exec = ThreadPoolUtil.getThreadPool();
			exec.execute(()-> {
				/**
				 * insert
				 */
				//mongoTemplate.insert(serviceLog);
				logger.info("CLASS_METHOD : ========>{}", serviceLog.getClassMethod());
				logger.info("PARAMTERS : ===========>{}", serviceLog.getParameter());
				logger.info("USERNAME : ============>{}", serviceLog.getUserName());
				logger.info("RETURNRESULT : ========>{}", serviceLog.getReturnResult());
			});
		}
		
		
		
	}
	
	

}

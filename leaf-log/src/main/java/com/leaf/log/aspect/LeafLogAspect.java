package com.leaf.log.aspect;

import java.text.MessageFormat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.leaf.log.annotation.LeafLog;
import com.leaf.log.constant.LeafLogConstant;
import com.leaf.log.handler.BaseLogHandlerService;
import com.leaf.log.model.LeafLogDetail;
import com.leaf.log.userManager.BaseUserManagerService;

/**
 * 语义化业务日志切面类
 * 
 * @description 标记业务处理方法，将业务日志入库
 * @author Leaf Xu
 * @date 2018-8-27 10:46:25
 */
//@ConditionalOnClass(Aspect.class)
@Aspect
// @Order(-4)
//@Component
public class LeafLogAspect extends BaseLeafLogAspect {

	@Autowired
	protected BaseUserManagerService userManager;

	@Autowired
	protected BaseLogHandlerService logHandler;

	private final static Logger logger = LoggerFactory.getLogger(LeafLogAspect.class);

	/**
	 * Controller层切点
	 */

	private static final String INSTANCE_METHOD_CALL = "execution (* com.leaf.boot.controller..*.*(..))";
	@Pointcut(INSTANCE_METHOD_CALL)
	public void controllerAspect() {
	}

	/**
	 * 环绕切面，记录操作日志入库
	 */
	@Around(value = "controllerAspect() && @annotation(leafLog)", argNames = "pjp,leafLog")
	public Object around(ProceedingJoinPoint pjp, LeafLog leafLog) throws Throwable {
		return super.around(pjp, leafLog);
	}

	@Override
	protected String formatDescription(LeafLogDetail log, String opeDesc) {
		String userId = userManager.getUserId();
		String userName = userManager.getUserName();
		String userFullName = userManager.getUserFullName();
		String titleDesc = MessageFormat.format(LeafLogConstant.OPERATE_TITLE_FORMAT, userId,
				StringUtils.isEmpty(userFullName) ? userName : userFullName, opeDesc);
		log.setLogTitle(titleDesc);
		return titleDesc;
	}

	@Override
	protected void handlerLog(LeafLogDetail log) {
		// TODO Auto-generated method stub
		System.out.println("???????? This is LeafLog handler begin ??????????");
		logHandler.handlerLog(log);
		System.out.println(log.toString());
		System.out.println("???????? This is LeafLog handler end ??????????");
	}

}

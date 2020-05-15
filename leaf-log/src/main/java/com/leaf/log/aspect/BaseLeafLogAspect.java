package com.leaf.log.aspect;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.leaf.log.annotation.LeafLog;
import com.leaf.log.annotation.LeafLogOperateType;
import com.leaf.log.constant.LeafLogConstant;
import com.leaf.log.model.LeafLogDetail;

public abstract class BaseLeafLogAspect {

	private final static Logger logger = LoggerFactory.getLogger(LeafLogAspect.class);

	/**
	 * 业务日志内容处理
	 * 
	 * @param pjp
	 * @param leafLog
	 * @param result
	 * @param error
	 */
	private void handlerLeafLogFilter(ProceedingJoinPoint pjp, LeafLog leafLog, Object result, Throwable error) {
		try {
			LeafLogDetail log = new LeafLogDetail();
			// 判断业务处理状态
			if (result != null) {
				log.setLogOperateStatus(LeafLogConstant.OPERATE_STATUS_SUCCESS);
				log.setLogOperateResult(LeafLogConstant.OPERATE_STATUS_MSG_SUCCESS);
			} else if (error != null) {
				log.setLogOperateStatus(LeafLogConstant.OPERATE_STATUS_ERROR);
				log.setLogOperateException(error.toString());
				log.setLogOperateResult(LeafLogConstant.OPERATE_STATUS_MSG_ERROR + error.getMessage());
			}

			String targetName = pjp.getTarget().getClass().getName();
			String methodName = pjp.getSignature().getName();

			log.setLogOperateParams(getMethodParamJson(pjp));
			log.setLogOperateMethod(targetName + "." + methodName);

			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();

			log.setLogRequestUri(request.getRequestURI());
			log.setLogRemoteAddr(request.getRemoteAddr());
			// ---------- 注解逻辑处理 开始-----------

			// 通过spring工具类获取leafLog注解参数（二次加工）
			MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
			Method method = methodSignature.getMethod();
			LeafLog springLeafLog = AnnotationUtils.findAnnotation(method, LeafLog.class);
			//springLeafLog = method.getAnnotation(LeafLog.class);

			// leafLog 操作类型 获取
			LeafLogOperateType[] opeTypeArr = springLeafLog.operationType();
			String opeTypeIndexOf = springLeafLog.index();
			LeafLogOperateType opeType = null;

			if (LeafLogConstant.OPERATE_TYPE_INDEX_ZERO.equals(opeTypeIndexOf)) {
				opeType = opeTypeArr[0];
			} else {
				Integer index = getExpressionValue(pjp, opeTypeIndexOf, Integer.class);
				if (index == null) {
					index = 0;
				}
				if (index > -1 && index < opeTypeArr.length) {
					opeType = opeTypeArr[index];
				} else {
					opeType = opeTypeArr[0];
				}
			}
			log.setLogType(opeType.getValue());
			// 设置日志内容标题
			String opeName = springLeafLog.operationName();
			String opeDesc = null;
			if (!StringUtils.isEmpty(opeName)) {
				opeDesc = getExpressionValue(pjp, opeName, String.class);
				if (opeDesc == null) {
					opeDesc = opeName;
				}
			}
			// 解析生成日志描述
			String titleDesc = formatDescription(log, opeDesc);
			// log.setLogTitle(titleDesc);
			// ----------- 注解逻辑处理结束 ----------

			ExecutorService exec = ThreadPoolUtil.getThreadPool();
			exec.execute(() -> {
				handlerLog(log);
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("业务日志处理异常---------{}", e.getMessage());
		}
	}

	/**
	 * 处理业务日志对象 --
	 * 
	 * @param log
	 */
	protected abstract void handlerLog(LeafLogDetail log);

	/**
	 * 解析生成日志描述
	 * 
	 * @param log
	 * @param opeDesc
	 * @return
	 */
	protected abstract String formatDescription(LeafLogDetail log, String opeDesc);

	/**
	 * 处理spel表达式
	 * 
	 * @param joinPoint
	 *            切点
	 * @param expression
	 *            表达式
	 * @param T
	 *            表达式结果值 类型
	 * @return 表达式结果
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getExpressionValue(JoinPoint joinPoint, String expression, Class<?> T) {
		try {
			ExpressionParser parser = new SpelExpressionParser();
			Expression expr = parser.parseExpression(expression);

			String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
			Object[] args = joinPoint.getArgs();
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			if (args != null && args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					Object arg = args[i];
					if (arg instanceof Serializable) {
						paramMap.put("p" + i, arg);
						paramMap.put(parameterNames[i], arg);
					}
				}
			}
			StandardEvaluationContext context = new StandardEvaluationContext();
			context.setVariables(paramMap);
			return (T) expr.getValue(context, T);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取spel表达式结果
	 * 
	 * @param paramMap
	 *            表达式数据源
	 * @param expression
	 *            表达式
	 * @param T
	 *            表达式结果类型
	 * @return 表达式结果
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private static <T> T getExpressionValue(Map<String, Object> paramMap, String expression, Class<?> T) {
		try {
			ExpressionParser parser = new SpelExpressionParser();
			StandardEvaluationContext context = new StandardEvaluationContext();
			context.setVariables(paramMap);
			return (T) parser.parseExpression(expression).getValue(context, T);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将目标方法参数 装配 为json对象字符串返回
	 * 
	 * @param joinPoint
	 * @return
	 */
	private static String getMethodParamJson(JoinPoint joinPoint) {
		// 获取用户请求方法的参数并序列化为JSON格式字符串
		JSONObject json = new JSONObject();
		String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
		Object[] args = joinPoint.getArgs();

		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				Object arg = args[i];
				if (arg instanceof Serializable) {
					json.put(parameterNames[i], arg);
				}
			}
		}
		return json.toJSONString();
	}

	protected Object around(ProceedingJoinPoint pjp, LeafLog leafLog) throws Throwable {
		// TODO Auto-generated method stub
		Object result = null;
		Throwable error = null;
		try {
			result = pjp.proceed();
			return result;
		} catch (Throwable e) {
			error = e;
			e.printStackTrace();
			throw e;
		} finally {
			handlerLeafLogFilter(pjp, leafLog, result, error);
		}

	}

	/*
	 * @Deprecated private void handlerLeafLog(ProceedingJoinPoint pjp, LeafLog
	 * leafLog, Object result, Throwable error) { try { LeafLogDetail log = new
	 * LeafLogDetail(); //判断业务处理状态 if(result != null && result instanceof
	 * ExecuteResult) { Boolean operateStatus =
	 * ((ExecuteResult<?>)result).isSuccess();
	 * log.setLogOperateStatus(operateStatus?LeafLogConstant.OPERATE_STATUS_SUCCESS:
	 * LeafLogConstant.OPERATE_STATUS_FAIL); if(operateStatus) {
	 * log.setLogOperateResult(LeafLogConstant.OPERATE_STATUS_MSG_SUCCESS); }else {
	 * log.setLogOperateResult(LeafLogConstant.OPERATE_STATUS_MSG_FAIL+((
	 * ExecuteResult<?>)result).getMessage()); } }else if(error != null){
	 * log.setLogOperateStatus(LeafLogConstant.OPERATE_STATUS_ERROR); //
	 * log.setLogOperateException(Exceptions.getStackTraceAsString(error));
	 * log.setLogOperateException(error.toString());
	 * log.setLogOperateResult(LeafLogConstant.OPERATE_STATUS_MSG_ERROR+error.
	 * getMessage()); }
	 * 
	 * //获取登录用户信息 HttpServletRequest request = ((ServletRequestAttributes)
	 * RequestContextHolder.getRequestAttributes()).getRequest(); String userId =
	 * null; String userName = null; String userFullName = null; Authentication
	 * authentication = SecurityUtil.getAuthentication(); if (!(authentication
	 * instanceof AnonymousAuthenticationToken)) { userId =
	 * systemManager.getUserId(); userName = systemManager.getUsername();
	 * userFullName = systemManager.getAuthUser().getName(); } String targetName =
	 * pjp.getTarget().getClass().getName(); String methodName =
	 * pjp.getSignature().getName();
	 * 
	 * log.setLogOperateParams(getMethodParamJson(pjp));
	 * log.setLogOperateMethod(targetName+"."+methodName);
	 * 
	 * log.setLogRequestUri(request.getRequestURI());
	 * log.setLogRemoteAddr(request.getRemoteAddr()); log.setCreateBy(userId);
	 * log.setCreateDate(Calendar.getInstance().getTime());
	 * log.setId(Sequence.getUUID32()); log.setCreateDate(new Date());
	 * 
	 * //---------- 注解逻辑处理 开始-----------
	 * 
	 * //通过spring工具类获取leafLog注解参数（二次加工） MethodSignature methodSignature =
	 * (MethodSignature) pjp.getSignature(); Method method =
	 * methodSignature.getMethod(); LeafLog springLeafLog =
	 * AnnotationUtils.findAnnotation(method, LeafLog.class);
	 * 
	 * //leafLog 操作类型 获取 LeafLogOperateType[] opeTypeArr =
	 * springLeafLog.operationType(); String opeTypeIndexOf = springLeafLog.index();
	 * LeafLogOperateType opeType = null;
	 * 
	 * if(LeafLogConstant.OPERATE_TYPE_INDEX_ZERO.equals(opeTypeIndexOf)) { opeType
	 * = opeTypeArr[0]; }else { Integer index =
	 * getExpressionValue(pjp,opeTypeIndexOf,Integer.class); if(index == null) {
	 * index = 0 ; } if(index >-1 && index < opeTypeArr.length) { opeType =
	 * opeTypeArr[index]; }else { opeType = opeTypeArr[0]; } }
	 * log.setLogType(opeType.getValue()); //设置日志内容标题 String opeName =
	 * springLeafLog.operationName(); String opeDesc = null;
	 * if(StringUtils.isNotBlank(opeName)) { opeDesc = getExpressionValue(pjp,
	 * opeName, String.class); if(opeDesc == null) { opeDesc = opeName; } } String
	 * titleDesc =MessageFormat.format( LeafLogConstant.OPERATE_TITLE_FORMAT,
	 * userId, StringUtils.isNotBlank(userFullName) ? userFullName : userName,
	 * opeDesc); log.setLogTitle(titleDesc); //----------- 注解逻辑处理结束 ----------
	 * 
	 * // 保存数据库 ExecutorService exec = ThreadPoolUtil.getThreadPool();
	 * exec.execute(()-> { systemLogService.insert(log); }); } catch (Exception e) {
	 * // TODO Auto-generated catch block e.printStackTrace();
	 * logger.error("业务日志处理异常---------{}", e.getMessage()); } }
	 */

}

class ThreadPoolUtil {

	private static ExecutorService executor;

	public static ExecutorService getThreadPool() {
		if (executor == null) {
			synchronized (ThreadPoolUtil.class) {
				if (executor == null) {
					executor = Executors.newFixedThreadPool(1000);
				}
			}
		}
		return executor;
	}

}

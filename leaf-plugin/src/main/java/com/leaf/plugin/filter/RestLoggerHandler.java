package com.leaf.plugin.filter;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.alibaba.fastjson.JSON;
import com.leaf.plugin.security.BaseUserDetails;
import com.leaf.plugin.util.ThreadPoolUtil;

@WebFilter(urlPatterns = "/*",filterName = "apiFilter")
public class RestLoggerHandler implements Filter {
	
	private final static Logger logger = LoggerFactory.getLogger(RestLoggerHandler.class);  
	
//	@Autowired
//	private MongoTemplate mongoTemplate;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)req;
		RestLogger apiLog=new RestLogger();
		apiLog.setIp(request.getRemoteAddr());
		apiLog.setHttpMethod(request.getMethod());
		apiLog.setClassMethod("");
		apiLog.setArgs("");
		apiLog.setParameter(JSON.toJSONString(request.getParameterMap()));
		apiLog.setDateTime(new Date());
		apiLog.setUrl(String.valueOf(request.getRequestURI()));
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication==null){
			apiLog.setUserId("");
			apiLog.setUserName("");
		} else {
			if(authentication.isAuthenticated()){
				Object obj=authentication.getPrincipal();
				if(obj instanceof String){
					apiLog.setUserId(String.valueOf(obj));
					apiLog.setUserName(String.valueOf(obj));
				} else {
					BaseUserDetails authUser=(BaseUserDetails)authentication.getPrincipal();
					apiLog.setUserId(authUser.getId());
					apiLog.setUserName(authUser.getUsername());
				}
			} else {
				apiLog.setUserId("");
				apiLog.setUserName("");
			}
		}
		
		
		ExecutorService exec = ThreadPoolUtil.getThreadPool();
		exec.execute(()-> {
			/**
			 * insert
			 */
			//mongoTemplate.insert(apiLog);
			logger.info("URL : =================>{}", apiLog.getUrl());
			logger.info("HTTP_METHOD : =========>{}", apiLog.getHttpMethod());
			logger.info("IP : ==================>{}", apiLog.getIp());
			logger.info("CLASS_METHOD : ========>{}", apiLog.getClassMethod());
			logger.info("ARGS : ================>{}", apiLog.getArgs());
			logger.info("PARAMTERS : ===========>{}", apiLog.getParameter());
			logger.info("USERNAME : ============>{}", apiLog.getUserName());
			logger.info("RETURNRESULT : ========>{}", apiLog.getReturnResult());
		});
//		exec.shutdown();
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

}

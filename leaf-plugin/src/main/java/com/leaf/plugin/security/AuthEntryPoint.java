package com.leaf.plugin.security;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.leaf.plugin.exception.ExceptionEnum;

/**
 * AuthEntryPoint
 * @author siufung
 *
 */
public class AuthEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
    	/*ExecuteResult<String> result=new ExecuteResult<String>();
    	result.setCode(ExceptionEnum.NOT_AUTHORIZATION.getCode());
    	result.setMessage(ExceptionEnum.NOT_AUTHORIZATION.getMsg());
    	result.setSuccess(false);
    	result.setData(null);
    	result.setUrl(request.getRequestURI());
        response.getWriter().write(JSON.toJSONString(result));*/
    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ExceptionEnum.NOT_AUTHORIZATION.getMsg());
    }
}